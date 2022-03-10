<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}


$getset;
$PromoImage;
$Id;

if(isset($_GET["getset"])){
	$getset = $_GET["getset"];
}
else{
	die("getset must be set");
}
if(isset($_GET["Id"])){
	$Id = $_GET["Id"];
}
else{
	die("No ID error");
}
if($getset===0){
	getPromotionsImage($Id);
}
elseif($getset===1){
	$PromoImage = $_GET["PromoImage"];
	setPromotionsImage($Id, $PromoImage);
}
else{
	die("getset not properly set");
}

function setPromotionsImage($id, $newImage){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoImage = '$newImage'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Image Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsImage($id){
	global $conn;

	$sql = "
		SELECT PromoImage
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Image Retrieval Unsuccessful")

	echo json_encode($result)
}

$conn->close();
?>