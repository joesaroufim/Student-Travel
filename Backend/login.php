<?php

include ("db_info.php");

$email = $_POST['email'];
$password = hash("sha256",$mysqli->real_escape_string($_POST['password']));

$query = $mysqli->prepare("SELECT user_id FROM users WHERE email = ? AND password = ? ");
$query->bind_param('ss', $email, $password);
$query->execute();
$query->store_result();
$query->bind_result($id);
$query->fetch();
$_SESSION['id'] = $ID;

?>