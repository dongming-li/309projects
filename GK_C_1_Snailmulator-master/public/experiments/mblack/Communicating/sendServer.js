var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

var Matter = require('matter-js');

var Engine = Matter.Engine,
  Render = Matter.Render,
  World = Matter.World,
  Bodies = Matter.Bodies;

app.get('/', function(req, res) {
  res.sendFile(__dirname + '/index.html');
  //res.sendFile(__dirname + '/js/index.js');
});

http.listen(3000, function() {
  console.log('listening on *:3000');
});

// Prepare to make stringify MatterJS stuff
var necromancer = new Resurrect();



function matterJSCode() {
  var arrayOfBodies = [];
  arrayOfBodies.push(Bodies.rectangle(0, 200, 90, 90));
  return arrayOfBodies;
}

function sendingOutObjects(socket, arrayOfSockets, bodiesArray) {
  var length = arrayOfSockets.length;
  console.log(length + " - " + bodiesArray.length);

  // Verify correct input
  if (length != bodiesArray.length) {
    console.log("SocketsArray and bodiesArray have different lengths");
    return;
  } else {
    console.log("Beginning render");
  }

  // Send each node their list of bodies
  // Might want to tell them their node #?
  for (var i = 0; i < length; i++) {
    socket.to(arrayOfSockets[i].id).emit('startRendering', necromancer.stringify(bodiesArray[i]));
    //socket.to(arrayOfSockets[i].id).emit('arrayOfSockets', listOfNodes);
  }
}

// sends all bodies to the appropiate area
function disperseBodies(numAreas, bodiesArray) {
  // find size of window
  var sizeOfWindow = 800;

  // Find the size of each area
  var areaSize = sizeOfWindow / numAreas;

  // Create the array of arrays
  var arrayOfArrays = [];
  var i = 0;

  // Make arrayOfArrays a two-dimensional array
  for (i = 0; i < numAreas; i++) {
    arrayOfArrays.push([]);
  }

  // Split up the objects
  var length = bodiesArray.length;
  for (i = 0; i < length; i++) {
    // Get x coordinate of object
    var x = bodiesArray[i].position.x;

    // Divide x coordinate by areaSize
    var arrayNumber = Math.floor(x / areaSize);

    // Use the arrayNumber value to place the object into the appropriate array
    arrayOfArrays[arrayNumber].push(bodiesArray[i]);
  }

  // return the arrayOfArrays
  return arrayOfArrays;
}
