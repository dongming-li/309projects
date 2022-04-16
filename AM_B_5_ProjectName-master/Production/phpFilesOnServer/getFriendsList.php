<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}

$UserID = NULL;

if(isset($_REQUEST["ID"])){
	$UserID = ''.$_REQUEST["ID"];
}
else{
	die("No UserID Provided");
}

getFriendsList();

function getFriendsList(){
	global $conn;
	global $UserID;

	$sql = "
			SELECT FirstName, LastName, UserName
			FROM Users
			INNER JOIN Friendships
			ON ('$UserID' = Friendships.UserID1 and 
			Users.ID = Friendships.UserID2) or
			(Users.ID = Friendships.UserID1 and 
			'$UserID' = Friendships.UserID2)
			WHERE Users.ID != '$UserID'
			GROUP BY FirstName, LastName, UserName
			ORDER BY FirstName;
			";
	$result = $conn->query($sql) or die("Selection Query Unsuccessful");
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