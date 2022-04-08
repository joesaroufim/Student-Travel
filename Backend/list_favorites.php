<?php

include ("db_info.php");

$id = $_SESSION['id'];

$query = $mysqli->prepare("SELECT name, status, university FROM users NATURAL JOIN voyages WHERE  user_id IN (SELECT favorite_id FROM favorites WHERE user_id = ? ");
$query->bind_param('i', $id);
$query->execute();

$array = $query->get_result();

$response = [];

while($result = $array->fetch_assoc()){
    $response[] = $result;
}


?>