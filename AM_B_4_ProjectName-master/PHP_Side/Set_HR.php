<?php
/*
	Description: Takes the username and bpm of a user and adds that BPM measurement to the database with the current time of the server.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Set_HR.php?username=<USERNAME>&hr=<HR>
	Output: N/A
*/
	$input_username = $_GET['username'];
	$HR = $_GET['hr'];

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

	$date = "insert into db309amb4.User_HeartRate(User_ID, Entry_Time, BPM) VALUES('$input_username', NOW(), $HR)";
	$result = $conn->query($date);
	
	mysqli_close($conn);
	?>
