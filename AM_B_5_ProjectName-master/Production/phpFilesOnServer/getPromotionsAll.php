<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}


getPromotionsAll();
/** This function retrieves all the promotions from the Promotions table and sends back the PromoTitle, PromoDescription, PromoStartDate and PromoEndDate **/
function getPromotionsAll(){
	global $conn;

	$sql = "
			SELECT PromoTitle,PromoDescription,PromoStartDate,PromoEndDate
			FROM Promotions
			";

	$result = $conn->query($sql) or die("Selection Query Unsuccessful");
	$tmpArr = array();

	while($row = $result->fetch_assoc()) {
		$tmpArr[] = $row;
	}
	
	echo json_encode($tmpArr);
}

$conn->close();
?>