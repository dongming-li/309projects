<?php
//author gunnar
$db_name = "db309gpb2";
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
$class = $_POST['type'];
$charactername = $_POST['charactername'];
$health = (int)$_POST['health'];
$intellect = (int)$_POST['intellect'];
$strength = (int)$_POST['strength'];
$agility = (int)$_POST['agility'];
$level = (int)$_POST['level'];
$exp = (int)$_POST['exp'];
$inv = $_POST['inv'];
$skill = $_POST['skill'];

$sql = "insert into User(Username,Password,CharName,health,intellect,strength,agility,level,exp,class,inv,skill) values('".$username."','".$password."','".$charactername."','".$health."','".$intellect."','".$strength."','".$agility."','".$level."','".$exp."','".$class."','".$inv."','".$skill."');";
$result = $conn->query($sql);
if ($result == true){
	echo "register success";
}
else{
	echo "failure";
}
?>
