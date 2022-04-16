<?php
session_start();
     ?>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>

<?php
    
    

    
    
/**
* write the number of nights for the current game
* with specific $gameID
*
* @param  int $gameID
* @param  mysqli $conn
* @return int|string
*/    
function writeNight($gameID, $conn){
    $sql = "SELECT * from GameState where GameID =".$gameID;
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $night = $row["Night"];
        return $night;
    }
    return "no such game exists";
}
    
/**
* write the turn of players for the current game
* with specific $gameID
*
* @param  int $gameID
* @param  mysqli $conn
* @return string
*/         
function writeTurn($gameID, $conn){
    $sql = "SELECT * from GameState where GameID =".$gameID;
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $turn = $row["Turn"];
        return $turn;
    }
    return "no such game exists";
}

function getGameID($username, $conn){
    $sql = "select GameID from Game where Username = '".$username."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        return $row["GameID"];
    }else{
        return "wrong match";
    }
}
    
    

    
    
/**
* The database server name.
*
* @var string
*/   
$servername = "mysql.cs.iastate.edu";
/**
* The database username to log into the databse.
*
* @var string
*/ 
$username = "dbu309vcb2";
/**
* The database password to log into the databse.
*
* @var string
*/ 
$password = "QRZD1qw#";
/**
* The database name.
*
* @var string
*/ 
$DBname = "db309vcb2";

$conn = mysqli_connect($servername, $username, $password, $DBname);
if(!$conn){
    die("connection failed:".mysqli_connect_error());
}
/**
* type to classify the action through ajax.
*
* @var string
*/ 
$type = $_GET['t'];
/**
* gameID if it exists.
*
* @var int
*/ 
$name = $_SESSION["username"];
$gameID = getGameID($name, $conn);
if($type == "night"){
    echo writeNight($gameID, $conn);
}else if($type == "turn"){
    echo writeTurn($gameID, $conn);
}

    
    
    
    
    
    
    
    
    
mysqli_close($conn);

?>

</body>
</html>