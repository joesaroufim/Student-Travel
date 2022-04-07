<?php

include ("db_info.php");

$name = $_POST['name'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$password = hash("sha256",$mysqli->real_escape_string($_POST['password']));



?>