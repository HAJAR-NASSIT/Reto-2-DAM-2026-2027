package VentaEntradasCenima.Controlador;


import java.time.LocalDate;
import java.util.ArrayList;

import VentaEntradasCenima.Modelo.BBDD.GestorDAO.PeliculaDAO;
import VentaEntradasCenima.Modelo.BBDD.GestorDAO.SalaDAO;
import VentaEntradasCenima.Modelo.BBDD.GestorDAO.SesionDAO;
import VentaEntradasCenima.Modelo.Pojos.Pelicula;
import VentaEntradasCenima.Modelo.Pojos.Sala;
import VentaEntradasCenima.Modelo.Pojos.Sesion;
import VentaEntradasCenima.Vista.Menu;

public class Controlador {
	
	private final Menu menu;
    private final PeliculaDAO peliculaDAO;
    private final SesionDAO sesionDAO;
    private final SalaDAO salaDAO;

    // Carrito simple (id_sesion repetido por cada espectador)
    private final ArrayList<Integer> carritoSesiones = new ArrayList<>();

    public Controlador(Menu menu, PeliculaDAO peliculaDAO, SesionDAO sesionDAO, SalaDAO salaDAO) {
        this.menu = menu;
        this.peliculaDAO = peliculaDAO;
        this.sesionDAO = sesionDAO;
        this.salaDAO = salaDAO;
    }

    public void iniciar() {
        menu.pantallaBienvenida(); 

        boolean salir = false;
        while (!salir) {

            ArrayList<Pelicula> peliculas = peliculaDAO.listaPeliculasPorSecionMasCerca();
            int opcion = menu.mostrarPeliculasYLeerOpcion(peliculas, !carritoSesiones.isEmpty());

            if (opcion == -1) {
                salir = true;

            } else if (opcion == 0 && !carritoSesiones.isEmpty()) {
                System.out.println("(Pendiente) Resumen/Compra/Login..."); // siguiente fase del reto 

            } else if (opcion >= 1 && opcion <= peliculas.size()) {
                Pelicula peli = peliculas.get(opcion - 1);
                flujoFechasSesiones(peli); // lleva a la pantalla de fecha

            } else {
                System.out.println("Opcion invalida");
            }
        }
    }

    private void flujoFechasSesiones(Pelicula pelicula) {

        ArrayList<LocalDate> fechas = sesionDAO.listarFechas(pelicula.getIdPelicula());
        if (fechas.isEmpty()) {
            System.out.println("No hay fechas disponibles para esa pelicula.");
            return;
        }

        int opcionFecha = menu.mostrarFechasYLeerOpcion(pelicula, fechas);
        if (opcionFecha == 0) return; // volver a peliculas 

        if (opcionFecha < 1 || opcionFecha > fechas.size()) {
            System.out.println("Opción inválida");
            return;
        }

        LocalDate fechaElegida = fechas.get(opcionFecha - 1);

        ArrayList<Sesion> sesiones = sesionDAO.listarSesiones(pelicula.getIdPelicula(),fechaElegida);
        if (sesiones.isEmpty()) {
            System.out.println("No hay sesiones para esa fecha.");
            return;
        }

        // Sacamos nombres de sala (misma posición que la sesión)
        ArrayList<Sala> nombresSala = new ArrayList<>();
        for (Sesion s : sesiones) {
            nombresSala.add(salaDAO.obtenerNombreSala(s.getIdSala()));
        }

        int opcionSesion = menu.mostrarSesionesYLeerOpcion(pelicula, fechaElegida, sesiones, nombresSala);
        if (opcionSesion == 0) return; // volver a fechas 

        if (opcionSesion < 1 || opcionSesion > sesiones.size()) {
            System.out.println("Opción inválida");
            return;
        }

        Sesion sesionElegida = sesiones.get(opcionSesion - 1);
        Sala salaElegida = nombresSala.get(opcionSesion - 1);

        // Enunciado: pedir nº de espectadores 
        int espectadores = menu.pedirNumeroEspectadores();

        // Incluimos en compra: repetimos id_sesion por espectador 
        for (int i = 0; i < espectadores; i++) {
            carritoSesiones.add(sesionElegida.getIdSesion());
        }

        // Enunciado: mensaje y volver al menu inicial 
        menu.mensajeSeleccionOK(pelicula, sesionElegida, salaElegida, espectadores);
    }
}