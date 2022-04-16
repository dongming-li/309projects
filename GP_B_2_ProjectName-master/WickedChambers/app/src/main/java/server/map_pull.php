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
$map_num = (int)$_GET['map_num'];
//$map_num = $argv[1];

$sql = "SELECT map_data FROM gm_maps WHERE map_num = '$map_num'";

$result = $conn->query($sql);

if ($result->num_rows > 0){
	while($row = $result->fetch_assoc()){
		echo  " ".$row["map_data"]." ";	
	}
}
else {
	echo "Nothing there";
}
$conn->close();
?>
