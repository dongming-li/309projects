<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';

/** Connects to database and alerts if there is a connection error*/
$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}

$UserID = NULL;

/** Alows request to database to be specific to a user's ID which is set before running this file*/
if(isset($_REQUEST["ID"])){
	$UserID = ''.$_REQUEST["ID"];
}
else{
	die("No UserID Provided");
}

getFriendsList();

/**Finds and echos all of the userIds in the saame row as the given userId, i.e. all of the friends of the user*/
function getFriendsList(){
	global $conn;
	global $UserID;

	$sql = "
			SELECT FirstName, LastName, UserName, Location
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
		$tmpArr[] = $row;
	}

	echo json_encode($tmpArr);

}

$conn->close();
?>