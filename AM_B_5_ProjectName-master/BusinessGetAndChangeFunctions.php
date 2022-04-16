<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';

$conn = mysqli_connect($servername, $username, $password, $dbname);

if ($conn->connect_error) {
        die("connection failed: " . $conn->connect_error);
}

function getBusinessUsername($id){
	global $conn;

	$sql = "
			SELECT Username
			FROM Businesses 
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Username"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeBusinessUsername($id, $newName){
	global $conn;

	$sql = "
			UPDATE Businesses
			SET Username = '$newName'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Username
			FROM Businesses
			WHERE ID = '$id' and Username = '$newName'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "Business' username is now $newName. \n";
	} 
	else 
	{
		echo "Username for business with id equal to $id did not get set to $newName \n";
	}
}


function getBusinessTitle($id){
	global $conn;

	$sql = "
			SELECT BusinessTitle
			FROM Businesses 
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["BusinessTitle"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeBusinessTitle($id, $newName){
	global $conn;

	$sql = "
			UPDATE Businesses
			SET BusinessTitle = '$newName'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT BusinessTitle
			FROM Businesses
			WHERE ID = '$id' and BusinessTitle = '$newName'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "Business' title is now $newName. \n";
	} 
	else 
	{
		echo "BusinessTitle for business with id equal to $id did not get set to $newName \n";
	}
}


function getBusinessPhone($id){
	global $conn;

	$sql = "
			SELECT Phone
			FROM Businesses 
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Phone"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeBusinessPhone($id, $newNumber){
	global $conn;

	$sql = "
			UPDATE Businesses
			SET Phone = '$newNumber'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Phone
			FROM Businesses
			WHERE ID = '$id' and Phone = '$newNumber'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "Business' phone number is now $newNumber. \n";
	} 
	else 
	{
		echo "Phone number for business with id equal to $id did not get set to $newNumber \n";
	}
}


function getBusinessEmail($id){
	global $conn;

	$sql = "
			SELECT Email
			FROM Businesses 
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Email"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeBusinessEmail($id, $newEmail){
	global $conn;

	$sql = "
			UPDATE Businesses
			SET Email = '$newEmail'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Email
			FROM Businesses
			WHERE ID = '$id' and Email = '$newEmail'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "Business' email is now $newEmail. \n";
	} 
	else 
	{
		echo "Email for business with id equal to $id did not get set to $newEmail \n";
	}
}


function getBusinessPassword($id){
	global $conn;

	$sql = "
			SELECT Password
			FROM Businesses 
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Password"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeBusinessPassword($id, $newPassword){
	global $conn;

	$sql = "
			UPDATE Businesses
			SET Password = '$newPassword'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Password
			FROM Businesses
			WHERE ID = '$id' and Password = '$newPassword'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "Business' password is now $newPassword. \n";
	} 
	else 
	{
		echo "Password for business with id equal to $id did not get set to $newPassword \n";
	}
}


function getBusinessAddress($id){
	global $conn;

	$sql = "
			SELECT Address 
			FROM Businesses 
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["Address"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeBusinessAddress($id, $newAddress){
	global $conn;

	$sql = "
			UPDATE Businesses
			SET Address = '$newAddress'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT Address
			FROM Businesses
			WHERE ID = '$id' and Address = '$newAddress'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "Business' address is now $newAddress. \n";
	} 
	else 
	{
		echo "Address for business with id equal to $id did not get set to $newAddress \n";
	}
}


function getBusinessCity($id){
	global $conn;

	$sql = "
			SELECT City 
			FROM Businesses 
			WHERE ID = '$id'
			";
	$result = $conn->query($sql) or die("Selection Query Failed");
	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["City"] . "\n";
	} 
	else 
	{
		echo "0 results \n";
	}
}


function changeBusinessCity($id, $newCity){
	global $conn;

	$sql = "
			UPDATE Businesses
			SET City = '$newCity'
			WHERE ID = '$id'
			";
	$result = $conn->query($sql);
	$sql = "
			SELECT City
			FROM Businesses
			WHERE ID = '$id' and City = '$newCity'
			";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) 
	{
		echo "Business' city is now $newCity. \n";
	} 
	else 
	{
		echo "City for business with id equal to $id did not get set to $newCity \n";
	}
}


$conn->close();

?>