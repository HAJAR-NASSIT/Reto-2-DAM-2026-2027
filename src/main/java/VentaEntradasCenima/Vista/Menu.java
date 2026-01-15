package VentaEntradasCenima.Vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import VentaEntradasCenima.Modelo.Pojos.Pelicula;
import VentaEntradasCenima.Modelo.Pojos.Sala;
import VentaEntradasCenima.Modelo.Pojos.Sesion;

public class Menu {
	
	  private Scanner teclado  ; 
	
	  public Menu() {
		  teclado = new Scanner(System.in);
	  }
	  
	  public void pantallaBienvenida() {
		  System.out.println("********************************************+********");
		  System.out.println("--< Bienvenido a Elorrieta Ventas Entradas Cinema >--");
		  System.out.println("  Disfruta de las mejores Peliculas al mejor precio");
		  System.out.println("*****************************************************");
		  System.out.println("Pulsa ENTER para Continuar...");
		
		  teclado.nextLine();
	  }
	  
	  public int mostrarPeliculasYLeerOpcion(ArrayList<Pelicula> peliculas, boolean hayCarrito) {
	        System.out.println("\n=== PELICULAS ===");
	        for (int i = 0; i < peliculas.size(); i++) {
	            System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
	        }
	        if (hayCarrito) System.out.println("0) Finalizar compra");
	        System.out.println("-1. Salir");
	        System.out.print("Elegir una Opcion: ");
	        return leerEntero();
	    }

	    public int mostrarFechasYLeerOpcion(Pelicula pelicula, ArrayList<LocalDate> fechas) {
	        System.out.println("\nPelicula Seleccionada: " + pelicula.getTitulo());
	        System.out.println("=== FECHAS ===");
	        for (int i = 0; i < fechas.size(); i++) {
	            System.out.println((i + 1) + ") " + fechas.get(i));
	        }
	        System.out.println("0) Volver");
	        System.out.print("Elegir una Opcion:  ");
	        return leerEntero();
	    }

	    public int mostrarSesionesYLeerOpcion(Pelicula pelicula, LocalDate fecha,
	                                         ArrayList<Sesion> sesiones, ArrayList<Sala> nombresSala) {

	        System.out.println("\nHorarios " + fecha + " (" + pelicula.getTitulo().toUpperCase() + ")");
	        for (int i = 0; i < sesiones.size(); i++) {
	            Sesion s = sesiones.get(i);
	            Sala sala = nombresSala.get(i);

	            System.out.println((i + 1) + ") " + s.getHoraInicio()
	                    + " - " + pelicula.getTitulo().toUpperCase()
	                    + " (" + sala + ") - "
	                    + s.getPrecio() + " €");
	        }
	        System.out.println("0) Volver");
	        System.out.print("Elegir una Opcion:  ");
	        return leerEntero();
	    }

	    public int pedirNumeroEspectadores() {
	        System.out.print("Numero de espectadores: ");
	        return leerEntero();
	    }

	    public void mensajeSeleccionOK(Pelicula pelicula, Sesion sesion, Sala nombreSala, int espectadores) {
	        System.out.println("\n--> Seleccion realizada:");
	        System.out.println(pelicula.getTitulo() + " - " + sesion.getFecha() + " " + sesion.getHoraInicio()
	                + " (" + nombreSala + ") - " + espectadores + " espectadores");
	        System.out.println("Pulsa ENTER para volver al menu inicial...");
	        teclado.nextLine();
	    }

	    private int leerEntero() {
	        while (!teclado.hasNextInt()) {
	        	teclado.nextLine();
	            System.out.print("Introduce un numero valido: ");
	        }
	        int n = teclado.nextInt();
	        teclado.nextLine();
	        return n;
	    }
}