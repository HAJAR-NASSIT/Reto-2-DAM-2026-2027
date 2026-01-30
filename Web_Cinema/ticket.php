<?php
session_start();
require "config.php";

$user = $_SESSION['usuario'] ?? null;
if (!$user) { 
    echo "Debes iniciar sesión."; 
exit; 
}

$id_compra = $_GET['id_compra'] ?? null;
if (!$id_compra) {
     echo "Compra no válida."; 
exit; 
}
            //obtener nombre del usuarioo
$stmt_usuario = $conn->prepare("SELECT nombre FROM cliente WHERE DNI=?");
$stmt_usuario->bind_param("s", $user);
$stmt_usuario->execute();
$usuario_info = $stmt_usuario->get_result()->fetch_assoc();
$nombre_usuario = $usuario_info['nombre'] ?? 'Usuario';

            //datos de ticket
$stmt = $conn->prepare("
    SELECT c.id_compra, c.fecha_compra, c.hora_compra, c.precio_total, 
           e.id_entrada, e.precio, s.fecha, s.hora_inicio, sa.nombre AS sala,
           p.titulo, p.duracion
    FROM compra c
    JOIN entrada e ON c.id_compra = e.id_compra
    JOIN sesion s ON e.id_sesion = s.id_sesion
    JOIN sala sa ON s.id_sala = sa.id_sala
    JOIN pelicula p ON s.id_pelicula = p.id_pelicula
    WHERE c.id_compra=?
");

$stmt->bind_param("i", $id_compra);
$stmt->execute();
$ticket = $stmt->get_result()->fetch_assoc();
if (!$ticket) { 
    echo "Ticket no encontrado."; 
    exit;
     }
?>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/estilos.css">
<link rel="stylesheet" href="css/cssticket.css">
<title>Ticket de Cine</title>

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

<div class="ticket">
    <h2><?= htmlspecialchars($ticket['titulo']) ?></h2>
    <p>Duración: <?= htmlspecialchars($ticket['duracion']) ?> min</p>
    <ul>
        <li>Fecha: <?= htmlspecialchars($ticket['fecha']) ?></li>
        <li>Hora: <?= htmlspecialchars($ticket['hora_inicio']) ?></li>
        <li>Sala: <?= htmlspecialchars($ticket['sala']) ?></li>
        <li>Precio: <?= number_format($ticket['precio'], 2) ?> €</li>
        <li>Total pagado: <?= number_format($ticket['precio_total'], 2) ?> €</li>
    </ul>

    <div class="ticket-buttons">
        <button onclick="window.print()">Imprimir Ticket</button>
        <a href="peliculas.php"><button>Volver a Cartelera</button></a>
    </div>
</div>

 <div style="height: 60px;"></div>
    
    <footer class="footer">
        <div class="container">
            <div class="footer-container">
                <h3>Cine Elorrieta</h3>
                <p>Tu cine de confianza desde 2025</p>
                        
                <div class="social-icons">
                    <a href="https://es-es.facebook.com/"><img src="imagen/facebook.png" alt="facebook"></a>
                    <a href="https://github.com/HAJAR-NASSIT/Reto-2-DAM-2026-2027"><img src="imagen/github.png" alt="github"></a>
                    <a href="https://workspace.google.com/intl/es/products/gmail/?utm_source=bing&utm_medium=cpc&utm_campaign=emea-es-all-es-dr-bkws-all-all-trial-e-t1-1713698&utm_content=text-ad-none-none-DEV_c-CRE_-ADGP_Hybrid+%7C+AW+SEM+%7C+BKWS+~+EXA_1:1_ES_ES_Gmail_GMB06_google+mail-KWID_335428347-kwd-76553690187899:loc-170-userloc_292766&utm_term=KW_google%20mail-o&&msclkid=ef1e0abfe5e51fdb5d5a63cc87ac8c71&gclid=ef1e0abfe5e51fdb5d5a63cc87ac8c71&gclsrc=3p.ds&gad_source=7&gad_campaignid=12470730368"><img src="imagen/gmail.png" alt="gmail"></a>
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