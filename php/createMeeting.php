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
$meetingName = $_POST['meetingName'];
$location = $_POST['location'];
$latitude = $_POST('latitude');
$longitude = $_POST('longitude');
$time = $_POST('time');
$organizer = $_POST('organizer');

// $meetingName = 'Sprint Kickoff test2';
// $location = 'FL 210';
// $latitude = 47.23;
// $longitude = -56.66;
// $meetingTime = '2016-04-14 23:00';
// $organizerId = 1;

// step 1. insert into meeting table
$sql1 =  "INSERT INTO meeting(mid, meetingName, location, latitude, longitude, meetingTime, organizerId, meetingStatus, timeNeedsChange) VALUES (null, '$meetingName', '$location', $latitude, $longitude, '$meetingTime', $organizerId, 0, 0)";
$sqlResult1 = mysqli_query($connection, $sql1);

// step 2. insert into meeting_user table
$mid = mysqli_insert_id($connection);
//echo "meeting id: ".($mid);
$sql2 = "INSERT INTO meeting_user(mid, uid, userRole, userLatitude, userLongitude, activity, arrivalTime, arrivalStatus, okWithTimeChange, anotherTime) VALUES ($mid, $organizerId , 'Organizer', null, null, null, null, null, null, null)";
$sqlResult2 = mysqli_query($connection, $sql2);

if($sqlResult1 && $sqlResult2) {
	$response["success"] = 1;
	$response["message"] = "A meeting is successfully created!";
} else {
	$response["success"] = 0;
	$response["message"] = "An Exception Occured!";
}

//echo json_encode($response);
mysqli_close($connection);
?>