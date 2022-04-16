$(document).ready(function() {
  var socket = io.connect('http://localhost:2999');
  //setInterval(UpdateUsers, 1000);
  UpdateUsers();

  function UpdateUsers() {
    socket.emit('authtype', {
      pid: location.href.substring(location.href.indexOf("?") + 1)
    });
  }

  oldDate = 0;
  socket.on('authtype', function(data) {
    datas = data;
    //if (parseInt(data.datemod) > oldDate) {

      var markup = "";
      arr = data.users.users;
      console.log(arr);
      for (i = 0; i < arr.length; i++) {
        markup += "<tr><td>" + "test" + "</td><td>" + "test2" + "</td><td>";
      }
      $("table tbody").empty();
      $("table tbody").append(markup);
    //}
    oldDate = data.datemod;
  });
});
