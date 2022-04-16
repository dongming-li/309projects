var MatterWrapper = function(options) {
  var Engine = Matter.Engine,
    Render = Matter.Render,
    Runner = Matter.Runner,
    Composites = Matter.Composites,
    MouseConstraint = Matter.MouseConstraint,
    Mouse = Matter.Mouse,
    World = Matter.World,
    Bodies = Matter.Bodies;

  // create engine
  var engine = Engine.create(),
    world = engine.world;

  // create renderer
  var render = Render.create({
    element: $('#canvas')[0],
    engine: engine,
    options: {
      width: options.width,
      height: options.height,
      showAngleIndicator: true
    }
  });

  Render.run(render);

  // create runner
  var runner = Runner.create();
  Runner.run(runner, engine);

  var wrappedFns = {
    'body': Bodies,
    'composite': Composites
  };

  return {
    engine: engine,
    runner: runner,
    render: render,
    canvas: render.canvas,
    stop: function() {
      Matter.Render.stop(render);
      Matter.Runner.stop(runner);
    },
    add: function(...data) {
      World.add(world, data.map((datum) =>
        wrappedFns[datum.type][datum.geo].apply(null, datum.args)));
    }
  };
};
