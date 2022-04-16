<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>delete the deliver</title>
</head>

<body>
<?php if($_POST["submit"]){
	$uname=$_POST["d_name"];
	$reason=$_POST["d_reason"];
	$verification=$_POST[verification];
		
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($reason==""){
		echo "<script language='javascript'>alert('the reason cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');window.location.href='del_deliver.php'</script>";  
            
		}else{
		$count = "select d_id from Deliver where d_username='".$_SESSION[d_name]."' ";
		$query1=mysql_query($count);
		$num1=mysql_fetch_array($query1);
		$sql="select o_id from Orderlist where o_deli='".$num1[0]."' ";
		$query=mysql_query($sql);
		$num=mysql_fetch_array($query);
		$sql1 = "UPDATE Orderlist set o_deli=NULL where o_id='".$num[0]."' ";
		mysql_query($sql1) or die("add error");
		echo "<script language='javascript'>alert('delete successfully!');</script>";
			$_SESSION[d_change] = 1;
		}
	}
	
?>

<?php
	if($_SESSION[d_change]==""){
?>

<form id="form1" name="form1" action="del_deliver.php" method="post">
  <table width="353" height="248" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td height="50" colspan="2" align="center" bgcolor="#FB6748">Reason to cancel the deliver</td>
      </tr>
      <tr>
        <td width="86" height="50" align="center">Delivername:</td>
        <td width="263"><input name="d_name" type="text" id="d_name" size="20"></td>
      </tr>
      <tr>
        <td height="60" align="center">Reason:</td>
        <td><input name="d_reason" type="text" id="d_reason" style="width:200px; height:60px;" ></td>
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
	echo "delete successfully! After 3 seconds go to accepted order page";
		header("Refresh:3;url=ac_order.php");
	}
?>

<p><a href="delivermain.php" target="_self">Return Deliverman main page</a></p>
</body>
</html>