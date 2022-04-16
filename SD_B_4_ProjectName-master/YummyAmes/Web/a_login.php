<?php include("regsave.php");?>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Administrator Login</title>
<!--STYLESHEETS-->
<link href="css/style.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php 
	$_SESSION[name]="";
	if($_POST["submit1"]){
	$uid=$_POST["f_id"];
	$upass=$_POST["f_pass"];
	$verification=$_POST[verification];
		
	if($uid==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($upass==""){
		echo "<script language='javascript'>alert('the password cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else{
		$sql="select * from Admin where a_username='".$uid."' and a_pwd='".$upass."' ";
		$query=mysql_query($sql);
		$info=mysql_fetch_array($query);
		$aname=$info["a_username"];
		$num=mysql_num_rows($query);
		if($num==0){
		echo "<script language='javascript'>alert('username or password wrong!');location.href='javascript:history.go(-1)';</script>";
		}
		else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');window.location.href='a_login.php'</script>";  
              //Verify the verification code is correct or not
		}else{
			$_SESSION[name] = $aname;
			$_SESSION[pass] = $upass; 
			$_SESSION[a_name] = $aname;
			$_SESSION[u_name]="";
		}
	}
	
	

}
?>

<?php
	if($_SESSION[name]==""){
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
    <!--TITLE--><h1>Administrator Login Form</h1><!--END TITLE-->
   
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">
	<!--USERNAME--><input name="f_id" type="text" id="f_name" class="input username" value="Username" onfocus="this.value=''" /><!--END USERNAME-->
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
 <a href="RL.php" class="button">Cancel</a>
    <input type="submit" name="submit1" value="Login" class="button" />

    </div>
    <!--END FOOTER-->

</form>
<!--END LOGIN FORM-->


</div>
<!--END WRAPPER-->

<!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->



<?php
	}else{
	echo "<script>alert('Administrator " .$_SESSION[name]. ", login in successfully');window.location.href='Administrator.php'</script>";
		$_SESSION[name]="";
	}
?>



</body>
</html>