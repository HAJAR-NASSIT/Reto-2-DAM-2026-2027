package VentaEntradasCinema.Controlador;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import VentaEntradasCinema.Modelo.BBDD.GestorDAO.*;
import VentaEntradasCinema.Modelo.Pojos.*;
import VentaEntradasCinema.Vista.Menu;

public class ControladorTest {

	private Controlador controlador;

	private Menu menu;
	private PeliculaDAO peliculaDAO;
	private SesionDAO sesionDAO;
	private SalaDAO salaDAO;
	private ClienteDAO clienteDAO;
	private CompraDAO compraDAO;
	private EntradaDAO entradaDAO;

	private Pelicula pelicula;
	private Sala sala;
	private Sesion sesion;
	private LocalDate fecha;

	private CompraDAOFake compraDAOFake;
	private EntradaDAOFake entradaDAOFake;

	@Before
	public void setUp() {

		pelicula = crearPeliculaTest();
		sala = crearSalaTest();
		fecha = crearFechaTest();
		sesion = crearSesionTest(pelicula, sala, fecha);

		menu = crearMenuFake();
		peliculaDAO = crearPeliculaDAOFake(pelicula);
		sesionDAO = crearSesionDAOFake(fecha, sesion);
		salaDAO = crearSalaDAOFake(sala);
		clienteDAO = crearClienteDAOFake();

		compraDAOFake = new CompraDAOFake();
		entradaDAOFake = new EntradaDAOFake();

		compraDAO = compraDAOFake;
		entradaDAO = entradaDAOFake;

		controlador = new Controlador(menu, peliculaDAO, sesionDAO, salaDAO, clienteDAO, compraDAO, entradaDAO);
	}

	@Test
	public void testIniciarCompraConLogin() {

		controlador.iniciar();

		assertTrue(compraDAOFake.insertarCompraLlamado);
		assertEquals(1, compraDAOFake.idCompraDevuelto);

		assertEquals(2, entradaDAOFake.totalEntradasInsertadas);
	}



	private Pelicula crearPeliculaTest() {

		Pelicula pelicula = new Pelicula();

		int idPelicula = 1;
		String titulo = "Test";

		pelicula.setIdPelicula(idPelicula);
		pelicula.setTitulo(titulo);

		return pelicula;
	}

	private Sala crearSalaTest() {

		Sala sala = new Sala();

		int idSala = 1;
		String nombre = "Sala 1";
		int capacidad = 100;

		sala.setIdSala(idSala);
		sala.setNombre(nombre);
		sala.setCapacidad(capacidad);

		return sala;
	}

	private LocalDate crearFechaTest() {

		LocalDate fecha = LocalDate.of(2026, 6, 18);

		return fecha;
	}

	private Sesion crearSesionTest(Pelicula pelicula, Sala sala, LocalDate fecha) {

		Sesion sesion = new Sesion();

		int idSesion = 1;
		LocalTime horaInicio = LocalTime.of(18, 0);
		LocalTime horaFin = LocalTime.of(20, 0);
		double precio = 10;

		sesion.setIdSesion(idSesion);
		sesion.setPelicula(pelicula);
		sesion.setSala(sala);
		sesion.setFecha(fecha);
		sesion.setHoraInicio(horaInicio);
		sesion.setHoraFin(horaFin);
		sesion.setPrecio(precio);

		return sesion;
	}

	private Menu crearMenuFake() {

		Menu menu = new MenuFake();

		return menu;
	}

	private PeliculaDAO crearPeliculaDAOFake(Pelicula pelicula) {

		PeliculaDAO peliculaDAO = new PeliculaDAOFake(pelicula);

		return peliculaDAO;
	}

	private SesionDAO crearSesionDAOFake(LocalDate fecha, Sesion sesion) {

		SesionDAO sesionDAO = new SesionDAOFake(fecha, sesion);

		return sesionDAO;
	}

	private SalaDAO crearSalaDAOFake(Sala sala) {

		SalaDAO salaDAO = new SalaDAOFake(sala);

		return salaDAO;
	}

	private ClienteDAO crearClienteDAOFake() {

		ClienteDAO clienteDAO = new ClienteDAOFake();

		return clienteDAO;
	}

	

