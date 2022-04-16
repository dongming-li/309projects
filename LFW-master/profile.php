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
* output the personal profile table for current user
*
* @param  string $type
* @param  mysqli $conn
* @return string
*/ 
function getProfile($type,$conn){
    $result;
    $user = $_SESSION["username"];
    $sql = "SELECT Username,Win, Lose from User where Username = '".$user."'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_assoc($result);
        $win = $row["Win"];
        $lose = $row["Lose"];
        $sqlach = "SELECT * from Achievement where Username = '".$user."'";
        $resultach = mysqli_query($conn, $sqlach);
        if(mysqli_num_rows($resultach) >0){
            $rowach = mysqli_fetch_assoc($resultach);
            $completed = "";
            $pending = "";
            if($rowach["FirstJack"] == 0){
                $pending = $pending."First time playing as Jack the Ripper <br>"; 
            }else{
                $completed = $completed."First time playing as Jack the Ripper <br>";
            }
            if($rowach["FirstInspector"] == 0){
               $pending = $pending."First time playing as Inspector <br>"; 
            }else{
                $completed = $completed."First time playing as Inspector <br>";
            }
            if($rowach["FirstWinJ"] == 0){
                $pending = $pending."First win earned playing Jack the Ripper <br>"; 
            }else{
               $completed = $completed."First win earned playing Jack the Ripper <br>";
            }
            if($rowach["FirstWinI"] == 0){
                $pending = $pending."First win earned playing Inspector <br>"; 
            }else{
                $completed = $completed."First win earned playing Inspector <br>";
            }
            if($type == "username"){
                $result ="Username:".$user;
            }else if($type == "wins"){
                $result = "Win:".$win;
            }else if($type == "losses"){
                $result = "Losses".$lose;
            }else if($type == "achievements"){
                $result = "Acheveiments:<br>"."Completed:<br>".$completed."Pending:<br>".$pending;
            }
            
            return $result;
            
        }else{
            echo "No user exists";
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
echo getProfile($type,$conn);    

    
mysqli_close($conn);
?>    
    
    
    
    
    
    
    
    
    
    
    
    
    </body>
</html>