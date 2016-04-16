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
$response["uids"] = array();

$mid = $_POST['mid'];
// $mid = 1;

$sql_uid = "SELECT * FROM meeting_user WHERE mid = $mid";
$sqlResult_uid = mysqli_query($connection, $sql_uid);

while($row_uid = mysqli_fetch_assoc($sqlResult_uid)){
	array_push($response["uids"], $row_uid);
}

//echo json_encode($response);
mysqli_free_result($sqlResult_uid);
mysqli_close($connection);
?>