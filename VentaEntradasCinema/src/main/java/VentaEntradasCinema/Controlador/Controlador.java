package VentaEntradasCinema.Controlador;

import java.time.LocalDate;
import java.util.ArrayList;

import VentaEntradasCinema.Controlador.Ficheros.GestorFactura;
import VentaEntradasCinema.Modelo.BBDD.GestorDAO.*;
import VentaEntradasCinema.Modelo.ClasesAxiliares.Carrito;
import VentaEntradasCinema.Modelo.ClasesAxiliares.Seleccion;
import VentaEntradasCinema.Modelo.Pojos.*;
import VentaEntradasCinema.Vista.Menu;

/**
 * Clase principal del controlador. Coordina la interacción entre la vista
 * (Menu), el modelo (DAO, Carrito) y la lógica de la aplicación.
 */

public class Controlador {

	private final Menu menu;
	private final PeliculaDAO peliculaDAO;
	private final SesionDAO sesionDAO;
	private final SalaDAO salaDAO;
	private final ClienteDAO clienteDAO;
	private final CompraDAO compraDAO;
	private final EntradaDAO entradaDAO;

	/**
	 * Constructor del controlador.
	 *
	 * @param menu        menú principal de la aplicación
	 * @param peliculaDAO acceso a datos de películas
	 * @param sesionDAO   acceso a datos de sesiones
	 * @param salaDAO     acceso a datos de salas
	 * @param clienteDAO  acceso a datos de clientes
	 * @param compraDAO   acceso a datos de compras
	 * @param entradaDAO  acceso a datos de entradas
	 */

	public Controlador(Menu menu, PeliculaDAO peliculaDAO, SesionDAO sesionDAO, SalaDAO salaDAO, ClienteDAO clienteDAO,
			CompraDAO compraDAO, EntradaDAO entradaDAO) {

		this.menu = menu;
		this.peliculaDAO = peliculaDAO;
		this.sesionDAO = sesionDAO;
		this.salaDAO = salaDAO;
		this.clienteDAO = clienteDAO;
		this.compraDAO = compraDAO;
		this.entradaDAO = entradaDAO;
	}

	/**
	 * Inicia la ejecución principal del programa. Muestra el menú y gestiona el
	 * flujo general de la aplicación.
	 */

	public void iniciar() {

		Carrito carrito = new Carrito();
		GestorFactura gestorFactura = new GestorFactura();
		Cliente clienteActual = null;

		menu.pantallaBienvenida();
		boolean salir = false;

		while (salir == false) {

			ArrayList<Pelicula> peliculas = peliculaDAO.listaPeliculasPorSecionMasCerca();
			boolean hayCarrito = carrito.haySeleccion();

			int opcion = menu.mostrarMenuPeliculas(peliculas, hayCarrito);

			switch (opcion) {
			case 99:
				System.out.println(" Saliendo del programa... Hasta pronto! ");
				salir = true;
				break;

			case 0:
				clienteActual = opcionFinalizarCompra(carrito, gestorFactura, clienteActual);
				break;

			default:
				opcionSeleccionarPelicula(opcion, peliculas, carrito);
				break;
			}
		}
	}

	/**
	 * Finaliza la compra si hay selecciones en el carrito.
	 *
	 * @param carrito       carrito actual
	 * @param gestorFactura gestor de facturas
	 * @param clienteActual cliente actualmente identificado
	 * @return cliente final tras la compra
	 */
	private Cliente opcionFinalizarCompra(Carrito carrito, GestorFactura gestorFactura, Cliente clienteActual) {

		Cliente cliente = clienteActual;

		if (carrito.haySeleccion() == false) {
			System.out.println("No hay selecciones !!! ");
		} else {
			cliente = pantallaResumen(carrito, gestorFactura, clienteActual);
		}

		return cliente;
	}

	/**
	 * Gestiona la selección de una película.
	 *
	 * @param opcion    opción elegida por el usuario
	 * @param peliculas lista de películas disponibles
	 * @param carrito   carrito de la compra
	 */

