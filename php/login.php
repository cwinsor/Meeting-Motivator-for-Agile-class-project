<?php
require_once('./db_config.php');
$connection = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

if(mysqli_connect_errno()){
	die("Database connecction failed: " 
		. mysqli_connect_error() 
		. " (the error number is: " 
			. mysqli_connect_errno() 
			. ")"
	);
}

$response = array();
$username = $_POST['username'];
$password = $_POST['password'];

$sql = "SELECT * FROM user WHERE username = '$username'";
$sqlResult = mysqli_query($connection, $sql);
$row = mysqli_fetch_assoc($sqlResult);
if($row["password"] != $password) {
	$response["loginSuccess"] = 0;
	$response["message"] = "Access Denied. Invalid user name or password.";
}
else {
	$response["loginSuccess"] = 1;
	$response["message"] = "Welcome!";
}
echo ($response["message"]);
mysqli_close($connection);
?>