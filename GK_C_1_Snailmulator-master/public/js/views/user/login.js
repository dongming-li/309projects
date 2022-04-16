var socket = io.connect('http://localhost:2999');

function send() {
  socket.emit('login', {
    email : $("#email").val(),
    pass : $("#pass").val()
  });
  console.log("sent: "+ $("#email").val());
  window.location.href = "/";
}
