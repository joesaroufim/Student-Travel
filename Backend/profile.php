<?php

include ("db_info.php");

session_start();

$id = $_SESSION['id'];

// $name = $_POST['name'];
$country = $_POST['country'];
$gender = $_POST['gender'];
$country = $_POST['country'];
$college = $_POST['college'];
$major = $_POST['major'];
$date = $_POST['date'];
$status = $_POST['status'];
// $id = 7;

$query = $mysqli->prepare("UPDATE users SET  gender = ?, status = ? WHERE user_id = ?");
if ($query){
    $query->bind_param('ssi', $gender, $status, $id);
    $query->execute();
}else{
    echo("user failed");
}

$query = $mysqli->prepare("SELECT voyage_id FROM voyages WHERE  user_id = ?");
$query->bind_param('i', $id);
$query->execute();
$query->store_result();
$query->fetch();

if($query->num_rows > 0){
    $sql = $mysqli->prepare("UPDATE voyages SET destination = ?, university = ?, arriving_date = ?, major = ? WHERE user_id = ?");
    if ($sql){
        $sql->bind_param('ssssi', $country, $college, $date, $major, $id);
        $sql->execute();
    }else{
        echo("voyages failed");
    }
}else{
    $query = $mysqli->prepare("INSERT INTO voyages (user_id, destination, university, arriving_date, major) VALUES (?, ?, ?, ?, ?)");
    $query->bind_param('issss', $id, $country, $college, $date, $major);
    $query->execute();
}


?>