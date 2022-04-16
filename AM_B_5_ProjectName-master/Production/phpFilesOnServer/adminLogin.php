<?php
require "db_connect.php";

$run = TRUE;
$loggedIn = FALSE;
$table = NULL;

while($run){
  $loggedIn = login();
  /**
     * Loop continuously allowing admin to select option to edit database tables
     */
  while($loggedIn){
    echo "-- SQL SERVER OPTIONS --" . PHP_EOL
       . "1 - Create table" . PHP_EOL
       . "2 - Insert into table" . PHP_EOL
       . "3 - Delete from table" . PHP_EOL
       . "4 - Update table" . PHP_EOL
       . "5 - Print table information" . PHP_EOL
       . "D - Drop table" . PHP_EOL
       . "Q - Logout" . PHP_EOL;
       
       /**
        * 
        */
      $option = get_option();
      switch($option){
          case "1":
        echo banner("Table Creation");
        $success = create_table();
        if($success === FALSE)
          echo "Table not created." . PHP_EOL;
        else{
          mysqli_query($db, $success);
          echo "Table created!" . PHP_EOL;
        }
              break;
          case "2":
        echo banner("Insert Into Table");
        select_table();
              insert_into();
        break;
          case "3":
        echo banner("Delete From Table");
              select_table();
        delete_from();
              break;
      case "4":
        echo banner("Update Table");
        select_table();
        update_table();
        break;
          case "5":
        echo banner("Print Table");
              select_table();
              /**
                * Display all contents of the specified table.
                */
              $sql_cmd = "SELECT * FROM " . $table;
              $sql_res = mysqli_query($db, $sql_cmd);
              if(mysqli_num_rows($sql_res) > 0){
                  while($row = mysqli_fetch_assoc($sql_res)){
                      foreach($row as $i => $i_value)
                          echo $i . ": " . $i_value . PHP_EOL;
                      echo PHP_EOL;
                  }
              }else{
                  echo "Zero results." . PHP_EOL;
              }
              break;
      case "d":
      case "D":
        echo banner("Drop Table");
		echo PHP_EOL . "WARNING: This feature will IRREVERSIBLY delete an entire table from existence!".PHP_EOL;
		echo "Are you sure you want to continue?".PHP_EOL;
		if(yes()){
		  echo "NOTE: You will be prompted for confirmation once again after selecting the table.".PHP_EOL;
		  select_table();
		  echo "Are you sure you want to IRREVERSIBLY delete $table from existence?".PHP_EOL;
		  if(yes()){
			$sql_cmd = "DROP TABLE " . $table;
			mysqli_query($db, $sql_cmd);
			echo $table . " table dropped." . PHP_EOL;
		  }
		  echo "$table lives to fight another day.".PHP_EOL;
		}
        break;
      case "q":
      case "Q":
        $loggedIn = FALSE;
        break;
      default :
        echo $option . " is not a valid option." . PHP_EOL;
      }
  }// while loggedIn

}// while run

mysqli_close($db);

/**
  * Prompts user to specify what information they wish to delete from the data table
  * selected by select_table().
  * Concatenates input into finished SQL statement and executes statement, printing
  * statement indicating success/failure
  */
function delete_from(){
	global $db, $dbName, $table;
    $sql_insert_cmd = "DELETE FROM " . $table . " WHERE";
    $sql = "SHOW columns FROM $table";
    $sql_res = mysqli_query($db, $sql);
    if($sql_res === FALSE) echo "\nERROR\n" . PHP_EOL;
    else{
        $columns = array();
        $values = array();
        echo "Select column names:".PHP_EOL;
        while($row = mysqli_fetch_assoc($sql_res)){
            $entry = $row['Field'];
            echo $entry."? ";
            if(yes()){
                $columns[] = $entry;
            }
        }
        $count = count($columns);
        for($i = 0; $i < $count; $i++){
            echo $columns[$i]."'s value: ";
            $values[$i] = get_input();
            $sql_insert_cmd = $sql_insert_cmd." ".$columns[$i]."='".$values[$i]."' ";
            if($i < $count-1) $sql_insert_cmd = $sql_insert_cmd."AND";
        }
    }
    //echo $sql_insert_cmd.PHP_EOL;
    
    $sql_res = mysqli_query($db, $sql_insert_cmd);
    if($sql_res === FALSE) echo "\nERROR\n" . PHP_EOL;
    else echo "Entries successfully deleted from table.". PHP_EOL ."View using \"Print table information\" option.".PHP_EOL;
    
}

