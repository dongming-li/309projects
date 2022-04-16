<?
require "connection.php"



$user_name = "gunnara";
$user_pass = "testpw1";
error_log(print_r($_REQUEST,true), 0);
$mysql_qry = "select * from User where Username like '$user_name' and Password like '$user_pass';";
$result = mysql_query($conn, $mysql_qry);
if(mysqli_num_rows($result)>0){
	echo "login success";
}
else{
	echo "login failed";
}

?>
