<?php
$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';

$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}
$sql = "select * from Users";
if (mysqli_query($conn, $sql)) {
echo "New record inserted successfully. \n";
} else {
echo "Error: " . $sql . "br" . mysqli_error($conn);
}
mysqli_close($conn); 
?>