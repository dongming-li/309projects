
<?php
	error_reporting(-1);
	ini_set('display_errors','On');
	$host="mysql.cs.iastate.edu";	
	$port=3306;
	$socket="";
	$user="dbu309amc1";
	$password="XFsBvb1t";
	$dbname="db309amc1";

	$con = new mysqli($host, $user, $password, $dbname, $port, $socket)
     	or die ('Could not connect to the database server' . mysqli_connect_error());
	
	if($con->connect_errno){
		echo "<p>encountered an error connecting</p>";
	}	

	$query = "Select * from user";
	
	$result = $con->query($query)
		or trigger_error($con->error);

	$rows = array();

	while($row = $result->fetch_array(MYSQL_BOTH)){
		   $rows[] = $row;
	}

	die(json_encode($rows));
	
	$con->close();
?> 

