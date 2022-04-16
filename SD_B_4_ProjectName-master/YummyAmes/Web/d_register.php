<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Deliverman register</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php if($_POST["submit"]){
	$uid=$_POST["d_id"];
	$uname=$_POST["d_name"];
	$upass=$_POST["d_pass"];
	$upass1=$_POST["d_pass2"];
	$uemail=$_POST["d_email"];
	$uphone=$_POST["d_phone"];
	$verification=$_POST["verification"];
	
	$sql1="select * from Deliver where d_username = '$uid'";  
	$query1=mysql_query($sql1);  
    $rows=mysql_num_rows($query1); 
	
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}
	else if ((strlen($uname) < 4)||(!preg_match('/^\w+$/i', $uname))) {  
		echo "<script>alert('The user name is at least 4 digits and does not contain illegal characters！Please refill it');window.location.href='r_register.php'</script>";  
            //judge the longth of username 
    }else if  ($uid==""){  
		echo "<script>alert('The ID number cannot be null! Please refill it');window.location.href='r_register.php'</script>";  
            //judge the id is right or wrong   
    }else if( $rows > 0){  
        echo "<script>alert('This id number has been registered! Please refill it');window.location.href='r_register.php'</script>";  
            //check is the id have register
    }else if(strlen($upass) < 6){  
        echo "<script>alert('Password at least 6 numbers！Please refill it');window.location.href='r_register.php'</script>";  
             //judge the longth of password  
    }else if (!preg_match('/^[\w\.]+@\w+\.\w+$/i', $uemail)) {  
        echo "<script>alert('the email address is not fit！Please refill it');window.location.href='r_register.php'</script>";  
              //judge the email address is fit or not
     }//elseif($verification !=$_SESSION['$code']) {  
     else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');location.href='javascript:history.go(-1)';</script>";
    }else if($upass==""||$upass1==""){
		echo "<script language='javascript'>alert('the password cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($upass!=$upass1){
		echo "<script language='javascript'>alert('the password input two times are not fit!');location.href='javascript:history.go(-1)';</script>";
	}else{
		$sql="insert into Deliver(d_username,d_name,d_pwd,d_email,d_phone,created_at) values('".$uid."','".$uname."','".$upass."','".$uemail."','".$uphone."','".date('Y-m-d H:i:s')."')";
		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('Registered successfully!');</script>";
		$_SESSION[name]=$uname;
	}
	
}
?>

<?php
	if($_SESSION[name]==""){
?>


<div id="wrapper">
<form id="form1" name="form1" class="login-form" action="r_register.php" method="post">
    <div class="content">
  <table width="330" height="471" border="0" align="center" cellspacing="0">
    <tbody>
 <div class="header">
    <!--TITLE--><h1>Deliver User Registration</h1><!--END TITLE-->
   
    </div>

    <tbody>

      <tr>
        <td width="146" height="42" align="center">Username:</td>
        <td width="243"><input name="d_id" type="text" id="d_id" style="width:180px; height:20px;"></td>
      </tr>
      <tr>
        <td width="146" height="42" align="center">Real name:</td>
        <td width="243"><input name="d_name" type="text" id="d_name" style="width:180px; height:20px;"></td>
      </tr>
      <tr>
        <td height="40" align="center">Password:</td>
        <td><input name="d_pass" type="password" id="d_pass" style="width:180px; height:20px;"></td>
      </tr>
      <tr>
        <td height="42" align="center">Confirm :</td>
        <td><input name="d_pass2" type="password" id="d_pass2" style="width:180px; height:20px;"></td>
      </tr> 
      <tr>
        <td height="40" align="center">Email:</td>
        <td><input name="d_email" type="text" id="d_email" style="width:180px; height:20px;"></td>
      </tr>
      <tr>
        <td height="40" align="center">Phone:</td>
        <td><input name="d_phone" type="text" id="d_phone" style="width:180px; height:20px;"></td>
      </tr>
      <tr>
        <td height="40" align="center">verification </td>
        <td><input name="verification" type="text" id="verification" style="width:180px; height:20px;">
         <br></td>  </tr>
        
        <tr><td>
        <img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/>
        </td>
      </tr>
    </tbody>
  </table>
        </div>
 <div class="footer">
    <a href="RL.php" class="button">Cancel</a>
    <input type="submit" name="submit" value="Register" class="button" />
    <!--END REGISTER BUTTON-->
    </div>


 
</form>
</div>


<?php
	}else{
	echo "Hello: Deliverman " .$_SESSION[name]. ", login in successfully!";
		header("Refresh:3;url=delivermain.php");
	}
?>



</body>
</html>