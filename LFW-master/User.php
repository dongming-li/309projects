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
* check whther there is a user with the same username.
*
* @param  string $username
* @param  mysqli $conn
* @return string
*/ 
function hasUser($username, $conn){
    $sql = "SELECT * from User where Username = "."'".$username."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        return "true";
    }else{
        return "false";
    }
}
/**
* select the corresponding user record in the specific $table.
*
* @param  string $table
* @param  string $username
* @param  mysqli $conn
* @return string
*/ 
function select($table, $username,$conn){
    $sql = "SELECT * from ".$table." where Username = "."'".$username."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        return $row["Password"];
    }else{
        return "wrong match";
    }
}
    
/**
* delete the corresponding user record in the specific $table.
*
* @param  string $table
* @param  string $username
* @param  mysqli $conn
* @return string
*/ 
function delete($table, $username,$conn){
    $todelete = "'".$username."'";
    $sql = "DELETE from ".$table." where Username = ".$todelete;
    if(mysqli_query($conn, $sql)){
        echo "successfully deleteed"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }   
}
    
/**
* add the corresponding user record in the user table.
*
* @param  string $username
* @param  string $password
* @param  string $role
* @param  mysqli $conn
* @return string
*/ 
function addUser($username,$password,$role, $conn){
    $sql = "INSERT INTO User (Username, Password, Role, Win, Lose)
        VALUES ('".$username."', '".$password."', '".$role."', 0, 0)";
    $sqlach = "INSERT INTO Achievement (Username, FirstJack, FirstInspector, FirstWinJ, FirstWinI)
        VALUES ('".$username."', 0, 0, 0, 0)";
    if(mysqli_query($conn, $sql)){
        mysqli_query($conn, $sqlach);
        echo "account successful created"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }  
}

/**
* update number of wins for the user in the user table.
*
* @param  string $username
* @param  mysqli $conn
* @return string
*/ 
function updateWin($username, $conn){
    $sql = "UPDATE User SET Win = Win + 1 where Username = '".$username."'";
    if(mysqli_query($conn, $sql)){
        echo "update successfully"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }
}
/**
* update number of loses for the user in the user table.
*
* @param  string $username
* @param  mysqli $conn
* @return string
*/    
function updateLose($username, $conn){
    $sql = "UPDATE User SET Lose = Lose + 1 where Username = '".$username."'";
    if(mysqli_query($conn, $sql)){
        echo "update successfully"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }
}
/**
* update specific attributs for the user in the user table.
*
* @param  string $username
* @param  string $attri
* @param  string $attrinew
* @param  mysqli $conn
* @return string
*/
function updateUser($username, $attri, $attrinew, $conn){
    $sql = "UPDATE User SET ".$attri." = '".$attrinew."' where Username = '".
        $username."'";
    if(mysqli_query($conn, $sql)){
        echo "update successfully"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }
}
/**
* update achievements for the user in the Acheveiment table.
*
* @param  string $username
* @param  string $attri
* @param  mysqli $conn
* @return string
*/  
function updateAch($username, $attri, $conn){
    $sql = "UPDATE Achievement SET ".$attri."= 1 where Username = '".
        $username."' and ".$attri. "= 0";
    if(mysqli_query($conn, $sql)){
        echo "update successfully"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }
}
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
function creatLobby($username, $lobbyID, $conn){
    $sql = "INSERT INTO Lobby (Username, lobbyID, Role, Status)
        VALUES ('".$username."',".$lobbyID.", 'Host', 'Pending')";
    if(mysqli_query($conn, $sql)){
        echo "Create Lobby successfully"."<br>";
    }else{
        echo "Error occured:" . mysqli_error($conn)."<br>";
    }
}
/**
* write win table in the database.
*
* @param  mysqli $conn
* @return string
*/  
function writeWin($conn){
    $sql = "SELECT Username, Win, Lose from User ORDER BY Win DESC";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        echo "<table><tr><th>Username</th><th>Win</th><th>Win Rate</th></tr>";
    // output data of each row
        while($row = mysqli_fetch_assoc($result)){
            $total = $row["Win"] + $row["Lose"];
            if($total === 0){
                $wrate = 0;
            }{
                $wrate = ($row["Win"] / $total) * 100;
            }
            
            echo "<tr><td>".$row["Username"]."</td><td>".$row["Win"].
                "</td><td>".$wrate."%</td></tr>";
        }
        echo "</table>";
    }else{
        echo "No user exists";
    }
}
/**
* write lose table in the database.
*
* @param  mysqli $conn
* @return string
*/      
function writeLose($conn){
    $sql = "SELECT Username,Win, Lose from User ORDER BY Lose DESC";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        echo "<table><tr><th>Username</th><th>Lose</th><th>Lose Rate</th></tr>";
    // output data of each row
        while($row = mysqli_fetch_assoc($result)){
            $total = $row["Win"] + $row["Lose"];
            if($total === 0){
                $lrate = 0;
            }{
                $lrate = ($row["Lose"] / $total) * 100;
            }
            
            echo "<tr><td>".$row["Username"]."</td><td>".$row["Lose"].
                "</td><td>".$lrate."%</td></tr>";
        }
        echo "</table>";
    }else{
        echo "No user exists";
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
* username that the user try to log in.
*
* @var string
*/ 
$name = $_GET['u'];
/**
* password that the user try to log in.
*
* @var string
*/ 
$password = $_GET['p'];
if($type == "check"){
    if(select("User", $name, $conn)===$password){
        echo "login...";
    }else{
        echo "wrong username or password";
    }
}else if($type == "create"){
    if(hasUser($name, $conn) == "true"){
        echo "username exists";
    }else{
        addUser($name, $password, "player", $conn);
    }
    
}else if($type == "update"){
    $newpassword = $_GET['n'];
    if(strlen($newpassword) == 0){
        echo "invaild new password";
    }
    else if(hasUser($name, $conn) == "true" && select("User", $name, $conn)===$password){
        updateUser($name, "Password",$newpassword, $conn);
    }else{
        echo "no such user exists"; 
    }
}else if($type == "delete"){
    if(hasUser($name, $conn) == "true" && select("User", $name, $conn)===$password){
        delete("User",$name, $conn);
    }else{
        echo "no such user exists"; 
    }
}else if($type == "updateWin"){
    updateWin($name, $conn);
}else if($type == "updateLose"){
    updateLose($name, $conn);
}else if($type == "showStat"){
    echo writeWin($conn).writeLose($conn);
}

    
    
    
    
    
    
    
    
    
    
mysqli_close($conn);

?>

</body>
</html>