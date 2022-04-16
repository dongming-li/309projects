<?php
include('regsave.php');

$id     = $_GET['id'];
$sql    = "select * from Food where id='$id'";
$result = mysql_query($sql, $conn);
if (!$result)
    die("读取图片失败！");
$num = mysql_num_rows($result);
if ($num < 1)
    die("暂无图片");
$data = mysql_result($result, 0, 'pic');
$type = mysql_result($result, 0, 'type');
mysql_close($id);
Header("Content-type: $type");
echo $data;
?>