<?php  
// connect database 
$conn=@mysql_connect("mysql.cs.iastate.edu","dbu309sdb4","ZvtQfv!x")  or die(mysql_error());  
@mysql_select_db('db309sdb4',$conn) or die(mysql_error());  
  
// judgeaction  
$action = isset($_REQUEST['action'])? $_REQUEST['action'] : '';  
  
  
// upload picture  

	if($_POST["submit"]){
	$mname=$_POST["f_name1"];
	$type=$_POST["f_type"];
	$char=$_POST["f_char"];
	$price=$_POST["f_price"];
	$from=$_POST["f_from"];
	
	$sql1="select * from Food where f_name = '$mname'";  
	$query1=mysql_query($sql1);  
    $rows=mysql_num_rows($query1); 
	
	if($mname==""){
		echo "<script language='javascript'>alert('the food name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}
	else if($type==""){
		echo "<script language='javascript'>alert('the food type cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($char==""){
		echo "<script language='javascript'>alert('the descript of the food cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else if($price==""){
		echo "<script language='javascript'>alert('the price of the food cannot be null!');location.href='javascript:history.go(-1)';</script>";
    }else if( $rows > 0){  
        echo "<script>alert('This id number has been registered! Please refill it');window.location.href='r_main.php'</script>";  
	}else{
		if($action=='add'){ 
    $image = mysql_escape_string(file_get_contents($_FILES['photo']['tmp_name']));  
    $ptype = $_FILES['photo']['type'];
		/*$image = addslashes($_FILES['image']['tmp_name']);
		$name = addslashes($_FILES['image']['name']);
		$image=file_get_contents($image);
		$image=base64_encode($image);*/
		}
		$sql="insert into Food(f_name,f_price,f_type,f_from,f_char,fp_type,binarydata) values('".$mname."','".$price."','".$type."','".$from."','".$char."','".$ptype."','".$image."')";
		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('Upload successfully!');</script>";
		$_SESSION[m_name]=$mname;
		echo "Food upload successfully! return to main page after 3 seconds";
		header("Refresh:3;url=r_page.php");
	}
	}
	



if($action=='add'){  
  
    $image = mysql_escape_string(file_get_contents($_FILES['photo']['tmp_name']));  
    $ptype = $_FILES['photo']['type'];  
    $sqlstr = "insert into Food(fp_type,binarydata) values('".$ptype."','".$image."')";  
  
    @mysql_query($sqlstr) or die(mysql_error());  
  
    //header('location:r_main.php');  
    exit();
  
// show pictures  
} elseif($action=='show'){  
  
    $id = isset($_GET['id'])? intval($_GET['id']) : 0;  
    $sqlstr = "select * from Food where id=$id";  
    $query = mysql_query($sqlstr) or die(mysql_error());  
      
    $thread = mysql_fetch_assoc($query);  
      
    if($thread){  
        header('content-type:'.$thread['type']);  
        echo $thread['binarydata'];  
        exit();  
    }


}  
  
if($_SESSION[m_name]==""){  
// show picture list or picture information
?>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
 <head>  
  <meta http-equiv="content-type" content="text/html; charset=utf-8">  
  <title> upload food </title>  
  <!--STYLESHEETS-->
<link href="css/stylef.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
 </head>  
  
 <body>  
 
 <div id="wrapper">
 <form id="form1" name="form1" action="r_main.php" class="login-form" method="post" enctype="multipart/form-data">
  <div class="header">
    <!--TITLE--><h1>food information</h1><!--END TITLE-->
   
    </div>
    <div class="content">
  <table width="393" height="471" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td width="146" height="42" align="center">Food name:</td>
        <td width="243"><input name="f_name1" type="text" id="f_name1" class="input"></td>
      </tr>
            <tr>
        <td width="146" height="42" align="center">From:</td>
        <td width="243"><input name="f_from" type="text" id="f_from" class="input"></td>
      </tr>
      <tr>
        <td height="40" align="center">Price:</td>
        <td><input name="f_price" type="text" id="f_price" class="input"></td>
      </tr>
      <tr>
        <td height="42" align="center">type:</td>
        <td>
      <select name="f_type" id="f_type"> 
		<option value="American">American</option> 
		<option value="Chinese">Chinese</option> 
	  </select> </td>
      </tr>
      <tr>
        <td height="40" align="center">Characteristic:</td>
        <td><input name="f_char" type="text" id="f_char" class="input"></td>
      </tr>
      
      <tr>
      <td height="40" align="center">Photo:</td>
      <td>
      <p><input type="file" name="photo"></p>  
  	  <p><input type="hidden" name="action" value="add"></p>  
      </td>
      </tr>

    </tbody>
  </table>
</div>

        <div class="footer">
<p><a href="r_page.php" target="_self" class="register">Return back</a></p>
    <input type="submit" name="submit" value="Submit" class="button" />
    <!--END REGISTER BUTTON-->
    </div>
</form>
</div>
 

 </body>  
</html>  
<?php  
}  
?>  