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

/**
 *Inicializa el objeto Scanner para permitir la entrada de datos por consola.
 */
	public Menu() {
		teclado = new Scanner(System.in);
	}

	/**
	 *Muestra la pantalla de bienvenida del sistema y espera a que el usuario pulse ENTER

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
	 * Muestra el menu principal con la lista de peliculas disponibles
	 * @param peliculas  Lista de peliculas disponibles para mostrar
	 * @param hayCarrito Indica si existe una compra en curso
	 * @return Opcion seleccionada por el usuario
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
	 * Muestra el menu de fechas disponibles para una pelicula seleccionada
	 * @param pelicula Pelicula seleccionada
	 * @param fechas Lista de fechas disponibles para esa pelicula
	 * @return Opcion seleccionada por el usuario
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
	 * Muestra las sesiones disponibles para una pelicula en una fecha determinada

	 * @param pelicula seleccionada
	 * @param fecha  seleccionada
	 * @param sesiones Lista de sesiones disponibles
	 * @param salas Lista de salas asociadas a las sesiones
	 * @return Opcion seleccionada por el usuario
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
	 * Solicita al usuario el numero de espectadores para la sesion seleccionada

	 * @return Numero de espectadores introducido
	 */
	public int pedirNumEspectadores() {
		int numEspectadores = leerEntero("\n--> Introducir el Numero de Espectadores  : ");

		return numEspectadores;
	}

	/**
	 * Muestra un resumen de la seleccion realizada por el usuario
	 * @param pelicula seleccionada
	 * @param sesion  seleccionada
	 * @param sala  asignada a la sesion
	 * @param espectadores Numero de espectadores
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
	 * Muestra el resumen final de la compra con importes y lineas de detalle

	 * @param lineas Lista de lineas descriptivas de la compra
	 * @param subtotal Importe subtotal
	 * @param descuento Importe del descuento aplicado
	 * @param total Importe total a pagar
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
	 *  Muestra las opciones despues del resumen de compra

	 * @return Opcion seleccionada por el usuario
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
	 * Solicita al usuario su DNI
	 * @return DNI introducido
	 */
	public String pedirDni() {
		System.out.print("DNI : ");
		String dni = teclado.nextLine().trim();
		return dni;
	}

	/**
	 * Solicita al usuario su password
	 * @return Password introducido
	 */
	public String pedirPassword() {

	    String password = "";

	    System.out.print("Password : ");
	    
	    password =teclado.next().trim();
	    return password;
	}


	/**
	 * Muestra un mensaje indicando que el login fue correcto
	 */
	public void mensajeLoginCorrecto() {
		System.out.println("\nBien!  LOGIN Correcto ");
	}

	/**
	 * Muestra un mensaje indicando que el login fue incorrecto
	 */
	public void mensajeLoginIncorrecto() {
		System.out.println("\nLOGIN Incorrecto !! ");
	}

	/**
	 * Muestra el menu de inicio de sesion y registro
	 * @return Opcion seleccionada por el usuario
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
	 * Solicita los datos necesarios para registrar un nuevo cliente
	 * @return Objeto Cliente con los datos introducidos y validados
	 */
	public Cliente nuevoCliente() {

		Cliente cliente = new Cliente();
		String dni , email;
		boolean DniValid,emailValid;
		
		System.out.println("\n=============== REGISTRAR CLIENTE ===================");
		System.out.println("|  Introduce tus datos para completar el registro     |");
		System.out.println("+-----------------------------------------------------+");
		
	    do {
	        System.out.print("DNI (ex DNI valido:11111111H): ");
	        dni = teclado.nextLine().trim();
	        
	        DniValid=cliente.verificarDNI(dni);

	      if (!DniValid) {
	            System.out.println("DNI incorrecto. Intenta de nuevo.");
	        }

	    } while (!DniValid);

	    cliente.setDni(dni);


		System.out.print("Nombre : ");
		cliente.setNombre(teclado.nextLine().trim());
		
		System.out.print("Apellido: ");
		cliente.setApellido(teclado.nextLine().trim());

		do {
			System.out.print("Email : ");
	        email = teclado.nextLine().trim();
	        
	        emailValid=cliente.verificarEmail(email);

	      if (!emailValid) {
	            System.out.println("Email incorrecto. Intenta de nuevo.");
	        }

	    } while (!emailValid);
		
		cliente.setEmail(email);

		cliente.setPassword(pedirPassword());
		
		System.out.println("+----------------------------------------------------------+");
	    System.out.println("|  Registro listo. Ya puedes continuar con la compra.       |");
	    System.out.println("+----------------------------------------------------------+");


		return cliente;
	}

	
	
	/**
	 * Muestra el menu de opciones para la factura
	 * @return Opcion seleccionada por el usuario
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
	 * Muestra un mensaje indicando que la factura ha sido guardada correctamente
	 */
	public void mensajeFacturaGuardada() {
		System.out.println("\n+-------------------------------------------------------+");
	    System.out.println("|                 FACTURA GUARDADA                        |");
	    System.out.println("+---------------------------------------------------------+");
	    System.out.println("|       Estado : Factura guardada con Exito               |");
	    System.out.println("|   --> Pulsa ENTER para volver al inicio                 |");
	    System.out.println("+---------------------------------------------------------+");
		teclado.nextLine();
	}

	/**
	 * Lee un numero entero desde teclado con control de errores
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
