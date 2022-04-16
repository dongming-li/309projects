<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Accept Order</title>
</head>

<body>

<?php
$dname=$_SESSION[d_name];

	$sql1="select d_id from Deliver where d_username = '". $dname."' ";  
	$query1=mysql_query($sql1);  
    $rows=mysql_fetch_array($query1);
	$del_id=$rows[0];
	
	
$sql="update Orderlist set o_deli=$del_id where o_id =  '".$_GET['id']."' ";
mysql_query($sql) or die("add error");
echo "<script language='javascript'>alert('accept successfully!');</script>";
 
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
 
echo "<table border='0'>";
echo "<tr>";
echo "<th>&nbsp;&nbsp;Order id</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Customer name</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Price</th>";
echo "<th>Customer Phone</th>";	
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Address</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Note</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[1]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[3]</td>";  //show the content of the database 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[4]</td>";  
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[5]</td>";  ;
	
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
?>
<p><a href="o_list.php" target="_self">Go back</a></p>
</body>
</html>