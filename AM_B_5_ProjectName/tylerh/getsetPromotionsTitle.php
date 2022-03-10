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
$PromoTitle;
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
	getPromotionsTitle($Id);
}
elseif($getset===1){
	$PromoTitle = $_GET["PromoTitle"];
	setPromotionsTitle($Id, $PromoTitle);
}
else{
	die("getset not properly set");
}

function setPromotionsTitle($id, $newTitle){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoTitle = '$newTitle'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Title Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsTitle($id){
	global $conn;

	$sql = "
		SELECT PromoTitle
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Title Retrieval Unsuccessful")

	echo json_encode($result)
}

$conn->close();
?>