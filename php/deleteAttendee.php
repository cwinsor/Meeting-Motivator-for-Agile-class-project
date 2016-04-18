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

// $mid = $_POST['mid'];
// $uid = $_POST['uid'];
$mid = 1;
$uid = 2;

$sql = "DELETE FROM meeting_user WHERE mid = $mid and uid = $uid";
$sqlResult = mysqli_query($connection, $sql);
if($sqlResult) {
	$response["success"] = 1;
	$response["message"] = "Successfully delete some attendees from one meeting!";	   
} 
else {
	$response["success"] = 0;
	$response["message"] = "An exception occured!";
}  

echo json_encode($response);
mysqli_close($connection);
?>