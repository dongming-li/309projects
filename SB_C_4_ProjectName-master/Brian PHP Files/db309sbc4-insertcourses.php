<?php
$servername = 'mysql.cs.iastate.edu';
$username = 'dbu309sbc4';
$password = 'sXrWaR#@';
$dbname = 'db309sbc4';

$conn = mysqli_connect($servername, $username, $password, $dbname);

if (!$conn)
{
	die("Connection failed: " . mysqli_connect_error());
}

$sql = "INSERT INTO db309sbc4.courses
	(course_name, course_city, course_state, course_number_of_holes, course_total_par, course_created_date)
    VALUES
    ('Carroll Marty DGC', 'Ames', 'Iowa', 18, 55, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.courses
	(course_name, course_city, course_state, course_number_of_holes, course_total_par, course_created_date)
    VALUES
    ('Stable Run DCG', 'Ames', 'Iowa', 18, 54, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

mysqli_close($conn);
?>