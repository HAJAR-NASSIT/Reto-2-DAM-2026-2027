document.addEventListener('DOMContentLoaded', function() {
    
    var boton = document.querySelector('button[name="confirmar"]');
    
    var existeBoton = false;
    if (boton !== null) {
        existeBoton = true;
    }
    
    if (existeBoton === true) {
        boton.addEventListener('click', function(e) {
            
            var cancelar = false;
            
            // validacion minima 
            var idPelicula = document.querySelector('input[name="id_pelicula"]').value;
            var idSesion = document.querySelector('input[name="id_sesion"]').value;
            
            if (idPelicula === '' || idSesion === '') {
                alert('Error: Datos incompletos');
                cancelar = true;
            }
            
            // Confirmacion de la compra
            if (cancelar === false) {
                var confirmar = confirm('¿Confirmar compra?');
                if (confirmar === false) {
                    cancelar = true;
                }
            }
            
            // Cancelar la compra
            if (cancelar === true) {
                e.preventDefault();
            }
        });
    }
});
