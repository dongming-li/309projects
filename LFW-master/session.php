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
* set up session that stores $username, $password
* and log in time for the user
*
* @param  string $username
* @param  string $password
* @return void
*/    
function setsession($username, $password){
    $_SESSION["username"] = $username;
    $_SESSION["password"] = $password;
    $_SESSION["date"] = date("l jS \of F Y h:i:s A");
}
/**
* get the $username from the current session
*
* @return string
*/ 
function getusername(){
    $temp = $_SESSION["username"];
    if(strlen($temp) ==0){
        echo "no username";
    }else{
        echo $temp;
    }
}
/**
* type to classify the action through ajax.
*
* @var string
*/ 
$type = $_GET['t'];
if($type == "set"){
    $username = $_GET['u'];
    $password = $_GET['p'];
    setsession($username, $password);
}else if($type == "get"){
    getusername();
}

    
?>

</body>
</html>