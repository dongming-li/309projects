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
* write the achievement table by First Game playing 
* as Jack the Ripper.
*
* @param  mysqli $conn
* @return string
*/
function writeFJ($conn){
    $sql = "SELECT Username from Achievement ORDER by FirstJack DESC LIMIT 10";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        echo "<table><tr><th>First Jack</th></tr>";
    // output data of each row
        while($row = mysqli_fetch_assoc($result)){
            echo "<tr><td>".$row["Username"]."</td></tr>";
        }
        echo "</table>";
    }else{
        echo "No user exists";
    }
}
    
/**
* write the achievement table by First Game playing 
* as the Inspector.
*
* @param  mysqli $conn
* @return string
*/    
function writeFI($conn){
    $sql = "SELECT Username from Achievement ORDER by FirstInspector DESC LIMIT 10";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        echo "<table><tr><th>First Inspector</th></tr>";
    // output data of each row
        while($row = mysqli_fetch_assoc($result)){
            echo "<tr><td>".$row["Username"]."</td></tr>";
        }
        echo "</table>";
    }else{
        echo "No user exists";
    }
}
/**
* write the achievement table by First Win playing 
* as Jack the Ripper.
*
* @param  mysqli $conn
* @return string
*/
function writeFWJ($conn){
    $sql = "SELECT Username from Achievement ORDER by FirstWinJ DESC LIMIT 10";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        echo "<table><tr><th>First Win Playing Jack</th></tr>";
    // output data of each row
        while($row = mysqli_fetch_assoc($result)){
            echo "<tr><td>".$row["Username"]."</td></tr>";
        }
        echo "</table>";
    }else{
        echo "No user exists";
    }
}
/**
* write the achievement table by First Win playing 
* as the Inspector.
*
* @param  mysqli $conn
* @return string
*/    
function writeFWinI($conn){
    $sql = "SELECT Username from Achievement ORDER by FirstWinI DESC LIMIT 10";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        echo "<table><tr><th>First Win Playing Inspector</th></tr>";
    // output data of each row
        while($row = mysqli_fetch_assoc($result)){
            echo "<tr><td>".$row["Username"]."</td></tr>";
        }
        echo "</table>";
    }else{
        echo "No user exists";
    }
}
/**
* check whether this is the first game for this user
*
* @param  mysqli $conn
* @return string
*/
function checkFirstGame($conn){
    $user = $_SESSION["username"];
    $sql = "SELECT * from Achievement where Username = '".$user."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $firstJack = $row["FirstJack"];
        $firstInspector = $row["FirstInspector"];
        if($firstJack == 1 || $firstInspector == 1){
            return "true";
        }else{
            return "false";
        }
    }else{
        echo "No user exists";
    }
}
    
/**
* check whether this is the first win as 
* Jack the Ripper for this user
*
* @param  mysqli $conn
* @return string
*/    
function checkFirstWinJ($conn){
    $user = $_SESSION["username"];
    $sql = "SELECT * from Achievement where Username = '".$user."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $firstWinJack = $row["FirstWinJ"];
        if($firstWinJack == 1){
            return "true";
        }else{
            return "false";
        }
    }else{
        echo "No user exists";
    }
}
/**
* check whether this is the first win as 
* the Inspector for this user
*
* @param  mysqli $conn
* @return string
*/     
function checkFirstWinI($conn){
    $user = $_SESSION["username"];
    $sql = "SELECT * from Achievement where Username = '".$user."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $firstWinInspector= $row["FirstWinI"];
        if($firstWinInspector == 1){
            return "true";
        }else{
            return "false";
        }
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
    
if($type=="FirstGame"){
    echo checkFirstGame($conn);
}else if($type == "FirstWinJ"){
    echo checkFirstWinJ($conn);
}else if($type == "FirstWinI"){
    echo checkFirstWinI($conn);
}


    
    
    
    
    
    
    
    
    
    
mysqli_close($conn);

?>

</body>
</html>