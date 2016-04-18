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
$uid = $_POST['uid']; 
$okWithTimeChange = $_POST['okWithTimeChange'];
$anotherTime = $_POST['anotherTime'];

// $mid = 1;
// $uid = 3; 
// $okWithTimeChange = 0;
// $anotherTime = '2017-01-01';

$sql = "UPDATE meeting_user SET okWithTimeChange = $okWithTimeChange, anotherTime = '$anotherTime' WHERE mid = $mid and uid = $uid";
$sqlResult = mysqli_query($connection, $sql);
if($sqlResult) {
	$response["success"] = 1;
	$response["message"] = "Successfully responsed to intended time change";	   
} 
else {
	$response["success"] = 0;
	$response["message"] = "An exception occured!";
}  

//echo json_encode($response);
mysqli_close($connection);
?>