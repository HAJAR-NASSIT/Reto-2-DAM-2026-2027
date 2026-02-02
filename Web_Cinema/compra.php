<?php
session_start();
require "config.php";


$user = $_SESSION['usuario'] ?? null;
if (!$user) {
    echo "Debes iniciar sesión.";
    exit;
}


$stmt = $conn->prepare("SELECT nombre FROM cliente WHERE DNI=?");
$stmt->bind_param("s", $user);
$stmt->execute();
$usuario_info = $stmt->get_result()->fetch_assoc();
$nombre_usuario = $usuario_info['nombre'] ?? 'Usuario';


$id_pelicula = $_POST['id_pelicula'] ?? null;
$id_sesion = $_POST['id_sesion'] ?? null;
$fecha_sesion = $_POST['fecha_sesion'] ?? '';
$hora_sesion = $_POST['hora_sesion'] ?? '';
$sala_sesion = $_POST['sala_sesion'] ?? '';
$precio_sesion = $_POST['precio_sesion'] ?? 0.00;

if (!$id_pelicula || !$id_sesion) {
    echo "No se ha seleccionado una sesión válida.";
    exit;
}


$pelicula_res = $conn->query("SELECT titulo, descripcion, duracion, imagen FROM pelicula WHERE id_pelicula=$id_pelicula");
$pelicula = $pelicula_res->fetch_assoc();


if (isset($_POST['confirmar'])) {
    $descuento_total = 0.00;
    $tipo_compra = 'Web';

    $stmt = $conn->prepare("
        INSERT INTO compra (DNI, fecha_compra, hora_compra, precio_total, descuento_total, tipo_compra)
        VALUES (?, CURDATE(), CURTIME(), ?, ?, ?)
    ");

    if (!$stmt) {
        die("SQL ERROR: " . $conn->error);
    }
    $stmt->bind_param("sdds", $user, $precio_sesion, $descuento_total, $tipo_compra);
    $stmt->execute();
    $id_compra = $stmt->insert_id;

    $stmt2 = $conn->prepare("
        INSERT INTO entrada (id_sesion, id_compra, importe)
        VALUES (?, ?, ?)
    ");
    if (!$stmt2) {
        die("SQL ERROR: " . $conn->error);
    }

    $stmt2->bind_param("iid", $id_sesion, $id_compra, $precio_sesion);
    $stmt2->execute();
    header("Location: ticket.php?id_compra=" . $id_compra);
    exit;
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Confirmar Reserva</title>
    <link rel="stylesheet" href="css/estilos.css">
    <link rel="stylesheet" href="css/csscompra.css">
    <script src="js/jscompra.js"></script>
</head>

<body>
    <header class="header">
        <div class="container">
            <div class="header-content">
                <div class="logo">
                    <div class="logo-icon">🎬</div>
                    <h1>Cine Elorrieta</h1>
                </div>

                <nav class="navbar">
                    <ul class="nav-menu">
                        <li class="active"><a href="peliculas.php">Cartelera</a></li>
                        <li><a href="contacto.html">Contacto</li>
                        <li><a href="about.html">Sobre Nosotros</a></li>
                    </ul>
                </nav>

                <div class="user-info">
                    <div class="user-text">

                        <p class="user_nombre">👤 <strong><?php echo htmlspecialchars($nombre_usuario) ?></strong></p>
                        <p class="user_dni">Usuario:<strong><?php echo htmlspecialchars($user) ?></strong></p>
                    </div>
                    <a href="logout.php"><button class="btn-login">Cerrar</button></a>
                </div>
            </div>
    </header>

    <div class="containerr">
        <h2>Confirmar Reserva 🎫</h2>
        <div class="resumen-reserva">
            <div class="ticket">
                <div class="ticket-header">
                    <h3><?= htmlspecialchars($pelicula['titulo']) ?></h3>
                    <p>Duración: <?= htmlspecialchars($pelicula['duracion']) ?> min</p>
                </div>

                <div class="ticket-body">
                    <p><strong>Detalles de la sesión:</strong></p>
                    <ul>
                        <li>Fecha: <?= htmlspecialchars($fecha_sesion) ?></li>
                        <li>Hora: <?= htmlspecialchars($hora_sesion) ?></li>
                        <li>Sala: <?= htmlspecialchars($sala_sesion) ?></li>
                        <li>Precio: <?= number_format($precio_sesion, 2) ?> €</li>
                        <li>Total a pagar: <?= number_format($precio_sesion, 2) ?> €</li>
                    </ul>
                </div>

                <div class="ticket-footer">
                    <form method="post">
                        <input type="hidden" name="id_pelicula" value="<?= $id_pelicula ?>">
                        <input type="hidden" name="id_sesion" value="<?= $id_sesion ?>">
                        <input type="hidden" name="fecha_sesion" value="<?= $fecha_sesion ?>">
                        <input type="hidden" name="hora_sesion" value="<?= $hora_sesion ?>">
                        <input type="hidden" name="sala_sesion" value="<?= $sala_sesion ?>">
                        <input type="hidden" name="precio_sesion" value="<?= $precio_sesion ?>">
                        <button type="submit" name="confirmar">Confirmar Compra</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <footer class="footer">
        <div class="container">
            <div class="footer-container">
                <h3>Cine Elorrieta</h3>
                <p>Tu cine de confianza desde 2025</p>

                <div class="social-icons">
                    <a href="https://es-es.facebook.com/"><img src="imagen/facebook.png" alt="facebook"></a>
                    <a href="https://github.com/HAJAR-NASSIT/Reto-2-DAM-2026-2027"><img src="imagen/github.png"
                            alt="github"></a>
                    <a
                        href="https://workspace.google.com/intl/es/products/gmail/?utm_source=bing&utm_medium=cpc&utm_campaign=emea-es-all-es-dr-bkws-all-all-trial-e-t1-1713698&utm_content=text-ad-none-none-DEV_c-CRE_-ADGP_Hybrid+%7C+AW+SEM+%7C+BKWS+~+EXA_1:1_ES_ES_Gmail_GMB06_google+mail-KWID_335428347-kwd-76553690187899:loc-170-userloc_292766&utm_term=KW_google%20mail-o&&msclkid=ef1e0abfe5e51fdb5d5a63cc87ac8c71&gclid=ef1e0abfe5e51fdb5d5a63cc87ac8c71&gclsrc=3p.ds&gad_source=7&gad_campaignid=12470730368"><img
                            src="imagen/gmail.png" alt="gmail"></a>
                    <a href="https://www.instagram.com/"><img src="imagen/social.png" alt="instagram"></a>
                </div>

                <p style="color: #d8d4d4; margin-top: 10px;">
                    © 2026 Cine Elorrieta. Todos los derechos reservados.
                </p>
            </div>
        </div>
    </footer>

</body>

</html>