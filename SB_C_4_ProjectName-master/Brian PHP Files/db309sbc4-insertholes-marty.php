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

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 1, 334, 377, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 2, 170, 239, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 3, 200, 260, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 4, 184, NULL, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 5, 146, 185, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 6, 325, 425, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 7, 279, 315, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 8, 245, 292, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 9, 225, 295, 4, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 10, 126, 150, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 11, 276, 313, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 12, 185, 238, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 13, 233, 243, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 14, 290, 375, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 15, 156, 194, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 16, 226, 299, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 17, 169, 242, 3, DATE(NOW()));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "INSERT INTO db309sbc4.holes
	(hole_course_id, hole_number, hole_distance_one, hole_distance_two, hole_par, hole_created_date)
    VALUES
    (1, 18, 165, 206, 3, DATE(NOW()));";

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