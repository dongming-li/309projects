<?php
/*
Description: This script takes the username and then start and end datetimes in the form of YYYY-MM-DD HH:MM:SS and then returns all of the step data within that timespan.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Steps.php?username=<USERNAME>&start_date=<DATETIME>&end_date=<DATETIME>
	Output: <Steps>:<DATETIME in YYYY-MM-DD HH:MM:SS>
		1000:2017-06-23 00:00:01
		2000:2017-06-23 00:00:02
		3000:2017-06-23 00:00:03
		4000:2017-06-23 00:00:04

*/
	$input_username = $_GET['username'];
	$start_date = $_GET['start_date'];
	$end_date = $_GET['end_date'];

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

	$date = "SELECT Total_Steps, Entry_Time 
				FROM db309amb4.User_Steps
				WHERE Entry_Time > '$start_date'
					AND Entry_Time < '$end_date'
					AND User_ID = '$input_username'
				ORDER BY Entry_Time";
	$result = $conn->query($date);
	
	
if ($result->num_rows > 0) 
{
	
    // output data of each row
	while($row = $result->fetch_assoc())
	{
	$Total_Steps = $row["Total_Steps"];
	$Time = $row["Entry_Time"];
	
	echo $Total_Steps . ":" . $Time . "<BR>";
	}
	}
else
{
	echo "99";
}
	
	mysqli_close($conn);
	?>
