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
$meetingname = $_POST['meetingname'];
$location = $_POST['location'];
$latitude = $_POST('latitude');
$longitude = $_POST('longitude');
$time = $_POST('time');
$organizer = $_POST('organizer');

// $meetingname = 'Sprint Kickoff test2';
// $location = 'FL 210';
// $latitude = 47.23;
// $longitude = -56.66;
// $meetingTime = '2016-04-14 23:00';
// $organizerId = 1;

$sql =  "INSERT INTO meeting(mid, meetingname, location, latitude, longitude, meetingTime, organizerId, meetingStatus, timeNeedsChange) VALUES (null, '$meetingname', '$location', $latitude, $longitude, '$meetingTime', $organizerId, 0, 0)";
$sqlResult = mysqli_query($connection, $sql);

if($sqlResult) {
	$response["success"] = 1;
	$response["message"] = "A meeting is successfully created!";
} else {
	$response["success"] = 0;
	$response["message"] = "An Exception Occured!";
}

//echo ($response["message"]);
mysqli_close($connection);
?>