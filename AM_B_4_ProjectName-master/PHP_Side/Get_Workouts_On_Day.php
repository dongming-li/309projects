<?php
/*
	Description: Takes the day of the week in form of "Monday, Tuesday, etc" and then returns all workouts possible for that day.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Workouts_On_day.php?day=<DAY>
	Output: <PLAN_ID>,<WORKOUT1:WORKOUT2:.....:WORKOUTN>
		0:5x Walks:12xJibs:213xFlies
		1:2x Bloops
		2:4x Kleen:10x Jumps
*/
	$day = $_GET['day'];

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

	$date = "SELECT " . $day . "_Workout FROM db309amb4.Workout_Plan";
	$result = $conn->query($date);
	
	if ($result->num_rows > 0) 
	{
	
    // output data of each row
		while($row = $result->fetch_assoc())
		{
	
		echo  $row[ $day ."_Workout"] . "<BR>";
		}
	}
	else
	{
		echo "99";
	}
	mysqli_close($conn);
	?>
