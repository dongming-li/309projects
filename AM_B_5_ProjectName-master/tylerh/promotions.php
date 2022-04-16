<?php
$servername = 'mysql.cs.iastate.edu';
$username = 'dbu309amb5';
$password = 'e3#zBQGf';
$dbname = 'db309amb5';
$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}

function getPromotionsAll(){
	global $conn;

	$sql = "
		SELECT PromoTitle, PromoDescription, PromoStartDate, PromoEndDate
		FROM Promotions
		";
	$result = $conn->query($sql) or die ("Selection Query Unsuccessful";)
	$tmpArr = array();
	while($row = $result->fetch_assoc()) {
			// echo "Title: " . $row["PromoTitle"]. " - Description: " . $row["PromoDescription"]. " - Start Date " . $row["PromoStartDate"]. " - End Date " . $row["PromoEndDate"]. "\n" . "<br>";
		$tmpArr[] = $row;
	}

		// $row = $result->fetch_assoc();
		// echo $row["FirstName"] . "\n";
	echo json_encode($tmpArr)
}

function getPromotionsBusiness($id){
	global $conn;

	$sql = "
		SELECT PromoTitle, PromoDescription, PromoStartDate, PromoEndDate
		FROM Promotions
		WHERE BusinessID = '$id'
		";
	$result = $conn->query($sql) or die ("Selection Query Unsuccessful";)
	$tmpArr = array();
	while($row = $result->fetch_assoc()) {
			// echo "Title: " . $row["PromoTitle"]. " - Description: " . $row["PromoDescription"]. " - Start Date " . $row["PromoStartDate"]. " - End Date " . $row["PromoEndDate"]. "\n" . "<br>";
		$tmpArr[] = $row;
	}

		// $row = $result->fetch_assoc();
		// echo $row["FirstName"] . "\n";
	echo json_encode($tmpArr)
}

function setPromotionsTitle($id, $newTitle){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoTitle = '$newTitle'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Title Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsTitle($id){
	global $conn;

	$sql = "
		SELECT PromoTitle
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Title Retrieval Unsuccessful")

	echo json_encode($result)
}

function setPromotionsDescription($id, $newDescription){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoDescription = '$newDescription'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Description Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsTitle($id){
	global $conn;

	$sql = "
		SELECT PromoDescription
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Title Retrieval Unsuccessful")

	echo json_encode($result)
}

function setPromotionsDate($id, $newDate){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoDate = '$newDate'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Date Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsDate($id){
	global $conn;

	$sql = "
		SELECT PromoDate
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Date Retrieval Unsuccessful")

	echo json_encode($result)
}

function setPromotionsDuration($id, $newDuration){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoDuration = '$newDuration'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Duration Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsDuration($id){
	global $conn;

	$sql = "
		SELECT PromoDuration
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Duration Retrieval Unsuccessful")

	echo json_encode($result)
}

function setPromotionsStartDate($id, $newStartDate){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoStartDate = '$newStartDate'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Start Date Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsStartDate($id){
	global $conn;

	$sql = "
		SELECT PromoStartDate
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Start Date Retrieval Unsuccessful")

	echo json_encode($result)
}

function setPromotionsEndDate($id, $newEndDate){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoEndDate = '$newEndDate'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("End Date Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsEndDate($id){
	global $conn;

	$sql = "
		SELECT PromoEndDate
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("End Date Retrieval Unsuccessful")

	echo json_encode($result)
}

function setPromotionsImage($id, $newImage){
	global $conn;

	$sql = "
		UPDATE Promotions
		SET PromoImage = '$newImage'
		WHERE PromoId = '$id'
		";
	$result = $conn->query($sql) or die ("Image Set Unsuccessful";)

	//echo json_encode($tmpArr)
}

function getPromotionsImage($id){
	global $conn;

	$sql = "
		SELECT PromoImage
		FROM Promotions
		WHERE PromoId = '$id'
		";

	$result = $conn->query($sql) or die ("Image Retrieval Unsuccessful")

	echo json_encode($result)
}
mysqli_close($conn);
?>