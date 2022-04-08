<?php

include ("db_info.php");

$name = $_POST['name'];
$country = $_POST['country'];
$gender = $_POST['gender'];
$travel_country = $_POST['travel_country'];
$college = $_POST['college'];
$major = $_POST['major'];
$date = $_POST['date'];
$status = $_POST['status'];
$id = $_SESSION['id'];

$user_update = ("UPDATE users name = $name, gender = $gender, country = $country WHERE user_id = $id");

$query = $mysqli->prepare("SELECT travel_id FROM travels WHERE  user_id = ?");
$query->bind_param('i', $id);
$query->execute();
$query->store_result();
$query->fetch();

if($query->num_rows == 1){
    $voyage_update = ("UPDATE voyages user_id = $id, destination = $travel_country, university = $college, arriving_date = $date WHERE user_id = $id");
}else{
    $query = $mysqli->prepare("INSERT INTO voyages (user_id, destination, university, arriving_date) VALUES (?, ?, ?, ?)");
    $query->bind_param("ssss", $id, $travel_country, $college, $date);
    $query->execute();
}


?>