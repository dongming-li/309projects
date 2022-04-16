<?php include("regsave.php");?>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Login in</title>
</head>

<body>
<?php if($_POST["submit"]){
	$uname=$_POST["f_name"];
	$upass=$_POST["f_pass"];
	$verification=$_POST[verification];
		
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($upass==""){
		echo "<script language='javascript'>alert('the password cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else{
		$sql="select * from Customer where c_username='".$uname."' and c_pwd='".$upass."' ";
		$query=mysql_query($sql);
		$num=mysql_num_rows($query);
		if($num==0){
		echo "<script language='javascript'>alert('username or password wrong!');location.href='javascript:history.go(-1)';</script>";
		}
		else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');window.location.href='login.php'</script>";  
              //Verify the verification code is correct or not
		}else{
			$_SESSION[c_name] = $uname;
			$_SESSION[pass] = $upass; 
			$_SESSION[fo_name] = 1; 
			$_SESSION[c_id]=$uname;
		}
	}
	
	

}
?>

<?php
	if($_SESSION[c_name]==""){
?>

<form id="form1" name="form1" action="login.php" method="post">
  <table width="353" height="248" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td height="50" colspan="2" align="center" bgcolor="#48A8FB">Customer User Login in</td>
      </tr>
      <tr>
        <td width="187" height="50" align="center">Username:</td>
        <td width="162"><input name="f_name" type="text" id="f_name" size="20"></td>
      </tr>
      <tr>
        <td height="60" align="center">Password:</td>
        <td><input name="f_pass" type="password" id="f_pass" size="20"></td>
      </tr>
      <tr>
        <td height="40" align="center">verification: </td>
        <td><div>
          <input name="verification" type="text" id="verification" size="20">
          <img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/></div></td>
      </tr>

      <tr>
        <td height="48" colspan="2" align="center"><input type="submit" name="submit" id="submit" value="Submit"> 
           &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="cancel" id="cancel" value="Reset"></td>
      </tr>
      <tr>
 <td height="50" colspan="2" align="center"> Don't have a account? <a href="register.php" >Register Now!</a></td>
      </tr>
    </tbody>
  </table>
</form>

<?php
	}else{
	echo "Hello: customer " .$_SESSION[c_name]. ", login in successfully! After 3 seconds go to customer main page";
		header("Refresh:3;url=c_main.php");
	}
?>

<p><a href="RL.php" target="_self">cancel</a></p>


</body>
</html>