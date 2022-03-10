<?php
/*
	Description: Takes the username and new loss goal of the user and then updates that users loss goal.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Update_LossGoal.php?username=<USERNAME>&loss_goal=<DESIRED WEIGHT>
	Output: 1 if successful.
*/
	$input_username = $_GET['username'];
	$input_weight = $_GET['loss_goal'];

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

	$query = "UPDATE db309amb4.User_Table SET Loss_Goal = '" . $input_weight . "' WHERE Username = '" . $input_username . "'";
	echo $query;
	$result = $conn->query($query);
	
	mysqli_close($conn);
	?>
