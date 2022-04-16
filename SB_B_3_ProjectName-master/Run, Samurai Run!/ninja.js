/*
  Enemies
*/
function ninja(){
  var ninj;
  //Hashmap for spots X spots to spawn enemies
  var dict = {};
  dict[0] = (windowWidth / 2) * 0.46;
  dict[1] =(windowWidth / 2) * 1.54;
  dict[2] = windowWidth / 2;

  //RNG to choose where
  rand = getRandomInt(0, 2);
  rand2 = getRandomInt(0,2);
  this.x = dict[rand];
  this.y = -100;
  this.size = windowWidth * 0.07
  this.velocity = 5;

  this.show = function(){
    fill(255);
    ellipse(this.x, this.y, this.size, this.size);
  }

  this.update = function(){
    this.y += this.velocity;
  }

  this.offscreen = function(){
    return this.y > windowHeight;
  }

  this.collision = function(player){
    if(player.x < (this.x + this.size) && player.x > (this.x - this.size)){
      if(player.y < (this.y + this.size) && player.y > (this.y - this.size)){
        return true;
      }
    }
  }

  function getRandomInt(min, max){
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }
}
