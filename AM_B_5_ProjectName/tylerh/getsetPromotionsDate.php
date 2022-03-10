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
$PromoDate;
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
	getPromotionsDate($Id);
}
elseif($getset===1){
	$PromoDate = $_GET["PromoDate"];
	setPromotionsDate($Id, $PromoDate);
}
else{
	die("getset not properly set");
}

function setPromotionsDate($id, $newDate){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoDate = '$newDate'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Date Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsDate($id){
	global $conn;

	$sql = "
		SELECT PromoDate
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Date Retrieval Unsuccessful")

	echo json_encode($result)
}

$conn->close();
?>