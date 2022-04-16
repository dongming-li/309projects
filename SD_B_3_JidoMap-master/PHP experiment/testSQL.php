<?php 
$con=mysqli_connect("mysql.cs.iastate.edu","dbu309sdb3","ZRTgf2B0","db309sdb3"); 
if (!$con) 
{ 
    die("Link Error: " . mysqli_connect_error()); 
} 
else{
	echo "Hello world!<br>";
}

//================================================================
//INSERT
//================================================================
//mysqli_query($con,"INSERT INTO student VALUES(2334,'as2dasd','34')");


//================================================================
//DELETE
//================================================================
//mysqli_query($con,"DELETE FROM student WHERE stuid=2334");

//================================================================
//SELECT
//================================================================

$sql="SELECT * FROM student";


if ($result=mysqli_query($con,$sql))
{
    // fetch one by one
    while ($row=mysqli_fetch_row($result))
    {
        printf ("%s : %s : %s",$row[0],$row[1],$row[2]);
        echo "<br>";
    }
    
    // 释放结果集合
    mysqli_free_result($result);
}

//================================================================
//Add table
//================================================================
// $sql="create table student3  
// (  
//     stuid int primary key auto_increment,  
//     stuName varchar(40) not null ,
//     age varchar(10) not null
// ); ";
mysqli_query($con,$sql);

?>