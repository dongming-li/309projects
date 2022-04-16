
/**
 * @file chat-server.js
 * @fileOverview Serverside logic for creating socket.io server, exchanging messages, users and server messages
 * @author Ryan Evans <rievans@iastate.edu>
 * @version 1.1
 * @module ChatServer
 */

var port = 81;
var express = require('express');
var app = express();
var server = app.listen(port);
var path = require('path');
var io = require('socket.io')(server);
var localDevPath = 'C:\\Users\\Rev\\COMSCI309\\LFW\\comsci309';
var request = require('request');
var moment = require('moment');

var users = [];
var user = "username";
var msgCount = 0;
var serverMsgCount = 0;
/**
* @function log
* @param {String} msg user or server msg 
* @description logs all messages in console with timestamp
*/
function log(msg) {
    console.log(moment().format() + ': ' + msg);
}
/**
* @function formatServerMessage
* @param {String} message server msg 
* @description formats server messages with timestamp
*/
function formatServerMessage(message) {
    return '[' + moment().format('h:mm') + '] ' + message;
}

server.listen(port, function () {
    log('started chat server on port ' + port);
});

/**
* @function function
* @param {String} socket new user connects to server via socket
* @description creates a new user connection to server
*/
io.on('connection', function (socket) {
    let thisUser;

/**
* @function broadcastNewUser
* @description sends out a server message that a new client has joined, updates everyones' userlist
*/
    function broadcastNewUser() {
        socket.broadcast.emit('user-list-add', thisUser);
        socket.broadcast.emit('server-msg', {
            id: serverMsgCount++,
            message: formatServerMessage(thisUser + ' has joined the chat.')
        });
    }

 /**
* @function getAllUsers
* @param {String} allUsers a list of all current users
* @description gives a list of all current users to new user
*/
    function getAllUsers(allUsers) {
        socket.emit('user-list', allUsers);
    }

    /**
* @function broadcastRemainingUsers
* @param {String} userThatLeft user thata disconnnects
* @description emits a new userlist as well as a disconnect server message
*/
    function broadcastRemainingUsers(userThatLeft) {
        socket.broadcast.emit('user-list', users);
        socket.broadcast.emit('server-msg', {
            id: serverMsgCount++,
            message: formatServerMessage(userThatLeft + ' has left the chat.')
        })
    }

 /**
* @function 'socket.on'
* @param {String} data user upon login
* @description adds user to chat server, broadcasts join to everyone, new user gets current userlist
*/
    socket.on('user-login', function (data) {
        log('user-login ' + data);
        thisUser = data;
        users.push(thisUser);
        log('All users: ' + users);

        getAllUsers(users);
        broadcastNewUser();
    });

/**
* @function 'socket.on'
* @param {String} data a new message
* @description when a new message arrives, format and send to all connected users
*/
    socket.on('msg', function (data) {
        data.id = msgCount++;
        log('msg from ' + thisUser + ": " + data.message);
        data.user = thisUser;
        data.time = moment().format('h:mm');
        io.sockets.emit('newmsg', data);
    });

    socket.on('disconnect', function (data) {
        log('disconnecting ' + thisUser);

        users = users.filter(function (user) {
            return user != thisUser;
        });

        broadcastRemainingUsers(thisUser);
        log('remaining users: ' + users);
    });
});

// fetch http stuff
app.use(express.static(path.join(__dirname)));

app.get('/', function (req, res) {
    var indexPage;
    if (localDevPath == __dirname) {
        indexPage = path.join(__dirname + '/pages/index.html');
    } else {
        // dont load index if on server
        indexPage = path.join(__dirname + '/chat-server.html');
    }

    res.sendFile(indexPage);
});

// call off to server when local since i dont have php
if (localDevPath == __dirname) {
    app.get('/User.php', function (req, res) {
        var urlToCall = 'http://proj-309-vc-b-2.cs.iastate.edu' + req.originalUrl;

        request(urlToCall, function (error, response, body) {
            res.send(body);
        });
    });

    app.get('/session.php', function (req, res) {
        var urlToCall = 'http://proj-309-vc-b-2.cs.iastate.edu' + req.originalUrl;

        request(urlToCall, function (error, response, body) {
            res.send(body);
        });
    });
}