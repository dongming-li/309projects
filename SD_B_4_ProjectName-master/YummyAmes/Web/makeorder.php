<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Make Order</title>
<!--STYLESHEETS-->
<link href="css/styleord.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php 
	$owner = $_SESSION[c_name];
	$sql1="select sum(ca_price) from Cart where c_owner = '". $owner."' ";  
	$query1=mysql_query($sql1);  
    $rows=mysql_fetch_array($query1);
	$totalprice=$rows[0];

	$sql2="select c_id from Customer where c_username = '".$_SESSION[c_name]."' ";  
	$query2=mysql_query($sql2);  
    $rows1=mysql_fetch_array($query2);
	$co_id = $rows1[0];
	
	if($_POST["submit"]){
	$oname=$_POST["c_name"];
	$oaddress=$_POST["c_address"];
	$oemail=$_POST["c_email"];
	$ophone=$_POST["c_phone"];
	$oprice=$_POST["c_price"];
	$verification=$_POST["verification"];
$deli = 0;
		$sit=0;
		
	if($oname==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
    }else if  ($ophone==""){  
		echo "<script>alert('The ID number cannot be null! Please refill it');window.location.href='makeorder.php'</script>";  
            //judge the id is right or wrong   
    }else if (!preg_match('/^[\w\.]+@\w+\.\w+$/i', $oemail)) {  
        echo "<script>alert('the email address is not fit！Please refill it');window.location.href='makeorder.php'</script>";  
              //judge the email address is fit or not
     }//elseif($verification !=$_SESSION['$code']) {  
     else if(($_SESSION['rand'])!=($verification)){  
        echo "<script>alert('Verification code error! please refill it');window.location.href='makeorder.php'</script>";
	}else{
		$sql="insert into Orderlist(o_price,o_cusname,o_res,o_address,o_cusphone,o_cus,o_deli, situation) values('".$totalprice."','".$oname."','".$_SESSION[res]."','".$oaddress."','".$ophone."','".$co_id."','".$deli."','".$sit."')";



		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('checkout successfully!');</script>";
		 
	$sql3="SET SQL_SAFE_UPDATES = 0";
	mysql_query($sql3);
	$sql5="delete from Cart where c_owner='".$_SESSION[c_name]."' ";
	mysql_query($sql5);
		$_SESSION[order]=$oname;
	}
	
}
?>

<?php
	if($_SESSION[order]==""){
?>


<div id="wrapper">
<form id="form1" name="form1" action="makeorder.php" class="login-form" method="post" >
  <div class="header">
    <!--TITLE--><h1>Customer check out</h1><!--END TITLE-->
   
    </div>
    <div class="content">
  <table width="393" height="391" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td width="146" height="42" align="center" >Customer name:</td>
        <td width="243"><input name="c_name" type="text" id="c_name" class="input username"></td>
      </tr>
      <tr>
        <td width="146" height="42" align="center">Total price:</td>
        <td width="243"><input name="c_price" type="text" value= "<?php echo $totalprice ?>" readonly id="c_price" class="input"></td>
      </tr>
      <tr>
        <td width="146" height="42" align="center">Address:</td>
        <td width="243"><input name="c_address" type="text" id="c_address"  class="input" ></td>
      </tr>
      <tr>
        <td height="40" align="center">phone:</td>
        <td><input name="c_phone" type="text" id="c_phone" class="input"></td>
      </tr>
      <tr>
        <td height="40" align="center">Email:</td>
        <td><input name="c_email" type="text" id="c_email" class="input"></td>
      </tr>
      <tr>
        <td height="40" align="center">verification </td>
        <td><input name="verification" type="text" id="verification" class="input">
        <img align="absmiddle" name="validate" onClick="validate.src+='?' + Math.random();" src="verification.php"  alt="refresh"/>
        </td>
      </tr>

    </tbody>
  </table>
	</div>
        <div class="footer">
      
               <!--REGISTER BUTTON--><input type="submit" name="submit1" value="Cancel" class="register" onclick="javascript:location.href='c_cart.php'" />
    <input type="submit" name="submit" value="Submit" class="button" />
    <!--END REGISTER BUTTON-->
    </div>
</form>
</div>

<?php
	}else{
	echo "Customer " .$_SESSION[c_name]. ", your order check out successfully!";
		header("Refresh:3;url=c_main.php");
	}
?>



</body>
</html>