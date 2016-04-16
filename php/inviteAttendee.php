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
$response["success"] = 1;
$response["message"] = array();

$mid = $_POST['mid'];
$uids = $_POST['uids']; // expect a json array of uid sent from andriod app
// $mid = 1;
// $uids = array(1, 2, 3); 

for ($i = 0; $i < count($uids); $i++) {
	$uid = $uids[$i];
	$sql = "INSERT INTO meeting_user(mid, uid, userRole, userLatitude, userLongitude, activity, arrivalTime, arrivalStatus, okWithTimeChange, anotherTime) VALUES ($mid, $uid , 'Attendee', null, null, null, null, null, null, null)";
    $sqlResult = mysqli_query($connection, $sql);
    if(!$sqlResult) {
    	$response["success"] = 0;
	    $message = "Failed to invite user ".$uid;
	    array_push($response["message"], $message);
    }    
}

//echo json_encode($response);
mysqli_close($connection);
?>