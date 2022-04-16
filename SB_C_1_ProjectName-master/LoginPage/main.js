document.addEventListener('DOMContentLoaded', function()
{
  document.getElementById("login_button").addEventListener('click',function ()
  {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if(username == "" && password == "")
    {
      alert("Please enter login information");
    }
    else if(username == "")
    {
      alert("Please enter username");
    }
    else if(password == "")
    {
      alert("Please enter password");
    }
    else
    {

    }
  });

  document.getElementById("username").addEventListener("keyup", function(event)
  {
    event.preventDefault();
    if (event.keyCode == 13)
    {
      document.getElementById("login_button").click();
    }
  });

  document.getElementById("password").addEventListener("keyup", function(event)
  {
    event.preventDefault();
    if (event.keyCode == 13)
    {
      document.getElementById("login_button").click();
    }
  });
});
