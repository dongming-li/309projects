<?php
/*
	Description: Returns the plan_ID, Name, and description of each workout plan in the database.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Workouts.php
	Output: <PLAN_ID>:<NAME>:<DESCRIPTION>
		0:Hardcore:Gets dem gainz good
		1:Softcore:Gets dem gains poor
		2:Mediumcore:Gets dem gains ok
*/
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

	$date = "SELECT * FROM db309amb4.Workout_Plans";
	$result = $conn->query($date);
	
if ($result->num_rows > 0) 
{
    // output data of each row
	while($row = $result->fetch_assoc())
	{
	$Plan_ID = $row["Workout_Plan_ID"];
	$Plan_Name = $row["Plan_Name"];
	$Plan_Description = $row["Plan_Description"];
	echo $Plan_ID . ":" . $Plan_Name . ":" . $Plan_Description . "<br>";
	}
//	echo "Hey"
	}
else
{
	echo "99";
}
	?>