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
$PromoDuration;
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
	getPromotionsDuration($Id);
}
elseif($getset===1){
	$PromoDuration = $_GET["PromoDuration"];
	setPromotionsDuration($Id, $PromoDuration);
}
else{
	die("getset not properly set");
}

function setPromotionsDuration($id, $newDuration){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoDuration = '$newDuration'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Duration Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsDuration($id){
	global $conn;

	$sql = "
		SELECT PromoDuration
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Duration Retrieval Unsuccessful")

	echo json_encode($result)
}

$conn->close();
?>