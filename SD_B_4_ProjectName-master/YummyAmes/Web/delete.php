<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete operator</title>
</head>

<body>

<?php
	
	$sql2="delete from Customer where c_id='". $_GET['id']."' ";
	$query=mysql_query($sql2);
?>

<?php
	echo "<script>alert('the data delete successfully!');window.location.href='user.php'</script>";
?>


</body>
</html>