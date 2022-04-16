<?php
//Author Aaron
// This file is used to move players into the game that they select to join
$db_name = "db309gpb2";
$mysql_username= "dbu309gpb2";
$mysql_password= "#bEdxrXT";
$server_name= "mysql.cs.iastate.edu";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn->connect_error){
	die("Connection failed: ".$conn->connection_error);
}

$username = $_GET['username'];
$game_table = $_GET['game_num'];



$sql = "INSERT INTO game_one(Username,CharName,health,intellect,strength,agility,level,exp,class,inv,skill) SELECT Username,CharName,health,intellect,strength,agility,level,exp,class,inv,skill FROM User where Username = '$username'";

$result = $conn->query($sql);

if ($result == true){
	echo "Success\n";
}
else {
	echo "Failure\n";
}
?>
