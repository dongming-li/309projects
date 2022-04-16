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
* get Role played by the current user.
*
* @param  mysqli $conn
* @return string
*/
function getRole($conn){
    $user = $_SESSION["username"];
    $sql = "SELECT * from Game where Username='".$user."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        return $row["Role"];
    }else{
        return "no such game exists";
    }
}
/**
* check whether the current user is playing 
* Jack the Ripper.
*
* @param  mysqli $conn
* @return string
*/    
function isJack($conn){
    $role = getRole($conn);
    if(strcmp($role, "jack")==0){
        return "true";
    }else{
        return "false";
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
if($type == "isJack"){
    echo isJack($conn);
}
    
mysqli_close($conn);
?>    
    
    
    
    
    
    
    
    
    
    
    
    
    </body>
</html>