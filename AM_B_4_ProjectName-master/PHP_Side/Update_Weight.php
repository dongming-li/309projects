<?php
/*
	Description: Takes the username and new weight of the user and then updates that users loss goal.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Update_Weight.php?username=<USERNAME>&weight=<WEIGHT>
	Output: 1 if successful.
*/
	$input_username = $_GET['username'];
	$input_weight = $_GET['weight'];

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

	$query = "UPDATE db309amb4.User_Table SET Weight = '" . $input_weight . "' WHERE Username = '" . $input_username . "'";
	echo $query;
	$result = $conn->query($query);
	
	$date = "insert into db309amb4.User_Weight(User_ID, Entry_Time, Weight) VALUES('$input_username', NOW(), $input_weight)";
	$result = $conn->query($date);
	
	mysqli_close($conn);
	?>
