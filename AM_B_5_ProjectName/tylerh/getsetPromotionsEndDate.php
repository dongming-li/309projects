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
$PromoEndDate;
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
	getPromotionsEndDate($Id);
}
elseif($getset===1){
	$PromoEndDate = $_GET["PromoEndDate"];
	setPromotionsEndDate($Id, $PromoEndDate);
}
else{
	die("getset not properly set");
}

function setPromotionsEndDate($id, $newEndDate){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoEndDate = '$newEndDate'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("End Date Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsEndDate($id){
	global $conn;

	$sql = "
		SELECT PromoEndDate
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("End Date Retrieval Unsuccessful")

	echo json_encode($result)
}

$conn->close();
?>