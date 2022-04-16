<?php  	session_start();
		$conn= @mysql_connect("mysql.cs.iastate.edu","dbu309sdb4","ZvtQfv!x") or die("fail to connect to mysql");
		@mysql_select_db("db309sdb4",$conn) or die("fail to open the database");
		mysql_query("set names 'GBK'");
			
?>