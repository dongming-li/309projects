<?php
	ini_set('display_errors', 1);
	include 'connectToDatabase.php';
	$conn = connectToDatabase();
	$name = $_POST['username'];
	$password = $_POST['password'];

	$response = array();
	
	$sql = "SELECT player_id, player_name, player_email FROM players WHERE player_name = '$name' AND player_password = '$password'"; 
	$result =$conn->query($sql);
	if ($result->num_rows > 0) 
	{
   
		while($row = $result->fetch_assoc())
		{
			$response["playerid"] = $row["player_id"];
			$response["username"] = $row["player_name"];
			$response["email"] = $row["player_email"];
			$response["success"] = true;
		}
	}
	else 
	{
		$response["success"] = false;
		$response["error_msg"] = "Invalid username or password";
	}
	
	echo json_encode($response);
	$conn->close();
	
?>