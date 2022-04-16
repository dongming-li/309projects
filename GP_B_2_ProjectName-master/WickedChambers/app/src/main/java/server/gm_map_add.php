<?php
//author Aaron
$db_name = "db309gpb2";
$mysql_username= "dbu309gpb2";
$mysql_password= "#bEdxrXT";
$server_name= "mysql.cs.iastate.edu";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn->connect_error){
	die("Connection failed: ".$conn->connection_error);
}

//$map_name = $_GET["map_name"];
$map_data = $_GET["map_data"];
$map_num = (int)$_GET["map_num"];

//$map_name = $argv[1];
//$map_data = $argv[1];
//$map_num = $argv[2];

$sql = "UPDATE gm_maps SET map_data = '$map_data' WHERE map_num = '$map_num'";

$result = $conn->query($sql);

if($result == true){
	echo "Success\n";
}
else{
	echo "Failure\n";
}
?>
