<?php
$dbhost = "dhcp-212-228.cs.dartmouth.edu";
$dbuser = "root";
$dbpass = "147147147";
$dbname = "CS528MeetingManager";
$connection = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname);

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

mysqli_close($connection);
?>