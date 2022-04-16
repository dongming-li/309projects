<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Food List</title>
<!--STYLESHEETS-->
<link href="css/stylefood.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>

<?php
	

 
	if($_SESSION[r_name]==""){
	$_SESSION[fo_name]="";
	$sql = "select r_name from Restaurant where r_id='".$_SESSION[r_id]."'";
	$query=mysql_query($sql);
	$rows=mysql_fetch_array($query); 
	$rname=$rows[0];
	$_SESSION[r_name]=$rname;}
	
$perNumber=6; 
$page=$_GET['page']; 

$count=mysql_query("select count(*) from Food where f_from='".$_SESSION[r_name]."'"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Food where f_from='".$_SESSION[r_name]."' limit $startCount,$perNumber"); 
	?>
	<div id="wrapper">
    <form name="login-form" class="login-form" action="">
    
	<div class="header">
    <!--TITLE--><h1>My cart</h1><!--END TITLE-->
   
    </div>	
    <div class="content">
     <table width="500" height="391" border="0" align="center" cellspacing="0"><tbody>
 <?php
echo "<table border='0'>";
echo "<tr>";
echo "<th>&nbsp;&nbsp;Foodid&nbsp;&nbsp;&nbsp;&nbsp;</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Food name</th>";
echo "<th>&nbsp;&nbsp;Price</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type</th>";	
echo "<th>Descript</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Photo</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; Operator</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[1]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[3]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";  //show the content of the database 
 echo "<td>$row[4]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";  

 echo '<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img height="300" width="300" src="data:image;base64,'.$row[5].'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>';?>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p><a href="cart.php?id=<?php echo $row[0] ?>" target="_self">Add to cart</a></p></td>
<?php	
echo "</tr>";
}
echo "</table>";
 
if ($page != 1) { //page is not equal to 1
?>
<a href="foodshow.php?page=<?php echo $page - 1;?>">past</a> <!--show the last page-->
<?php
}
for ($i=1;$i<=$totalPage;$i++) {  //recyle to show the page
?>
<a href="foodshow.php?page=<?php echo $i;?>"><?php echo $i ;?></a>
<?php
}
if ($page<$totalPage) { //if page smalled than the total page, show the link of next page
?>
<a href="foodshow.php?page=<?php echo $page + 1;?>">next</a>
<?php
} 
?> </tbody></table>
			</div>
        <div class="footer">
<p><a href="r_info.php" target="_self" class="register">Go back</a></p>
<p><a href="r_list.php" target="_self" class="register">Return restaurant list</a></p>

</div>
	</form></div>

</body>
</html>