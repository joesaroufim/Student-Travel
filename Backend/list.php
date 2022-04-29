<?php

include ("db_info.php");

$university = $_POST['college'];
$country = $_POST['country'];
$major = $_POST['major'];
$status = $_POST['status'];


$query = $mysqli->prepare("SELECT name, username FROM users NATURAL JOIN voyages WHERE destination = ? AND university = ? AND major = ? AND status = ?");
$query->bind_param('ssss', $country, $university, $major, $status);
$query->execute();

$array = $query->get_result();

$response = [];

while($result = $array->fetch_assoc()){
    $response[] = $result;
}

$json_response = json_encode($response);
echo $json_response;

?>