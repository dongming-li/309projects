$(document).ready(function() {
  var wrap = MatterWrapper({
    width: 800,
    height: 600
  });

  wrap.add({
    type: 'body',
    geo: 'rectangle',
    args: [310, 100, 600, 20, {
      isStatic: true,
      angle: Math.PI * -.95,
    }]
  }, {
    type: 'body',
    geo: 'rectangle',
    args: [490, 250, 600, 20, {
      isStatic: true,
      angle: Math.PI * .95,
    }]
  }, {
    type: 'body',
    geo: 'rectangle',
    args: [310, 400, 600, 20, {
      isStatic: true,
      angle: Math.PI * -.95,
    }]
  }, {
    type: 'body',
    geo: 'circle',
    args: [20, 10, 20, {
      speed: 10
    }]
  });
});
