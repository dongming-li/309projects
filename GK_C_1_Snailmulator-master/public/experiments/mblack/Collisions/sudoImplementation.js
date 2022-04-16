/*
  It doesn't look like the MatterJS bodies are stored sorted
    => Will continue looking through source code to verify this is the case
*/

// The size of a boundary, needs to be optimized
var boundarySize = 10;

// Can most likely be refactored to not care about Left or Right
boundaries = createBounds(listOfNodes, world, nodeNumber);

// Current method is less than ideal and will result in some inaccuracies
function updateEverything(boundaries, world, listOfNodes, nodeNumber, render){
  Matter.Render.stop(render);
  moveObjectsBetweenAreas(boundaries, world, listOfNodes, nodeNumber);
  Matter.Render.run(render);
}

function moveObjectsBetweenAreas(boundaries, world, listOfNodes, nodeNumber){
  // Find all bodies in the border regions
  // Always take O(n) time, where n = number of bodies
  inR = Matter.Query.region(World.bodies, boundaries.right);
  inL = Matter.Query.region(World.bodies, boundaries.left);

  // Get the bodies that weren't in the region before
  Left = getNew(oldL, inL);
  Right = getNew(oldR, inR);

  // Send the objects out
  // Still send twice the data
  //  => still sends to B(L) if object goes from B(L) to B(R)
  sendLeft(Left);
  sendRight(Right);

  oldL = inL;
  oldR = inR;

  // Sleep for X frames
  // TODO
}

// Returns A - (A n B)
// Takes O(m + n) time
// Assumes that the bodies are sorts by id number
function getNew(oldBodies, newBodies){
  var difference = [];
  var i = 0;
  var j = 0;

  var oldBodiesLength = oldBodies.length;
  var newBodiesLength = newBodies.length;

  while(i < oldBodiesLength && j < newBodies.length){
    if(oldBodies[i].id < newBodies[j].id){
      i++;
    }
    else if(oldBodies[i].id > newBodies[j].id){
      difference.push(newBodies[j]);
      j++;
    }
    else{
      i++;
      j++;
    }
  }

  return difference;
}


function createBounds(listOfNodes, world, nodeNumber){
  var bound = World.bounds;

  // Get the size of the area
  var sizeOfArea = (bounds.max.x - bounds.min.x) / (listOfNodes.length);

  // Get the bounds of the area
  var leftCoordinate = sizeOfArea * nodeNumber;
  var rightCoordinate = leftCoordinate + sizeOfArea;

  // Still need to calculate the optimal border size

  // Not sure if this body matter
  var unUsedRectangle = Bodies.rectangle(400, 200, 80, 80);

  // I think these are in the correct order, not positive if the body matters...

  // Making the vectors for making the vertices for making the boundaries
  var leftVectors = [Matter.Vector.create(leftCoordinate, bounds.min.y), Matter.Vector.create(leftCoordinate + boundarySize, bounds.min.y),
    Matter.Vector.create(leftCoordinate + boundarySize, bounds.max.y), Matter.Vector.create(leftCoordinate, bounds.max.y)];
  var rightVectors = [Matter.Vector.create(rightCoordinate - boundarySize, bounds.min.y), Matter.Vector.create(rightCoordinate, bounds.min.y),
    Matter.Vector.create(rightCoordinate, bounds.max.y), Matter.Vector.create(rightCoordinate - boundarySize, bounds.max.y)];

  /*
  var leftVertices = Matter.Vertices.create([{ x: leftCoordinate, y: bounds.min.y }, { x: leftCoordinate + boundarySize, y: bounds.min.y},
    { x: leftCoordinate + boundarySize, y: bounds.max.y}, { x: leftCoordinate, y: bounds.max.y}], unUsedRectangle);
  var rightVertices = Matter.Vertices.create([{ x: rightCoordinate - boundarySize, y: bounds.min.y}, { x: rightCoordinate, y: bounds.min.y}
    { x: rightCoordinate, y: bounds.max.y}, { x: rightCoordinate - boundarySize, y: bounds.max.y }], unUsedRectangle);
  */

  // Create the vertices for the boundaries
  var leftVertices = Matter.Vertices.create(leftVector, unUsedRectangle);
  var rightVertices = Matter.Vertices.create(rightVector, unUsedRectangle);

  // Create the physical bounds
  var leftBound = Matter.Bounds.create(leftVertices);
  var rightBound = Matter.Bounds.create(rightVertices);

  // put them into a nice little objects
  var boundsObject = {left: leftBound, right: rightBound};

  // return the two boundaries
  return boundsObject;
}
