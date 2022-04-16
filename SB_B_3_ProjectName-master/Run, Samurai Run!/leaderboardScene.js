function leaderboardScene(){

  var exit = false;
  var jString;

  /*
  var jString = '[{"user_name": "rjfaure","user_score": 999999},' +
                '{"user_name": "zippy", "user_score": 183},' +
                '{"user_name": "bryan","user_score": 153},' +
                '{"user_name": "DeniBoi","user_score": 120},' +
                '{"user_name": "yousef","user_score": 100},' +
                '{"user_name": "BryantMyers","user_score": 92},' +
                '{"user_name": "theTruNinja","user_score": 85},' +
                '{"user_name": "JohnnyBoi","user_score": 73},' +
                '{"user_name": "gerbear","user_score": 69},' +
                '{"user_name": "Butternut", "user_score": 69}]';
                */
  var json;

  this.setup = function(){
    start = this.sceneManager.findScene(startScreen).oScene;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://proj-309-sb-b-3.cs.iastate.edu/leader.php", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    var someStuff='userName='+userName.value()+'&userPass='+sha256(password.value());
    xhr.send(someStuff);

    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        jString = xhr.responseText.trim();
        json = JSON.parse(jString);
        drawMenu();
      }
    }
  }

  this.reset = function(){
    this.setup();
  }

  this.mousePressed = function(){
    console.log(json[0].user_name);

    if(exit){
      exit = false;
      removeElements();
      clear();
      start.reset();
      this.sceneManager.showScene(startScreen);
    }
  }

  
  function drawMenu(){
    background(50);
    textAlign(CENTER, CENTER);
    textSize(20);
    fill(225);
    text("TOP 10 Players", width / 2, height * 0.02);

    //Buttons Setup
    exitButton = createButton("X");
    buttonCSSSetup(exitButton, 0.6);
    exitButton.mousePressed(goToMainMenu);

    for(i = 0; i < 10; i++){
      if(i == getJSONSize()){
        break;
      }

      textSize(windowWidth * windowHeight * 0.00008);
      fill(255);
      textAlign(CENTER);
      text(i+1, width * 0.06, height * ((i+1) * 0.08))
      textAlign(LEFT);
      text(json[i].userName, width * 0.14, height * ((i + 1) * 0.08));
      textAlign(RIGHT);
      fill(255, 0, 0);
      text(json[i].score, width * 0.90, height * ((i + 1) * 0.08))
    }

    for(i = 0; i < getJSONSize(); i++){
      if(json[i].userName == userName.value()){
        fill(255);
        textAlign(CENTER);
        text(i+1, width * 0.06, height * 0.93);
        textAlign(LEFT);
        text(json[i].userName, width * 0.14, height * (0.93));
        fill(255,0,0);
        textAlign(RIGHT);
        text(json[i].score, width * 0.90, height * (0.93));
      }
    }

  }
  

  /**
    CSS STYLING FOR BUTTONS :D
  */
  function buttonCSSSetup(button, YpositionMultiple){
    button.style("width", "35px");
    button.style("height", "35px");
    button.style("text-align", "center");
    button.style("font-size", "150%");
    button.style("font-weight", "bold")
    button.style("color", "#FFF");
    button.style("background", "#323232");
    button.style("border-radius", "4px");
    button.style("display", "inline-block");
    button.style("border", "none");
    button.style("outline", "none");

    //NOT CSS
    //Center Button
    button.position(windowWidth  >> 6, windowHeight/100);
  }

  function goToMainMenu(){
      exit = true;
  }

  function getJSONSize(){
    return Object.keys(json).length;
  }
}
