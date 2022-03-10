<?php
/*
	Description: Script that when run returns the first and last name of each person on the server.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Get_Users.php
	Output: <FIRST NAME> <LAST NAME> \n
		Bruce Banner
		Taylor Swift
		Matthew Murdock
		Justin Bader
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

	
	$query = "SELECT First_Name, Last_Name FROM db309amb4.User_Table";
	$result = $conn->query($query);

if ($result->num_rows > 0) 
{
    // output data of each row
    while($row = $result->fetch_assoc()) {
//		echo "hello";
//		echo "Rows : " . $row;
        echo $row["First_Name"]. " " . $row["Last_Name"] . "<br>";
    }
//} else
//{
//echo "0 Results;
}
	mysqli_close($conn);
	?>
