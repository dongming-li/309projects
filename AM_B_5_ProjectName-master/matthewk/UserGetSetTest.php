<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}

global $id;
// global $newName;
function getUserFirstName($userid){
$id = $userid;
$sql = "
SELECT FirstName
FROM Users
WHERE ID = '$id'
";
$result = $conn->query($sql) or die("Selection Query Failed !!!");
if ($result->num_rows > 0) {
$row = $result->fetch_assoc();
echo $row["FirstName"] . "\n";
} else {
echo "0 results \n";
}
}



function changeUserFirstName($id, $newName){
$sql = "
UPDATE Users
SET FirstName = '$newName'
WHERE ID = '$id'
";
$result = $conn->query($sql);
$sql = "
SELECT FirstName
FROM Users
WHERE ID = '1' and FirstName = '$newName'
";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
echo "User's first name is now $newName. \n";
} else {
echo "First name for user with id equal to $id did not get set to $newName \n";
}
}

function getUserFirstName($id){
$sql = "
SELECT FirstName
FROM Users
WHERE ID = '$id'
";
$result = $conn->query($sql) or die("Selection Query Failed !!!");
if ($result->num_rows > 0) {
$row = $result->fetch_assoc();
echo $row["FirstName"] . "\n";
} else {
echo "0 results \n";
}
}





$conn->close();
?>