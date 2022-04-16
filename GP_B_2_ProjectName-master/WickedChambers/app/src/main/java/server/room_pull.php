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

$sql = "SELECT room_ID, type, enemy_1, enemy_2, enemy_3, enemy_4, treasure FROM map_one WHERE room_ID=1";

$result = $conn->query($sql);

if ($result->num_rows > 0){
	while($row = $result->fetch_assoc()){
		echo "room_ID: " . $row['room_ID']. " - Type: " . $row['type']. " - Enemy_1: " . $row['enemy_1']. " - Enemy_2: " . $row['enemy_2']. " - Enemy_3: " . $row['enemy_3']. " - Enemy_4: " . $row['enemy_4']. " - Treasure: " . $row['treasure']. "\n";	
	}
}
else {
	echo "Nothing there";
}

?>