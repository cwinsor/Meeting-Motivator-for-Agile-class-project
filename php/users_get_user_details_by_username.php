<?php

/*
 * Following code will get single user details
 * A user is identified by username
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["username"])) {
    $username = $_GET['username'];

    // get from table
    $sql = "SELECT * FROM user WHERE username = \"$username\"";
    $result = mysql_query($sql);
    //
    //
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $userdata = array();
            $userdata["uid"] = $result["uid"];
            $userdata["username"] = $result["username"];
            $userdata["password"] = $result["password"];

            // user node
            $response["user"] = array();

            array_push($response["user"], $userdata);

            // success
            $response["success"] = 1;

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "Not found" . $sql;

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "Not found(2)" . $sql;

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0; // zona this is a change
    $response["message"] = "Required field(s) is missing" . $sql;

    // echoing JSON response
    echo json_encode($response);
}
?>