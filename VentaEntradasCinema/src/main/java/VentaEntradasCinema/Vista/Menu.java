package VentaEntradasCinema.Vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import VentaEntradasCinema.Modelo.Pojos.Cliente;
import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;

public class Menu {

	private Scanner teclado;

	public Menu() {
		teclado = new Scanner(System.in);
	}

	/**
	 * 
	 */
	public void pantallaBienvenida() {

		System.out.println("        ======================================================");
		System.out.println("        |  Bienvenido a Elorrieta Ventas Entradas Cinema     |");
		System.out.println("        | Disfruta de las mejores Peliculas al mejor precio  |");
		System.out.println("        ======================================================");
		System.out.println("\n----->Pulsa ENTER para Continuar...");

		teclado.nextLine();
	}

	/**
	 * 
	 * @param peliculas
	 * @param hayCarrito
	 * @return
	 */
	public int mostrarMenuPeliculas(ArrayList<Pelicula> peliculas, boolean hayCarrito) {

		System.out.println("\n+--------------------------------+");
		System.out.println("|        MENU PELICULAS          |");
		System.out.println("+--------------------------------+");

		for (int i = 0; i < peliculas.size(); i++) {
			System.out.printf("|  %2d. %-25s |\n", (i + 1), peliculas.get(i).getTitulo());
		}

		System.out.println("+--------------------------------+");

		if (hayCarrito == true) {
			System.out.println("|  0. Finalizar compra           |");
		}

		System.out.println("| 99. Salir                      |");
		System.out.println("+--------------------------------+");

		int opcionElegida = leerEntero("\n--> Elegir una opcion : ");

		return opcionElegida;
	}

	/**
	 * 
	 * @param pelicula
	 * @param fechas
	 * @return
	 */
	public int mostrarMenuFechas(Pelicula pelicula, ArrayList<LocalDate> fechas) {

		System.out.println("\n+-------------------------------------------+");
		System.out.printf(" PELICULA SELECCIONADA:");
		System.out.printf("  %-36s  \n", pelicula.getTitulo());
		System.out.println("+-------------------------------------------+");
		System.out.println("|          FECHAS DISPONIBLES               |");
		System.out.println("+-------------------------------------------+");

		for (int i = 0; i < fechas.size(); i++) {
			System.out.printf("|  %2d. %-36s |\n", (i + 1), fechas.get(i));
		}

		System.out.println("+-------------------------------------------+");
		System.out.println("|  0. Volver a Peliculas                    |");
		System.out.println("+-------------------------------------------+");

		int opcionElegida = leerEntero("\n--> Elegir una opcion : ");

		return opcionElegida;
	}

	/**
	 * 
	 * @param pelicula
	 * @param fecha
	 * @param sesiones
	 * @param salas
	 * @return
	 */
	public int mostrarMenuSesiones(Pelicula pelicula, LocalDate fecha, ArrayList<Sesion> sesiones,
			ArrayList<Sala> salas) {

		System.out.println("\n+---------------------------------------------------------+");
		System.out.printf("   HORARIOS %s (%s)    \n", fecha, pelicula.getTitulo().toUpperCase());
		System.out.println("+---------------------------------------------------------+");

		for (int i = 0; i < sesiones.size(); i++) {
			Sesion sesion = sesiones.get(i);
			Sala sala = salas.get(i);

			System.out.printf("|  %2d. %s | %-15s | %-10s | %6.2f EUR |\n", (i + 1), sesion.getHoraInicio(),
					pelicula.getTitulo().toUpperCase(), sala.getNombre(), sesion.getPrecio());
		}

		System.out.println("+---------------------------------------------------------+");
		System.out.println("|  0. Volver a Fechas                                     |");
		System.out.println("+---------------------------------------------------------+");

		int opcionElegida = leerEntero("\n--> Elegir una opcion : ");

		return opcionElegida;
	}

	/**
	 * 
	 * @return
	 */
	public int pedirNumEspectadores() {
		int numEspectadores = leerEntero("\n--> Introducir el Numero de Espectadores  : ");

		return numEspectadores;
	}

	/**
	 * 
	 * @param pelicula
	 * @param sesion
	 * @param sala
	 * @param espectadores
	 */
	public void mostrarSeleccion(Pelicula pelicula, Sesion sesion, Sala sala, int espectadores) {

		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                 SELECCION REALIZADA                                  |");
		System.out.println("+----------------------------------------------------------------------+");

		System.out.printf("|   Pelicula      : %-50s |\n", pelicula.getTitulo());
		System.out.printf("|   Fecha         : %-50s |\n", sesion.getFecha());
		System.out.printf("|   Hora          : %-50s |\n", sesion.getHoraInicio());
		System.out.printf("|   Sala          : %-50s |\n", sala.getNombre());
		System.out.printf("|   Espectadores  : %-50d |\n", espectadores);

		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| Pulsa ENTER para volver al Menu Inicial de Peliculas                 |");
		System.out.println("+----------------------------------------------------------------------+");

		teclado.nextLine();
	}

