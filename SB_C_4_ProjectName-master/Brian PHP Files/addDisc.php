<?php
	ini_set('display_errors', 1);
	include 'connectToDatabase.php';
	$conn = connectToDatabase();
	$playerid = $_POST['playerid'];
	$brand = $_POST['discbrand'];
	$model = $_POST['discmodel'];
	$speed = $_POST['discspeed'];
	$glide = $_POST['discglide'];
	$turn = $_POST['discturn'];
	$fade = $_POST['discfade'];
	
	$sql = "INSERT INTO db309sbc4.discs (disc_player_id, disc_brand, disc_name, disc_speed, disc_glide, disc_turn, disc_fade, disc_created_date) VALUES ('$playerid', '$brand', '$model', '$speed', '$glide', '$turn', '$fade', DATE(NOW()))"; 

	if ($conn->query($sql) === TRUE) {
		echo "New record created succesfully!";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

	$conn->close();
?>