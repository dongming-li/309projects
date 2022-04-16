var Example = Example || {};
var necromancer = new Resurrect();
pid = location.href.substring(location.href.indexOf("?p=") + 3).split("#")[0];

$("#sbutton").click(function() {
  console.log("sending update");
  var socket = io.connect('http://localhost:2999');
  socket.emit('updateBodies', {
    pid: pid,
    bodies: necromancer.stringify(engine.world)
  });
  console.log("update sent");
});

$(document).ready(function() {
  var socket = io.connect('http://localhost:2999');
  //setInterval(UpdateUsers, 1000);
  preUser();

  socket.on('done', function(data) {
    location.href = "/";
    console.log("done");
  });



  function preUser() {
    engine = Matter.Engine.create();
    render = Matter.Render.create({
      element: document.body,
      engine: engine,
      options: {
        width: 800,
        height: 600,
        showAngleIndicator: true
      }
    });
    i = 0;
    Matter.Events.on(Matter.Engine.run(engine), "tick", function() {
      if (i > 60) {
        var socket = io.connect('http://localhost:2999');
        socket.emit('save', {
          image: $('.matter-header-outer').siblings('canvas')[0].toDataURL(),
          pid: pid,
          rtime: engine.timing.timestamp
        });
        console.log("sent image");
        i = 0;
      } else {
        i++;
      }
    });


    Example.wreckingBall = new Function($('#code').val());
    Example.wreckingBall();
    demo.examples = [

      {
        name: 'Wrecking Ball',
        id: 'wreckingBall',
        init: Example.wreckingBall
      }
    ]
    document.body.appendChild(demo.dom.root);
    engine.world = Matter.Engine.create().world;
    MatterTools.Demo.start(demo);
    //setTimeout(function() {
    if (datas1.users.bodies != null) {
      engine.world = necromancer.resurrect(datas1.users.bodies);
    }
    //}, 1000);
    //Matter.Composite.remove(engine.world, engine.world.bodies);

  }
});
