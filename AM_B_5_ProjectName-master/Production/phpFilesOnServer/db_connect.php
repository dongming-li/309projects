<?php

$sqlServer = "mysql.cs.iastate.edu";
$sqlUser = "dbu309amb5";
$sqlPswd = "e3#zBQGf";
$dbName = "db309amb5";

$db = mysqli_connect($sqlServer, $sqlUser, $sqlPswd, $dbName);
if(!$db) die("SQL server connection failed: ".mysqli_connect_error());
//echo "<h3>Successfully established connection with AM_B_5 MySQL server</h3>";

?>