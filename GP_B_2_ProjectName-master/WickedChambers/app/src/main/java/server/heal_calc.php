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


//$heal_val = (int)$_GET["heal_val"];
//$target = $_GET["target"];

//$heal_val = $argv[1];
//$target = $argv[2];

$sql = "SELECT p1,p2,p3,p4,e1,e2,e3,e4 FROM game_health";

$result = $conn->query($sql);

if ($result->num_rows > 0){
	$row = $result->fetch_assoc();
	echo " ". $row["p1"].":". $row["p2"].":". $row["p3"].":". $row["p4"].":". $row["e1"].":". $row["e2"].":". $row["e3"].":". $row["e4"]." ";
}
else {
	echo "Update Failure";
}
?>
