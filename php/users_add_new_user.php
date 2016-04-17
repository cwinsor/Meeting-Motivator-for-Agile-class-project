<?php

/*
 * Following code will create a new user row
 * All user details are from HTTP Post
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['username']) && isset($_POST['password'])) {

    $username = $_POST['username'];
    $password = $_POST['password'];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $sql = "INSERT INTO user (username, password) VALUES ('" . $username . "', '" . $password . "');";
    $result = mysql_query($sql);

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully created.";

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
    $response["message"] = "Required field(s) missing." . $sql;

    // echoing JSON response
    echo json_encode($response);
}
?>