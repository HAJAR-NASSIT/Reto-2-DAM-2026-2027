<?php
session_start();
require "config.php";
// Verificar si el usuario ha iniciado sesion
// Si no hay sesion activa, redirigir al login
if (!isset($_SESSION['usuario'])) {
    header("Location: index.php");
    exit();
}
// Obtener el DNI del usuario desde la sesion
$user = $_SESSION['usuario'];

// obtener informacion del usuario
$stmt = $conn->prepare("SELECT nombre FROM cliente WHERE DNI=?");
$stmt->bind_param("s", $user);
$stmt->execute();
$usuario_info = $stmt->get_result()->fetch_assoc();
$nombre_usuario = $usuario_info['nombre'];
// Consulta SQL para obtener las películas disponibles
$sql = "SELECT p.id_pelicula, p.titulo, g.id_genero,g.nombre_genero, p.duracion,
               p.idioma, p.descripcion, p.imagen,
               MIN(CONCAT(s.fecha, ' ', s.hora_inicio)) as proxima_sesion_fecha_hora
        FROM pelicula p
        JOIN genero g ON p.id_genero = g.id_genero
        LEFT JOIN sesion s ON p.id_pelicula = s.id_pelicula
        WHERE CONCAT(s.fecha, ' ', s.hora_inicio) >= NOW()
        GROUP BY p.id_pelicula, p.titulo, g.nombre_genero,
                 p.duracion, p.idioma, p.descripcion, p.imagen
        ORDER BY proxima_sesion_fecha_hora ASC, p.titulo";
// Ejecutar la consulta
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/estilos.css">
    <link rel="stylesheet" href="css/cssPeliculas.css">
    <title>CINE ELORRIETA</title>
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

    <section class="hero">
        <div class="container">
            <h2>Cartelera de Cine</h2>
            <p>Descubre las mejores peliculas en estreno</p>
        </div>
    </section>

    <section class="movies-section">
        <div class="container">
            
            <h2 class="section-title">Peliculas Disponibles</h2>
            <div class="movies-grid">
                <?php if ($result->num_rows > 0): ?>
                    <?php while ($row = $result->fetch_assoc()): ?>
                        <form action="sesiones.php" method="post" class="movie-card-form">
                            <input type="hidden" name="id_pelicula" value="<?php echo $row['id_pelicula'] ?>">

                            <div class="movie-card">
                                <img src="imagen/<?php echo htmlspecialchars($row['imagen']) ?>"
                                    alt="<?php echo htmlspecialchars($row['titulo']); ?>" class="movie-poster">


                                <div class="movie-content">
                                    <h3 class="movie-title">
                                        <?php echo htmlspecialchars($row['titulo']); ?>
                                        <br>
                                        <span class="movie-genre"><?php echo htmlspecialchars($row['nombre_genero']) ?></span>
                                    </h3>

                                    <div class="movie-details">
                                        <span>🕐<?php echo $row['duracion']; ?> min</span>
                                        <span>idioma : <?php echo htmlspecialchars($row['idioma']); ?></span>
                                    </div>


                                    <button type="submit" class="btn-select">Seleccionar Pelicula</button>
                                </div>
                            </div>
                        </form>
                    <?php endwhile; ?>
                <?php else: ?>
                    <div style="grid-column: 1 / -1; text-align: center; padding: 40px;">
                        <h3 style="color: #666; margin-bottom: 20px;">No hay peliculas disponible en este momento</h3>
                        <p style="color: #888;">Vuelve mas tarde para ver nuestra cartelera</p>
                    </div>
                <?php endif; ?>


            </div>
        </div>
    </section>

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