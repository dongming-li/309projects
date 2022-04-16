<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Untitled Document</title>
</head>

<body>
<?php
$sql="insert into Restaurant(r_usrname,r_name,r_pwd,r_email,r_phone,r_address) values('"test"','"Good"','"123456"','"diaeuiw@qq.com"','"5157356353"','"duiwqoudoiwqu"')";
		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('Registered successfully!');</script>"; ?>
	
</body>
</html>