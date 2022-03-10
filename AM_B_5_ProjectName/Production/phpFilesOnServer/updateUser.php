<?php
/**
 * updateUser.php
 * Recieves data from the android app and uses the information to create an SQL statement to update the Users table.
 */
require "db_connect.php";

$FirstName = null;
$LastName = null;
$UserName = null;
$Password = null;
$Phone = null;
$Email = null;
$Location = null;

if(isset($_REQUEST["firstName"])){
	$FirstName = ''.$_REQUEST["firstName"];
}
if(isset($_REQUEST["lastName"])){
	$LastName = ''.$_REQUEST["lastName"];
}
if(isset($_REQUEST["userName"])){
	$UserName = ''.$_REQUEST["userName"];
}
if(isset($_REQUEST["password"])){
	$Password = ''.$_REQUEST["password"];
}
if(isset($_REQUEST["phone"])){
	$Phone = ''.$_REQUEST["phone"];
}
if(isset($_REQUEST["email"])){
	$Email = ''.$_REQUEST["email"];
}
if(isset($_REQUEST["location"])){
	$Location = ''.$_REQUEST["location"];
}

$sql = "
            UPDATE Users
			SET FirstName = '$FirstName',
            LastName =  '$LastName',
            Username = '$UserName',
            Password = '$Password',
            Phone = '$Phone',
            Email = '$Email',
            Location = '$Location'
			WHERE Username = '$UserName'
            ";

if($result = mysqli_query($db, $sql)) echo $result;
else echo mysqli_connect_error();

?>