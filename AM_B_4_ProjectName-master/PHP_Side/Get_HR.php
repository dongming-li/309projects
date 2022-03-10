<?php
/*
	Description: This script takes the username and then start and end datetimes in the form of YYYY-MM-DD HH:MM:SS and then returns all of the heartrate data within that timespan.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_HR.php?username=<USERNAME>&start_date=<DATETIME>&end_date=<DATETIME>
	Output: <BPM>:<DATETIME in YYYY-MM-DD HH:MM:SS>
		100:2017-06-23 00:00:01
		120:2017-06-23 00:00:02
		100:2017-06-23 00:00:03
		101:2017-06-23 00:00:04
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

	$date = "SELECT BPM, Entry_Time 
				FROM db309amb4.User_HeartRate
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
	$BPM = $row["BPM"];
	$Time = $row["Entry_Time"];
	
	echo $BPM . ":" . $Time . "<BR>";
	}
	}
else
{
	echo "99";
}
	
	mysqli_close($conn);
	?>
