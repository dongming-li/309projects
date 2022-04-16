

var Engine = Matter.Engine,
  Render = Matter.Render,
  World = Matter.World,
  Bodies = Matter.Bodies;

/*
var engine = Engine.create();

var render = Render.create({
  element: document.body,
  engine: engine,
  options: {
    width: 800,
    height: 400,
    wireframes: false
  }
});
*/

var boxA = Bodies.rectangle(400, 200, 80, 80);
var ballA = Bodies.circle(380, 100, 40, 10);
var ballB = Bodies.circle(460, 10, 40, 10);
var ground = Bodies.rectangle(400, 380, 810, 60, {
  isStatic: true
});
var i, arrayOfBodies, y;
arrayOfBodies = [boxA, ballA];
arrayOfBodies.push(Bodies.rectangle(0, 200, 90, 90));

for (i = 0; i < 5; i++) {
  for (j = 0; j < 2; j++) {
    arrayOfBodies.push(Bodies.circle(30 + 8 * i, 30 - (12 * j), 2, 200));
  }
}

var number = 3;
var arrayOfArrays = [];
arrayOfArrays = disperseBodies(number, arrayOfBodies);

for(i = 0; i < 3; i++){
  console.log(arrayOfArrays[i].toString());
}


var enginesArray = [];
var rendererArray = [];
enginesArray = createEnginesArray(number, arrayOfArrays);
rendererArray = createRenderArray(number, enginesArray);

console.log(arrayOfArrays.length);

// Need to actually start all of these of something
// How to have multiple canvas's?
for(i = 0; i < number; i++){
  Engine.run(enginesArray[i]);
  Render.run(rendererArray[i]);
}


//arrayOfBodies.push(ground);
//World.add(engine.world, arrayOfArrays);

// send the correct objects to other computers using sockets.io
// Might need to convert the objects to a different format for this
function sendToOtherComputers(arrayOfArrays){
  return 0;
}
// sends all bodies to the appropiate area
// totally works, just find correct windowSize
function disperseBodies(numAreas, bodiesArray){
  // find size of window
  var sizeOfWindow = 800;

  // Find the size of each area
  var areaSize = sizeOfWindow / numAreas;

  // Create the array of arrays
  var arrayOfArrays = [];
  var i = 0;

  for (i = 0; i < numAreas; i++) {
    arrayOfArrays.push([]);
  }

  // Split up the objects
  var length = bodiesArray.length;
  for (i = 0; i < length; i++) {
    // Get x coordinate of object
    // This line of code is wrong
    var x = bodiesArray[i].position.x;

    // Divide x coordinate by areaSize
    var arrayNumber = Math.floor(x / areaSize);

    // Use the arrayNumber value to place the object into the appropriate array
    arrayOfArrays[arrayNumber].push(bodiesArray[i]);
  }

  // return the arrayOfArrays
  return arrayOfArrays;
}

// Creates a 2D engine array with the objects
// arrayOfArrays holds the 2D array of Bodies
function createEnginesArray(numEngines, arrayOfArrays){
    var enginesArray = [];
    for(var i = 0; i < numEngines; i++){
        var engine = Engine.create();
        // Not positive this is correct
        World.add(engine.world, arrayOfArrays[i]);
        enginesArray.push(engine);
    }
    return enginesArray;
}

function createRenderArray(numRenderers, enginesArray){
  var rendererArray = [];
  var i = 0;
  for(i = 0; i < numRenderers; i++){
    var renderer = Render.create({
      element: document.body,
      engine: enginesArray[i],
      options: {
        width: 800 / numRenderers,
        height: 400,
        wireframes: false
      }
    });
    rendererArray.push(renderer);
  }
  return rendererArray;
}
