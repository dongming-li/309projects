<?php
$servername = "mysql.cs.iastate.edu";
$username = "dbu309amb5";
$password = "e3#zBQGf";
$dbname = "db309amb5";

//Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

//Check connection
if ($conn->connect_error) {
        die("connection failed: " . $conn->connect_error);
}

function String getCurrentPromoTitles($currentTime) {
	global $conn;

	$sql = "SELECT promoTitle 
			FROM Promotions 
			WHERE promoStart <= '$currentTime' AND promoEnd >= '$currentTime'";

	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
	 	promosString = "";
    	// output data of each row
    	while($row = $result->fetch_assoc()) {
        promosString += $row["promoTitle"]. "SEPARATE";
    	}
    }
	else {
    echo "0 results";
	}
}


$currentTime = date_create('2017-10-4');

getCurrentPromoTitle($currentTime);


$conn->close();

?>