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

$mid = $_POST['mid'];
$meetingTime = $_POST['meetingTime'];
// $mid = 4;
// $meetingTime = '2017-01-01 1:00';

$sql = "UPDATE meeting SET meetingTime = '$meetingTime' WHERE mid = $mid";
$sqlResult = mysqli_query($connection, $sql);
if($sqlResult) {
	$response["success"] = 1;
	$response["message"] = "Meeting Time Successfully Changed!";	   
} 
else {
	$response["success"] = 0;
	$response["message"] = "An exception occured!";
}  

//echo json_encode($response);
mysqli_close($connection);
?>