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

if(isset($_REQUEST["Username"])){
	$Username = ''.$_REQUEST["Username"];
}


userCheckUsername($Username);

function userCheckUsername($Username){
	global $conn;
	global $Username;

	$sql = "
			SELECT Username
			FROM Users
			WHERE Username = '$Username'
			";
	$result = $conn->query($sql) or die("Selection Query Failed !!!");
	if ($result->num_rows > 0) 
	{
		echo "Pass\n";
	} 
	else 
	{
		echo "Incorrect Username.\n";
	}
}

$conn->close();
?>