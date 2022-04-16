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
$PromoStartDate;
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
	getPromotionsStartDate($Id);
}
elseif($getset===1){
	$PromoStartDate = $_GET["PromoStartDate"];
	setPromotionsStartDate($Id, $PromoStartDate);
}
else{
	die("getset not properly set");
}

function setPromotionsStartDate($id, $newStartDate){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoStartDate = '$newStartDate'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Start Date Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsStartDate($id){
	global $conn;

	$sql = "
		SELECT PromoStartDate
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Start Date Retrieval Unsuccessful")

	echo json_encode($result)
}

$conn->close();
?>