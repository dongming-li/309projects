$(document).ready(function() {
  var socket = io.connect('http://localhost:2999');
  setInterval(UpdateProjects, 1000);

  function UpdateProjects() {
    socket.emit('view', {
      pid: location.href.substring(location.href.indexOf("?") + 1)
    });
  }

  socket.on('imagev', function(data) {
    if(data.image != "failed"){
      $('#img').attr("src", data.image);
    }
    console.log(data.image);
  });
});
