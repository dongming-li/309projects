<?php

require "db_connect.php";

// All the fields for Businesses table.
//ID, Username, BusinessTitle, Phone, Email, Password, Address, City
$ID = null;
$Username = null;
$BusinessTitle = null;
$Phone = null;
$Email = null;
$Password = null;
$Address = null;
$City = null;

if(isset($_REQUEST["ID"])){
    $ID = ''.$_REQUEST["ID"];
}
if(isset($_REQUEST["username"])){
	$Username = ''.$_REQUEST["username"];
}
if(isset($_REQUEST["businessTitle"])){
	$BusinessTitle = ''.$_REQUEST["businessTitle"];
}
if(isset($_REQUEST["phone"])){
	$Phone = ''.$_REQUEST["phone"];
}
if(isset($_REQUEST["email"])){
	$Email = ''.$_REQUEST["email"];
}
if(isset($_REQUEST["password"])){
	$Password = ''.$_REQUEST["password"];
}
if(isset($_REQUEST["address"])){
	$Address = ''.$_REQUEST["address"];
}
if(isset($_REQUEST["city"])){
    $City = ''.$_REQUEST["city"];
}

/** This statement takes in all of the input and updates it for the Business **/

$sql = "
            UPDATE Businesses
			SET Username = '$Username',
            BusinessTitle =  '$BusinessTitle',
            Phone = '$Phone',
            Email = '$Email',
            Password = '$Password',
            Address = '$Address',
            City = '$City'
			WHERE ID = '$ID'
            ";

if($result = mysqli_query($db, $sql)) echo $result;
else echo mysqli_connect_error();

?>