<?php
//author aaron 
$db_name = "db309gpb2";
$truth = "true";
$notTruth = "";
$mysql_username= "dbu309gpb2";
$mysql_password= "#bEdxrXT";
$server_name= "mysql.cs.iastate.edu";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn->connect_error){
	die("Connection failed: ".$conn->connection_error);
}
else{
	echo "connection is a go co-pilot \n";
}

$username = $_POST['username'];
$password = $_POST['password'];

$sql = "SELECT Username, Password FROM User where Username = '$username' AND Password = '$password'";

$result = $conn->query($sql);


if ($result->num_rows > 0){
	//$_SESSION["logged in"] = true;
	echo "true";
}
else{
	echo "false";
}
?>