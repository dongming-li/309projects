var listOfNodes = [];
var RenderAtX = 2;

function socket(serverState) {
  var server = serverState.express.app.listen(2999);
  var io = require('socket.io')(server);
  var fs = require('fs');

  //Whenever someone connects this gets executed
  io.on('connection', function(socket) {
    //console.log('A user connected');
    socket.emit('news', {
      hello: 'world'
    });

    // Don't actually need the data
    socket.on('firstCom', function(data) {
      console.log(socket.id);
      // Just a fail-safe, double checking all nodes are added
      if (listOfNodes.indexOf(socket.id) < 0) {
        listOfNodes.push(socket.id);
      }
      /*
      if (listOfNodes.length >= RenderAtX - 1) {
        console.log('rendering starts');

        var bodiesArray = matterJSCode();
        var arrayOfArrays = disperseBodies(RenderAtX, bodiesArray);

        console.log('sending objects');
        sendingOutObjects(socket, listOfNodes, arrayOfArrays);
      }
      */
    });

    socket.on('disconnect', function() {
      console.log('user disconnected');
      var nodeLocation = listOfNodes.indexOf(socket.id);
      if (nodeLocation > -1) {
        listOfNodes.splice(nodeLocation, 1);
      }
    });

    socket.on('sendOnwards', function(info) {
      data = info.data;
      nodeID = info.nodeID;
      socket.to(nodeID).emit('dataFromPeer', data);
    });

    //recieve object positions
    socket.on('position', function(data) {
      console.log('Recieved data: ' + JSON.stringify(data));
    });

    //send projects
    socket.on('projects', function(data) {
      //console.log('Recieved date: ' + data.date + " Mod Date: " + serverState.projectModifiedDate);
      if (data.date >= serverState.projectModifiedDate) {
        socket.emit('projects', {
          projects: JSON.stringify(serverState.projects),
          datemod: serverState.projectModifiedDate
        });
      }
    });

    //change a projects bodie data
    socket.on('updateBodies', function(data) {
      socket.emit('done', {
        code: "done"
      });
      if(serverState.projects[data.pid] == null){
        serverState.projects[data.pid] = {
            pid: data.pid,
            img: "",
            bodies: null
          }
      }
      serverState.projects[data.pid].bodies = data.bodies;
      console.log("Project State updated for pid: "+ data.pid);
    });


    //save image from base64 encoding
    socket.on('save', function(data) {
      var img = data.image.replace(/^data:image\/\w+;base64,/, "");
      var buf = new Buffer(img, 'base64');
      var dir = "images/" + data.pid;
      try {
        if (!fs.existsSync("images")) {
          fs.mkdirSync("images");
        }
        if (!fs.existsSync(dir)) {
          fs.mkdirSync(dir);
        }
        console.log(data.rtime);
        fs.writeFile(dir + "/t-" + Math.round(data.rtime) + ".png", buf);
        serverState.projects.find(function(arr) {
          return arr.time == data.pid;
        }).img = data.image;
      } catch (e) {
        console.log("failed to write file");
      }
    });

    //send latest image
    socket.on('view', function(data) {
      image = "failed";
      try {
        image = serverState.projects.find(function(arr) {
          return arr.time == data.pid;
        }).img

      } catch (e) {
      }

      socket.emit('imagev', {
        image: image
      });
    });

    //send allowed users
    socket.on('authtype', function(data) {
      socket.emit('authtype', {
        users: serverState.projects.find(function(arr) {
          return arr.time == data.pid;
        })
      });
    });

    //login data
    socket.on('login', function(data) {
      User.findOne({
        where: {
          email: data.email
        }
      }).then(user => {
        console.log("User " + user.email + " authenticated with password " + user.password);
      }).catch(err => {
        console.error("Error: ", err);
      });
      console.log(data.email + " " + data.pass);
    });

    //signup data
    socket.on('signup', function(data) {
      if (data.email != '' && data.pass != '' && data.pass == data.cpass) {
        User.sync({
          force: true
        }).then(() => {
          return User.create({
            email: data.email,
            password: data.pass,
            is_admin: false
          });
        });
      } else {
        console.error("Passwords don't match. Try again.");
      }
      console.log(data.email + " " + data.pass + " " + data.cpass);
    });
  });

  return serverState;
};

module.exports = socket;
