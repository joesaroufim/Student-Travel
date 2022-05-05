<?php

include ("db_info.php");

$id = $_GET['id'];

$query = $mysqli->prepare("SELECT name, username FROM users WHERE user_id = ?");
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