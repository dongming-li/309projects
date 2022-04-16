<?php
 // echo "Im in<br>";

$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';

$conn = mysqli_connect($servername, $username, $password, $dbname);

if ($conn->connect_error) {
        die("connection failed: " . $conn->connect_error);
}
// else{
// 	echo "The Database has been penetrated<br>";
// }



$function = null;
$friendName = null;
$userID = null;
$friendID = null;

if(isset($_REQUEST["function"])){
	$function = ''.$_REQUEST["function"];
	// echo "".$function."<br>";
}
if(isset($_REQUEST["name"])){
	$friendName = ''.$_REQUEST["name"];
	// echo "".$friendName."<br>";
}
else{
	// echo "no name is set<br>";
}
if(isset($_REQUEST["id"])){
	$userID = ''.$_REQUEST["id"];
	// echo "".$userID."<br>";
}
if(isset($_REQUEST["fid"])){
	$friendID = ''.$_REQUEST["fid"];
	// echo "".$friendID."<br>";
}
else{
	// echo "no fid set<br>";
}

if($function === 'MFR')
{
	makeFriendRequest();
}
if($function === 'CFR')
{
	confirmFriendRequest();
}
if($function === 'DFR')
{
	denyFriendRequest();
}
if($function === 'GFR'){
	getFriendRequest();
}

function makeFriendRequest(){
	global $conn;
	global $friendName;
	global $userID;

	// echo $friendName;

	// $sql = "
	// 	SELECT ID
	// 	FROM Users
	// 	WHERE Username = '$friendName'
	// 	";

	// $sql1 = "
	// 	SELECT UserID1
	// 	FROM Friendships
	// 	WHERE (UserID1 = '&userID' and UserID2 = '$tmp') or
	// 	(UserID1 = '$tmp' and UserID2 = '$userID')
	// 	";

	$sql2 = "
		UPDATE Users
		SET FriendRequests = '$userID'
		WHERE Username = '$friendName'
		";

	$result = $conn->query($sql2) or die("Update failed");
	echo 1;
	//$tmp = $result->fetch_assoc();
	// $result2 = $conn->query($sql1) or die("Selection failed");
	// $tmp1 = $result2->fetch_assoc();
	// if(is_null($tmp){
	// 	echo 0;
	// }
	// else{
	// 	echo 1;
	// }
	// else if(!is_null($tmp1)){
	// 	echo 1;
	// }
	// else{
	// 	$conn->query($sql2) or die("Update failed");
	// 	echo 2;
	// }

}

function confirmFriendRequest(){
	global $conn;
	global $friendName;
	global $userID;

	$getFID = "
			SELECT ID
			FROM Users
			WHERE Username = '$friendName'
			";

	$result = $conn->query($getFID) or die ("Selection Query Failed");
	$temp = $result->fetch_assoc();
	$FID = $temp["ID"];

	$sql = "
			INSERT INTO Friendships
			VALUES ('$FID', '$userID')
			";

	$sql1 = "
			UPDATE Users
			SET FriendRequests = null , FriendsCount = FriendsCount + 1
			WHERE ID = '$userID'
			";

	$sql2 = "
			UPDATE Users
			SET FriendsCount = FriendsCount + 1
			WHERE ID = '$FID'
			";

	$conn->query($sql) or die("Insertion Query Failed");
	$conn->query($sql1) or die("Update Query Failed");
	$conn->query($sql2) or die("Update Query Failed");

	echo 1;
}

function denyFriendRequest(){
	global $conn;
	global $userID;

	$sql = "
			UPDATE Users
			SET FriendRequests = null
			WHERE ID = '$userID'
			";

	$result = $conn->query($sql) or die("Update Query Failed");

	echo ''.$result;
}

function getFriendRequest(){
	global $conn;
	global $userID;

	// echo "I'm getting friend requests<br>";
	// echo ''.$userID.'<br>';

	$sql = "
			SELECT FriendRequests
			FROM Users
			WHERE ID = '$userID'
			";
	$freq = $conn->query($sql) or die("Selection Query Failed");
	$temp = $freq->fetch_assoc();
	$friendID = $temp["FriendRequests"];

	// if(is_null($freq)){
	// 	echo "freq is null";
		
	// }

	

	$sql = "
			SELECT FirstName, LastName, UserName
			FROM Users
			WHERE ID = '$friendID'
			";
	$result = $conn->query($sql) or die("Selection Query Unsuccessful");
	// $tmpArr = array();
	// while($row = $result->fetch_assoc()) {
	// 		// echo "Title: " . $row["PromoTitle"]. " - Description: " . $row["PromoDescription"]. " - Start Date " . $row["PromoStartDate"]. " - End Date " . $row["PromoEndDate"]. "\n" . "<br>";
	// 	$tmpArr[] = $row;
	// }

	$row = $result->fetch_assoc();
	$tmpArr = array();
	$tmpArr[0] = $row;
	// echo $row["FirstName"] . "\n";
	// echo $result;
	echo json_encode($tmpArr);

}

$conn->close();

?>