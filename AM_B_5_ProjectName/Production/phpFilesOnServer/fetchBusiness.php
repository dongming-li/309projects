<?php

require "db_connect.php";

// All the fields for Users table.
//ID, FirstName, LastName, Username, Password, Phone, Email, Location, FriendsCount
$ID = NULL;

if(isset($_REQUEST["ID"])){
	$ID = ''.$_REQUEST["ID"];
}
else {
    die("No User Name");
}

/** This query selects all of the information pertaining to that user **/

$sql = " SELECT *
            FROM Businesses
            Where ID = '$ID'";

$result = mysqli_query($db, $sql);
if (mysqli_num_rows($result) > 0) echo  json_encode(mysqli_fetch_assoc($result));
else echo "Error: ".mysqli_connect_error();

?>