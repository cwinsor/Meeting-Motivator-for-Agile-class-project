<?php
require_once('./db_config.php');
//$connection = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
//$connection = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD) or die(mysql_error());
//$connection = mysql_connect(DB_HOST, "foo", DB_PASSWORD) or die(mysql_error());
//$connection = mysql_connect("localhost", "user1", "user1password") or die(mysql_error());
//$db = mysql_select_db("CS528MeetingManager") or die(mysql_error()) or die(mysql_error());
//$connection = mysqli_connect("localhost", "chriswinsor", "chriswinsorpassword", "cs528");
//$connection = mysqli_connect("localhost", "chriswinsor", "chriswinsorpassword", "CS528MeetingManager");
$connection = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

if(mysqli_connect_errno()){
	die("Database connection failed: "
		. mysqli_connect_error() 
		. " (the error number is: " 
			. mysqli_connect_errno() 
			. ")"
	);
}

$response = array();
// $username = $_POST['username'];
// $password = $_POST['password'];
 $username = 'test';
 $password = '123';

$sql_name_taken = "SELECT * FROM user WHERE username = '$username'";
$sqlResult_name_taken = mysqli_query($connection, $sql_name_taken);
$row_name_taken = mysqli_fetch_assoc($sqlResult_name_taken);

if(sizeof($row_name_taken)!= 0){
	$response["signUpSuccess"] = 0;
	$response["message"] = "The Username Has Already Taken!";
}
else{
	$sql_insert = "INSERT INTO user(uid, username, password) "."VALUES (null, '$username', '$password')";
	$sqlResult_insert = mysqli_query($connection, $sql_insert);
	if($sqlResult_insert) {
		$response["success"] = 1;
	    $response["message"] = "Welcome to Meeting Manager!";
	}
	else {
		$response["success"] = 0;
	    $response["message"] = "A Database Exception Occured!";
	}
}
echo ($response["message"]);
mysqli_free_result($sqlResult_name_taken);
mysqli_close($connection);
?>