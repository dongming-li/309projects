<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}

$PromoTitle;
$PromoDescription;
$PromoStartDate;
$PromoEndDate;
$BusinessID;

if(isset($_GET["BusinessID"])){
	$BusinessID = $_GET["BusinessID"];
}
else{
	die("No BusinessID Provided");
}
if(isset($_GET["PromoTitle"])){
	$PromoTitle = $_GET["PromoTitle"];
}
if(isset($_GET["PromoDescription"])){
	$PromoDescription = $_GET["PromoDescription"];
}
if(isset($_GET["PromoStartDate"])){
	$PromoStartDate = $_GET["PromoStartDate"];
}
if(isset($_GET["PromoEndDate"])){
	$PromoEndDate = $_GET["PromoEndDate"];
}


getPromotionsBusiness($BusinessID);

function getPromotionsBusiness($id){
	global $conn;
	global $PromoTitle;
	global $PromoDescription;
	global $PromoStartDate;
	global $PromoEndDate;

	$sql = "
		SELECT '$PromoTitle', '$PromoDescription', '$PromoStartDate', '$PromoEndDate'
		FROM Promotions
		WHERE BusinessID = '$id'
		";
	$result = $conn->query($sql) or die ("Selection Query Unsuccessful";)
	$tmpArr = array();
	while($row = $result->fetch_assoc()) {
			// echo "Title: " . $row["PromoTitle"]. " - Description: " . $row["PromoDescription"]. " - Start Date " . $row["PromoStartDate"]. " - End Date " . $row["PromoEndDate"]. "\n" . "<br>";
		$tmpArr[] = $row;
	}

		// $row = $result->fetch_assoc();
		// echo $row["FirstName"] . "\n";
	echo json_encode($tmpArr)
}

$conn->close();
?>