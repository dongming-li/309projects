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

$sql = "SELECT * FROM User where Username = '$username';";
$result = $conn->query($sql);
if ($result->num_rows > 0){
	$row = mysql_fetch_row($result);
	echo $row[0];
}
else{
	echo "false";
}
?>