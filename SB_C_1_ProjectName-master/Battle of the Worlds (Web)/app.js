require('./Entity');
require('./client/Inventory');

var express = require('express');
var app = express();
var serv = require('http').Server(app);

var databaseUser = require('mysql');
var con = databaseUser.createConnection({
			host: "mysql.cs.iastate.edu",
			user: "dbu309sbc1",
			password: "gwgCfDXc",
			database: "db309sbc1"
		});


app.get('/',function(req, res) {
	res.sendFile(__dirname + '/client/index.html');
});
app.use('/client',express.static(__dirname + '/client'));

serv.listen(process.env.PORT || 8080);
console.log("Server started.");

SOCKET_LIST = {};

var DEBUG = true;

var io = require('socket.io')(serv,{});
io.sockets.on('connection', function(socket){
	
	socket.id = Math.random();
	SOCKET_LIST[socket.id] = socket;
	
	//registers the client into the database
	socket.on('register', function (data) {
		register(data.email_address, data.username, data.password, socket);
		socket.disconnect();
		console.log("Client disconnected after registering.");
	});

	//Checks to see if the client entered the correct login information in order to log in.
	socket.on('login', function (data) {
		login(data.username, data.password, socket);
	});
	/*
	socket.on('signIn',function(data){ //{username,password}
		isValidPassword(data,function(res){
			if(res){
				Player.onConnect(socket,data.username);
				socket.emit('signInResponse',{success:true});
			} else {
				socket.emit('signInResponse',{success:false});			
			}
		});
	});
	socket.on('signUp',function(data){
		isUsernameTaken(data,function(res){
			if(res){
				socket.emit('signUpResponse',{success:false});		
			} else {
				addUser(data,function(){
					socket.emit('signUpResponse',{success:true});					
				});
			}
		});		
	});
	*/
	
	socket.on('disconnect',function(){
		delete SOCKET_LIST[socket.id];
		Player.onDisconnect(socket);
	});
	
	socket.on('evalServer',function(data){
		if(!DEBUG)
			return;
		var res = eval(data);
		socket.emit('evalAnswer',res);		
	});
});


function login(username, userPassword, client) {
	con.connect(function(err) {
        var queryString = "SELECT ACCOUNTS_ID FROM db309sbc1.ACCOUNTS WHERE USERNAME = '" + username + "' AND PASSWORD = '" + userPassword + "'";
        con.query(queryString, function (err, result) {
          if (result.length <= 0) {
			  client.emit('signInResponse', {success : false});
			  client.disconnect();
		  }
          else {
			client.username = username;
			Player.onConnect(client,username);
			client.emit('signInResponse', {success : true});
          }
        });
      });
}

function register(email_address, username, userPassword, client) {

		var nodemailer = require('nodemailer');

		var transporter = nodemailer.createTransport({
			service: 'gmail',
			auth: {
				user: 'battleoftheworldsgame@gmail.com',
				pass: 'Battleworlds206!'
			}
		});

		var mailOptions = {
			from: 'battleoftheworldsgame@gmail.com',
			to: email_address,
			subject: 'Account Confirmation',
			text: 'This is confirmation that you have made an account for Battle of the Worlds.'
		};

		transporter.sendMail(mailOptions, function (error, info){
			if (error) {
				console.log(error);
			} else {
				console.log('Email sent: ' + info.response);
			}
		});

		con.connect(function(err) { 
			// create user
			var queryString = "SELECT ACCOUNTS_ID FROM db309sbc1.ACCOUNTS WHERE USERNAME = '" + username + "'";
			con.query(queryString, function (err, result) {
				if (result.length <= 0) {
					var userLoginInfo = "INSERT INTO db309sbc1.ACCOUNTS (USERNAME, EMAIL, PASSWORD) VALUES ('" + username + "', '" + email_address + "', '" + userPassword + "')";
					con.query(userLoginInfo, function (err, result) {
						console.log("1 record inserted");
						client.emit('signUpResponse', {success : true});
					});
				}
				else client.emit('signUpResponse', {success : false});
			});
		});
}

setInterval(function(){
	var packs = Entity.getFrameUpdateData();
	for(var i in SOCKET_LIST){
		var socket = SOCKET_LIST[i];
		socket.emit('init',packs.initPack);
		socket.emit('update',packs.updatePack);
		socket.emit('remove',packs.removePack);
	}
	
},1000/25);