var backgroundImage;
var samImg;
var ninImg;
var ninImg2;
var ninImg3;

function setBackgroundImage(){
  backgroundImage = "url('./Textures/StartScreenBackground2.jpeg')";
  document.body.style.backgroundSize = "100% 100%";
}

function setup(){
  createCanvas(windowWidth, windowHeight);
  setBackgroundImage();

  var sceneManager = new SceneManager();
  sceneManager.backgroundImage = backgroundImage;
  sceneManager.wire();
  sceneManager.showScene(loginScene);
}
