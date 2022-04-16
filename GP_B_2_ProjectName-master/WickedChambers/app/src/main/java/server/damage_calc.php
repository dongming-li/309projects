<?php
//author aaron
$db_name = "db309gpb2";
$mysql_username= "dbu309gpb2";
$mysql_password= "#bEdxrXT";
$server_name= "mysql.cs.iastate.edu";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn->connect_error){
	die("Connection failed: ".$conn->connection_error);
}


$p1 = (int)$_GET['p1'];
$p2 = (int)$_GET['p2'];
$p3 = (int)$_GET['p3'];
$p4 = (int)$_GET['p4'];
$e1 = (int)$_GET['e1'];
$e2 = (int)$_GET['e2'];
$e3 = (int)$_GET['e3'];
$e4 = (int)$_GET['e4'];

//$dmg_val = $argv[1];
//$target = $argv[2];

$sql = "UPDATE game_health
	SET p1 = '$p1',
	p2 = '$p2',
	p3 = '$p3',
	p4 = '$p4',
	e1 = '$e1',
	e2 = '$e2',
	e3 = '$e3',
	e4 = '$e4'";

$result = $conn->query($sql);

if ($result == true){
	echo "Update Success";
}
else {
	echo "Update Failure";
}
?>
