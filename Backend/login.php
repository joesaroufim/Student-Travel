<?php

include ("db_info.php");

session_start();
$username = $_POST['username'];
$password = hash("sha256",$mysqli->real_escape_string($_POST['password']));

$query = $mysqli->prepare("SELECT user_id FROM users WHERE username = ? AND password = ? ");
$query->bind_param('ss', $username, $password);
$query->execute();
$query->store_result();
$query->bind_result($id);
$query->fetch();
// $_SESSION['id'] = $id;

if ($query->num_rows > 0){
    echo("true");
    echo(" " + $id);
}else{
    echo("false");
}


?>