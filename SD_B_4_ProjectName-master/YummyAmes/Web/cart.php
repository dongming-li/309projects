<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cart</title>
<!--STYLESHEETS-->
<link href="css/stylecart.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>

<?php
$pr=0;
$cus=$_SESSION[c_name];
	$_SESSION[order]="";
	
if($_SESSION[fo_name]==""){
$sql2 = "select * from Food where f_id='". $_GET['id']."' ";
$query2=mysql_query($sql2);
$row2=mysql_fetch_array($query2);
$sql="insert into Cart(ca_name,ca_price,ca_type,c_from,c_owner) values('".$row2[1]."','".$row2[2]."','".$row2[3]."','".$row2[4]."','".$cus."')";
		mysql_query($sql) or die("add error");
		echo "<script language='javascript'>alert('add successfully!');</script>";
$_SESSION[fo_name]=1;}
	
?>

<?php
if($_SESSION[fo_name]==1){ ?><div id="wrapper">
    <form name="login-form" class="login-form" action="">
    
	<div class="header">
    <!--TITLE--><h1>My cart</h1><!--END TITLE-->
   
    </div>
<div class="content">
	<?php	
$perNumber=6; 
$page=$_GET['page']; 
$count=mysql_query("select count(*) from Cart where c_owner='".$cus."'"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Cart where c_owner='".$cus."' limit $startCount,$perNumber"); 
 
echo "<table border='0'>";
echo "<tr>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Food id</th>";	
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Food name</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Price($)</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type</th>";	
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; Operator</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 $pr+=$row[2];
echo "<tr>";
	 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[1]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[3]</td>";?>
<td><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="deletefood.php?id=<?php echo $row[0] ?>" target="_self">&nbsp;delete</a></p></td>
<?php	
echo "</tr>";
}
echo "</table>";
 
if ($page != 1) { //page is not equal to 1
	?></div>
<a href="cart.php?page=<?php echo $page - 1;?>">past</a> <!--show the last page-->
<?php
}
for ($i=1;$i<=$totalPage;$i++) {  //recyle to show the page
?>
<tr><td> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tr></td> 
<a href="cart.php?page=<?php echo $i;?>"><?php echo $i ;?></a>
<?php
}
if ($page<$totalPage) { //if page smalled than the total page, show the link of next page
?>
<a href="cart.php?page=<?php echo $page + 1;?>">next</a>
<?php
} } 

?>
<p><tr><td> &nbsp;</td></tr></p><br>
<div class="header"><h1><p><td width="166" height="50" align="right">Total price:
<?php echo $pr;?> $</td></p></h1></div>
 <div class="footer">

<p><a href="c_main.php" target="_self" class="register">Go customer main page</a></p>
<p><a href="makeorder.php" target="_self" class="button">Make order</a></p> </div> </form></div>
</body>
</html>