<?php
/*
Description: Script that when run returns the username of each person on the server.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Usernames.php
	Output: <Username> \n
*/

	$servername = 'mysql.cs.iastate.edu';
	$username = 'dbu309amb4';
	$password = 'ZtWqGRXX';
	$dbname = 'db309amb4';
	$conn = mysqli_connect($servername, $username, $password, $dbname);
	
	if (!$conn)
	{
		die("Connection failed: " . mysqli_connect_error());
	}

	
	$query = "SELECT Username FROM db309amb4.User_Table";
	$result = $conn->query($query);

if ($result->num_rows > 0) 
{
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo $row["Username"] . "<br>";
    }
}
	mysqli_close($conn);
	?>