/**
 * Prompts user to select which values they wish to add to the table specified by select_table()
 * and prompts for the exact value and concatenates input into an SQL statement that inserts the
 * data into the specified table, printing message on success/failure.
 */
function insert_into(){
	global $db, $dbName, $table;
    $sql_insert_cmd = "INSERT INTO " . $table . "(";
    $sql = "SHOW columns FROM $table";
    $sql_res = mysqli_query($db, $sql);
    if($sql_res === FALSE) echo "\nERROR\n" . PHP_EOL;
    else{
        $columns = array();
        $values = array();
        echo "Indicate whether to include the column:".PHP_EOL;
        while($row = mysqli_fetch_assoc($sql_res)){
            $entry = $row['Field'];
            echo $entry."? ";
            if(yes()){
                $columns[] = $entry;
            }
        }
        $count = count($columns);
        for($i = 0; $i < $count; $i++){
                $sql_insert_cmd = $sql_insert_cmd.$columns[$i];
                if($i < $count-1) $sql_insert_cmd = $sql_insert_cmd.", ";
                else $sql_insert_cmd = $sql_insert_cmd.") VALUES(";
        }
        for($i = 0; $i < $count; $i++){
            echo $columns[$i]."'s value: ";
            $values[$i] = get_input();
            $sql_insert_cmd = $sql_insert_cmd."'".$values[$i]."'";
                if($i < $count-1) $sql_insert_cmd = $sql_insert_cmd.", ";
                else $sql_insert_cmd = $sql_insert_cmd.")";
        }
    }
    //echo $sql_insert_cmd.PHP_EOL;
    $sql_res = mysqli_query($db, $sql_insert_cmd);
    if($sql_res === FALSE) echo "\nERROR\n" . PHP_EOL;
    else echo "Values successfully inserted into table.". PHP_EOL ."View using \"Print table information\" option.".PHP_EOL;
}

/**
 * Prompts user to select which values they wish to update in the table specified by select_table()
 * and prompts for the exact value and concatenates input into an SQL statement that updates the
 * data into the specified table, printing message on success/failure.
 */
function update_table(){
	global $db, $dbName, $table;
    $sql_update_cmd = "UPDATE " . $table . " SET ";
    $sql = "SHOW columns FROM $table";
    $sql_res = mysqli_query($db, $sql);
    if($sql_res === FALSE) die("\nERROR: update_table(): query 1\n" . PHP_EOL);
    else{
        $columns = array();
        $values = array();
        
        // read in input of fields to update
        echo "Indicate whether to include the specified column:".PHP_EOL;
        $rowCount = 0;
        $rows = array();
        while($rows[$rowCount] = mysqli_fetch_assoc($sql_res)){
            $entry = $rows[$rowCount]['Field'];
            echo $entry."? ";
            if(yes()){
                $columns[] = $entry;
            }
            $rowCount++;
        }
        //echo "row count: ".$rowCount;
        $count = count($columns);
        for($i = 0; $i < $count; $i++){
            echo $columns[$i]."'s value: ";
            $values[$i] = get_input();
        }
        
        // append column, value pairs to the end of the sql command
        for($i = 0; $i < $count; $i++){
          if($i < $count-1)
            $sql_update_cmd = $sql_update_cmd.$columns[$i]."='".$values[$i]."', ";
          else
            $sql_update_cmd = $sql_update_cmd.$columns[$i]."='".$values[$i]."'";
        }
        
        // append the WHERE clause to limit update to a specific entry
        $whereClause = " WHERE ";
        echo $whereClause."?\nChoose column:".PHP_EOL;
        for($i = 0; $i < $rowCount; $i++){
          echo ($i + 1)." - ".$rows[$i]['Field'].PHP_EOL;
        }
        $option = get_number(1, $rowCount + 1);
        $whereClause = $whereClause.$rows[$option - 1]['Field']." = ";
        echo $whereClause."?\nEnter value: ".PHP_EOL;
        $input = get_input();
        $sql_update_cmd = $sql_update_cmd.$whereClause."'$input'";
    }
    echo "UPDATE TABLE COMMAND:\n".$sql_update_cmd.PHP_EOL;
    $sql_res = mysqli_query($db, $sql_update_cmd);
    if($sql_res === FALSE){
      echo "No change was made to the table.".PHP_EOL;
      echo "Either the SQL statement had an error, or the WHERE clause did not match any fields.". PHP_EOL;
    }
    else echo $table." table successfully updated.". PHP_EOL ."View using \"Print table information\" option.".PHP_EOL;
}// update table


