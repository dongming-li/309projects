function multiplayerScene(){

  var score, score2, goal;
  var winner;
  var samurai, samurai2;
  var ninjas;
  var planks;
  var pauseAreaClicked;
  var isPaused;
  var pauseCreated;
  var firstRun;
  var gameOverCreated;

  this.setup = function(){
    createCanvas(windowWidth, windowHeight);
    samurai = new Samurai();
    samurai2 = new Samurai();
    ninjas = [];
    planks = [];
    score = 0;
    score2 = 0;
    goal = 5;
    winner = "";
    firstRun = true;
  }

  this.reset = function(){
    this.setup();
  }

  this.getScore = function(){
    if(score == goal){
      winner = "Black Samurai Wins";
    }
    else if(score2 == goal){
      winner = "White Samurai Wins";
    }
    return winner;
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

    //Score
    fill(255);
    textAlign(CENTER, CENTER);
    textSize(25);
    text("Score: " + score, windowWidth/1.25, windowHeight * .05)

    //Goal
    fill(255);
    textAlign(CENTER, CENTER);
    textSize(25);
    text("Goal: " + goal + " points" , windowWidth/2, windowHeight * .05)

    //Score
    fill(255);
    textAlign(CENTER, CENTER);
    textSize(25);
    text("Score: " + score2, windowWidth/7, windowHeight * .05)

    //Watch for framerate
    var fps = frameRate();
    stroke(0);
    text("FPS:" + fps.toFixed(2), windowWidth / 2, height - 20);

    samurai.update();
    samurai.show();

    samurai2.update();
    samurai2.show();
    samurai2.constantCharacter2();

    if(frameCount % 10 == 0){
      samurai.animate();
      samurai2.animate();
    }

    //Update enemy position every frame.
    for(var i = ninjas.length - 1; i >= 0; i--){
      ninjas[i].show();
      ninjas[i].update();

      if(ninjas[i].collision(samurai) || ninjas[i].collision(samurai2)){
        if(ninjas[i].collision(samurai)){
          if(samurai.attacking){
            score++;
          }
          else{
            if(score > 0)
              score--;
          }
        }

        else if(ninjas[i].collision(samurai2)){
          if(samurai2.attacking)
            score2++;
          else{
            if(score2 > 0)
              score2--;
          }
        }

        if(score == goal || score2 == goal){
          clear();
          removeElements();

          if(gameOverCreated){
            var oOver = this.sceneManager.findScene(gameOverMulti).oScene;
            oOver.reset();
          }

          gameOverCreated = true;
          this.sceneManager.showScene(gameOverMulti);
        }
        ninjas.splice(i, 1);
      }
    }

    //Spawn new enemies every 120 frames.
    if(frameCount % 120 == 0){
      ninjas.push(new ninja());
    }

    function pauseGame(){
      pauseAreaClicked = true;
    }

    this.unpause = function(){
      isPaused = false;
    }
  }

  this.keyPressed= function(){
    if(keyCode == RIGHT_ARROW){
      samurai.switchLanes("right");
    }
    else if(keyCode == LEFT_ARROW){
      samurai.switchLanes("left");
    }
    else if(keyCode == UP_ARROW){
      samurai.attack();
    }

    if(keyCode == 68){
      samurai2.switchLanes("right");
    }
    else if(keyCode == 65){
      samurai2.switchLanes("left");
    }
    else if(keyCode == 87){
      samurai2.attack();
    }
    return false;
  }
}
