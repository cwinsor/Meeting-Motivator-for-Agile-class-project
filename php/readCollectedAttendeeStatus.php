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
$uids = $_POST['uids']; 
// $mid = 14;
// $uids = array(1, 2, 3);

for ($i = 0; $i < count($uids); $i++) {
	$singleResponse = array();

	$uid = $uids[$i];

	//step 1. get username from "user" table
	$sql_username = "SELECT username FROM user WHERE uid = $uid";
    $sqlResult_username = mysqli_query($connection, $sql_username);
    $row_username = mysqli_fetch_assoc($sqlResult_username);
    $singleResponse["username"] = $row_username["username"];

    //step 2. get user's status from "meeting-user" table 
	$sql_status = "SELECT * FROM meeting_user WHERE mid = $mid AND uid = $uid";
	$sqlResult_status = mysqli_query($connection, $sql_status);
	$row_status = mysqli_fetch_assoc($sqlResult_status);

	
	$singleResponse["userLatitude"] = $row_status["userLatitude"];
	$singleResponse["userLongitude"] = $row_status["userLongitude"];
	$singleResponse["activity"] = $row_status["activity"];
	$singleResponse["arrivalTime"] = $row_status["arrivalTime"];
	$singleResponse["arrivalStatus"] = $row_status["arrivalStatus"];
	array_push($response, $singleResponse);	
	
	//echo json_encode($singleResponse);
	mysqli_free_result($sqlResult_username);
	mysqli_free_result($sqlResult_status);
} 

echo json_encode($response);
mysqli_close($connection);
?>