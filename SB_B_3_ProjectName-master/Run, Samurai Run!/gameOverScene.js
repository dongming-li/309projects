function gameOverScene(){
  var oStart;
  var oGame;
  var game;
  var start;

  this.setup = function(){
    background(50);
    textAlign(CENTER, CENTER);
    fill(255);
    textSize(windowWidth * windowHeight * 0.0004);
    oStart = this.sceneManager.findScene(startScreen).oScene;
    oGame = this.sceneManager.findScene(gameScreen).oScene;
    sendScore(oGame.getScore());
    text(oGame.getScore(), windowWidth / 2, windowHeight * 0.4);

    fill(255, 0, 0);
    textSize(windowWidth * windowHeight * 0.00009);
    text(userName.value(), windowWidth / 2, windowHeight * 0.2);

    this.gameButton = createButton("Play Again");
    this.gameButton.mousePressed(goToGame);
    buttonCSSSetup(this.gameButton, 0.6);

    this.menuButton = createButton("Go back to Menu");
    this.menuButton.mousePressed(backToMainMenu);
    buttonCSSSetup(this.menuButton, 0.7)

    game = false;
    start = false;
  }

  this.mousePressed = function(){
    if(game){
      clear();
      removeElements();
      oGame.reset();
      game = false;
      this.sceneManager.showScene(gameScreen);
    }

    else if(start){
      clear();
      removeElements();
      oStart.reset();
      start = false;
      this.sceneManager.showScene(startScreen);
    }
  }

  this.reset = function(){
    this.setup();
  }

  function goToGame(){
    game = true;
  }

  function backToMainMenu(){
    start = true;
  }
}

function gameOverMulti(){
  var oStart;
  var oMulti;
  var start;
  var game;

  this.setup = function(){
    background(50);
    textAlign(CENTER, CENTER);
    fill(255);
    textSize(windowWidth * windowHeight * 0.00007);
    oStart = this.sceneManager.findScene(startScreen).oScene;
    oMulti = this.sceneManager.findScene(multiplayerScene).oScene;
    text(oMulti.getScore(), windowWidth / 2, windowHeight * 0.4);

    this.gameButton = createButton("Play Again");
    this.gameButton.mousePressed(goToGame);
    buttonCSSSetup(this.gameButton, 0.6);

    this.menuButton = createButton("Go back to Menu");
    this.menuButton.mousePressed(backToMainMenu);
    buttonCSSSetup(this.menuButton, 0.7)
  }

  this.mousePressed = function(){
    if(game){
      clear();
      removeElements();
      oMulti.reset();
      game = false;
      this.sceneManager.showScene(multiplayerScene);
    }

    else if(start){
      clear();
      removeElements();
      oStart.reset();
      start = false;
      this.sceneManager.showScene(startScreen);
    }
  }

  this.reset = function(){
    this.setup();
  }

  function goToGame(){
    game = true;
  }

  function backToMainMenu(){
    start = true;
  }
}

/**
  CSS STYLING FOR BUTTONS :D
*/
function buttonCSSSetup(button, YpositionMultiple){
  button.style("width", "400px");
  button.style("height", "50px");
  button.style("text-align", "center");
  button.style("font-size", "150%");
  button.style("color", "#000");
  button.style("background", "white");
  button.style("display", "inline-block");
  button.style("font-family", "Arial");
  button.style("border", "none");
  button.style("border-radius", "9px");
  button.style("box-shadow", "0 8px #989898");

  //NOT CSS
  //Center Button
  button.position(windowWidth - button.width >> 1, windowHeight * YpositionMultiple);
}

function sendScore(playerScore){
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "http://proj-309-sb-b-3.cs.iastate.edu/score.php", true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
  var someStuff='userName='+userName.value()+'&userScore='+playerScore;
  console.log(someStuff);
  xhr.send(someStuff);
  console.log(xhr.responseText);
}
