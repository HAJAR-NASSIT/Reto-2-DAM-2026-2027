//http://10.5.6.70/Web_Cinema/index.php
<?php
// config.php - SOLO si no hay sesión iniciada
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

$servername = "10.5.6.68:33060";
$username = "dam";
$password = "Elorrieta9753$";
$dbname = "cine_elorrieta";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
// echo "Connected to database successfully ";
?>