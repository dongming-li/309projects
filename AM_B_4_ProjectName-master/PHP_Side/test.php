<?php
$servername = 'mysql.cs.iastate.edu';
$username = 'dbu309amb4';
$password = 'ZtWqGRXX';
$dbname = 'db309amb4';
$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}
echo "Connected Successfully.\n";
mysqli_close($conn);
?>
