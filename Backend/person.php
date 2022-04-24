<?php

include ("db_info.php");

session_start();

$username = $_GET['username'];


$query = $mysqli->prepare("SELECT name, phone_number, gender, university, destination, status, arriving_date, major FROM users NATURAL JOIN voyages WHERE username = ? ");
$query->bind_param('s', $username);
$query->execute();

$array = $query->get_result();

$response = [];

while($result = $array->fetch_assoc()){
    $response[] = $result;
}

$json_response = json_encode($response);
echo $json_response;

?>