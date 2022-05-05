<?php

include ("db_info.php");

$query = $mysqli->prepare("SELECT name, username, status, university FROM users NATURAL JOIN voyages");
$query->execute();

$array = $query->get_result();

$response = [];

while($result = $array->fetch_assoc()){
    $response[] = $result;
}

$json_response = json_encode($response);
echo $json_response;

?>