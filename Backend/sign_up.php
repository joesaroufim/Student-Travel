<?php

include ("db_info.php");

$name = $_POST['full_name'];
$phone = $_POST['phone'];
$username = $_POST['username'];
$password = hash("sha256",$mysqli->real_escape_string($_POST['password']));

$query = $mysqli->prepare("SELECT user_id FROM users WHERE  username = ?");
$query->bind_param('s', $username);
$query->execute();
$query->store_result();
$query->fetch();

if($query->num_rows == 1){
    echo ("username already exists");
}else{
    echo("correct")
    $query = $mysqli->prepare("INSERT INTO users (name, email, password, phone_number) VALUES (?, ?, ?, ?)");
    $query->bind_param("ssss", $name, $email, $password, $phone);
    $query->execute();
}



?>