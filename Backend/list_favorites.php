<?php

include ("db_info.php");

// $id = $_SESSION['id'];
$id = 7;

// user_id IN (SELECT favorite_id FROM favorites WHERE user_id = ? ");

$query = $mysqli->prepare("SELECT name, phone_number, username FROM users NATURAL JOIN favorites WHERE user_id = ? ");
$query->bind_param('i', $id);
$query->execute();

$array = $query->get_result();

$response = [];

while($result = $array->fetch_assoc()){
    $response[] = $result;
}

$json_response = json_encode($response);
echo $json_response;


?>