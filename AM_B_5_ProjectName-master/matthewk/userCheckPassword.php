<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}



$Username = null;
$Password = null;

if(isset($_REQUEST["Username"])){
	$Username = ''.$_REQUEST["Username"];
}

if(isset($_REQUEST["Pass"])){
	$Password = ''.$_REQUEST["Password"];
}


userCheckPassword($Username, $Password);


function userCheckPassword($Username, $Password){
	global $conn;
	global $Username;
	global $Password;

	$sql = "
			SELECT Password
			FROM Users
			WHERE Username = '$Username' and Password = '$Password'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		echo "Pass\n";
	} 
	else 
	{
		echo "Incorrect Password.\n";
	}
}

$conn->close();
?>