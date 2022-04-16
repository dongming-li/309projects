<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Forget password</title>
</head>

<body>

<?php if($_POST["submit"]){
	$uname=$_POST["f_name"];
	$upass=$_POST["f_pass"];
	$utype=$_POST["f_type"];
	$verification=$_POST[verification];
		
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($upass==""){
		echo "<script language='javascript'>alert('the password cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($utype==""){
		echo "<script language='javascript'>alert('You should choose a user type!');location.href='javascript:history.go(-1)';</script>";
	}else{
		if($utype=="Customer"){
		$sql="select * from Customer where c_username='".$uname."' and c_phone='".$upass."' ";}
		else if($utype=="Restaurant"){
			$sql="select * from Restaurant where r_username='".$uname."' and r_phone='".$upass."' ";
		}else{
			$sql="select * from Deliver where d_username='".$uname."' and d_phone='".$upass."' ";
		}
		
		$query=mysql_query($sql);
		$num=mysql_num_rows($query);
		if($num==0){
		echo "<script language='javascript'>alert('username or phone number wrong!');location.href='javascript:history.go(-1)';</script>";
		}
		else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');window.location.href='login.php'</script>";  
              //Verify the verification code is correct or not
		}else{
			$_SESSION[name] = $uname;
			$_SESSION[type] = $utype;
		}
	}
	
	

}
?>

<?php
	if($_SESSION[name]==""){
?>
<form id="form1" name="form1" action="forpass.php" method="post">
  <table width="380" height="248" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td height="50" colspan="2" align="center" bgcolor="#48A8FB">Forget password</td>
      </tr>
      <tr>
        <td width="114" height="50" align="center">Username:</td>
        <td width="262"><input name="f_name" type="text" id="f_name" size="20"></td>
      </tr>
      <tr>
        <td width="114" height="50" align="center">Usertype:</td>
        <td><select name="f_type" id="f_type" > 
      	<option value="">--choose--</option>
		<option value="Customer">Customer</option> 
		<option value="Restaurant">Restaurant</option> 
		<option value="Deliverman">Deliverman</option> 
	  </select></td>
      </tr>
      <tr>
        <td height="60" align="center">Userphone:</td>
        <td><input name="f_pass" type="text" id="f_pass" size="20"></td>
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
window.location.href='setpass.php';
		</script>
		 <?php
		 }
?>
<p><a href="RL.php" target="_self">cancel</a></p>

</body>
</html>