<?php

$servername = "mysql.cs.iastate.edu";
$username = "dbu309sdb3";
$password = "ZRTgf2B0";
$dbname = "db309sdb3";

function OpenDB(){

    return new mysqli($servername, $username, $password, $dbname);
}
function CloseDB($conn){
    $conn->close();
}
/*$conn = OpenDB();
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$sql = "SELECT id, firstname, lastname FROM MyGuests";
$result = $conn->query($sql);
CloseDB($conn);*/

?>