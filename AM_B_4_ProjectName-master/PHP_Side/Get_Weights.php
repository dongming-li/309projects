<?php
/*
	Description: Script that returns the current and desired weights of a user. Takes the username in question as the input.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Weights.php?username=<USERNAME>
	Output: <CURRENT WEIGHT>,<DESIRED WEIGHT>
		145,100
*/
	$input_username = $_GET['username'];

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

	$date = "SELECT Loss_Goal as Desired, Weight FROM db309amb4.User_Table WHERE Username = '$input_username'";
	$result = $conn->query($date);
	
if ($result->num_rows > 0) 
{
    // output data of each row
	$row = $result->fetch_assoc();
	$desired = $row["Desired"];
	$current = $row["Weight"];
	
	echo $current . "," . $desired;
	}
else
{
	echo "99";
}
	?>