/**
 * Prompts user to input information for table and field names and uses input
 * to generate an SQL statement creating the specified table. Prints message
 * indicating success/failure.
 */
function create_table(){
	$table_name = get_name("table");
	echo "How many columns?" . PHP_EOL;
	$option = get_number(0, 100);
  $sql_cmd = "CREATE TABLE " . $table_name . " ( ";
  $cols = array();
  $types = array();
  $extras = array();
  $in = fopen("php://stdin", "r");
  for($i = 0; $i < $option; $i++){
    $cols[] = get_name("column " . ($i + 1) );
    echo "Enter " . $cols[$i] . "'s data type (no type checking): ";
    $input = fgets($in, 64);
    $input = rtrim($input);
    $types[] = $input;
    echo "Enter optional attributes for " . $cols[$i] . "(no checking, [ENTER] to skip): ";
    $input = fgets($in, 64);
    $input = rtrim($input);
    $extras[] = $input;
    $sql_cmd = $sql_cmd . $cols[$i] . " " . $types[$i] . " " . $extras[$i];
    if($i !== $option - 1) $sql_cmd = $sql_cmd . ", ";
    else $sql_cmd = $sql_cmd . ")";
  }
  fclose($in);
  return $sql_cmd;
}

/**
 * Displays all tables in the database and prompts the user to select the table they wish to edit.
 * Sets global table variable used by other functions.
 */
function select_table(){
	global $db, $dbName;
	$sql_cmd = "SELECT table_name
				FROM INFORMATION_SCHEMA.TABLES
				WHERE TABLE_SCHEMA = '$dbName'";
    $sql_res = mysqli_query($db, $sql_cmd);
	$rows = mysqli_num_rows($sql_res);
	if($rows > 0){
		echo "Select a table:" . PHP_EOL;
		$count = 0;
		while($row = mysqli_fetch_assoc($sql_res)){
			echo ($count+1). " - " . $row['table_name'] . PHP_EOL;
			$res_arr[] = $row['table_name'];
			$count = $count + 1;
		}
	}else{
		die("No tables in database.");
	}
  $option = get_number(1, $count);
  $GLOBALS['table'] = $res_arr[$option - 1];
  echo "Selected " . $GLOBALS['table'] . " table." . PHP_EOL;
}

/**
 * read input from the command line limited to numbers and letters
 */
function get_name($name){
	$in = fopen("php://stdin", "r");
	do{
		$cont = FALSE;
		echo "Enter " . $name . " name (one word of letters and numbers) : ";
		$input = fgets($in, 64);
		$input = rtrim($input);
		$len = strlen($input);
		for($j = 0; $j < $len; $j++){
			$char = substr( $input, $j, 1 );
			if(!ctype_alpha($char) && !ctype_digit($char)){
				echo "Invalid name." . PHP_EOL;
				$cont = TRUE;
				break;
			}
		}
	}while($cont);
	fclose($in);
	return $input;
}

