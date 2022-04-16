<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>
</head>

<body>
<?php
$perNumber=6; 
$page=$_GET['page']; 
$count=mysql_query("select count(*) from Orderlist"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Orderlist limit $startCount,$perNumber"); 
 
echo "<table border='0'>";
echo "<tr>";
echo "<th>Order ID</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Order Price</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Customer name</th>";
echo "<th>&nbsp;&nbsp;Customerphone</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; Address</td>";
	echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; Operator</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[1]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[3]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[4]</td>"; ?>
 <td><p><a href="foodshow.php?id=<?php echo $row[0] ?>" target="_self">accept this order</a></p></td>
<?php
echo "</tr>";
}
echo "</table>";
 
if ($page != 1) { //page is not equal to 1
?>
<a href="r_list.php?page=<?php echo $page - 1;?>">past</a> <!--show the last page-->
<?php
}
for ($i=1;$i<=$totalPage;$i++) {  //recyle to show the page
?>
<a href="r_list.php?page=<?php echo $i;?>"><?php echo $i ;?></a>
<?php
}
if ($page<$totalPage) { //if page smalled than the total page, show the link of next page
?>
<a href="r_list.php?page=<?php echo $page + 1;?>">next</a>
<?php
} 
?>
<p><a href="RL.php" target="_self">Return main page</a></p>
</body>
</html>