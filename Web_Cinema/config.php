<?php
// config.php - SOLO si no hay sesión iniciada
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

$servername = "127.0.0.1:3307";
$username = "root";
$password = "Hsn19611";
$dbname = "cine_elorrieta";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
// echo "Connected to database successfully ";
?>