<?php

include ("db_info.php");

session_start();

$user_id = $_POST['id'];
$username = $_POST['username'];

$sql = $mysqli->prepare("SELECT user_id FROM users WHERE  username = ?");
$sql->bind_param('s', $username);
$sql->execute();
$sql->store_result();
$sql->bind_result($favorite_id);
$sql->fetch();

$query = $mysqli->prepare("INSERT INTO favorites (user_id, favorite_id) VALUES (?, ?)");
$query->bind_param("ii", $user_id, $favorite_id);
$query->execute();

?>