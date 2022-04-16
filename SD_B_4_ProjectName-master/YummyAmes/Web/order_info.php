<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Order information</title>
</head>

<body>
 
 <?php
	$_SESSION[o_id]=$_GET['id'];
	$sql1 = "select o_res from Orderlist where o_id='".$_SESSION[o_id]."'";
	$query1=mysql_query($sql1);
	$rows1=mysql_fetch_array($query1); 
	
	$sql2 = "select r_name, r_address from Restaurant where r_id='".$rows1[0]."'";
	$query2=mysql_query($sql2);
	$rows2=mysql_fetch_array($query2); 
	

	$sql = "select * from Orderlist where o_id='".$_SESSION[o_id]."'";
	$query=mysql_query($sql);
	$rows=mysql_fetch_array($query); 
?>

<form id="form1" name="form1" action="login.php" method="post">
  <table width="393" height="248" border="0" align="center" cellspacing="0">
    <tbody>
      <tr>
        <td height="50" colspan="2" align="center" bgcolor="#48A8FB">Order information</td>
      </tr>
      <tr>
        <td width="166" height="50" align="center">Restaurant name:</td>
        <td width="223"><?php echo $rows2[0]?></td>
      </tr>
      <tr>
        <td width="166" height="50" align="center">Customer name:</td>
        <td width="223"><?php echo $rows[2]?></td>
      </tr>
      <tr>
        <td height="60" align="center">Customer Phone:</td>
        <td><?php echo $rows[3]?></td>
      </tr
	   <tr>
        <td height="60" align="center">Restaurant Address:</td>
        <td><?php echo $rows2[1]?></td>
      </tr>
      
      <tr>
        <td height="60" align="center">Customer Address:</td>
        <td><?php echo $rows[4]?></td>
      </tr>
      
      <tr>
        <td height="60" align="center">Price:</td>
        <td><?php echo $rows[1]?></td>
      </tr>

    </tbody>
  </table>
</form>
<p>&nbsp;</p>

 <style>
      #map {
		  height: 400px;
		  width: 50%;
		  margin:0px auto;
      }
      html, body {
        height: 50%;
        margin: 0;
        padding: 0;
      }

    </style>
      
<tr><div id="map"></div>
	 <script> var start = "<?php echo $rows2[1]?>"; 
		var end = "<?php echo $rows[4]?>";
	  </script>  
<script src="direction.js"> </script> 
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDPs8OwdPE-LPifz-nWUJcA7rIvTRl6O8o&callback=initMap">
    </script>
        </tr>
        
  <p><a href="o_list.php" target="_self">Go back</a></p>
</body>
</html>