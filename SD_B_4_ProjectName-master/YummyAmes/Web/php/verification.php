<?php    
$img = imagecreatetruecolor(100, 35);  
$black = imagecolorallocate($img, 0x00, 0x00, 0x00);  
$green = imagecolorallocate($img, 0x00, 0xFF, 0x00);  
$white = imagecolorallocate($img, 0xFF, 0xFF, 0xFF);  
imagefill($img,0,0,$white);  
//random verification code  
$code = '';  
for($i = 0; $i < 4; $i++) {  
    $code .= rand(0, 9);  
}  
$_SESSION['rand'] = $code;  //Store verification code 
imagestring($img, 30, 28, 10, $code, $black);  

//add interference  
for($i=0;$i<200;$i++) {  
  imagesetpixel($img, rand(0, 100) , rand(0, 100) , $black);   
  imagesetpixel($img, rand(0, 100) , rand(0, 100) , $green);  
}  
//input Verification code 
header("content-type: image/png");  
imagepng($img);  
imagedestroy($img);  
?>  