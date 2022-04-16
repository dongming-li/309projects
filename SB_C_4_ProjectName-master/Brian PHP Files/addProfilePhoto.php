<?php
	ini_set('display_errors', 1);
	include 'connectToDatabase.php';
	$conn = connectToDatabase();
	$playerid = $_POST['playerid'];
	$encodedimage = $_POST['image_encoded'];
	
	$sql = "INSERT INTO db309sbc4.photos (photo_type_id, photo_author_id, photo_url, photo_created_date) VALUES ('1', '$playerid', '$encodedimage', DATE(NOW()))"; 

	if ($conn->query($sql) === TRUE) {
		echo "New record created succesfully!";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

	$conn->close();
?>