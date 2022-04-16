function plank(givenY){
  this.y = givenY;

  this.show = function(){
    fill(0);
    line(0, this.y, windowWidth, this.y);
  }

  this.update = function(){
    this.y += 5;
  }

  this.offscreen = function(){
    return this.y > windowHeight;
  }
}
