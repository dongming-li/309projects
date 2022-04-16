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
if(isset($_REQUEST["userID"])){
	$ID = ''.$_REQUEST["userID"];
}









if($function === 'insertUser')
{
	insertUser();
}
if($function === 'checkUserName')
{
	checkUserName();
}
if($function === 'checkPassword')
{
	checkPassword();
}

/** This function inserts a new user into the Users table **/
function insertUser(){
	global $conn;

	global $FirstName;
	global $LastName;
	global $UserName;
	global $Password;
	global $Phone;
	global $Email;
	global $Location;
	$FriendsCount = 0;


	$dupForUserName = " 
						SELECT Username
						FROM Users
						Where Username = '$UserName'";
	$dupUserName = $conn->query($dupForUserName);
	if($dupUserName->num_rows < 1)
	{
		$dupForEmail = " 
						SELECT Email
						FROM Users
						Where Email = '$Email'";
		$dupEmail = $conn->query($dupForEmail);
		if($dupEmail->num_rows < 1)
		{
			$sql = "
			INSERT INTO Users
			VALUES(NULL,'r','$FirstName','$LastName','$UserName','$Password','$Phone','$Email','$Location','$FriendsCount')
			";
			$result = $conn->query($sql) or die ("Selection Query Unsuccessful for Users");

			echo "$result";
		}
		else
		{
			echo "$Email already in use.";
		}
	}

	else
	{
		echo "$UserName already in use.";
	}


}

/** This function checks to see if the given username is in the Users table, if it is, it returns that particular users ID **/
function checkUserName(){
	global $conn;
	global $UserName;

	$sql = "
			SELECT ID, UserType
			FROM Users
			WHERE Username = '$UserName'
			";
	$result = $conn->query($sql) or die("false");

	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo "" . $row["ID"] . " " . $row["UserType"];
	}

	else
	{
		echo "false";
	}
}

/** This function checks to see if the given password is the correct one for this particular user with that ID **/
function checkPassword(){
	global $conn;
	global $ID;
	global $Password;

	$sql = "
			SELECT Password
			FROM Users
			WHERE ID = '$ID' and Password = '$Password'
			";
	$result = $conn->query($sql) or die("false");

	
	if ($result->num_rows > 0) 
	{
		echo "$Password";
	}

	else
	{
		echo "false";
	}

}




$conn->close();
?>