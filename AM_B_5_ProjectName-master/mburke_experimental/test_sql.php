<?php
$servername = "mysql.cs.iastate.edu";
$username = "dbu309amb5";
$password = "e3#zBQGf";

//Create connection
$conn = new mysqli($servername, $username, $password);

//Chack connection
if ($conn->connect_error) {
        die("connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
?>