	private void opcionSeleccionarPelicula(int opcion, ArrayList<Pelicula> peliculas, Carrito carrito) {

		if (opcion >= 1 && opcion <= peliculas.size()) {

			Pelicula pelicula = peliculas.get(opcion - 1);
			pantallaFechas(pelicula, carrito);

		} else {
			System.out.println(" Opcion invalida ");
		}
	}

	/**
	 * Muestra las fechas disponibles para una película.
	 *
	 * @param pelicula película seleccionada
	 * @param carrito  carrito de la compra
	 */

	private void pantallaFechas(Pelicula pelicula, Carrito carrito) {

		ArrayList<LocalDate> fechas = sesionDAO.listarFechas(pelicula.getIdPelicula());

		if (fechas.size() == 0) {
			System.out.println("No hay fechas para esta pelicula   ");
		} else {
			boolean volver = false;
			boolean seleccionHecha = false;

			while (volver == false && seleccionHecha == false) {
				int opcionFecha = menu.mostrarMenuFechas(pelicula, fechas);

				switch (opcionFecha) {
				case 0:
					volver = true; // salir de Pantalla Fechas Y volver a Peliculas
					break;

				default:
					if (opcionFecha >= 1 && opcionFecha <= fechas.size()) {
						LocalDate fechaElegida = fechas.get(opcionFecha - 1);
						seleccionHecha = pantallaSesiones(pelicula, fechaElegida, carrito);

					} else {
						System.out.println(" Opcion invalida      ");
					}
					break;
				}
			}
		}

	}

	/**
	 * Muestra las sesiones disponibles para una película y fecha.
	 *
	 * @param pelicula     película seleccionada
	 * @param fechaElegida fecha elegida
	 * @param carrito      carrito de la compra
	 * @return true si se realizó una selección
	 */

	private boolean pantallaSesiones(Pelicula pelicula, LocalDate fechaElegida, Carrito carrito) {
		

		boolean seleccionHecha = false;
		boolean disponible=false;
		int espectadores =0;
		ArrayList<Sesion> sesiones = sesionDAO.listarSesiones(pelicula.getIdPelicula(), fechaElegida);

		if (sesiones.size() == 0) {
			System.out.println(" No hay sesiones para esta fecha ");

		} else {

			ArrayList<Sala> salas = cargarSalasDeSesiones(sesiones);

			boolean volver = false;

			while (volver == false) {
				int opcionSesion = menu.mostrarMenuSesiones(pelicula, fechaElegida, sesiones, salas);
			
				switch (opcionSesion) {
				case 0:
					volver = true; // salir de Sesiones y volver a Fechas
					break;

				default:
					if (opcionSesion >= 1 && opcionSesion <= sesiones.size()) {

						Sesion sesionElegida = sesiones.get(opcionSesion - 1);
						Sala salaElegida = salas.get(opcionSesion - 1);

						espectadores = menu.pedirNumEspectadores();
						
						disponible = carrito.addSeleccion(pelicula, sesionElegida , salaElegida, espectadores);


						if (espectadores <= 0  ) {
							System.out.println("Debe ser mayor  que 0  ");

						}
						
						if (disponible){
							
							// carrito.addSeleccion(pelicula, sesionElegida, salaElegida, espectadores);
							menu.mostrarSeleccion(pelicula, sesionElegida, salaElegida, espectadores);
							seleccionHecha = true; // avisar a pantallaFechas() para salir tambien y volver a peliculas
							volver = true; // salir de Pantalla sesiones
							
						}

					} else {
						System.out.println(" Opcion  invalida    ");
					}
					break;
				}
			}
		}

		return seleccionHecha;
	}

	/**
	 * Carga las salas asociadas a las sesiones.
	 *
	 * @param sesiones lista de sesiones
	 * @return lista de salas
	 */

	private ArrayList<Sala> cargarSalasDeSesiones(ArrayList<Sesion> sesiones) {

		ArrayList<Sala> salas = new ArrayList<>();

		for (Sesion sesion : sesiones) {
			int idSala = sesion.getSala().getIdSala();

			salas.add(salaDAO.obtenerSala(idSala));
		}

		return salas;
	}

