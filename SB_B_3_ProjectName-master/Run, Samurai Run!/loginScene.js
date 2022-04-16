function loginScene(){
  var submitted = false;
  var newSamurai = false;
  var goToGame = false;
  this.setup = function(){
    document.body.style.backgroundImage = this.sceneManager.backgroundImage;
    document.body.style.backgroundSize = "100% 100%";
    var name = createDiv("Username");
    setupText(name, 0.35);

    userName = createInput();
    userName.size(windowWidth * 0.8, windowHeight * 0.05);
    userName.position(windowWidth * 0.1, windowHeight * 0.42);
    userName.style("text-align", "center");
    userName.style('font-size', '200%');
    userName.style("font-family", "Arial");

    var pass = createDiv("Password");
    setupText(pass, 0.48)
    password = createInput("", "password");
    password.size(windowWidth * 0.8, windowHeight * 0.05);
    password.position(windowWidth * 0.1, windowHeight * 0.55);
    password.style("text-align", "center");
    password.style('font-size', '200%');
    password.style("font-family", "Arial");

    submit = createButton('Submit');
    submit.position(CENTER, windowHeight * 0.65);
    submit.mousePressed(goToMainMenu);
    buttonCSSSetup(submit);

    var reg = createDiv("Not a Samurai?");
    setupText(reg, 0.85);
    reg.style("font-size", "200%");
    reg.style("text-shadow", "0px 2px #989898");
    register = createButton("Register Now!");
    register.position(CENTER, windowHeight * 0.9);
    register.mousePressed(goToRegister);
    buttonCSSSetup(register);
    }

  this.draw = function(){
    if(goToGame){
      removeElements();
      clear();
      this.sceneManager.showScene(startScreen);
    }
  }

  //Feature: You can enter to login. =)
  this.keyPressed = function(){
    if(keyCode == 13){
      login();
    }
  }

  //Pressed buttons
  this.mousePressed = function(){
    if(submitted){
      login();
    }
    else if(newSamurai){
      removeElements();
      clear();
      newSamurai = false;
      this.sceneManager.showScene(registerScene);
    }
  }

  function login(){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://proj-309-sb-b-3.cs.iastate.edu/dologin.php", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    var someStuff='userName='+userName.value()+'&userPass='+sha256(password.value());
    xhr.send(someStuff);

    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        if(xhr.responseText.trim() == "true"){
          goToGame = true;
        }
        else{
          submitted = false;
        }
      }
    }
  }

  function buttonCSSSetup(button, YpositionMultiple){
    //CSS
    button.style("text-align", "center");
    button.style("font-size", "150%");
    button.style("color", "#000");
    button.style("background", "white");
    button.style("display", "inline-block");
    button.style("font-family", "Arial");
    button.style("border", "none");
    button.style("border-radius", "9px");
    button.style("box-shadow", "0 6px #989898");

    //NOT CSS
  }

  function setupText(givenDiv, heightVariable){
    givenDiv.style("width", windowWidth + "px");
    givenDiv.style("margin", "0 auto");
    givenDiv.style("text-align", "center");
    givenDiv.style("position", 0, windowHeight * heightVariable);
    givenDiv.style("color", "white");
    givenDiv.style("font-family", "Arial");
    givenDiv.style("text-shadow", "0px 3px #989898")
    givenDiv.style("font-size", "300%");
  }

  function goToMainMenu(){
    submitted = true;
  }

  function goToRegister(){
    newSamurai = true;
  }
}
