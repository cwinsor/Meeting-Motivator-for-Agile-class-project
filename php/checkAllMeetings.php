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

$uid = $_POST['uid'];
//$uid = 1;

// step 1. get all mids from "meeting_user" table
$sql = "SELECT mid FROM meeting_user WHERE uid = $uid";
$sqlResult = mysqli_query($connection, $sql);
while($row = mysqli_fetch_assoc($sqlResult)) {
	// step 2. get detail information of a specific meeting
	$mid = $row["mid"];
	$sql_detail = "SELECT * FROM meeting WHERE mid = $mid";
	$sqlResult_detail = mysqli_query($connection, $sql_detail);
	$row_detail = mysqli_fetch_assoc($sqlResult_detail);

	$singleResponse = array();
	$singleResponse["meetingName"] = $row_detail["meetingName"];
	$singleResponse["location"] = $row_detail["location"];
	$singleResponse["latitude"] = $row_detail["latitude"];
	$singleResponse["longitude"] = $row_detail["longitude"];
	$singleResponse["meetingTime"] = $row_detail["meetingTime"];
	$singleResponse["organizerId"] = $row_detail["organizerId"];

	array_push($response, $singleResponse);	
	mysqli_free_result($sqlResult_detail);
}
mysqli_free_result($sqlResult);
echo json_encode($response);
mysqli_close($connection);

?>