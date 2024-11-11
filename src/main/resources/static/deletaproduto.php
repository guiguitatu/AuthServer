<?php
session_start();
date_default_timezone_set('America/Sao_Paulo');

if (!$_COOKIE['token']) {
    header('Location: login.php');
    exit();
}

$role = 'http://localhost:8080/api/users/role/' . $_COOKIE['role'];
if (file_get_contents($role) != 'ADM') {
    header('Location: index.php');
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = isset($_POST['id']) ? $_POST['id'] : '';

    $ch = curl_init('http://localhost:8080/api/produto/' . $id);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'DELETE');
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, ['Authorization: Bearer ' . $_COOKIE['token']]);
    $response = curl_exec($ch);
    curl_close($ch);

    $result = json_decode($response, true);

    header('Location: adm.php');
    exit();
}