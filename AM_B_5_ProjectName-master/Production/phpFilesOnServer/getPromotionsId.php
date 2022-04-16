<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}


$ID = null;

if(isset($_REQUEST["ID"])){
    $ID = ''.$_REQUEST["ID"];
}

getPromotionsId();

/** This function gets all the promotions that the Business with the inseted Id has made **/
function getPromotionsId(){
	global $conn;
	global $ID;

	$sql = "
			SELECT PromoTitle,PromoDescription,PromoStartDate,PromoEndDate
			FROM Promotions
			WHERE BusinessID = '$ID'
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