	/**
	 * Muestra el resumen de la compra.
	 *
	 * @param carrito       carrito actual
	 * @param gestorFactura gestor de facturas
	 * @param clienteActual cliente actual
	 * @return cliente tras confirmar la compra
	 */

	private Cliente pantallaResumen(Carrito carrito, GestorFactura gestorFactura, Cliente clienteActual) {

		double subtotal = carrito.calcularSubtotal();
		double descuento = carrito.calcularDescuento();
		double total = subtotal - descuento;

		ArrayList<String> lineas = carrito.generarLineasResumen();
		menu.mostrarResumenCompra(lineas, subtotal, descuento, total);

		int opcion = menu.opcionResumenCompra();

		Cliente cliente = clienteActual;

		switch (opcion) {
		case 0:
			// volver a peliculas
			break;

		case 1:
			cliente = pantallaLoginYCompra(total, descuento, carrito, gestorFactura);
			break;

		default:
			System.out.println("  Opcion invalida");
			break;
		}

		return cliente;
	}

	/**
	 * Gestiona el login del cliente y la finalización de la compra.
	 *
	 * @param total         importe total
	 * @param descuento     descuento aplicado
	 * @param carrito       carrito actual
	 * @param gestorFactura gestor de facturas
	 * @return cliente que realiza la compra
	 */

	private Cliente pantallaLoginYCompra(double total, double descuento, Carrito carrito, GestorFactura gestorFactura) {

		Cliente cliente = identificarCliente(carrito);

		if (cliente != null) {
			String dni = cliente.getDni();

			int idCompra = guardarCompraBD(dni, total, descuento, carrito);

			if (idCompra != 0) {

				guardarEntradasBD(idCompra, carrito);

				System.out.println("\nCOMPRA FINALIZADA Con exito   ");
				System.out.println("ID COMPRA : " + idCompra);

				pantallaFactura(cliente, idCompra, descuento, carrito, gestorFactura);

			} else {
				System.out.println("Error, al guardar la compra. Operacion cancelada ");
			}

		} else {
			System.out.println("Compra cancelada por el usuario ");
		}

		return cliente;
	}

	/**
	 * Identifica al cliente (login o registro).
	 *
	 * @param carrito carrito actual
	 * @return cliente identificado o null
	 */

	private Cliente identificarCliente(Carrito carrito) {

		boolean hecho = false;
		Cliente clienteFinal = null;

		while (hecho == false) {
			int opcion = menu.menuLogin();

			switch (opcion) {
			case 0:
				System.out.println(" Compra cancelada. Volviendo al inicio...");
				finalizarCompra(carrito);
				hecho = true;
				clienteFinal = null;
				break;

			case 1:
				clienteFinal = iniciarSesionCliente();
				if (clienteFinal != null) {
					hecho = true;
				}
				break;

			case 2:
				clienteFinal = registrarCliente();
				if (clienteFinal != null) {
					hecho = true;
				}
				break;

			default:
				System.out.println(" Opcion invalida ");
				break;
			}
		}

		return clienteFinal;
	}

	/**
	 * Inicia sesión de un cliente existente.
	 *
	 * @return cliente autenticado o null
	 */

	private Cliente iniciarSesionCliente() {

		String dni = menu.pedirDni();
		String password = menu.pedirPassword();

		boolean accesoCorrecto = clienteDAO.login(dni, password);

		Cliente cliente = null;

		if (accesoCorrecto == true) {
			menu.mensajeLoginCorrecto();
			cliente = clienteDAO.obtenerClientePorDni(dni);

		} else {
			menu.mensajeLoginIncorrecto();
			cliente = null;
		}

		return cliente;
	}

	/**
	 * Registra un nuevo cliente.
	 *
	 * @return cliente registrado o null
	 */

