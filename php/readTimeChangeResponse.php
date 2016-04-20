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

    //step 2. get user's response from "meeting-user" table 
	$sql_response = "SELECT userRole, okWithTimeChange, anotherTime FROM meeting_user WHERE mid = $mid AND uid = $uid";
	$sqlResult_response = mysqli_query($connection, $sql_response);
	$row_response = mysqli_fetch_assoc($sqlResult_response);

	if($row_response["userRole"] == "Attendee"){
		$singleResponse["okWithTimeChange"] = $row_response["okWithTimeChange"];
	    $singleResponse["anotherTime"] = $row_response["anotherTime"];
	    array_push($response, $singleResponse);	
	}
	
	//echo json_encode($singleResponse);
	mysqli_free_result($sqlResult_username);
	mysqli_free_result($sqlResult_response);
} 

//echo json_encode($response);
mysqli_close($connection);
?>