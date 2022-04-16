<?php


$servername = 'mysql.cs.iastate.edu'; 
$username = 'dbu309amb5'; 
$password = 'e3#zBQGf'; 
$dbname = 'db309amb5';



$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}




// All the fields for Businesses table.
//ID, Username, BusinessTitle, Phone, Email, Password, Address, City

$ID = null;
$UserName = null;
$BusinessTitle = null;
$Phone = null;
$Email = null;
$Password = null;
$Address = null;
$City = null;


$function = null;

if(isset($_REQUEST["function"])){
	$function = ''.$_REQUEST["function"];
}





if(isset($_REQUEST["userName"])){
	$UserName = ''.$_REQUEST["userName"];
}
if(isset($_REQUEST["businessTitle"])){
	$BusinessTitle = ''.$_REQUEST["businessTitle"];
}
if(isset($_REQUEST["phone"])){
	$Phone = ''.$_REQUEST["phone"];
}
if(isset($_REQUEST["email"])){
	$Email = ''.$_REQUEST["email"];
}
if(isset($_REQUEST["password"])){
	$Password = ''.$_REQUEST["password"];
}
if(isset($_REQUEST["address"])){
	$Address = ''.$_REQUEST["address"];
}
if(isset($_REQUEST["city"])){
	$City = ''.$_REQUEST["city"];
}
if(isset($_REQUEST["businessID"])){
	$ID = ''.$_REQUEST["businessID"];
}









if($function === 'insertBusiness')
{
	insertBusiness();
}
if($function === 'checkUserName')
{
	checkUserName();
}
if($function === 'checkPassword')
{
	checkPassword();
}

/** This function inserts a new Business user into the Businesses table **/
function insertBusiness(){
	global $conn;

	global $ID;
	global $UserName;
	global $BusinessTitle;
	global $Phone;
	global $Email;
	global $Password;
	global $Address;
	global $City;


	$dupForUserName = " 
						SELECT Username
						FROM Businesses
						Where Username = '$UserName'";
	$dupUserName = $conn->query($dupForUserName);
	if($dupUserName->num_rows < 1)
	{
		$dupForEmail = " 
						SELECT Email
						FROM Businesses
						Where Email = '$Email'";
		$dupEmail = $conn->query($dupForEmail);
		if($dupEmail->num_rows < 1)
		{
			$sql = "
			INSERT INTO Businesses
			VALUES(NULL,'$UserName', '$BusinessTitle', '$Phone', '$Email', '$Password', '$Address', '$City')
			";
			$result = $conn->query($sql) or die ("Selection Query Unsuccessful");

			//echo "$result";
			if($result == 1)
			{
				$logindata = "
				INSERT INTO LoginData
				VALUES('$UserName', '$Password', 'b')";

				$insertedIntoLoginData = $conn->query($logindata) or die ("Selection Query Unsuccessful for LgoinData");

				echo "$insertedIntoLoginData";
			}
			else
			{
				echo "$result was not 1";
			}
		}
		else
		{
			echo "$Email already in use.";
		}
	}

	else
	{
		echo "$UserName already in use.";
	}


}

/** This function checks to see if the given username is in the Businesses table, if it is, it returns that particular users ID **/
function checkUserName(){
	global $conn;
	global $UserName;

	$sql = "
			SELECT ID 
			FROM Businesses
			WHERE Username = '$UserName'
			";
	$result = $conn->query($sql) or die("false");

	if ($result->num_rows > 0) 
	{
		$row = $result->fetch_assoc();
		echo $row["ID"] . "\n";
	}

	else
	{
		echo "false";
	}
}

/** This function checks to see if the given password is the correct one for this particular user with that ID **/
function checkPassword(){
	global $conn;
	global $ID;
	global $Password;

	$sql = "
			SELECT Password
			FROM Businesses
			WHERE ID = '$ID' and Password = '$Password'
			";
	$result = $conn->query($sql) or die("false");

	
	if ($result->num_rows > 0) 
	{
		echo "$Password";
	}

	else
	{
		echo "false";
	}

}

$conn->close();
?>