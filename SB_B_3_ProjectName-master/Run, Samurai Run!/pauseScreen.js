function pauseScreen(){

  var game;
  var start;
  this.resumeGame;
  this.startOver;

  this.setup = function(){
    this.resumeGame = createButton("Resume Game");
    buttonCSSSetup(this.resumeGame, 0.5);
    this.resumeGame.mousePressed(goToGame);

    this.startOver = createButton("Main Menu");
    buttonCSSSetup(this.startOver, 0.6);
    this.startOver.mousePressed(backToMainMenu);

    game = false;
    start = false;
  }

  this.reset = function(){
    this.setup();
  }

  this.removePauseElements = function(){
    this.resumeGame.remove();
    this.startOver.remove();
  }

  this.mousePressed = function(){
    if(game || start){
      this.removePauseElements();
      currentGame = this.sceneManager.findScene(gameScreen).oScene;
    }

    if(game){
      game = false;
      currentGame.unpause();
      this.sceneManager.showScene(gameScreen);
    }

    else if(start){
      start = false;
      clear();
      removeElements();
      oStart = this.sceneManager.findScene(startScreen).oScene;
      oStart.reset();
      this.sceneManager.showScene(startScreen);
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

  function goToGame(){
    game = true;
  }

  function backToMainMenu(){
    start = true;
  }
}
