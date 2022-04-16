<?php
/*
	Description: Takes the Username, Password, Name, and email of a new user to be registered.
	URL: http://proj-309-am-b-4.cs.iastate.edu/Register_User.php?username=<USERNAME>&password=<PASSWORD>&name=<NAME>&email=<EMAIL>
	Output: N/A

*/
	$input_username = $_GET['username'];
	$input_password = $_GET['password'];
	$input_name = $_GET['name'];
	$input_email = $_GET['email'];



	$servername = 'mysql.cs.iastate.edu';
	$username = 'dbu309amb4';
	$password = 'ZtWqGRXX';
	$dbname = 'db309amb4';
	$conn = mysqli_connect($servername, $username, $password, $dbname);
	
	if (!$conn)
	{
		die("Connection failed: " . mysqli_connect_error());
	}


	
	$query = "INSERT INTO `db309amb4`.`User_Table` (`User_ID`,  									`First_Name`,                                      `Last_Name`,                                      `Email_Address`,                                      `Height`,                                      `Weight`,                                      `Age`,                                      `BirthDate`,                                      `Loss_Goal`,                                      `Workout_Plan_ID`,                                      `Assigned_Trainer_ID`,                                      `IsTrainer`,                                      `IsAdmin`,                                      `Username`,                                      `Passwordd`)  						VALUES ('475',  								'$input_name',                                 '$input_name',                                  '$input_email',                                  '70',                                  '240',                                  '22',                                  '1995-06-23',                                  '10',                                  '0',                                  '0',                                  '0',                                  '0',                                  '$input_username',                                  '$input_password')";
	echo $query;
	$result = $conn->query($query);

	mysqli_close($conn);
	?>

