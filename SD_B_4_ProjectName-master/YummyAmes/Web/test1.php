<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Untitled Document</title>
</head>

<body>
<?php
 
$perNumber=6; 
$page=$_GET['page']; 
$count=mysql_query("select count(*) from reg"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from reg limit $startCount,$perNumber"); 
 
echo "<table border='0'>";
echo "<tr>";
echo "<th>id</th>";
echo "<th>name</th>";
echo "<th>age</th>";
echo "<th>grade</td>";
echo "<th> operator</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>$row[0]</td>"; 
 echo "<td>$row[1]</td>";
 echo "<td>$row[2]</td>";
 echo "<td>$row[3]</td>";  //show the content of the database 
echo "</tr>";
}
echo "</table>";
 
if ($page != 1) { //page is not equal to 1
?>
<a href="test1.php?page=<?php echo $page - 1;?>">past</a> <!--show the last page-->
<?php
}
for ($i=1;$i<=$totalPage;$i++) {  //recyle to show the page
?>
<a href="test1.php?page=<?php echo $i;?>"><?php echo $i ;?></a>
<?php
}
if ($page<$totalPage) { //if page smalled than the total page, show the link of next page
?>
<a href="test1.php?page=<?php echo $page + 1;?>">next</a>
<?php
} 
?>
</body>
</html>