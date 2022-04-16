<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}




// All the fields for Users table.
//ID, FirstName, LastName, Username, Password, Phone, Email, Location, FriendsCount

$ID = null;
$FirstName = null;
$LastName = null;
$UserName = null;
$Password = null;
$Phone = null;
$Email = null;
$Location = null;
$FriendsCount = null;


$function = null;

if(isset($_REQUEST["function"])){
	$function = ''.$_REQUEST["function"];
}




if($funciton === 'insertUser')
{
	insertUser();
}



if(isset($_REQUEST["firstName"])){
	$FirstName = ''.$_REQUEST["firstName"];
}
if(isset($_REQUEST["lastName"])){
	$LastName = ''.$_REQUEST["lastName"];
}
if(isset($_REQUEST["userName"])){
	$UserName = ''.$_REQUEST["userName"];
}
if(isset($_REQUEST["password"])){
	$Password = ''.$_REQUEST["password"];
}
if(isset($_REQUEST["phone"])){
	$Phone = ''.$_REQUEST["phone"];
}
if(isset($_REQUEST["email"])){
	$Email = ''.$_REQUEST["email"];
}
if(isset($_REQUEST["location"])){
	$Location = ''.$_REQUEST["location"];
}




function insertUser(){
	global $conn;

	$ID = 0;
	global $FirstName;
	global $LastName;
	global $Password;
	global $Phone;
	global $Email;
	global $Location;
	$FriendsCount = 0;

	$sql = "
		INSERT INTO Users
		VALUES('$ID','$FirstName','$LastName','$Password','$Phone','$Email','$Location','$FriendsCount')
		";
	error_log($sql,0);
	$result = $conn->query($sql) or die ("Selection Query Unsuccessful");

		// $row = $result->fetch_assoc();
		// echo $row["FirstName"] . "\n";
}


function getUserFirstName($id){
	global $conn;

	$sql = "
			SELECT FirstName
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["FirstName"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeUserFirstName($id, $newName){
	global $conn;

	$sql = "
			UPDATE Users
			SET FirstName = '$newName'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT FirstName
			FROM Users
			WHERE ID = '$id' and FirstName = '$newName'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "User's first name is now $newName. \n";
	} 
	else 
	{
		echo "First name for user with id equal to $id did not get set to $newName \n";
	}
}


function getUserLastName($id){
	global $conn;

	$sql = "
			SELECT LastName
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["LastName"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeUserLastName($id, $newName){
	global $conn;

	$sql = "
			UPDATE Users
			SET LastName = '$newName'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT LastName
			FROM Users
			WHERE ID = '$id' and LastName = '$newName'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "User's last name is now $newName. \n";
	} 
	else 
	{
		echo "Last name for user with id equal to $id did not get set to $newName \n";
	}
}



function getUserUserName($id){
	global $conn;

	$sql = "
			SELECT UserName
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["UserName"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeUserUserName($id, $newName){
	global $conn;

	$sql = "
			UPDATE Users
			SET UserName = '$newName'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT UserName
			FROM Users
			WHERE ID = '$id' and UserName = '$newName'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "User's User name is now $newName. \n";
	} 
	else 
	{
		echo "User name for user with id equal to $id did not get set to $newName \n";
	}
}


function getUserPassword($id){
	global $conn;

	$sql = "
			SELECT Password
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Password"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeUserPassword($id, $newPassword){
	global $conn;

	$sql = "
			UPDATE Users
			SET Password = '$newPassword'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Password
			FROM Users
			WHERE ID = '$id' and Password = '$newPassword'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "User's Password is now $newPassword. \n";
	} 
	else 
	{
		echo "Password for user with id equal to $id did not get set to $newPassword \n";
	}
}


function getUserPhone($id){
	global $conn;

	$sql = "
			SELECT Phone
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Phone"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeUserPhone($id, $newNumber){
	global $conn;

	$sql = "
			UPDATE Users
			SET Phone = '$newNumber'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Phone
			FROM Users
			WHERE ID = '$id' and Phone = '$newNumber'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "User's Phone Number is now $newNumber. \n";
	} 
	else 
	{
		echo "Phone Number for user with id equal to $id did not get set to $newNumber \n";
	}
}


function getUserEmail($id){
	global $conn;

	$sql = "
			SELECT Email
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Email"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeUserEmail($id, $email){
	global $conn;

	$sql = "
			UPDATE Users
			SET Email = '$email'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Email
			FROM Users
			WHERE ID = '$id' and Email = '$email'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "User's Email is now $email. \n";
	} 
	else 
	{
		echo "Email for user with id equal to $id did not get set to $email \n";
	}
}


function getUserLocation($id){
	global $conn;

	$sql = "
			SELECT Location
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Location"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeUserLocation($id, $newLocation){
	global $conn;

	$sql = "
			UPDATE Users
			SET Location = '$newLocation'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Location
			FROM Users
			WHERE ID = '$id' and Location = '$newLocation'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "User's Location is now $newLocation. \n";
	} 
	else 
	{
		echo "Location for user with id equal to $id did not get set to $newLocation \n";
	}
}


function getUserFriends($id){
	global $conn;

	$sql = "
			SELECT FriendsCount
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["FriendsCount"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}

function changeUserIncrementFriendCount($id){
	global $conn;
	$friendCount; 

	$sql = "
			SELECT FriendsCount
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0)
	{
		$row = $result->fetch_assoc();
		$friendCount = $row["FriendsCount"];
		++$friendCount;
		$sql = "
				UPDATE Users
				SET FriendsCount = '$friendCount'
				WHERE ID = '$id'
				";
		$result = $conn->query($sql);
		echo "$friendCount \n";
	}
	else 
	{
		echo "0 results \n";
	}
}


function changeUserDecrementFriendCount($id){
	global $conn;
	$friendCount; 

	$sql = "
			SELECT FriendsCount
			FROM Users
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0)
	{
		$row = $result->fetch_assoc();
		$friendCount = $row["FriendsCount"];
		--$friendCount;
		$sql = "
				UPDATE Users
				SET FriendsCount = '$friendCount'
				WHERE ID = '$id'
				";
		$result = $conn->query($sql);
		echo "$friendCount \n";
	}
	else 
	{
		echo "0 results \n";
	}
}





$conn->close();
?>