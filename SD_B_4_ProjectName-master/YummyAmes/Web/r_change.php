<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Restaurant information change</title>
<!--STYLESHEETS-->
<link href="css/style2.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php if($_POST["submit"]){
	$uname=$_POST["f_name"];
	$uemail=$_POST["f_email"];
	$uphone=$_POST["f_phone"];
	$uaddress=$_POST["f_address"];
	$verification=$_POST["verification"];
	
	$sql1="select * from Restaurant where r_username = '".$_SESSION[r_name]."' ";  
	$query1=mysql_query($sql1);  
    $rows=mysql_num_rows($query1); 
	
	if($uname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}
	else if ((strlen($uname) < 4)||(!preg_match('/^\w+$/i', $uname))) {  
		echo "<script>alert('The user name is at least 4 digits and does not contain illegal characters！Please refill it');location.href='javascript:history.go(-1)';</script>";  
            //judge the longth of username 
    }else if (!preg_match('/^[\w\.]+@\w+\.\w+$/i', $uemail)) {  
        echo "<script>alert('the email address is not fit！Please refill it');location.href='javascript:history.go(-1)';</script>";  
              //judge the email address is fit or not
     }//elseif($verification !=$_SESSION['$code']) {  
     else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');location.href='javascript:history.go(-1)';</script>";
	}else{
		$sql="UPDATE Restaurant set r_name='".$uname."', r_email = '".$uemail."', r_phone='".$uphone."', r_address='".$uaddress."' where r_username='".$_SESSION[r_name]."' ";
		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('information change successfully!');</script>";
		$_SESSION[change]=1;
	}
	
}
?>

<?php
	if($_SESSION[change]==""){
?>



<form id="form1" name="form1" action="r_change.php" class="login-form" method="post" enctype="multipart/form-data">
 <div class="header">
    <!--TITLE--><h1>Restaurant information change</h1><!--END TITLE-->
   
    </div>
    <div class="content">

        <td width="95" height="42" align="center">ResName:</td>
        <input name="f_name" type="text" id="f_name"  class="input username"><br>
<tr> <td> <br></td></tr>

        <td height="40" align="center">&nbsp; Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <input name="f_email" type="text" id="f_email"  class="input"><br>
<tr> <td> <br></td></tr>

        <td height="40" align="center">&nbsp; Phone:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><input name="f_phone" type="text" id="f_phone"  class="input"><br>
<tr> <td> <br></td></tr>
        <td height="40" align="center">Address:&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <input name="f_address" type="text" id="f_address"  class="input"><br>
<tr> <td> <br></td></tr>
        <td height="40" align="center">verification: </td>
        <td><input name="verification" type="text" id="verification"  class="input">
        <img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/>
        </td>

	  </div>

        <div class="footer">
      
               <!--REGISTER BUTTON--><input type="submit" name="submit1" value="Reset" class="register" onclick="javascript:location.href='r_page.php'" />
    <input type="submit" name="submit" value="Submit" class="button" />
    <!--END REGISTER BUTTON-->
    </div>
</form>
</div>


<?php
	}else{
	echo "Restaurant information change successfully! After 3 seconds go to Restaurant main page";
		header("Refresh:3;url=r_page.php");
	}
?>
<p><a href="r_page.php" target="_self">Return to restaurant main page</a></p>

</body>
</html>