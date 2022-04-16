<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Deliverman Login in</title>
<!--STYLESHEETS-->
<link href="css/style.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php 
	$_SESSION[d_name]="";
	if($_POST["submit"]){
	$uname=$_POST["d_name"];
	$upass=$_POST["d_pass"];
	$verification=$_POST[verification];
		
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($upass==""){
		echo "<script language='javascript'>alert('the password cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else{
		$sql="select * from Deliver where d_username='".$uname."' and d_pwd='".$upass."' ";
		$query=mysql_query($sql);
		$num=mysql_num_rows($query);
		if($num==0){
		echo "<script language='javascript'>alert('username or password wrong!');location.href='javascript:history.go(-1)';</script>";
		}
		else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');window.location.href='d_login.php'</script>";  
              //Verify the verification code is correct or not
		}else{
			$_SESSION[d_name] = $uname;
			$_SESSION[pass] = $upass; 
		}
	}
	
	

}
?>

<?php
	if($_SESSION[d_name]==""){
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
    <!--TITLE--><h1>Deliverman Login Form</h1><!--END TITLE-->
   
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">
	<!--USERNAME--><input name="d_name" type="text" id="f_name" class="input username" value="Username" onfocus="this.value=''" /><!--END USERNAME-->
    <!--PASSWORD--><input name="d_pass" type="password" id="f_pass" class="input password" value="Password" onfocus="this.value=''" /><!--END PASSWORD-->
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
	echo "Hello: Deliverman " .$_SESSION[d_name]. ", login in successfully! After 3 seconds go to Deliverman main page";
		header("Refresh:3;url=delivermain.php");
	}
?>

<p><a href="RL.php" target="_self">cancel</a></p>


</body>
</html>