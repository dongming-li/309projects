<?php
/*
	Description: Takes the username and steps of a user and adds that steps measurement to the database with the current time of the server.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Set_Steps.php?username=<USERNAME>&steps=<STEPS>
	Output: N/A
*/

	$input_username = $_GET['username'];
	$steps = $_GET['steps'];

	echo $test;

	$servername = 'mysql.cs.iastate.edu';
	$username = 'dbu309amb4';
	$password = 'ZtWqGRXX';
	$dbname = 'db309amb4';
	$conn = mysqli_connect($servername, $username, $password, $dbname);
	
	if (!$conn)
	{
		die("Connection failed: " . mysqli_connect_error());
	}
//	echo "Connected Successfully.<br>";

	$date = "insert into db309amb4.User_Steps(User_ID, Entry_Time, Total_Steps) VALUES('$input_username', NOW(), $steps)";
	$result = $conn->query($date);
	
	mysqli_close($conn);
	?>
