<?php include("regsave.php");?>


<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Register</title>

<link href="css/style1.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php if($_POST["submit"]){
	$uid=$_POST["f_id"];
	$uname=$_POST["f_name"];
	$upass=$_POST["f_pass"];
	$upass1=$_POST["f_pass2"];
	$uemail=$_POST["f_email"];
	$uphone=$_POST["f_phone"];
	$uaddress=$_POST["f_address"];
	$verification=$_POST["verification"];
	
	$sql1="select * from Customer where c_username = '".$uid."' ";  
	$query1=mysql_query($sql1);  
    $rows=mysql_num_rows($query1); 
	
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}
	else if ((strlen($uname) < 4)||(!preg_match('/^\w+$/i', $uname))) {  
		echo "<script>alert('The user name is at least 4 digits and does not contain illegal characters！Please refill it');location.href='javascript:history.go(-1)';</script>";  
            //judge the longth of username 
    }else if  ($uid==""){  
		echo "<script>alert('The Username cannot be null! Please refill it');location.href='javascript:history.go(-1)';</script>";  
            //judge the id is right or wrong   
    }else if( $rows > 0){  
        echo "<script>alert('This id number has been registered! Please refill it');location.href='javascript:history.go(-1)';</script>";  
            //check is the id have register
    }else if(strlen($upass) < 6){  
        echo "<script>alert('Password at least 6 numbers！Please refill it');location.href='javascript:history.go(-1)';</script>";  
             //judge the longth of password  
    }else if (!preg_match('/^[\w\.]+@\w+\.\w+$/i', $uemail)) {  
        echo "<script>alert('the email address is not fit！Please refill it');location.href='javascript:history.go(-1)';</script>";  
              //judge the email address is fit or not
     }//elseif($verification !=$_SESSION['$code']) {  
     else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');location.href='javascript:history.go(-1)';</script>";
    }else if($upass==""||$upass1==""){
		echo "<script language='javascript'>alert('the password cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($upass!=$upass1){
		echo "<script language='javascript'>alert('the password input two times are not fit!');location.href='javascript:history.go(-1)';</script>";
	}else{
		$sql="insert into Customer(c_username,c_name,c_pwd,c_email,c_phone,c_address,c_date) values('".$uid."','".$uname."','".$upass."','".$uemail."','".$uphone."','".$uaddress."','".date('Y-m-d H:i:s')."')";

		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('Registered successfully!');</script>";
		$_SESSION[c_name]=$uname;
	}
	
}
?>

<?php
	if($_SESSION[c_name]==""){
?>


<div id="wrapper">


<form id="form1" name="form1" class="login-form" action="register.php" method="post">
    <div class="content">
  <table width="330" height="471" border="0" align="center" cellspacing="0">
    <tbody>
 <div class="header">
    <!--TITLE--><h1>Login Form</h1><!--END TITLE-->
   
    </div>
      
      <tr>
        <td width="95" height="42" align="center">Username:</td>
        <td width="252"><input name="f_id" type="text" id="f_id" style="width:180px; height:20px;"></td>
      </tr>
      <tr> <td><p></p> </td></tr>
      <tr>
        <td width="95" height="42" align="center">Real Name:</td>
        <td width="252"><input name="f_name" type="text" id="f_name" style="width:180px; height:20px;"></td>
      </tr>
      <tr> <td><p></p> </td></tr>
      <tr>
        <td height="40" align="center">Password:</td>
        <td><input name="f_pass" type="password" id="f_pass" style="width:180px; height:20px;"></td>
      </tr>
      <tr> <td><p></p> </td></tr>
      <tr>
        <td height="42" align="center">Confirm :</td>
        <td><input name="f_pass2" type="password" id="f_pass2" style="width:180px; height:20px;"></td>
      </tr>
      <tr> <td><p></p> </td></tr>
      <tr>
        <td height="40" align="center">Email:</td>
        <td><input name="f_email" type="text" id="f_email" style="width:180px; height:20px;"></td>
      </tr>
      <tr> <td><p></p> </td></tr>
      <tr>
        <td height="40" align="center">Phone:</td>
        <td><input name="f_phone" type="text" id="f_phone" style="width:180px; height:20px;"></td>
      </tr>
      <tr> <td><p></p> </td></tr>
      <tr>
        <td height="40" align="center">Address:</td>
        <td><input name="f_address" type="text" id="f_address" style="width:180px; height:20px;"></td>
      </tr>
      <tr> <td><p></p> </td></tr>
      <tr>
        <td height="40" align="center">verification: </td>
        <td><input name="verification" type="text" id="verification" style="width:180px; height:20px;">
        <br></td>  </tr>
        
        <tr><td><img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/></td></tr>
        
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
	echo "Hello: customer " .$_SESSION[c_name]. ", register successfully! After 3 seconds go to customer main page";
		
		header("Refresh:3;url=c_main.php");
	}
?>




</body>
</html>