<?php  
header ("Content-type:text/html;charset=utf8_bin");  
  
   define('HOST','127.0.0.1');  
   define('USERNAME','root');  
   define('PASSWORD','liuao2017');  
  
//
 $con=mysql_connect(HOST,USERNAME,PASSWORD);  
//  
mysql_select_db('309aliu');  
  
//
  mysql_query('set names utf8_bin');  
?>  