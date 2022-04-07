<?php

include ("db_info.php");

$name = $_POST['name'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$password = hash("sha256",$mysqli->real_escape_string($_POST['password']));

$query = $mysqli->prepare("INSERT INTO users (name, email, password, phone_number) VALUES (?, ?, ?, ?)");
$query->bind_param("ssss", $name, $email, $password, $phone);
$query->execute();

?>