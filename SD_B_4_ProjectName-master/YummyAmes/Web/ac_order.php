<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Accepted order</title>
<!--STYLESHEETS-->
<link href="css/stylecu.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<?php
$dname=$_SESSION[d_name];
	 
$perNumber=6; 
$page=$_GET['page']; 
$sql1 = "select d_id from Deliver where d_username='". $dname."' ";
echo $id;
$query1=mysql_query($sql1);
$row1=mysql_fetch_array($query1);
$count=mysql_query("select count(*) from Orderlist where o_deli='".$row1[0]."'"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Orderlist where o_deli='".$row1[0]."' limit $startCount,$perNumber"); 
  ?>	<div id="wrapper">
    <form name="login-form" class="login-form" action="">
    
	<div class="header">
    <!--TITLE--><h1>Accepted order</h1><!--END TITLE-->
   
    </div>	
    <div class="content">
     <table width="500" height="391" border="0" align="center" cellspacing="0"><tbody>
  <?php
echo "<table border='0'>";
echo "<tr>";
echo "<th>&nbsp;&nbsp;Order id</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Customer name</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Price</th>";
echo "<th>Customer Phone</th>";	
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Address</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Note</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; Operator</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[1]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[3]</td>";  //show the content of the database 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[4]</td>";  
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[5]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";  
?>
 <td><p><a href="del_deliver.php?id=<?php echo "$row[0]" ?>" target="_self">cancel this deliver</a></p></td>
<?php
	
echo "</tr>";
}
echo "</table>";
 
if ($page != 1) { //page is not equal to 1
?>
<a href="ac_order.php?page=<?php echo $page - 1;?>">past</a> <!--show the last page-->
<?php
}
for ($i=1;$i<=$totalPage;$i++) {  //recyle to show the page
?>
<a href="ac_order.php?page=<?php echo $i;?>"><?php echo $i ;?></a>
<?php
}
if ($page<$totalPage) { //if page smalled than the total page, show the link of next page
?>
<a href="ac_order.php?page=<?php echo $page + 1;?>">next</a>
<?php
} 
?>
</tbody></table>
			</div>
        <div class="footer">
<p><a href="delivermain.php" target="_self" class="register">Return restaurant main page</a></p>

</div>
	</form></div>

</body>
</html>