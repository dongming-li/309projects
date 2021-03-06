<?php include("regsave.php");?>
<!doctype html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>User info</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script>

    

    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/premium.css">

</head>
<body class=" theme-blue">



    <script type="text/javascript">
        $(function() {
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ')
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
    </script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover { 
            color: #fff;
        }
    </style>

    <script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
    </script>


    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  

    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> YummyAmes</span></a></div>

        <div class="navbar-collapse collapse" style="height: 1px;">
          <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span><?php echo $_SESSION[a_name];?>
                    <i class="fa fa-caret-down"></i>
                </a>

              <ul class="dropdown-menu">
                <li><a href="./">My Account</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Admin Panel</li>
                <li><a href="./">Users</a></li>
                <li><a href="./">Security</a></li>
                <li><a tabindex="-1" href="./">Payments</a></li>
                <li class="divider"></li>
                <li><a tabindex="-1" href="a_login.php">Logout</a></li>
              </ul>
            </li>
          </ul>

        </div>
      </div>
    </div>
    

    <div class="sidebar-nav">
    <ul>
    <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> Dashboard<i class="fa fa-collapse"></i></a></li>
    <li><ul class="dashboard-menu nav nav-list collapse in">
            <li><a href="Administrator.php"><span class="fa fa-caret-right"></span> Main</a></li>
            <li class="active"><a href="user.php"><span class="fa fa-caret-right"></span> User List</a></li>
            <li ><a href="user_ch.php"><span class="fa fa-caret-right"></span> User Profile</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Media</a></li>
    </ul></li>

    <li data-popover="true" data-content="Items in this group require a <strong><a href='http://portnine.com/bootstrap-themes/aircraft' target='blank'>premium license</a><strong>." rel="popover" data-placement="right"><a href="#" data-target=".premium-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-fighter-jet"></i> Premium Features<i class="fa fa-collapse"></i></a></li>
        <li><ul class="premium-menu nav nav-list collapse">
            <li ><a href=""><span class="fa fa-caret-right"></span> Enhanced Profile</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Blog</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Blog Page</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Pricing Tables</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Upgrade Account</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Enhanced Users List</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Enhanced Media</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Invoice</a></li>
            <li ><a href="premium-colors.html"><span class="fa fa-caret-right"></span> Additional Color Themes</a></li>
    </ul></li>

        <li><a href="#" data-target=".accounts-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-briefcase"></i> Account <span class="label label-info">+3</span></a></li>
        <li><ul class="accounts-menu nav nav-list collapse">
            <li ><a href="a_login.php"><span class="fa fa-caret-right"></span> Sign In</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Sign Up</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Reset Password</a></li>
    </ul></li>

        <li><a href="#" data-target=".legal-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-legal"></i> Legal<i class="fa fa-collapse"></i></a></li>
        <li><ul class="legal-menu nav nav-list collapse">
            <li ><a href=""><span class="fa fa-caret-right"></span> Privacy Policy</a></li>
            <li ><a href=""><span class="fa fa-caret-right"></span> Terms and Conditions</a></li>
    </ul></li>

            
            </ul>
    </div>

    <div class="content">
        <div class="header">
            
            <h1 class="page-title">Users</h1>
                    <ul class="breadcrumb">
            <li><a href="Administrator.php">Home</a> </li>
            <li class="active">Users</li>
        </ul>

        </div>
        <div class="main-content">
            
<div class="btn-toolbar list-toolbar">
    <button class="btn btn-primary" onclick="javascrtpt:window.location.href="user_ch.php?id=<?php echo null ?>"" target="_self" ><i class="fa fa-plus"></i> New User</button>
    <button class="btn btn-default">Import</button>
    <button class="btn btn-default">Export</button>
  <div class="btn-group">
  </div>
</div>


<table class="table">
  <?php
 
$perNumber=6; 
$page=$_GET['page']; 
$count=mysql_query("select count(*) from Customer"); 
$rs=mysql_fetch_array($count); 
$totalNumber=$rs[0];
$totalPage=ceil($totalNumber/$perNumber); //calculate the total page
if (!isset($page)) {
 $page=1;
} 
$startCount=($page-1)*$perNumber; 
$result=mysql_query("select * from Customer limit $startCount,$perNumber"); 
 
echo "<table border='0'>";
echo "<tr>";
echo "<th>&nbsp;&nbsp;&nbsp;id &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>";
echo "<th>&nbsp;Customer ID &nbsp;&nbsp;&nbsp;</td>";
echo "<th>Customer Name &nbsp;&nbsp;&nbsp;</td>";
//echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;grade</td>";
echo "<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;operator</t>";
echo "</tr>";
while ($row=mysql_fetch_array($result)) {
 
echo "<tr>";
 echo "<td>$row[0]     </td>"; 
 echo "<td>$row[1]     </td>";
 //echo "<td>$row[2]     </td>";
 echo "<td>$row[5]     </td>";  //show the content of the database  ?>
	 <td>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="user_ch.php?id=<?php echo $row[1]?>"><i class="fa fa-pencil"></i></a>
          <a href="delete.php?id=<?php echo $row[0] ?>" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>
      </td> 
      <?php
echo "</tr>";
}
echo "</table>";
	?>
</table>

<ul class="pagination">
 <?php
if ($page != 1) { //page is not equal to 1
?>
<li><a href="user.php?page=<?php echo $page - 1;?>">&laquo;</a></li> <!--show the last page-->
<?php
}
for ($i=1;$i<=$totalPage;$i++) {  //recyle to show the page
?>
<li><a href="user.php?page=<?php echo $i;?>"><?php echo $i ;?></a></li>
<?php
}
if ($page<$totalPage) { //if page smalled than the total page, show the link of next page
?>
<li><a href="user.php?page=<?php echo $page + 1;?>">&raquo;</a></li>
<?php
} 
?>
</ul>

<div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">??</button>
            <h3 id="myModalLabel">Delete Confirmation</h3>
        </div>
        <div class="modal-body">
            <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete the user?<br>This cannot be undone.</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
            <button class="btn btn-danger" data-dismiss="modal">Delete</button>
        </div>
      </div>
    </div>
</div>


            <footer>
                <hr>

                
                <p>?? 2017 </p>
            </footer>
        </div>
    </div>


    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  
</body></html>
