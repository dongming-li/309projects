<?php
$db_name = "db309gpb2";
$mysql_username= "dbu309gpb2";
$mysql_password= "#bEdxrXT";
$server_name= "mysql.cs.iastate.edu";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn->connect_error){
	die("Connection failed: ".$conn->connection_error);
}

$username = $_GET["username"];
$game_table = $_GET["game_table"];

$sql = "SELECT class, health, strength, intellect, agility, level, exp, inv, skill FROM game_one WHERE Username = '$username'";

$result = $conn->query($sql);

if ($result == true){
	$row = $result->fetch_assoc();
	echo " ". $row["class"].":".$row["health"].":". $row["strength"].":". $row["intellect"].":". $row["agility"].":". $row["level"].":". $row["exp"].":". $row["inv"].":". $row["skill"]." ";
}
else{
	echo "false";
}
?>
