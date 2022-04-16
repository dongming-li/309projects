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
* add the user record into exisiting lobby table.
*
* @param  string $username
* @param  string $lobbyID
* @param  mysqli $conn
* @return string
*/
function joinLobby($username, $lobbyID, $conn){
    $sql = "INSERT INTO Lobby (Username, lobbyID, Role, Status)
        VALUES ('".$username."',".$lobbyID.", 'Player', 'Pending')";
    if(mysqli_query($conn, $sql)){
        echo "Join Lobby successfully"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }
}
/**
* delete the user record from exisiting lobby table.
*
* @param  string $username
* @param  mysqli $conn
* @return void
*/
function exitLobby($username, $conn){
    $table = "Lobby";
    delete($table, $username, $conn);
}
/**
* create a lobby record into the lobby table.
*
* @param  string $username
* @param  string $lobbyID
* @param  mysqli $conn
* @return string
*/    
function creatLobby($username, $Lobbyname, $GameID, $conn){
    $sql = "INSERT INTO Lobby (LobbyName, GameID, Private, Timer, ToolTip) VALUES ('".$Lobbyname."',".$GameID.", 0， 0， 0)";
    $sqlgame = "INSERT INTO Game (Username, Role, Move, Position, GameID, Color) VALUES ('".$username."', 'jack', 0,27,".$GameID.",'balck')";
    $sqlGS = "INSERT INTO GameState (GameID, Night, Turn, CarriageActivated, StreetLampActivated, Done) VALUES (".$GameID.", 0,'".$username."', 0, 0, 0)";
    if(mysqli_query($conn, $sql)){
        mysqli_query($conn, $sqlgame);
        mysqli_query($conn, $sqlGS);
        echo "Create Lobby successfully"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }
}

function getNextGameID($conn){
    $sql = "select GameID from GameState order by GameID DESC";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        return $row["GameID"] + 1;
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
* current username.
*
* @var string
*/ 
$name = $_SESSION["username"];
/**
* lobbyID if it exists.
*
* @var string
*/ 
$lobby = $_GET['l'];
$GameID = getNextGameID($conn);   
if($type=="create"){
    createLobby($name, $lobby,$GameID, $conn);
}else if($type == "join"){
    joinLobby($name, $lobby, $conn);
}else if($type == "exit"){
    exitLobby($name, $conn);
}


    
    
    
    
    
    
    
    
    
    
mysqli_close($conn);

?>

</body>
</html>