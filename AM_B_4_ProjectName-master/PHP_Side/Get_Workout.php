<?php
/*
	Description: Returns the workout for a given day for a given user. Takes the username and date in YYYY-MM-DD as input
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Workout.php?username=<USERNAME>&date=<DATE>
	Output: <WORKOUT1>:<WORKOUT2>:.....<WORKOUTN>
		4x Kleen:10x Jumps
*/

	$input_username = $_GET['username'];
	$input_date = $_GET['date'];
	
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
	$date = "SELECT DAYNAME('" . $input_date . "') AS Day";
	$result = $conn->query($date);
	
if ($result->num_rows > 0) 
{
    // output data of each row
	$row = $result->fetch_assoc();
	$date = $row["Day"];	
	}
else
{
	echo "99";
}
	
	
	$workout = "SELECT Workout_Plan_ID FROM db309amb4.User_Table WHERE Username = '$input_username'";
	$result = $conn->query($workout);


if ($result->num_rows > 0) 
{
    // output data of each row
	$row = $result->fetch_assoc();
	$result = $row["Workout_Plan_ID"];	
}
else
{
	echo "100";
}


	$date = $date . "_Workout";
	//echo $date;
	$query = "SELECT " . $date . " FROM db309amb4.Workout_Plan WHERE Workout_Plan_ID = " . $result;
	$result = $conn->query($query);
	
if ($result->num_rows > 0) 
{
    // output data of each row
	$row = $result->fetch_assoc();
	echo $row[$date];
}
else
{
	echo "101";
}
	
	mysqli_close($conn);
	?>
