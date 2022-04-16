<?php
/*
	Description: Takes a username and password and then checks to see if those credentials are in the server. If not
		     0 will be returned. Otherwise it will echo an integer based on what kind of user that user is.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Validate_Login.php?username=<USERNAME>&password=<PASSWORD>
	Output: 1 if trainer
		2 if admin
		3 if user
		4 if admin and trainer
		0 if invalid
*/


	$input_username = $_GET['username'];
	$input_password = $_GET['password'];

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

	
	$query = "SELECT IsTrainer, IsAdmin FROM db309amb4.User_Table WHERE Username = '$input_username'  AND Passwordd = '$input_password'";
	$result = $conn->query($query);

if ($result->num_rows > 0) 
{
    // output data of each row
   $row = $result->fetch_assoc();

//   echo "IsTrainer: " . $row["IsTrainer"]. " IsAdmin:  " . $row["IsAdmin"]. "<br>";
   if($row["IsTrainer"] == 1 and $row["IsAdmin"] == 0)
	{
	echo "1";
	}
   if($row["IsTrainer"] == 0 and $row["IsAdmin"] == 1)
	{
	echo "2";
	}
   if($row["IsTrainer"] == 0 and $row["IsAdmin"] == 0)
	{
	echo "3";
	}
   if($row["IsTrainer"] == 1 and $row["IsAdmin"] == 1)
	{
	echo "4";
	}
//} else
//{
//echo "0 Results;
}
else
{
	echo "0";
}
	mysqli_close($conn);
	?>
