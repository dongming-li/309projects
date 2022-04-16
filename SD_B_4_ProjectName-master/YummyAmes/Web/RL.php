
<?php include("regsave.php");
session_cache_limiter('private, must-revalidate');
?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Register and Login in</title>


<link href="css/style.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>

</head>

<body>

<div id="wrapper">
 
 <form id="form1" name="form1" action="r_main.php"  class="login-form" method="post" enctype="multipart/form-data">
 
   <div class="header">
    <!--TITLE--><h1>Login in and Register</h1><!--END TITLE-->
   
    </div>
    <!--END HEADER-->

  
   <div class="content">
    <tbody>
     <tr>
        <td height="42" align="center">Login in type:</td>
        <td>
      <select name="f_type" id="f_type" onchange="window.location=this.value;"> 
      	<option value="choose">--choose--</option>
		<option value="login1.php">Customer</option> 
		<option value="r_login.php">Restaurant</option> 
		<option value="d_login.php">Deliverman</option> 
		<option value="a_login.php">Admin</option> 
	  </select> </td>
      </tr><br>
      <tr></tr><br>

</tbody>
    </div>
   
    <div class="footer">
     <tr>
        <td height="42" align="center">Register type: </td>
        <td>
      <select name="f_type" id="f_type" onchange="window.location=this.value;"> 
      	<option value="choose">--choose--</option>
		<option value="register.php">Customer</option> 
		<option value="r_register.php">Restaurant</option> 
		<option value="d_register.php">Deliverman</option> 
	  </select> </td>
      </tr>
    </div>
  
</form>

</div>


<?php
	$_SESSION[name]="";
	$_SESSION[c_id]="";
	$_SESSION[r_id]="";
	$_SESSION[id]="";
	$_SESSION[search]="";
	$_SESSION[o_id]="";
	$_SESSION[type]="";
	$_SESSION[r_change]="";
	$_SESSION[d_change]="";
	$_SESSION[change]="";
	$_SESSION[a_name]="";
	$_SESSION[r_name]="";
	$_SESSION[c_name]="";
	$_SESSION[d_name]="";
	$_SESSION[order]="";
	$_SESSION[m_name]="";
	$_SESSION[fo_name]="";
	$_SESSION[res]="";
	$_SESSION[change1]="";
?>

</body>
</html>