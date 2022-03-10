<?php
/**
 * fetchUser.php
 * Recieves username from the android app and uses the information to create an SQL statement that retrieves that users information from the table and sends it to the android app.
 */
require "db_connect.php";

$Username = NULL;

if(isset($_REQUEST["userName"])){
	$Username = ''.$_REQUEST["userName"];
}
else {
    die("No User Name");
}


$sql = " SELECT *
            FROM Users
            Where ID = '$Username'";

$result = mysqli_query($db, $sql);
if (mysqli_num_rows($result) > 0) echo  json_encode(mysqli_fetch_assoc($result));
else echo "Error: ".mysqli_connect_error();

?>