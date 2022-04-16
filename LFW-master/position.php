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
* write the position for the inspectors.
*
* @param  int $gameID
* @param  mysqli $conn
* @return string
*/    
function writeInspectorPosition($gameID,$conn){
    $sql = "SELECT * from Game where GameID =".$gameID." and Role = 'inspector'";
    $result = mysqli_query($conn, $sql);
    $position = "Inspector: <br>";
    if(mysqli_num_rows($result) > 0){
        while($row = mysqli_fetch_assoc($result)){
            $position = $position.$row["Username"].":".$row["Position"]."<br>";
        }
    }
    return $position;
}
/**
* write the position for Jack the Ripper.
*
* @param  int $gameID
* @param  mysqli $conn
* @return string
*/       
function writeJackPosition($gameID,$conn){
    $sql = "SELECT * from Game where GameID =".$gameID." and Role = 'jack'";
    $result = mysqli_query($conn, $sql);
    $position = "Jack: <br>";
    if(mysqli_num_rows($result) > 0){
        while($row = mysqli_fetch_assoc($result)){
            $position = $position.$row["Username"].":".$row["Position"]."<br>";
        }
    }
    return $position;
}
/**
* write the position for all the players in the game specified
* by the $gameID.
*
* @param  int $gameID
* @param  mysqli $conn
* @return string
*/       
function writePosition($gameID, $conn){
    $user = $_SESSION["username"];
    $sql = "SELECT * from Game where Username ='".$user."' and GameID =".$gameID;
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $role = $row["Role"];
        if(strcmp($role, "jack") == 0){
            return writeJackPosition($gameID, $conn)."<br>".writeInspectorPosition($gameID, $conn);
        }else{
            return writeInspectorPosition($gameID, $conn);
        }
    }
    return writeInspectorPosition($gameID, $conn);
}
    
    
/**
* write the current move for the game with specific $gameID.
*
* @param  int $gameID
* @param  mysqli $conn
* @return string
*/       
function writeMove($gameID, $conn){
    $user = $_SESSION["username"];
    $sql = "SELECT * from Game where Username ='".$user."' and GameID =".$gameID;
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $Move = $row["Move"];
        return $Move;
    }
    return "no such user exists";
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
if($type == "position"){
    echo writePosition($gameID, $conn);
}else if($type == "move"){
    echo writeMove($gameID, $conn);
}

    
    
    
    
    
    
    
    
    
mysqli_close($conn);

?>

</body>
</html>