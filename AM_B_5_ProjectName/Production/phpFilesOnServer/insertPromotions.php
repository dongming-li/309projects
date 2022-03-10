<?php


$servername = 'mysql.cs.iastate.edu';
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}

$BusinessID = null;
$PromoTitle = null;
$PromoDescription = null;
$PromoDate = null;
$PromoDuration = null;
$PromoStartDate = null;
$PromoEndDate = null;
$Id = 0;

if(isset($_REQUEST["BusinessID"])){
	$BusinessID = ''.$_REQUEST["BusinessID"];
}
else{
	die("No BusinessID Provided");
}
if(isset($_REQUEST["PromoTitle"])){
	$PromoTitle = ''.$_REQUEST["PromoTitle"];
}
else{
	die("No Title Provided");
}
if(isset($_REQUEST["PromoDescription"])){
	$PromoDescription = ''.$_REQUEST["PromoDescription"];
}
if(isset($_REQUEST["PromoDate"])){
	$PromoDate = ''.$_REQUEST["PromoDate"];
}
if(isset($_REQUEST["PromoDuration"])){
	$PromoDuration = ''.$_REQUEST["PromoDuration"];
}
if(isset($_REQUEST["PromoStartDate"])){
	$PromoStartDate = ''.$_REQUEST["PromoStartDate"];
}
if(isset($_REQUEST["PromoEndDate"])){
	$PromoEndDate = ''.$_REQUEST["PromoEndDate"];
}



insertPromotions();
/** This function inserts a new promotion into the Promotions table. **/
function insertPromotions(){
	global $conn;

	global $BusinessID;
	global $PromoTitle;
	global $PromoDescription;
	global $PromoDate; 
	global $PromoDuration;
	global $PromoStartDate;
	global $PromoEndDate;
	global $Id;

	$sql = "
		INSERT INTO Promotions
		VALUES('$BusinessID','$PromoTitle','$PromoDescription','$PromoDate','$PromoDuration','$PromoStartDate','$PromoEndDate','$Id')
		";

	$result = $conn->query($sql) or die ("Selection Query Unsuccessful");

	echo "$result";

}

$conn->close();
?>