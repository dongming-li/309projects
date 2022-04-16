var socket = io.connect('http://localhost:2999');

function send() {
  socket.emit('signup', {
    email : $("#email").val(),
    pass : $("#pass").val(),
    cpass : $("#cpass").val()
  });
  console.log("sent: "+ $("#email").val());
  window.location.href = "/login";
}
