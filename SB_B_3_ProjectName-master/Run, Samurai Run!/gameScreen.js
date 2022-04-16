function gameScreen(){

  var score;
  var samurai;
  var ninjas;
  var planks;
  var backgroundImg;
  var pauseAreaClicked;
  var isPaused;
  var pauseCreated;
  var gameOverCreated;
  var firstRun;

  this.setup = function(){
    createCanvas(windowWidth, windowHeight);
    samurai = new Samurai();
    ninjas = [];
    planks = [];
    score = 0;

    //Create the Pause Button
    pauseAreaClicked = false;
    firstRun = true;
    isPaused = false;
    pauseButton = createButton("| |");
    pauseButtonCSSSetup(pauseButton, 0.6);
    pauseButton.mousePressed(pauseGame);
  }

  this.keyPressed = function(){
    if(keyCode == RIGHT_ARROW){
      samurai.switchLanes("right");
    }

    else if(keyCode == LEFT_ARROW){
      samurai.switchLanes("left");
    }

    else if(keyCode == UP_ARROW){
      samurai.attack();
    }
  }

  this.reset = function(){
    this.setup();
  }

  this.getScore = function(){
    return score;
  }

  this.draw = function(){
    if(isPaused){
      return;
    }

    //Background Setup
    background(193, 154, 107);

    if(firstRun){
      for(var i = 0; i < windowHeight; i++){
        if(i % 50 == 0){
          planks.push(new plank(i));
        }
      }
      firstRun = false;
    }

    for(var i = planks.length - 1; i >= 0; i--){
      planks[i].show();
      planks[i].update();

      if(planks[i].offscreen()){
        planks.splice(i, 1);
        planks.push(new plank(0));
      }
    }

    //Text
    fill(255);
    textAlign(CENTER, CENTER);
    textSize(25);
    text("Score: " + score, windowWidth/2, windowHeight * .05)

    //Watch for framerate
    var fps = frameRate();
    stroke(0);
    text("FPS:" + fps.toFixed(2), windowWidth / 2, height - 20);

    samurai.update();
    samurai.show();

    if(frameCount % 10 == 0){
      samurai.animate();
    }

    //Update enemy position every frame.
    for(var i = ninjas.length - 1; i >= 0; i--){
      ninjas[i].show();
      ninjas[i].update();

      //If enemies go offscreen delete them to free up memory.
      if(ninjas[i].offscreen()){
        ninjas.splice(i, 1);
        if(score > 0)
          score--;
      }

      if(ninjas[i].collision(samurai)){
        if(samurai.attacking){
          score++;
        }
        else{
          clear();
          removeElements();
          if(gameOverCreated){
            var oOver = this.sceneManager.findScene(gameOverScene).oScene;
            oOver.reset();
          }

          gameOverCreated = true;
          this.sceneManager.showScene(gameOverScene);
        }
        ninjas.splice(i, 1);
      }
    }

    if(score <= 2){
      if(frameCount % 80 == 0){
        ninjas.push(new ninja());
      }
    }

    else if(score > 2 && score <= 5){
      if(frameCount % 70 == 0){
        ninjas.push(new ninja());
      }
    }

    else if(score > 5 && score <= 10){
      if(frameCount % 50 == 0){
        ninjas.push(new ninja());
      }
    }

    else if(score > 10 && score <= 50) {
      if(frameCount % 40 == 0){
        ninjas.push(new ninja());
      }
    }

    else{
      if(frameCount % 35 == 0){
        ninjas.push(new ninja());
      }
    }
  }


  this.mousePressed = function(){
    if(!pauseAreaClicked){
      if(mouseX > (windowWidth / 2) * 1.5){
        samurai.switchLanes("right");
      }

      else if(mouseX < (windowWidth / 2) * 0.5){
        samurai.switchLanes("left");
      }

      else{
        samurai.attack();
      }
    }

    else{
      if(isPaused){
        isPaused = false;
      }

      else{
        isPaused = true;
        if(pauseCreated){
          oPause = this.sceneManager.findScene(pauseScreen).oScene;
          oPause.reset();
        }

        pauseCreated = true;
        this.sceneManager.showScene(pauseScreen);
      }

      pauseAreaClicked = false;
    }
  }

    /**
      CSS STYLING FOR BUTTONS :D
    */
  function pauseButtonCSSSetup(button, YpositionMultiple){
    button.style("width", "35px");
    button.style("height", "35px");
    button.style("text-align", "center");
    button.style("font-size", "150%");
    button.style("font-weight", "bold")
    button.style("color", "#FFF");
    button.style("background", "#C19A6B");
    button.style("border-radius", "4px");
    button.style("display", "inline-block");
    button.style("border", "none");
    button.style("outline", "none");

    //NOT CSS
    //Center Button
    button.position(windowWidth  >> 6, windowHeight/100);
  }

  function pauseGame(){
    pauseAreaClicked = true;
  }

  this.unpause = function(){
    isPaused = false;
  }
}
