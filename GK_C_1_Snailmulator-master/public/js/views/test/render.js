$(document).ready(function() {
  var socket = io.connect('http://localhost:2999');

  socket.emit('render', {
    pid: location.href.substring(location.href.indexOf("?") + 1)
  });

  socket.on('loadRender', function(data) {
    datas = data;
    eval(data.code.code);
    console.log(data.code.code);
  });
});
