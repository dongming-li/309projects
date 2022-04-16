<?php
/*
	Description: Script that returns the current weight of the user over a given period of time.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Weight_Over_Time.php?username=<USERNAME>&start_date=<START_DATE>&end_date=<END_DATE>
	Output: <CURRENT WEIGHT>,<ENTR_TIME>
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

	$date = "SELECT Weight, Entry_Time 
				FROM db309amb4.User_Weight
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
	$Total_Steps = $row["Weight"];
	$Time = $row["Entry_Time"];
	
	echo $Total_Steps . "," . $Time . "<BR>";
	}
	}
else
{
	echo "99";
}
	
	mysqli_close($conn);
	?>