	/**
	 * 
	 * @param lineas
	 * @param subtotal
	 * @param descuento
	 * @param total
	 */
	public void mostrarResumenCompra(ArrayList<String> lineas, double subtotal, double descuento, double total) {

		System.out.println("\n+==================================================================+");
		System.out.println("|                        RESUMEN DE COMPRA                         |");
		System.out.println("+==================================================================+");

		for (String linea : lineas) {
			System.out.printf("| %-63s |\n", linea);
		}

		System.out.println("+------------------------------------------------------------------+");
		System.out.printf("|  Subtotal   : %46.2f EUR |\n", subtotal);
		System.out.printf("|  Descuento  : %46.2f EUR |\n", descuento);
		System.out.printf("|  TOTAL      : %46.2f EUR |\n", total);
		System.out.println("+==================================================================+");
	}

	/**
	 * 
	 * @return
	 */
	public int opcionResumenCompra() {
		int opcion = -1;
		boolean esValida = false;

		while (esValida == false) {

			System.out.print("0. Volver al inicio  ||  1. Comprar : ");
			opcion = leerEntero("");

			if (opcion == 0 || opcion == 1) {
				esValida = true;
			} else {
				System.out.println("Opcion NO valida. Introduce 0 o 1 ");
			}
		}

		return opcion;
	}

	/**
	 * 
	 * @return
	 */
	public String pedirDni() {
		System.out.print("DNI : ");
		String dni = teclado.nextLine().trim();
		return dni;
	}

	/**
	 * 
	 * @return
	 */
	public String pedirPasswordOculta() {

	    String password = "";
	    int c;

	    System.out.print("Password : ");

	    try {
	        while ((c = System.in.read()) != '\n') {
	            password += (char) c;
	            System.out.print("*");
	        }
	    } catch (Exception e) {
	    }

	    System.out.println();
	    return password.trim();
	}


	/**
	 * 
	 */
	public void mensajeLoginCorrecto() {
		System.out.println("\nBien!  LOGIN Correcto ");
	}

	/**
	 * 
	 */
	public void mensajeLoginIncorrecto() {
		System.out.println("\nLOGIN Incorrecto !! ");
	}

	/**
	 * 
	 * @return
	 */
	public int menuLogin() {

		System.out.println("\n+------------------------------------------------+");
		System.out.println("|  Para proceder con la compra, es obligatorio   |");
		System.out.println("|  identificarse                                 |");
		System.out.println("+------------------------------------------------+");
		System.out.println("|  1. Ya soy cliente (Acceder)                   |");
		System.out.println("|  2. Quiero registrarme (Nuevo)                 |");
		System.out.println("|  0. Cancelar y volver al Inicio...             |");
		System.out.println("+------------------------------------------------+");
		int opcion = -1;

		boolean esValida = false;

		while (esValida == false) {

			opcion = leerEntero(" Elige una opcion: ");

			if (opcion == 0 || opcion == 1 || opcion == 2) {
				esValida = true;
			} else {
				System.out.println("  Opcion NO valida. Introduce 0, 1 o 2 ");
			}
		}
		
	    System.out.println("+------------------------------------------------+");

		return opcion;
	}

	/**
	 * 
	 * @return
	 */
	public Cliente nuevoCliente() {

		Cliente cliente = new Cliente();
		String dni , Email;
		boolean DNIvalid,Emailvalid;
		
		System.out.println("\n=== REGISTRAR CLIENTE ===");

		
	    do {
	        System.out.print("DNI : ");
	        dni = teclado.nextLine().trim();
	        
	        DNIvalid=cliente.verificarDNI(dni);

	      if (!DNIvalid) {
	            System.out.println("DNI incorrecto. Intenta de nuevo.");
	        }

	    } while (!DNIvalid);

	    cliente.setDni(dni);


		System.out.print("Nombre : ");
		cliente.setNombre(teclado.nextLine().trim());

		do {
			System.out.print("Email : ");
	        Email = teclado.nextLine().trim();
	        
	        Emailvalid=cliente.verificarEmail(Email);

	      if (!Emailvalid) {
	            System.out.println("Email incorrecto. Intenta de nuevo.");
	        }

	    } while (!Emailvalid);
		
		cliente.setEmail(teclado.nextLine().trim());

		cliente.setPassword(pedirPasswordOculta());

		return cliente;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public int opcionFactura() {

		int opcion = -1;
		boolean esValida = false;

		while (esValida == false) {

			System.out.print(" 0. Volver al inicio  ||  1. Guardar factura TXT: ");
			opcion = leerEntero("");

			if (opcion == 0 || opcion == 1) {
				esValida = true;
			} else {
				System.out.println("Opcion NO valida. Introduce 0 o 1 ");
			}
		}

		return opcion;
	}

	/**
	 * 
	 */
	public void mensajeFacturaGuardada() {
		System.out.println("\nFACTURA GUARDADA Con Exito ! ");
		System.out.println("-->Pulsa ENTER para volver al inicio");
		teclado.nextLine();
	}

	/**
	 * 
	 * @param mensaje
	 * @return
	 */
	private int leerEntero(String mensaje) {
		int num = -1;
		boolean correcto = false;

		while (correcto == false) {
			try {
				System.out.print(mensaje);
				num = teclado.nextInt();
				teclado.nextLine();

				correcto = true;

			} catch (Exception e) {
				System.out.println("ERROR!! debes introducir un numero ");
				teclado.nextLine();
			}
		}

		return num;
	}

}