/** read string yes or no
 */
function yes(){
	$in = fopen("php://stdin", "r");
	do{
		$cont = FALSE;
		echo "(yes/no): ";
		$input = fgets($in, 64);
		$input = rtrim($input);
        if($input === "yes" || $input === "y") return TRUE;
        elseif($input === "no" || $input === "n") return FALSE;
        else{
            echo "Pick yes or no... it's not difficult..." . PHP_EOL;
            $cont = TRUE;
        }
	}while($cont);
	fclose($in);
	return $input;
}

/** prompt user to enter option number and read entry
 */
function get_number($min, $max){
    $in = fopen("php://stdin", "r");
	do{
		$cont = FALSE;
		echo "Enter number: ";
		$input = fgets($in, 64);
		$input = rtrim($input);
		if(!is_numeric($input)){
			$cont = TRUE;
			echo "Must enter a number." . PHP_EOL;
		}
    // input is a number
    $number = intval($input, 10);
    if($min){
      // minimum value enforced
      if($number < $min){
        $cont = TRUE;
        echo "Number must be greater than or equal to ".$min.".".PHP_EOL;
      }
    }
    if($max){
      // maximum value enforced
      if($number > $max){
        $cont = TRUE;
        echo "Number must be less than or equal to ".$max.".".PHP_EOL;
      }
    }
	}while($cont);
    fclose($in);
    return $number;
}

function get_option(){
    $in = fopen("php://stdin", "r");
	do{
		$cont = FALSE;
		echo "Enter choice: ";
		$input = fgets($in, 64);
		$input = rtrim($input);
		if(!ctype_digit($input) && !ctype_alpha($input)){
			$cont = TRUE;
			echo "Select an option from the menu." . PHP_EOL;
		}
	}while($cont);
    fclose($in);
    return $input;
}

function get_input(){
    $in = fopen("php://stdin", "r");
	$input = fgets($in, 64);
	$input = rtrim($input);
    fclose($in);
    return $input;
}

/**
 * Prompts the user for username and password which are concatenated into an SQL statement
 * which querys the user table for a matching login. 
 */
function login(){
	global $db, $dbName, $run;
  echo banner("Server Login");
  $in = fopen("php://stdin", "r");
  echo "username: ";
  $user = fgets($in, 64);
  $user = rtrim($user);
  if($user === "exit" or $user === "Exit" or $user === "quit" or $user === "Quit"){
    $run = FALSE;
    return FALSE;
  }
  $sql_login = "SELECT password FROM Users WHERE Username = '$user' AND UserType = 'a'";
  $sql_res = mysqli_query($db , $sql_login);
  $row = mysqli_fetch_assoc($sql_res);
  echo "password: ";
  $pswd = fgets($in, 64);
  $pswd = rtrim($pswd);
  //echo $pswd." vs ".$row['password'].PHP_EOL;
  if($pswd === $row['password']) return TRUE;
  else{
      echo "Incorrect login/password".PHP_EOL;
  }
  fclose($in);
  return FALSE;
}

/**
 * Print a banner with the text specified by $text
 * @param $text String containing text to display in banner.
 */
function banner($text){
	$banner_text = $text;
	$textLength = strlen($text);
	$extraSpace = 20 - $textLength;
	if($extraSpace < 0) die("Banner text too long.");
	elseif($extraSpace > 0){
		$spacing = $extraSpace / 2;
		for($i = 0; $i < floor($spacing); $i++) $banner_text = " " . $banner_text;
		for($i = 0; $i < ceil($spacing); $i++) $banner_text = $banner_text . " ";
	}
	$banner = PHP_EOL
	   . "||++++++++++++++++++++||" . PHP_EOL
	   . "||                    ||" . PHP_EOL
	   . "||" . $banner_text . "||" . PHP_EOL
	   . "||                    ||" . PHP_EOL
	   . "||++++++++++++++++++++||" . PHP_EOL . PHP_EOL;
	return $banner;
}
?>