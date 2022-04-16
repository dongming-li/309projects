<?php include("regsave.php");?>
<?php
	unset($_SESSION[name]);
	unset($_SESSION[pass]);
	//session_destroy();
	echo "<script language='javascript'>alert('You have login out!');location.href='RL.php';</script>";
?>


