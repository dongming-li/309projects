<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>My order</title>
<!--STYLESHEETS-->
<link href="css/stylecu.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php
$perNumber=6; 
$page=$_GET['page']; 
$sear = mysql_query("select c_id from Customer where c_username = '".$_SESSION[c_name]."' ");
$rows=mysql_fetch_array($sear);
$c_id=$rows[0];
$count=mysql_query("select count(*) from Orderlist where o_cus = '".$c_id."' "); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Orderlist where o_cus = '".$c_id."' limit $startCount,$perNumber"); 
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
echo "<th>Order ID</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Price</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Customer Name</th>";
echo "<th>&nbsp;&nbsp;&nbsp;Phone Number</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Address</td>";
echo "<th>&nbsp;&nbsp;&nbsp;Restaurant ID</td>";
echo "<th>&nbsp;&nbsp;&nbsp;Deliverman ID</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;status</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[1]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[3]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[4]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[5]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[7]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
 if($row[8]==1){
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Is out for deliver</td>";
 }else if($row[8]==0){
	echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Still in restaurant</td>"; 
 }else{
	 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Still prepare</td>"; 
 }
 
echo "</tr>";
}
echo "</table>";
 
if ($page != 1) { //page is not equal to 1
?>
<a href="c_order.php?page=<?php echo $page - 1;?>">past</a> <!--show the last page-->
<?php
}
for ($i=1;$i<=$totalPage;$i++) {  //recyle to show the page
?>
<a href="c_order.php?page=<?php echo $i;?>"><?php echo $i ;?></a>
<?php
}
if ($page<$totalPage) { //if page smalled than the total page, show the link of next page
?>
<a href="c_order.php?page=<?php echo $page + 1;?>">next</a>
<?php
} 
?>
</tbody></table>
			</div>
        <div class="footer">
<p><a href="c_main.php" target="_self" class="register">Go back</a></p>

</div>
	</form></div>

</body>
</html>