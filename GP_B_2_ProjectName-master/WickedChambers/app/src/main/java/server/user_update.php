<?php
$db_name = "db309gpb2";
$mysql_username= "dbu309gpb2";
$mysql_password= "#bEdxrXT";
$server_name= "mysql.cs.iastate.edu";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn->connect_error){
	die("Connection failed: ".$conn->connection_error);
}

$sql = "UPDATE User u, game_one g SET
	u.health = g.health,
	u.intellect = g.intellect,
	u.strength = g.strength,
	u.agility = g.agility,
	u.level = g.level,
	u.exp = g.exp,
	u.class = g.class,
	u.inv = g.inv,
	u.skill = g.skill
	WHERE u.Username = g.Username";
  
$result = $conn->query($sql);

if ($result == true){
	echo "Success\n";	
}
else{
	echo "Failure\n";
}
?>
