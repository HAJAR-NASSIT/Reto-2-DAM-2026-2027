document.addEventListener('DOMContentLoaded', function(){
    const formulario=document.getElementById('formulario');
    formulario.addEventListener('submit' , function(evento){
        const valido=validarLogin();

        if(valido!==true){
            evento.preventDefault();
        }
    })
})

function validarLogin(){
    let dni = document.getElementById('usuario').value.trim();
    let password= document.getElementById('password').value;

    let valido=true;
// Comprobar que ningin campo esta vacio
    if(dni ==='' || password ===''){
        alert("Por favor , completa todos los campos");
        valido=false;
    }
    // Validar que la contrasena tenga mnimo 6 caracteres
    if (password.length < 6) {
        alert("La contraseña debe tener al menos 6 caracteres");
        valido = false;
    }
    // Validar formato de DNI: 8 numeros + 1 letra
    if(valido===true && !/^\d{8}[A-Za-z]$/.test(dni)){
        alert("Formato DNI incorrecto. Debe ser 8 numeros + una letra");
        valido=false;
    }
    if(valido===true){
        document.getElementById('usuario').value = dni.toUpperCase();
    }
    
return valido;
}