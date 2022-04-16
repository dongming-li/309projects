<?php
	ini_set('display_errors', 1);
	include 'connectToDatabase.php';
	$conn = connectToDatabase();
	$playerid = $_POST['playerid'];
	

	$game = array();
	$response = array();
	
	$sql = "SELECT (SELECT course_name FROM db309sbc4.courses WHERE course_id = session_course_id) AS session_course_name, (SELECT course_city FROM db309sbc4.courses WHERE course_id = session_course_id) AS session_course_city, (SELECT course_state FROM db309sbc4.courses WHERE course_id = session_course_id) AS session_course_state, session_total_score, session_created_date FROM db309sbc4.sessions WHERE session_player_id = '$playerid'"; 
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
   
		while($row = $result->fetch_assoc())
		{
			$game["coursename"] = $row["session_course_name"];
			$game["coursecity"] = $row["session_course_city"];
			$game["coursestate"] = $row["session_course_state"];
			$game["totalscore"] = $row["session_total_score"];
			$game["sessiondate"] = $row["session_created_date"];
			$response[] = $game;
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