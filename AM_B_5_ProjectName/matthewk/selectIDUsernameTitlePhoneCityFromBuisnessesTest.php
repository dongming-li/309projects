<?php
$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';

$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}
$sql = "select ID,Username,BusinessTitle,Phone,City from Businesses";
$result = $conn->query($sql);


if ($result->num_rows > 0) {
// output data of each row
while($row = $result->fetch_assoc()) {
echo "ID: " . $row["ID"]. " - Name: " . $row["Username"]. " - Title " . $row["BusinessTitle"]. " - Phone " . $row["Phone"]. " - City " . $row["City"]. "<br>"; }
} else {
echo "0 results";
} $conn->close();
?>