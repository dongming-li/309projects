<?php
	ini_set('display_errors', 1);
	include 'connectToDatabase.php';
	$conn = connectToDatabase();
	$playerid = $_POST['playerid'];

	$disc = array();
	$response = array();
	
	$sql = "SELECT disc_brand, disc_name, disc_speed, disc_glide, disc_turn, disc_fade FROM db309sbc4.discs WHERE disc_player_id = '$playerid'"; 
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
   
		while($row = $result->fetch_assoc())
		{
			$disc["discBrand"] = $row["disc_brand"];
			$disc["discName"] = $row["disc_name"];
			$disc["discSpeed"] = $row["disc_speed"];
			$disc["discGlide"] = $row["disc_glide"];
			$disc["discTurn"] = $row["disc_turn"];
			$disc["discFade"] = $row["disc_fade"];
			$response[] = $disc;
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