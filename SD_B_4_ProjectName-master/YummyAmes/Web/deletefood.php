<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>delete food</title>
</head>

<body>

<?php
	$sql1="SET SQL_SAFE_UPDATES = 0";
	mysql_query($sql1);
	$sql2="delete from Cart where ca_id='". $_GET['id']."' ";
	mysql_query($sql2);
	$_SESSION[fo_name]=1;
?>

<?php
	echo "<script>alert('the food delete successfully!');window.location.href='cart.php'</script>";
?>


</body>
</html>