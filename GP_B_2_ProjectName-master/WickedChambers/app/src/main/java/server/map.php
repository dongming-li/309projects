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

$room_ID = $_POST['room_ID'];
$type = $_POST['type'];
$enemy_1 = $_POST['enemy_1'];
$enemy_2 = $_POST['enemy_1'];
$enemy_3 = $_POST['enemy_1'];
$enemy_4 = $_POST['enemy_1'];
$treasure = $_POST['treasure'];

$sql = "INSERT INTO Current_Map(room_ID, type, enemy_1, enemy_2, enemy_3, enemy_4, treasure)
        values('".$room_ID"','".$type"','".$enemy_1"','".$enemy_2"','".$enemy_3"','".$enemy_4"','".$treasure"');";

$result = $conn->query($sql);

?>