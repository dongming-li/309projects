var socket = io.connect('http://localhost:2999');

function create() {
  $(".form-group").hide('slow');
  $(".form-group2").show('slow');
  $("#sbutton").attr("onclick","codes()");
}

function codes() {
  $(".form-group2").hide('slow');
  $(".form-group3").show('slow');
  $("#sbutton").attr("onclick","send()");
  $("#sbutton").text("submit");
}

function send() {
  socket.emit('addproj', {
    projectName: $("#name").val(),
    desc : $("#desc").val(),
    code : $("#code").val()
  });
  console.log("sent: "+ $("#name").val());
  window.location.href = "/project";
}
