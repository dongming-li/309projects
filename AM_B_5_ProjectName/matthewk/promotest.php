<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';
$port = '3306';
$socket = '';




$conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket) or die('Could not find' . mysqli_connect_error());




	$sql = "
			SELECT *
			FROM promotest
			";
	$result = mysqli_query($conn, $sql) or die('error in selecting' . mysqli_error($con));
	$tmpArr = array();
	while($row = mysqli_fetch_assoc($result)) {
			// echo "Title: " . $row["PromoTitle"]. " - Description: " . $row["PromoDescription"]. " - Start Date " . $row["PromoStartDate"]. " - End Date " . $row["PromoEndDate"]. "\n" . "<br>";
		$tmpArr[] = $row;
	}

		// $row = $result->fetch_assoc();
		// echo $row["FirstName"] . "\n";
	echo json_encode($tmpArr);



$conn->close();
?>