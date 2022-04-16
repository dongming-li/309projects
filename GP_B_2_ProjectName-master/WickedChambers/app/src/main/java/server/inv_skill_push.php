<?php
$db_name = "db309gpb2";
$mysql_username= "dbu309gpb2";
$mysql_password= "#bEdxrXT";
$server_name= "mysql.cs.iastate.edu";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn->connect_error){
	die("Connection failed: ".$conn->connection_error);
}

$inv = $_GET['inv'];
$skill = $_GET['skill'];
$username = $_GET['username'];
$hp_inc = (int)$_GET['hp_inc'];
//$hp_inc_s = $_GET['hp_inc_s'];
$int_inc = (int)$_GET['int_inc'];
//$int_inc_s = $_GET['int_inc_s'];
$str_inc = (int)$_GET['str_inc'];
//$str_inc_s = $_GET['str_inc_s'];
$agi_inc = (int)$_GET['agi_inc'];
//$agi_inc_s = $_GET['agi_inc_s'];
$level_inc = (int)$_GET['level_inc'];
$exp_inc = (int)$_GET['exp_inc'];

$sql = "UPDATE game_one 
	SET inv = '$inv', 
	skill = '$skill', 
	health = health + '$hp_inc', 
	intellect = intellect + '$int_inc', 
	strength = strength + '$str_inc',
	agility = agility + '$agi_inc', 
	level = level + '$level_inc', 
	exp = exp + '$exp_inc'  
	WHERE Username = '$username'";

$result = $conn->query($sql);

if ($result == true){
	echo "Success\n";
}
else {
	echo "Failure\n";
}
?>
