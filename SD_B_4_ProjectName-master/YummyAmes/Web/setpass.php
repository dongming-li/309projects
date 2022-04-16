<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset password</title>
</head>

<body>

<?php 
	
	if($_POST["submit"]){
	$upass=$_POST["pass1"];
	$upass1=$_POST["pass2"];
	$verification=$_POST["verification"];
	
	
	if($upass==""){
		echo "<script language='javascript'>alert('the password cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}
  else if(strlen($upass) < 6){  
        echo "<script>alert('Password at least 6 numbers！Please refill it');window.location.href='setpass.php'</script>";  
    }else if  ($upass1==""){  
		echo "<script>alert('The password cannot be null! Please refill it');window.location.href='setpass.php'</script>";  
    }else if(strlen($upass1) < 6){  
        echo "<script>alert('Password at least 6 numbers！Please refill it');window.location.href='setpass.php'</script>";  

     }
     else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');location.href='javascript:history.go(-1)';</script>";
	}else if($upass!=$upass1){
		echo "<script language='javascript'>alert('the password input two times are not fit!');location.href='javascript:history.go(-1)';</script>";
	}else{
		 if($_SESSION[type]=="Customer"){
		 $sql="UPDATE Customer set c_pwd='".$upass."' where c_username='".$_SESSION[name]."' ";
		 }else if($_SESSION[type]=="Restaurant"){
			  $sql="UPDATE Restaurant set r_pwd='".$upass."' where r_username='".$_SESSION[name]."' ";
		 }else{
			 $sql="UPDATE Deliver set d_pwd='".$upass."' where d_username='".$_SESSION[name]."' ";
		 }
		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('password change successfully!');</script>";
		$_SESSION[change1]=1;
	}
	
}
?>

<?php
	if($_SESSION[change1]==""){
?>
<form id="form1" name="form1" action="setpass.php" method="post">
  <table width="380" height="248" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td height="50" colspan="2" align="center" bgcolor="#48A8FB">Reset password</td>
      </tr>
      <tr>
        <td width="114" height="50" align="center">New password:</td>
        <td width="262"><input name="pass1" type="password" id="f_name" size="20"></td>
      </tr>
      <tr>
        <td height="60" align="center">input again:</td>
        <td><input name="pass2" type="password" id="f_pass" size="20"></td>
      </tr>
      <tr>
        <td height="40" align="center">verification:</td>
        <td><div>
          <input name="verification" type="text" id="verification" size="20">
          <img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/></div></td>
      </tr>

      <tr>
        <td height="48" colspan="2" align="center"><input type="submit" name="submit" id="submit" value="Submit"> 
           &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="cancel" id="cancel" value="Reset"></td>
      </tr>
      <tr>

      </tr>
    </tbody>
  </table>
</form>

<?php
	}else{?>
		<script language="javascript" type="text/javascript"> 
window.location.href='RL.php';
		</script>
		 <?php
		 }
?>
</body>
</html>