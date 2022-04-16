<?php
	$servername = 'mysql.cs.iastate.edu';
	$username = 'dbu309amb4';
	$password = 'ZtWqGRXX';
	$dbname = 'db309amb4';
	$conn = mysqli_connect($servername, $username, $password, $dbname);
	
	if (!$conn)
	{
		die("Connection failed: " . mysqli_connect_error());
	}
	echo "Connected Successfully.<br>";

	
	$query = "SELECT First_Name, Last_Name, Email_Address FROM db309amb4.User_Table WHERE First_Name = 'Justin'";
	$result = $conn->query($query);

if ($result->num_rows > 0) 
{
    // output data of each row
    while($row = $result->fetch_assoc()) {
//		echo "hello";
//		echo "Rows : " . $row;
        echo "Name: " . $row["First_Name"]. " " . $row["Last_Name"]. " Email Address: " . $row["Email_Address"]. "<br>";
    }
//} else
//{
//echo "0 Results;
}
	mysqli_close($conn);
	?>