<?php
/*
	Description: Takes the first and last name and deletes the user that has that first and last name.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Remove_User.php?firstname=<FIRST>&lastname=<LAST>
	Output: <USERNAME TO BE DELETED>
*/
	$input_firstname = $_GET['firstname'];
	$input_lastname = $_GET['lastname'];
//	echo $_REQUEST['test']

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

	$query = "SELECT Username FROM db309amb4.User_Table WHERE First_name = '" . $input_firstname . "' AND Last_Name = '" . $input_lastname . "'";
	$result = $conn->query($query);
	
	if($result->num_rows > 0)
	{
		$row = $result->fetch_assoc();
		
		$result = $row["Username"];
	}
	else
	{
		echo "99";
	}




	$date = "DELETE FROM db309amb4.User_Table WHERE Username ='" . $result . "'";
	$result = $conn->query($date);
	
	mysqli_close($conn);
	?>
