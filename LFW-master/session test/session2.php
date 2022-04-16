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
    $_SESSION["password"] = "test";
print_r($_SESSION);

?>

</body>
</html>