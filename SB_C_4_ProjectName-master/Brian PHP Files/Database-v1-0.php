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

$sql = "CREATE TABLE db309sbc4.players (
  player_id INT NOT NULL AUTO_INCREMENT,
  player_name VARCHAR(16) NOT NULL,
  player_password VARCHAR(16) NOT NULL,
  player_email VARCHAR(50) NULL,
  player_is_certified TINYINT(1) NULL,
  player_created_date DATE NOT NULL,
  PRIMARY KEY (player_id));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.courses (
  course_id INT NOT NULL AUTO_INCREMENT,
  course_name VARCHAR(30) NOT NULL,
  course_city VARCHAR(20) NOT NULL,
  course_state VARCHAR(15) NOT NULL,
  course_number_of_holes INT NOT NULL,
  course_total_par INT NOT NULL,
  course_created_date DATE NOT NULL,
  PRIMARY KEY (course_id));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.groups (
  group_id INT NOT NULL AUTO_INCREMENT,
  group_player_one_id INT NOT NULL,
  group_player_two_id INT NOT NULL,
  group_player_three_id INT NULL,
  group_player_four_id INT NULL,
  group_created_date DATE NOT NULL,
  PRIMARY KEY (group_id),
  INDEX FK_groups_players_idx (group_player_one_id ASC),
  INDEX FK_groups_players_two_idx (group_player_two_id ASC),
  INDEX FK_groups_players_three_idx (group_player_three_id ASC),
  INDEX FK_groups_players_four_idx (group_player_four_id ASC),
  CONSTRAINT FK_groups_players
    FOREIGN KEY (group_player_one_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_groups_players_two
    FOREIGN KEY (group_player_two_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_groups_players_three
    FOREIGN KEY (group_player_three_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_groups_players_four
    FOREIGN KEY (group_player_four_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.holes (
  hole_id INT NOT NULL AUTO_INCREMENT,
  hole_course_id INT NOT NULL,
  hole_number INT NOT NULL,
  hole_distance_one INT NOT NULL,
  hole_distance_two INT NULL,
  hole_par INT NOT NULL,
  hole_created_date DATE NULL,
  PRIMARY KEY (hole_id),
  INDEX FK_holes_courses_idx (hole_course_id ASC),
  CONSTRAINT FK_holes_courses
    FOREIGN KEY (hole_course_id)
    REFERENCES db309sbc4.courses (course_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.sessions (
  session_id INT NOT NULL AUTO_INCREMENT,
  session_course_id INT NOT NULL,
  session_group_id INT NOT NULL,
  session_player_id INT NOT NULL,
  session_total_score INT NOT NULL,
  hole_one_score INT NULL,
  hole_two_score INT NULL,
  hole_three_score INT NULL,
  hole_four_score INT NULL,
  hole_five_score INT NULL,
  hole_six_score INT NULL,
  hole_seven_score INT NULL,
  hole_eight_score INT NULL,
  hole_nine_score INT NULL,
  hole_ten_score INT NULL,
  hole_eleven_score INT NULL,
  hole_twelve_score INT NULL,
  hole_thirteen_score INT NULL,
  hole_fourteen_score INT NULL,
  hole_fifteen_score INT NULL,
  hole_sixteen_score INT NULL,
  hole_seventeen_score INT NULL,
  hole_eighteen_score INT NULL,
  session_created_date DATE NULL,
  PRIMARY KEY (session_id),
  INDEX FK_sessions_course_idx (session_course_id ASC),
  INDEX FK_sessions_group_idx (session_group_id ASC),
  INDEX FK_sessions_players_idx (session_player_id ASC),
  CONSTRAINT FK_sessions_courses
    FOREIGN KEY (session_course_id)
    REFERENCES db309sbc4.courses (course_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_sessions_groups
    FOREIGN KEY (session_group_id)
    REFERENCES db309sbc4.groups (group_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_sessions_players
    FOREIGN KEY (session_player_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.friendships (
  friendship_id INT NOT NULL AUTO_INCREMENT,
  friend_one_id INT NOT NULL,
  friend_two_id INT NOT NULL,
  friendship_created_date DATE NOT NULL,
  PRIMARY KEY (friendship_id),
  INDEX FK_friendships_players_idx (friend_one_id ASC),
  INDEX FK_friendships_players2_idx (friend_two_id ASC),
  CONSTRAINT FK_friendships_players
    FOREIGN KEY (friend_one_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_friendships_players2
    FOREIGN KEY (friend_two_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.photo_types (
  photo_type_id INT NOT NULL AUTO_INCREMENT,
  photo_type_description VARCHAR(50) NOT NULL,
  photo_type_created_date DATE NOT NULL,
  PRIMARY KEY (photo_type_id));";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.photos (
  photo_id INT NOT NULL AUTO_INCREMENT,
  photo_type_id INT NOT NULL,
  photo_author_id INT NOT NULL,
  photo_description VARCHAR(50) NULL,
  photo_url VARCHAR(255) NOT NULL,
  photo_archived_date DATE NULL,
  photo_created_date DATE NOT NULL,
  PRIMARY KEY (photo_id),
  INDEX FK_photos_photo_types_idx (photo_type_id ASC),
  INDEX FK_photos_players_idx (photo_author_id ASC),
  CONSTRAINT FK_photos_photo_types
    FOREIGN KEY (photo_type_id)
    REFERENCES db309sbc4.photo_types (photo_type_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_photos_players
    FOREIGN KEY (photo_author_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.discs (
  disc_id INT NOT NULL AUTO_INCREMENT,
  disc_player_id INT NOT NULL,
  disc_photo_id INT NULL,
  disc_brand VARCHAR(20) NOT NULL,
  disc_name VARCHAR(20) NOT NULL,
  disc_speed INT NULL,
  disc_glide INT NULL,
  disc_turn INT NULL,
  disc_fade INT NULL,
  disc_created_date DATE NOT NULL,
  PRIMARY KEY (disc_id),
  INDEX FK_discs_players_idx (disc_player_id ASC),
  INDEX FK_discs_photos_idx (disc_photo_id ASC),
  CONSTRAINT FK_discs_players
    FOREIGN KEY (disc_player_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_discs_photos
    FOREIGN KEY (disc_photo_id)
    REFERENCES db309sbc4.photos (photo_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE db309sbc4.tips (
  tip_id INT NOT NULL AUTO_INCREMENT,
  tip_hole_id INT NOT NULL,
  tip_author_id INT NOT NULL,
  tip_message VARCHAR(100) NOT NULL,
  tip_vote_value INT NOT NULL,
  tip_created_date DATE NOT NULL,
  PRIMARY KEY (tip_id),
  INDEX FK_tips_holes_idx (tip_hole_id ASC),
  INDEX FK_tips_players_idx (tip_author_id ASC),
  CONSTRAINT FK_tips_holes
    FOREIGN KEY (tip_hole_id)
    REFERENCES db309sbc4.holes (hole_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_tips_players
    FOREIGN KEY (tip_author_id)
    REFERENCES db309sbc4.players (player_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

if (mysqli_query($conn, $sql))
{
	echo "New record inserted successfully. \n";
}
else
{
	echo "Error: " . $sql . "br" . mysqli_error($conn);
}

$sql = "CREATE TABLE `db309sbc4`.`events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_author_id` INT NOT NULL,
  `event_date` DATE NULL,
  `event_start_time` VARCHAR(10) NULL,
  `event_name` VARCHAR(45) NULL,
  `event_description` VARCHAR(255) GENERATED ALWAYS AS () VIRTUAL,
  `event_created_date` DATE NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_events_players_idx` (`event_author_id` ASC),
  CONSTRAINT `fk_events_players`
    FOREIGN KEY (`event_author_id`)
    REFERENCES `db309sbc4`.`players` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);";

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
