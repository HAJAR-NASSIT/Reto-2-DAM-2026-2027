<?php
session_start();
require "config.php";

$id_pelicula = $_POST['id_pelicula'] ?? null;
$user = $_SESSION['usuario'] ?? null;

$stmt = $conn->prepare("SELECT nombre FROM cliente WHERE DNI=?");
$stmt->bind_param("s", $user);
$stmt->execute();
$usuario_info = $stmt->get_result()->fetch_assoc();
$nombre_usuario = $usuario_info['nombre'];

if (!$id_pelicula || !$user) {
    echo "Seleccione primero una película.";
    exit;
}

$pelicula_res = $conn->query("SELECT titulo, descripcion, imagen FROM pelicula WHERE id_pelicula=$id_pelicula");
$pelicula = $pelicula_res->fetch_assoc();

$selected_fecha = $_POST['fecha'] ?? '';
$selected_hora = $_POST['hora'] ?? '';
$sala_val = '';
$precio_val = '';

$id_sesion = null;
if ($selected_fecha && $selected_hora) {
    $stmt = $conn->prepare("
        SELECT s.id_sesion, sa.nombre AS sala, s.precio
        FROM sesion s
        JOIN sala sa ON s.id_sala = sa.id_sala
        WHERE s.id_pelicula=? AND s.fecha=? AND s.hora_inicio=?
    ");
    $stmt->bind_param("iss", $id_pelicula, $selected_fecha, $selected_hora);
    $stmt->execute();
    $result = $stmt->get_result();
    if ($row = $result->fetch_assoc()) {
        $sala_val = $row['sala'];
        $precio_val = $row['precio'];
        $id_sesion = $row['id_sesion'];
    }
}

$stmt = $conn->prepare("SELECT DISTINCT fecha FROM sesion WHERE id_pelicula=? ORDER BY fecha");
$stmt->bind_param("i", $id_pelicula);
$stmt->execute();
$fechas_result = $stmt->get_result();
$fechas = [];
while ($f = $fechas_result->fetch_assoc())
    $fechas[] = $f['fecha'];

$horas = [];
if ($selected_fecha) {
    $stmt = $conn->prepare("SELECT hora_inicio FROM sesion WHERE id_pelicula=? AND fecha=? ORDER BY hora_inicio");
    $stmt->bind_param("is", $id_pelicula, $selected_fecha);
    $stmt->execute();
    $horas_result = $stmt->get_result();
    while ($h = $horas_result->fetch_assoc())
        $horas[] = $h['hora_inicio'];
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Sesión de Película</title>
      <link rel="stylesheet" href="css/estilos.css">
    <link rel="stylesheet" href="css/cssSesiones.css">
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
                        <li><a href="contacto.html">Contacto</a></li>
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
        </div>
    </header>
    <div class="main-content">
        <h2>Selecciona la sesión</h2>
        <br>
        <div class="container-info">
            <div class="movie-image-container">
                <img src="imagen/<?= htmlspecialchars($pelicula['imagen']) ?>"
                    alt="<?= htmlspecialchars($pelicula['titulo']) ?>">
                <div>
                    <h3><?= htmlspecialchars($pelicula['titulo']) ?></h3>
                    <p><?= htmlspecialchars($pelicula['descripcion']) ?></p>
                </div>
            </div>

            <div class="sessions-panel">
                <form method="post">
                    <input type="hidden" name="id_pelicula" value="<?= $id_pelicula ?>">
                    <input type="hidden" name="dni" value="<?= $user ?>">
                    <input type="hidden" name="id_sesion" value="<?= $id_sesion ?>">

                    <!-- Fecha -->
                    <label>Fecha:</label><br>
                    <select name="fecha" onchange="this.form.submit()">
                        <option value="">-- Selecciona fecha --</option>
                        <?php foreach ($fechas as $f): ?>
                            <option value="<?= $f ?>" <?= ($selected_fecha == $f) ? 'selected' : '' ?>><?= $f ?></option>
                        <?php endforeach; ?>
                    </select>
                    <br><br>

                    <!-- hora -->
                    <label>Hora:</label><br>
                    <select name="hora" onchange="this.form.submit()">
                        <option value="">-- Selecciona hora --</option>
                        <?php foreach ($horas as $h): ?>
                            <option value="<?= $h ?>" <?= ($selected_hora == $h) ? 'selected' : '' ?>><?= $h ?></option>
                        <?php endforeach; ?>
                    </select>
                    <br><br>

                    <!-- Sala -->
                    <label>Sala:</label><br>
                    <input type="text" value="<?= $sala_val ?>" readonly>
                    <br><br>

                     <!-- Precio -->
                    <label>Precio (€):</label><br>
                    <input type="text" value="<?= $precio_val ?>" readonly><br><br>

                    <?php if ($selected_fecha && $selected_hora): ?>
                        <input type="hidden" name="id_sesion" value="<?= $id_sesion ?>">
                        <input type="hidden" name="fecha_sesion" value="<?= $selected_fecha ?>">
                        <input type="hidden" name="hora_sesion" value="<?= $selected_hora ?>">
                        <input type="hidden" name="sala_sesion" value="<?= $sala_val ?>">
                        <input type="hidden" name="precio_sesion" value="<?= $precio_val ?>">
                        <button type="submit" formaction="compra.php">Reservar</button>
                    <?php else: ?>
                        <button type="submit" disabled>Selecciona fecha y hora</button>
                    <?php endif; ?>
                </form>
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