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

$sql = "ALTER TABLE db309sbc4.players 
	ADD COLUMN player_photo_id INT NULL AFTER player_email,
	ADD FOREIGN KEY fk_players_photos(player_photo_id)
	REFERENCES db309sbc4.photos(photo_id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION;";

if (mysqli_query($conn, $sql))
{
	echo "'player_photo_id' column successfully added to the db309sbc4.players table. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

mysqli_close($conn);
?>