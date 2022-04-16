<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order List</title>
<!--STYLESHEETS-->
<link href="css/stylecu.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php
	
$perNumber=6; 
$page=$_GET['page']; 
$count=mysql_query("select count(*) from Orderlist"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
if($totalNumber<$perNumber){
	$totalPage=1;
}else{
$totalPage=ceil($totalNumber/$perNumber);} //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Orderlist where o_deli is NULL limit $startCount,$perNumber"); 
  ?>	<div id="wrapper">
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
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Address</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Operator</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[1]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[3]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[4]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";?>
 <td><p><a href="order_info.php?id=<?php echo "$row[0]" ?>" target="_self">see the info</a></p></td>
 <td><p><a href="ordershow.php?id=<?php echo "$row[0]" ?>" target="_self">accept this order</a></p></td>
<?php
echo "</tr>";
}
echo "</table>";
 
if ($page != 1) { //page is not equal to 1
?>
<a href="o_list.php?page=<?php echo $page - 1;?>">past</a> <!--show the last page-->
<?php
}
for ($i=1;$i<$totalPage;$i++) {  //recyle to show the page
?>
<a href="o_list.php?page=<?php echo $i;?>"><?php echo $i ;?></a>
<?php
}
if ($page<$totalPage-1) { //if page smalled than the total page, show the link of next page
?>
<a href="o_list.php?page=<?php echo $page + 1;?>">next</a>
<?php
} 
?>
</tbody></table>
			</div>
        <div class="footer">
<p><a href="delivermain.php" target="_self" class="register">Return delimain page</a></p>
<p><a href="RL.php" target="_self" class="register">Return Web main page</a></p>

</div>
	</form></div>

</body>
</html>