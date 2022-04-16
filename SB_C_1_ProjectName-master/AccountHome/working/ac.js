document.addEventListener('DOMContentLoaded', function()
{

  document.getElementById("star_submit").addEventListener('click',function ()
  {
    var rating = document.getElementById("select_star").value;
    var profileName= document.getElementById("edit").value;

    if(rating==0 ){
      alert("please rate us!")
    }
    else if (profileName==="Name") {
      alert("please enter your name!")
    }
    else{

    var socket = io.connect('http://10.25.70.50:8080');

		socket.on('connect_failed', function(data)
		{
			console.log('connect_failed');
		});
		socket.on('connecting', function(data)
		{
			console.log('connecting');
		});
		socket.on('disconnect', function(data)
		{
			console.log('disconnect');
		});
		socket.on('error', function(reason)
		{
			console.log('error');
		});
		socket.on('reconnect_failed', function(data)
		{
			console.log('reconnect_failed');
		});
		socket.on('reconnect', function(data)
		{
			console.log('reconnect');
		});
		socket.on('reconnecting', function(data)
		{
			console.log('reconnecting');
		});

		socket.emit('AccountHome', { profileName: profileName, rating:rating});

		socket.on('success', function(data) {
			window.setTimeout(function(){
              window.location.href = "/BattleOfTheWorlds/CharacterSelect"
            , 5000});
		});
    }
  });
});