	private Cliente registrarCliente() {

		Cliente nuevoCliente = menu.nuevoCliente();

		boolean dniExiste = clienteDAO.existeDni(nuevoCliente.getDni());

		Cliente cliente = null;

		if (dniExiste == true) {
			System.out.println("El DNI ya existe ! ");
			cliente = null;

		} else {

			boolean registrado = clienteDAO.insertarCliente(nuevoCliente);

			if (registrado == true) {
				cliente = clienteDAO.obtenerClientePorDni(nuevoCliente.getDni());

			} else {
				System.out.println("No se pudo dar de alta ");
				cliente = null;
			}
		}

		return cliente;
	}
	
	/**
	 * Guarda la compra en la base de datos.
	 *
	 * @param dni       DNI del cliente
	 * @param total     importe total
	 * @param descuento descuento aplicado
	 * @param carrito   carrito actual
	 * @return id de la compra
	 */

	private int guardarCompraBD(String dni, double total, double descuento, Carrito carrito) {

		compraDAO.insertarCompra(dni, total, descuento);

		int idCompra = compraDAO.obtenerUltimoIdCompra();

		if (idCompra == 0) {
			System.out.println("Error: NO se puede obtener el ID de Compra");
		}

		return idCompra;
	}

	
	
	/**
	 * Guarda las entradas asociadas a una compra.
	 *
	 * @param idCompra identificador de la compra
	 * @param carrito  carrito actual
	 */

	private void guardarEntradasBD(int idCompra, Carrito carrito) {

		for (Seleccion selecion : carrito.getSelecciones()) {

			int idSesion = selecion.getSesion().getIdSesion();
			double precio = selecion.getSesion().getPrecio();
			int numEntradas = selecion.getEspectadores();

			for (int i = 0; i < numEntradas; i++) {

				entradaDAO.insertarEntrada(idSesion, idCompra, precio);
			}
		}
	}

	/**
	 * Gestiona la opción de generar factura.
	 *
	 * @param cliente       cliente actual
	 * @param idCompra      identificador de la compra
	 * @param descuento     descuento aplicado
	 * @param carrito       carrito actual
	 * @param gestorFactura gestor de facturas
	 */

	private void pantallaFactura(Cliente cliente, int idCompra, double descuento, Carrito carrito,
			GestorFactura gestorFactura) {

		int opcion = menu.opcionFactura();

		switch (opcion) {
		case 0:
			finalizarCompra(carrito);
			break;

		case 1:
			crearFacturaTxt(cliente, idCompra, descuento, carrito, gestorFactura);
			menu.mensajeFacturaGuardada();
			finalizarCompra(carrito);
			break;

		default:
			System.out.println(" Opcion invalida ");
			finalizarCompra(carrito);
			break;
		}
	}

	/**
	 * Genera el fichero TXT de la factura.
	 *
	 * @param cliente       cliente actual
	 * @param idCompra      identificador de la compra
	 * @param descuento     descuento aplicado
	 * @param carrito       carrito actual
	 * @param gestorFactura gestor de facturas
	 */

	private void crearFacturaTxt(Cliente cliente, int idCompra, double descuento, Carrito carrito,
			GestorFactura gestorFactura) {

		if (cliente != null) {

			String dni = cliente.getDni();

			String nombre = "Cliente";
			String apellido  = "Cliente";

			if (cliente.getNombre() != null) {
				nombre = cliente.getNombre();
			}
			
			if (cliente.getNombre() != null) {
				apellido = cliente.getApellido();
			}
			String email = "No disponible";
			if (cliente.getEmail() != null) {
				email = cliente.getEmail();
			}

			ArrayList<String> lineas = carrito.generarLineasResumen();
			double subtotal = carrito.calcularSubtotal();
			double totalFinal = subtotal - descuento;

			gestorFactura.guardarFactura(idCompra, dni, nombre, apellido ,email, lineas, subtotal, descuento, totalFinal);

		} else {
			System.out.println(" No se puedo generar la factura, Cliente no disponible !");
		}
	}

	/**
	 * Reinicia el carrito tras finalizar la compra.
	 *
	 * @param carrito carrito actual
	 */

	private void finalizarCompra(Carrito carrito) {
		carrito.reiniciarCarrito();

	}

}
