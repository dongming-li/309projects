<?php
$conn = OpenDB();

$name = $_POST["name"];
$username = $_POST["username"];
$password = $_POST["password"];

function signup() {
    global $conn, $name, $username, $password;
    $passwordHash = password_hash($password, PASSWORD_DEFAULT);
    $statement = mysqli_prepare($conn, "INSERT INTO user (name, username, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $username, $password);
    mysqli_stmt_execute($statement);
}

function usernameAvailable() {
    global $conn, $username;
    $statement = mysql_prepare($connect, "SELECT * FROM users WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    mysqli-stmt_store_result($statement);
    $count = mysqli_stmt_num_rows($statement);
    mysqli_stmt_close($statement);
    if($count < 1){
        return true;
    }else {
        return false;
    }
}

$response = array();
$response["success"] = false;

if(usernameAvailable()){
    signup();
    $response["success"] = true;
}

echo json_encode($response);
//CloseDB($conn);
?>