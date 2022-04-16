<?php include("regsave.php");?>
<!doctype html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Food Delivery | Restaurants Delivery | YummyAmes Order Online</title>
	<style>
		
		
		#header {
			float: left;
			background-color: #D80000;
			color: #E0E0E0;
			width: 70%;
			height: 80px;
			padding: 0px;
			letter-spacing: 4px;
			font-weight: bold;
			text-align: left;
		}
		
		#login {
			float: right;
			background-color: #D80000;
			width: 30%;
			height: 80px;
			text-align: center;
			padding: 0px;
			text-decoration: none;
			color: black;
			text-decoration: none;
		}
		
		#head {
			color: #D80000;
			width: 100%;
			height: 150px;
			text-align: center;
		}
		
		#end{
			background-color: #D80000;
			float:right;
			width:100%;
			height: 150px;
			color: black;
			text-align: right;
			padding: 15px;
		}
		
		.tu {
			width: 240px;
			height: 153px;
			margin-top: 20px;
			float: left;
			margin-left: 15px;
			cursor: pointer;
			padding: 15px;
		}
		
		.tu img {
			width: 100%;
			height: 160px;
		}
	</style>
</head>

<body background-color: red>
	<div id="header">
		<h1>YummyAmes</h1>
	</div>
	<div id="login">
		<p>
			<a href="RL.php">
			<img border="0" src="/login.png" width="30" height="30"/>
			</a>
		

			<br/>
			<a href="RL.php">Login/SignUp</a>
		</p>
	</div>

	<div id="head">
		<br/>
		<br/>
		<br/>
		<br/>
		<h2>Check out our dailly recomedation restaurants </h2>
	</div>
	<?php
		$_SESSION[ r_id ] = "";
		$_SESSION[ r_name ] = "";
		$_SESSION[ fo_name ] = 0;
		$perNumber = 6;
		$page = $_GET[ 'page' ];
		$count = mysql_query( $conn, "select count(*) from Restaurant " );
		$rs = mysql_fetch_array( $count );
		$totalNumber = $rs[ 0 ];
		$totalPage = ceil( $totalNumber / $perNumber ); //calculate the total page
		if ( !isset( $page ) ) {
			$page = 1;
		}
		$startCount = ( $page - 1 ) * $perNumber;
		$result = mysql_query( $conn, "select * from Restaurant limit $startCount,$perNumber" );
		$x = 1;

		while ( $row = mysql_fetch_array( $result )and $x == "1" ) {	
			?>
	<td>
		<div class="tu" onclick="jsw()">
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">			
			<img src="fd1.jpg"/>
			</a>
		
			<br/>
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">
				<?php echo $row[1] ?>
			</a>
		</div>
	</td>
	<?php
	$x++;
	}

	$startCount = ( $page - 1 ) * $perNumber + 1;
	$result = mysql_query( $conn, "select * from Restaurant limit $startCount,$perNumber" );
	$x = 1;
	while ( $row = mysql_fetch_array( $result )and $x == "1" ) {
		?>
	<td>
		<div class="tu" onclick="jsw()">
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">			
			<img src="fd2.jpg"/>
			</a>
		
			<br/>
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">
				<?php echo $row[1] ?>
			</a>
		</div>
	</td>
	<?php
	$x++;
	}

	$startCount = ( $page - 1 ) * $perNumber + 2;
	$result = mysql_query( $conn, "select * from Restaurant limit $startCount,$perNumber" );
	$x = 1;
	while ( $row = mysql_fetch_array( $result )and $x == "1" ) {
		?>
	<td>
		<div class="tu" onclick="jsw()">
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">			
			<img src="fd3.jpg"/>
			</a>
		
			<br/>
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">
				<?php echo $row[1] ?>
			</a>
		</div>
	</td>
	<?php
	$x++;
	}

	$startCount = ( $page - 1 ) * $perNumber + 3;
	$result = mysql_query( $conn, "select * from Restaurant limit $startCount,$perNumber" );
	$x = 1;
	while ( $row = mysql_fetch_array( $result )and $x == "1" ) {
		?>
	<td>
		<div class="tu" onclick="jsw()">
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">			
			<img src="fd4.jpg"/>
			</a>
		
			<br/>
			<a href="r_info.php?id=<?php echo $row[0] ?>" target="_self">
				<?php echo $row[1] ?>
			</a>
		</div>
	</td>
	<?php
	$x++;
	} 
	?>
	
<div class="end">
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<a href="r_list.php">
			>See All Restaurants<
		</a>
	</div>

</body>

</html>