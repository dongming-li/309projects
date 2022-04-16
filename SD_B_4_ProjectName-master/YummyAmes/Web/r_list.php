<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Rstaurant List</title>
<link href="styles.css" rel="stylesheet" type="text/css" />
</head>

<body>


<?php 
	if($_POST["Search"]){
	$_SESSION[r_name]=$_POST["res"];
	
		if($_SESSION[r_name]=="Please input the name of the restaurant"){
	$_SESSION[r_name]="";
		}
	if($_SESSION[r_name]==""){
		echo "<script language='javascript'>alert('the name cannot be null!');location.href='javascript:history.go(-1)';</script>";
	}else{
		$sql="select * from Restaurant where r_name='".$_SESSION[r_name]."' ";
		$query=mysql_query($sql);
		$num=mysql_num_rows($query);
		if($num==0){
		echo "<script language='javascript'>alert('Do not have this restaurant!');location.href='javascript:history.go(-1)';</script>";
		
		}else{
			$_SESSION[search] = 1; 
		}
	}}
	
	
	?>

<?php
	if($_SESSION[search]==""){
?>

<form id="form1" name="form1" action="r_list.php" method="post">
<div class="barse">
<input  class="email" name="res" type="text" id="res" value="Please input the name of the restaurant" onfocus="javascript:if(this.value=='Please input the name of the restaurant')this.value='';" >
<input type="submit" class="addemail" name="Search" id="Search" value="Search" >

</div>
</form>

<div class="n2">
<?php

$_SESSION[r_id]="";
$_SESSION[r_name]="";
$_SESSION[fo_name] = 0; 

$perNumber=6; 
$page=$_GET['page']; 
$count=mysql_query("select count(*) from Restaurant"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Restaurant limit $startCount,$perNumber"); 
 
echo "<table border='0'>";
echo "<tr>";
echo "<th>Restaurant ID</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Restaurant Name</th>";
echo "<th>Address</th>";
echo "<th>&nbsp;&nbsp;&nbsp;Phone Number</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; Operator</td>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row[1]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row[3]</td>"; ?>
 <td><p><a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">See the information</a></p></td>
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
</div>
<p><a href="c_main.php" target="_self">Return customer main page</a></p>
<p><a href="RL.php" target="_self">Return Web main page</a></p>

<?php
	}else{
	$_SESSION[search] = ""; 
		$result1=mysql_query("select * from Restaurant  where r_name='".$_SESSION[r_name]."' "); 
		$row1=mysql_fetch_array($result1);
?>
<div class="n2">
<?php
echo "<table border='0'>";
echo "<tr>";
echo "<th>Restaurant ID</th>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;Restaurant Name</th>";
echo "<th>Address</th>";
echo "<th>&nbsp;&nbsp;&nbsp;Phone Number</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp; Operator</td>";
echo "</tr>";
		
echo "<tr>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row1[0]</td>"; 
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$row1[1]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;$row1[2]</td>";
 echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp$row1[3]</td>"; ?>
 <td><p><a href="r_info.php?id=<?php echo $row1[0] ?>" target="_self">See the information</a></p></td>
<?php
echo "</tr>";
echo "</table>";
?>
	</div>
<p><a href="r_list.php" target="_self">Return past page</a></p>
<p><a href="c_main.php" target="_self">Return customer main page</a></p>
<?php
	}
?>
</body>
</html>