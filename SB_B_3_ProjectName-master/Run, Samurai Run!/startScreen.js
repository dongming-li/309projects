function startScreen(){

  var singlePlayer = false;
  var multiplayer = false;
  var leaderboard = false;
  var leaderboardCreated = false;
  var gameScreenCreated = false;
  var multiplayerScreenCreated = false;
  var opacityVal = 1;

  /*
    Setup start screen with buttons and button functions.
  */
  this.setup = function(){
    document.body.style.backgroundImage = this.sceneManager.backgroundImage;
    document.body.style.backgroundSize = "100% 100%";
    setupStartUpScreen();
  }

  /*
    Reset this screen on calls from other
  */
  this.reset = function(){
    this.setup();
  }

  this.mousePressed = function(){
    console.log('mousePressed');
    if(singlePlayer){
      singlePlayer = false;
      removeElements();
      clear();

      //If a game screen has been created before then reset it.
      //Creates a brand new instance of the game.
      if(gameScreenCreated){
        oGame = this.sceneManager.findScene(gameScreen).oScene;
        oGame.reset();
      }
      gameScreenCreated = true;

      this.sceneManager.showScene(gameScreen);
    }

    else if(multiplayer){
      multiplayer = false;
      removeElements();
      clear();

      //If a game screen has been created before then reset it.
      //Creates a brand new instance of the game.
      if(multiplayerScreenCreated){
        oMulti = this.sceneManager.findScene(multiplayerScene).oScene;
        oMulti.reset();
      }
      multiplayerScreenCreated = true;

      this.sceneManager.showScene(multiplayerScene);
    }

    else if(leaderboard){
      leaderboard = false;
      removeElements();
      clear();

      //If a leaderboard screen has been created before then reset it.
      //Creates a brand new instance of this display.
      if(leaderboardCreated){
        oLeaderboard = this.sceneManager.findScene(leaderboardScene).oScene;
        oLeaderboard.reset();
      }

      leaderboardCreated = true;
      this.sceneManager.showScene(leaderboardScene);
    }
  }

  //helper method to
  function setupStartUpScreen(){
    var title = createDiv("RUN SAMURAI, RUN!");
    setupText(title, 0.3, 1);
    setupButtons();
  }

  function setupButtons(){
    //Buttons Setup
    singlePlayerButton = createButton("Single Player");
    buttonCSSSetup(singlePlayerButton, 0.5);
    singlePlayerButton.mousePressed(goToSinglePlayer);
    multiplayerButton = createButton("Multiplayer");
    buttonCSSSetup(multiplayerButton, 0.6);
    multiplayerButton.mousePressed(goToMultiplayer);
    leaderboardButton = createButton("Leaderboard")
    buttonCSSSetup(leaderboardButton, 0.7)
    leaderboardButton.mousePressed(goToLeaderboard);
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
    button.style("border", "none");
    button.style("border-radius", "9px");
    button.style("box-shadow", "0 8px #989898");
    button.style("display", "inline-block");
    button.style("font-family", "Arial");

    //NOT CSS
    //Center Button
    button.position(windowWidth - button.width >> 1, windowHeight * YpositionMultiple);
  }

  function setupText(givenDiv, heightVariable, opacity){
    givenDiv.style("width", windowWidth + "px");
    givenDiv.style("margin", "0 auto");
    givenDiv.style("text-align", "center");
    givenDiv.style("position", 0, windowHeight * heightVariable);
    givenDiv.style("color", "white");
    givenDiv.style("font-family", "Arial");
    givenDiv.style("text-shadow", "0px 3px #989898")
    givenDiv.style("font-size", "320%");
    givenDiv.style("opacity", opacity);
  }

  function goToSinglePlayer(){
    singlePlayer = true;
  }

  function goToMultiplayer(){
    multiplayer = true;
  }

  function goToLeaderboard(){
    leaderboard = true;
  }
}
