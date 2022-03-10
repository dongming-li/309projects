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

function getPromotionsAll(){
	global $conn;

	$sql = "
			SELECT PromoTitle,PromoDescription,PromoStartDate,PromoEndDate
			FROM Promotions
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	$tmpArr = array();
	while($row = $result->fetch_assoc()) {
			// echo "Title: " . $row["PromoTitle"]. " - Description: " . $row["PromoDescription"]. " - Start Date " . $row["PromoStartDate"]. " - End Date " . $row["PromoEndDate"]. "\n" . "<br>";
		$tmpArr[] = $row;
	}

		// $row = $result->fetch_assoc();
		// echo $row["FirstName"] . "\n";
	echo json_encode($tmpArr);

}

$conn->close();
?>