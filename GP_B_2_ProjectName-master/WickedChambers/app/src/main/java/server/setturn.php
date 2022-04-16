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

$turn = (int)$_POST['myturn'];
$game = $_POST['game'];
$username = $_POST['username'];



$sql = "UPDATE `db309gpb2`.`$game` SET `turn`='$turn' WHERE `Username`='$username';";
$result = $conn->query($sql);

if ($result == true){
	//echo "register success";
}
else{
	//echo "failure";
}
?>