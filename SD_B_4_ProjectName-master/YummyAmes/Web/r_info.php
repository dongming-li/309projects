<?php include("regsave.php");?>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Restaurant information</title>

<!--STYLESHEETS-->
<link href="css/style3.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>

<?php
	if($_SESSION[r_id]==""){
	$_SESSION[r_id]=$_GET['id'];}
	$_SESSION[res]=$_SESSION[r_id];
	$sql = "select * from Restaurant where r_id='".$_SESSION[r_id]."'";
	$query=mysql_query($sql);
	$rows=mysql_fetch_array($query); 
?>

<div id="wrapper">
<form id="form1" name="form1" class="login-form" action="r_info.php" method="post">
     <div class="header">
    <!--TITLE--><h1>Restaurant information</h1><!--END TITLE-->
  <div class="content"> 
    </div>
  <table width="350" height="248" border="0" align="center" cellspacing="0">
    <tbody>

      <tr>
        <td width="166" height="50" align="center">Restaurant name:</td>
        <td width="223"><?php echo $rows[1]?></td>
      </tr>
      <tr>
        <td height="60" align="center">Phone number:</td>
        <td><?php echo $rows[3]?></td>
      </tr
	   <tr>
        <td height="60" align="center">Email:</td>
        <td><?php echo $rows[4]?></td>
      </tr>
      
      <tr>
        <td height="60" align="center">Address:</td>
        <td><?php echo $rows[2]?></td>
      </tr>


    </tbody>
  </table>
  </div>

          <div class="footer">
    <!--LOGIN BUTTON--><!--END LOGIN BUTTON-->
    <!--REGISTER BUTTON-->
    <a href="r_list.php" class="button">Go back</a>
    <a href="foodshow.php" class="button">Menu</a>
    </div>
</form>

</div>
<p>&nbsp;</p>

<style>
#map, #pano {

	height: 300px;
	width: 400px;
	margin:0px auto;
}
</style>

<div class="rbox">
<tr><div id="map"></div>
<div id="pano"></div>
<script> var address = "<?php echo $rows[2] ?>"; 
		var description = '<?php echo $rows[1] ?>';
	  </script> 
<script src="maps.js"> </script> 
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDPs8OwdPE-LPifz-nWUJcA7rIvTRl6O8o&callback=initMap">
    </script>
    </tr>

</div>
</body>
</html>