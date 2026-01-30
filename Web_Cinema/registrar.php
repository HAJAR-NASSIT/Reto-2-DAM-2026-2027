<?php
session_start();
require "config.php";

if (isset($_SESSION['usuario'])) {
    header("Location: peliculas.php");
    exit;
}

$msg = '';
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $conn->real_escape_string($_POST['email']);
    $username = $conn->real_escape_string($_POST['name']);
    $DNI = $conn->real_escape_string($_POST['DNI']);
    $password = $_POST['password'];
    $passwordc = $_POST['cpassword'];

    // Validaciones
    if ($password !== $passwordc) {
        $msg = "Las contraseñas no coinciden";
    } else {
        // Verificar si el usuario ya existe
        $select1 = "SELECT * FROM cliente WHERE DNI='$DNI' OR email='$email'";
        $select_user = $conn->query($select1);

        if ($select_user->num_rows > 0) {
            $msg = "El usuario ya existe (DNI o email ya registrado)";
        } else {
            // Crear el hash de la contraseña
            $password_hash =  hash('sha256', $password);

            // Insertar nuevo usuario
            $insert1 = "INSERT INTO `cliente`(`DNI`, `nombre`, `email`, `password`) 
                        VALUES ('$DNI', '$username', '$email', '$password_hash')";

            if ($conn->query($insert1)) {
                $_SESSION['usuario'] = $DNI;
                header('Location: index.php');
                exit();
            } else {
                $msg = "Error al registrar: " . $conn->error;
            }
        }
    }
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="css/cssregestrar.css">
    <title>Registro - Cine Elorrieta</title>

</head>

<body>
    <div class="register-container">
        <div class="register-box">
            <div class="logo">
                <h1>Crear Cuenta</h1>
                <p>Únete a Cine Elorrieta</p>
            </div>

            <?php if ($msg): ?>
                <div class="message error">
                    <?php echo htmlspecialchars($msg); ?>
                </div>
            <?php endif; ?>

            <form action="" method="post">
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" class="form-control" required placeholder="ejemplo@correo.com">
                </div>

                <div class="form-group">
                    <label>Nombre Completo</label>
                    <input type="text" name="name" class="form-control" required placeholder="Tu nombre y apellidos">
                </div>

                <div class="form-group">
                    <label>DNI</label>
                    <input type="text" name="DNI" class="form-control" required placeholder="12345678A">
                </div>

                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" name="password" id="password" class="form-control" required
                        placeholder="Mínimo 6 caracteres" minlength="6">
                    <div class="password-strength">
                        <div class="strength-bar" id="strengthBar"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Confirmar Contraseña</label>
                    <input type="password" name="cpassword" id="cpassword" class="form-control" required
                        placeholder="Repite tu contraseña">
                    <small id="passwordMatch" style="color: #666; display: none;">✓ Las contraseñas coinciden</small>
                </div>

                <button type="submit" class="btn-register" id="registerBtn">
                    Registrarse
                </button>

                <div class="login-link">
                    ¿Ya tienes una cuenta?
                    <a href="index.php">Inicia sesión aquí</a>
                </div>
            </form>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const passwordInput = document.getElementById('password');
            const cpasswordInput = document.getElementById('cpassword');
            const strengthBar = document.getElementById('strengthBar');
            const passwordMatch = document.getElementById('passwordMatch');
            const registerBtn = document.getElementById('registerBtn');



            // Verificar que las contraseñas coincidan
            function checkPasswordMatch() {
                if (cpasswordInput.value && passwordInput.value === cpasswordInput.value) {
                    passwordMatch.style.color = '#00C851';
                    passwordMatch.textContent = '✓ Las contraseñas coinciden';
                    passwordMatch.style.display = 'block';
                    return true;
                } else if (cpasswordInput.value) {
                    passwordMatch.style.color = '#ff4444';
                    passwordMatch.textContent = '✗ Las contraseñas no coinciden';
                    passwordMatch.style.display = 'block';
                    return false;
                }
                passwordMatch.style.display = 'none';
                return false;
            }

            passwordInput.addEventListener('input', checkPasswordMatch);
            cpasswordInput.addEventListener('input', checkPasswordMatch);

            // Validación del formulario
            document.querySelector('form').addEventListener('submit', function (e) {
                if (!checkPasswordMatch()) {
                    e.preventDefault();
                    alert('Las contraseñas no coinciden');
                    return false;
                }

                if (passwordInput.value.length < 6) {
                    e.preventDefault();
                    alert('La contraseña debe tener al menos 6 caracteres');
                    return false;
                }

            });
        });
    </script>
</body>

</html>