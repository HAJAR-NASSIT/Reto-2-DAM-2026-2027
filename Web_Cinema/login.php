<?php
require "config.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $user = $conn->real_escape_string($_POST['usuario']);
    $pass = $conn->real_escape_string($_POST['password']);
    // Query to check user
    $sql = "SELECT * FROM cliente WHERE DNI='$user' AND password='$pass' ";
    $result = $conn->query($sql);

    if ($result && $result->num_rows > 0) {
        $_SESSION['usuario'] = $user;
        header("Location: peliculas.php");
        exit();
    } else {
        $_SESSION['mensaje'] = "Usuario o contraseña incorrectos, regístrate";
        header("Location: index.php");
        exit();
    }
}
?>