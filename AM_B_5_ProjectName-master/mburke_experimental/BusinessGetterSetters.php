
Business functions

<?php
$servername = "mysql.cs.iastate.edu";
$username = "dbu309amb5";
$password = "e3#zBQGf";
$dbname = "db309amb5";

//Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

//Check connection
if ($conn->connect_error) {
        die("connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
/*
$sql = "INSERT INTO Businesses (ID, Username, BusinessTitle, Phone, Email, Password, Address, City)
VALUES ('1', 'outlaws', 'Outlaws', '5555555', 'outlaws@bar.com', 'secretpwd', '123 Welch Aveneue', 'Ames')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
*/


function addBusiness($id, $username, $businessTitle, $phone, $email, $password, $address, $city) {

	global $conn;
	
	$sql = "INSERT INTO Businesses (ID, Username, BusinessTitle, Phone, Email, Password, Address, City)
	VALUES ('$id', '$username', '$businessTitle', '$phone', '$email', '$password', '$address', '$city')";

	if ($conn->query($sql) === TRUE) {
	    echo "New record created successfully";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}

}

//addBusiness('2', 'mickys', 'Mickys', '5555555', 'mickys@bar.com', 'pwd2', '456 Welch Ave', 'Ames');

function getBusinessID($businessTitle) {
	global $conn;

	$sql = "SELECT ID 
		FROM Businesses 
		WHERE BusinessTitle = '$businessTitle'";

	$result = $conn->query($sql);



	if ($result->num_rows > 0) {
		
	    echo "Record retrieved successfully";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}
}

getBusinessID('Mickys');



$conn->close();


?>