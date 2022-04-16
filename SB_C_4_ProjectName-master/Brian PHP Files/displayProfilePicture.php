<?php
	ini_set('display_errors', 1);
	include 'connectToDatabase.php';
	$conn = connectToDatabase();
	$playerid = $_POST['playerid'];
	
	$response = array();
	
	$sql = "SELECT photo_url FROM db309sbc4.photos WHERE (photo_author_id = '$playerid') AND (photo_type_id = '1')"; 

	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		$response["photo"] = $result["photo_url"];
		$response["success"] = true;
	}
	else 
	{
		$response["success"] = false;
		$response["error_msg"] = "Invalid username or password";
	}
	
	echo json_encode($response);
	$conn->close();
?>