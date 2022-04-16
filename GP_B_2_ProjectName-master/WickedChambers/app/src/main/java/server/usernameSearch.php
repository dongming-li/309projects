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

$username = $_POST['username'];

$sql = "SELECT COUNT(*) FROM game_one where Username like '$username'";
$result = $conn->query($sql);
if ($result->num_rows > 0){
while($row = $result->fetch_assoc()){
		echo " ". $row["COUNT(*)"]." ";
	}
}
else{
	echo "false";
}
?>