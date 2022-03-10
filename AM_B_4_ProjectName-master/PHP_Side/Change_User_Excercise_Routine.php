<?php
	/**
	Description: This script takes the username of the user that the trainer wishes to modify and then the workout routine that the trainer wants to change the user to
	URL: http://proj-309-am-b-4.cs.iastate.edu/Change_User_Excercise_Routine.php?username=<USERNAME>&routine=<ROUTINE_ID>
	Output: Outputs the query on success to allow for debugging in case something is not updated correctly
			UPDATE db309amb4.User_Table SET Workout_Plan_ID = 1 WHERE Username = 'Blank'
	*/
	$input_username = $_GET['username'];
	$routine = $_GET['routine'];

	$servername = 'mysql.cs.iastate.edu';
	$username = 'dbu309amb4';
	$password = 'ZtWqGRXX';
	$dbname = 'db309amb4';
	$conn = mysqli_connect($servername, $username, $password, $dbname);
	
	if (!$conn)
	{
		die("Connection failed: " . mysqli_connect_error());
	}

	$query = "UPDATE db309amb4.User_Table SET Workout_Plan_ID = $routine WHERE Username = '" . $input_username . "'";
	echo $query;
	$result = $conn->query($query);
	
	mysqli_close($conn);
	?>