	private class MenuFake extends Menu {

		private int contadorMenuPeliculas = 0;

		
		public void pantallaBienvenida() {
		}

		
		public int mostrarMenuPeliculas(ArrayList<Pelicula> peliculas, boolean hayCarrito) {

			contadorMenuPeliculas++;

			int opcion = 0;

			if (contadorMenuPeliculas == 1) {
				opcion = 1; // seleccionar pelicula
			} else if (contadorMenuPeliculas == 2) {
				opcion = 0; // finalizar compra
			} else {
				opcion = 99; // salir
			}

			return opcion;
		}

		
		public int mostrarMenuFechas(Pelicula pelicula, ArrayList<LocalDate> fechas) {

			int opcion = 1;

			return opcion;
		}

		public int mostrarMenuSesiones(Pelicula pelicula, LocalDate fecha, ArrayList<Sesion> sesiones,
				ArrayList<Sala> salas) {

			int opcion = 1;

			return opcion;
		}

		
		public int pedirNumEspectadores() {

			int espectadores = 2;

			return espectadores;
		}

		
		public void mostrarSeleccion(Pelicula pelicula, Sesion sesion, Sala sala, int espectadores) {
		}

		public void mostrarResumenCompra(ArrayList<String> lineas, double subtotal, double descuento, double total) {
		}

		public int opcionResumenCompra() {

			int opcion = 1;

			return opcion;
		}

		
		public int menuLogin() {

			int opcion = 1; // login

			return opcion;
		}

		
		public String pedirDni() {

			String dni = "00000000T";

			return dni;
		}

	
		public String pedirPassword() {

			String password = "12345678";

			return password;
		}

		
		
		public int opcionFactura() {

			int opcion = 0;

			return opcion;
		}

		
		
	}

	private class PeliculaDAOFake extends PeliculaDAO {

		private ArrayList<Pelicula> peliculas;

		public PeliculaDAOFake(Pelicula pelicula) {

			peliculas = new ArrayList<>();
			peliculas.add(pelicula);
		}

		
		public ArrayList<Pelicula> listaPeliculasPorSecionMasCerca() {

			return peliculas;
		}
	}

	private class SesionDAOFake extends SesionDAO {

		private ArrayList<LocalDate> fechas;
		private ArrayList<Sesion> sesiones;

		public SesionDAOFake(LocalDate fecha, Sesion sesion) {

			fechas = new ArrayList<>();
			fechas.add(fecha);

			sesiones = new ArrayList<>();
			sesiones.add(sesion);
		}

		public ArrayList<LocalDate> listarFechas(int idPelicula) {

			return fechas;
		}

	
		public ArrayList<Sesion> listarSesiones(int idPelicula, LocalDate fechaElegida) {

			return sesiones;
		}
	}

	private class SalaDAOFake extends SalaDAO {

		private Sala sala;

		public SalaDAOFake(Sala sala) {

			this.sala = sala;
		}

		public Sala obtenerSala(int idSala) {

			return sala;
		}
	}

	private class ClienteDAOFake extends ClienteDAO {

		
		public boolean login(String dni, String password) {

			boolean correcto = false;

			if (dni.equals("00000000T") && password.equals("12345678")) {
				correcto = true;
			}

			return correcto;
		}

		public Cliente obtenerClientePorDni(String dni) {

			Cliente cliente = new Cliente();

			String nombre = "Test";
			String apellido = "User";
			String email = "test@test.com";

			cliente.setDni(dni);
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setEmail(email);

			return cliente;
		}

		
		public boolean existeDni(String dni) {

			return true;
		}
	}

	private class CompraDAOFake extends CompraDAO {

		public boolean insertarCompraLlamado = false;
		public int idCompraDevuelto = 1;

		
		public void insertarCompra(String dni, double total, double descuento) {

			insertarCompraLlamado = true;
		}

		
		public int obtenerUltimoIdCompra() {

			return idCompraDevuelto;
		}
	}

	private class EntradaDAOFake extends EntradaDAO {

		public int totalEntradasInsertadas = 0;

		
		public void insertarEntrada(int idSesion, int idCompra, double importe) {

			totalEntradasInsertadas++;
		}
	}
}
