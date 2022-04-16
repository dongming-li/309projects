
<?php include("regsave.php");?>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer login in</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login Form</title>

<!--STYLESHEETS-->
<link href="css/style.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<!--Slider-in icons-->
<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
		$(".user-icon").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-icon").css("left","0px");
	});
});
</script>

</head>

<body>
<?php
$_SESSION[c_name]="";?>

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
	else if($_POST["submit1"]){
		header("Refresh:0;url=register.php");
	}
	
?>

<?php
	if($_SESSION[c_name]==""){
?>

<div id="wrapper">

	<!--SLIDE-IN ICONS-->
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <!--END SLIDE-IN ICONS-->

<!--LOGIN FORM-->
<form name="login-form" class="login-form" action="" method="post">

	<!--HEADER-->
    <div class="header">
    <!--TITLE--><h1>Login Form</h1><!--END TITLE-->
   
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">
	<!--USERNAME--><input name="f_name" type="text" id="f_name" class="input username" value="Username" onfocus="this.value=''" /><!--END USERNAME-->
    <!--PASSWORD--><input name="f_pass" type="password" id="f_pass" class="input password" value="Password" onfocus="this.value=''" /><!--END PASSWORD-->
    <div class="header">
		<span><a href="forpass.php" target="_self">forget password?</a></span></div>
    </div>
    <!--END CONTENT-->
        <td><div class="content">
          <input name="verification" type="text" id="verification" class="input" value="verification" onfocus="this.value=''" />
                   <td><img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/></td></div>
    <!--FOOTER-->
    <div class="footer">
    <!--LOGIN BUTTON--><!--END LOGIN BUTTON-->
    <!--REGISTER BUTTON--><a href="register.php" ><input type="submit" name="submit1" value="Register" class="register" />
    <input type="submit" name="submit" value="Login" class="button" />
    </a><!--END REGISTER BUTTON-->
    </div>
    <!--END FOOTER-->

</form>
<!--END LOGIN FORM-->


</div>
<!--END WRAPPER-->

<!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

<?php
	}else{
	echo "Hello: customer " .$_SESSION[c_name]. ", login in successfully! After 3 seconds go to customer main page";
		header("Refresh:3;url=c_main.php");
	}
?>
<p><a href="RL.php" target="_self">return to main page</a></p>
</body>


</html>