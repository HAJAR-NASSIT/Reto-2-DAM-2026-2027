<?php
session_start();
require "config.php";

if (isset($_SESSION['usuario'])) {
    header("Location: peliculas.php");
    exit;
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $user = $conn->real_escape_string($_POST['usuario']);
    $pass = $_POST['password']; 


    $stmt = $conn->prepare("SELECT password FROM cliente WHERE DNI = ?");
    $stmt->bind_param("s", $user);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($row = $result->fetch_assoc()) {
        
        $hashed_password = $row['password'];
        // Comparar contraseña hasheada con SHA256
        $inputpass=hash('sha256', $pass);
        // Validar credenciales del usuario
        if ($inputpass===$hashed_password) {
            $_SESSION['usuario'] = $user;
            header("Location: peliculas.php");
            exit();
        } else {
            $error = "Usuario o contraseña incorrectos";
        }
    } else {
        $error = "Usuario o contraseña incorrectos";
    }
}
?>
<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Cine Elorrieta</title>
    <link rel="stylesheet" href="css/cssindex.css">
     <script src="js/jsindex.js"></script>
</head>

<body>

    <div class="login-container">
        <div class="texto">
            <h1>
                 <span class="titulo-1">Excelencia</span>
                 <span class="titulo-2">Cinematográfica.</span>
            </h1>
                <p> Bienvenido al futuro del cine. Disfruta historias cuidadosamente seleccionadas
                    en un entorno moderno y elegante, diseñado para verdaderos amantes del cine.</p>
        </div>
        <div class="login-box">
            <div class="logo">
                <h1> Cine Elorrieta</h1>
                <p> Tu cine de confianza</p>
            </div>
            <?php if (isset($error)): ?>
                <div class="error-message">
                    <?php echo $error; ?>
                </div>
            <?php endif; ?>

              <form id="formulario" method="post" action="index.php">
                <div class="form-group">
                    <label for="usuario">Usuario (DNI)</label>
                    <input id="usuario" type="text" name="usuario" class="form-control" required
                        placeholder="Ingresa tu DNI" value="<?php echo
                            isset($_POST['usuario']) ? htmlspecialchars($_POST['usuario']) : ''; ?>">
                </div>
                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input id="password" type="password" name="password" class="form-control" required
                        placeholder="Ingresa tu contraseña">
                </div>
                <button type="submit" class="btn-login"> Iniciar Sesion</button>
                <div class="register-link">
                    ¿No tienes una cuenta?
                    <a href="registrar.php">Regístrate aquí</a>
                </div>
            </form>
        </div>
            
    </div>
    
</body>

</html>