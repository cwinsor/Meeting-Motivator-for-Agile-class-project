<?php

/*
 * Following code will create a new user row
 * All user details are from HTTP Post
 */

// array for JSON response
$response = array();

// check for required fields

if (isset($_POST[ "meetingName"])&&
    isset($_POST[ "location"]) &&
    isset($_POST[ "latitude"]) &&
    isset($_POST[ "longitude"]) &&
    isset($_POST[ "meetingTime"]) &&
    isset($_POST[ "organizerId"]) &&
    isset($_POST[ "meetingStatus"]) &&
    isset($_POST[ "timeNeedsChange"])) {

    $meetingName = $_POST[ "meetingName"];
    $location = $_POST[ "location"];
    $latitude = $_POST[ "latitude"];
    $longitude = $_POST[ "longitude"];
    $meetingTime = $_POST[ "meetingTime"];
    $organizerId = $_POST[ "organizerId"];
    $meetingStatus = $_POST[ "meetingStatus"];
    $timeNeedsChange = $_POST[ "timeNeedsChange"];


// include db connect class
    require_once __DIR__ . '/db_connect.php';

// connecting to db
    $db = new DB_CONNECT();

// mysql inserting a new row
// $sql = "INSERT INTO meeting (username, password) VALUES ('" . $username . "', '" . $password . "');";
    $sql = "INSERT INTO meeting " .
        "(meetingName,location,latitude,longitude,meetingTime,organizerId,meetingStatus,timeNeedsChange) VALUES (" .
        "  '" . $meetingName . "'" .
        ", '" . $location . "'" .
        ", '" . $latitude . "'" .
        ", '" . $longitude . "'" .
        ", '" . $meetingTime . "'" .
        ", '" . $organizerId . "'" .
        ", '" . $meetingStatus . "'" .
        ", '" . $timeNeedsChange . "'" .
        ");";

    $result = mysql_query($sql);

// check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred." . $sql;

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) missing." . $POST_;

    // echoing JSON response
    echo json_encode($response);
}
?>