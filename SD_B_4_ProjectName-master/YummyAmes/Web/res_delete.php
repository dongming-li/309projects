<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant delete Order</title>
</head>

<body>
<?php 
	if($_POST["submit"]){
	$uname=$_POST["r_name"];
	$reason=$_POST["r_reason"];
	$verification=$_POST[verification];
		
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($reason==""){
		echo "<script language='javascript'>alert('the reason cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');window.location.href='del_deliver.php'</script>";  
            
		}else{
		$sql1="SET SQL_SAFE_UPDATES = 0";
		mysql_query($sql1);
		$sql2="delete from Orderlist where o_id ='".$_GET['id']."' ";
		mysql_query($sql2) or die("add error");
		echo "<script language='javascript'>alert('delete successfully!');</script>";
			$_SESSION[r_change] = 1;
		}
	}
	
?>

<?php
	if($_SESSION[r_change]==""){
		echo $_GET['id'];
?>

<form id="form1" name="form1" action="res_delete.php" method="post">
  <table width="353" height="248" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td height="50" colspan="2" align="center" bgcolor="#FB6748">Reason to cancel the order</td>
      </tr>
      <tr>
        <td width="86" height="50" align="center">Restaurant Name:</td>
        <td width="263"><input name="r_name" type="text" id="r_name" size="20"></td>
      </tr>
      <tr>
        <td height="60" align="center">Reason:</td>
        <td><input name="r_reason" type="text" id="r_reason" style="width:200px; height:60px;" ></td>
      </tr>
      <tr>
        <td height="40" align="center">verification: </td>
        <td><div><input name="verification" type="text" id="verification" size="20">
        <img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/></div>
        </td>
      </tr>
      <tr>
        <td height="48" colspan="2" align="center"><input type="submit" name="submit" id="submit" value="Submit"> 
           &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="cancel" id="cancel" value="Reset"></td>
      </tr>
    </tbody>
  </table>
</form>

<?php
	}else{
	$_SESSION[r_change]="";
	echo "delete successfully! After 3 seconds go to accepted order page";
		header("Refresh:3;url=rorder.php");
	}
?>

<p><a href="r_page.php" target="_self">Return Restaurant main page</a></p>
</body>
</html>