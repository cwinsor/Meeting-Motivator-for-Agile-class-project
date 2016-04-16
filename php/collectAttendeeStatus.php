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

$uids = $_POST['uids']; // expect a json array of uid sent from andriod app
$userLatitude = $_POST('userLatitude');
$userLongitude = $_POST('userLongitude');
$activity = $_POST('activity');
$arrivalTime = $_POST('arrivalTime');
$arrivalStatus = $_POST('arrivalStatus');

// $uids = array(1, 2, 3);
// $userLatitude = 43.22;
// $userLongitude = -60.11;
// $activity = 'Driving';
// $arrivalTime = '2016-07-09 11:00am';
// $arrivalStatus = 1;

for ($i = 0; $i < count($uids); $i++) {
	$uid = $uids[$i];
	$sql = "UPDATE meeting_user SET userLatitude=$userLatitude, userLongitude=$userLongitude, activity='$activity', arrivalTime='$arrivalTime', arrivalStatus=$arrivalStatus WHERE uid=$uid";
    $sqlResult = mysqli_query($connection, $sql);
    //echo "Try to collect info of ".$uid;
    if(!$sqlResult) {
    	$response["success"] = 0;
	    $message = "Failed to collect status information of ".$uid;
	    array_push($response["message"], $message);
    }    
}

//echo json_encode($response);
mysqli_close($connection);
?>