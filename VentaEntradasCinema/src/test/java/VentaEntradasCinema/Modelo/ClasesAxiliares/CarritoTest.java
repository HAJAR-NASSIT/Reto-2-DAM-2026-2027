package VentaEntradasCinema.Modelo.ClasesAxiliares;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;

public class CarritoTest {

	private Carrito carrito;

	@Before
	public void setUp() {

		carrito = new Carrito();
		Pelicula pelicula = new Pelicula();
		Sala sala = new Sala();
		Sesion sesion = new Sesion();

		pelicula.setIdPelicula(1);
		pelicula.setTitulo("Test");

		sala.setIdSala(1);
		sala.setNombre("Sala 1");
		sala.setCapacidad(100);

		sesion.setIdSesion(1);
		sesion.setPelicula(pelicula);
		sesion.setSala(sala);
		sesion.setFecha(LocalDate.of(2026, 6, 18));
		sesion.setHoraInicio(LocalTime.of(18, 0));
		sesion.setHoraFin(LocalTime.of(20, 0));
		sesion.setPrecio(10);

		boolean added = carrito.addSeleccion(pelicula, sesion, sala, 2);
		assertTrue(added);
	}

	@After
	public void tearDown() {
		carrito.reiniciarCarrito();
	}

	@Test
	public void testAddSeleccion() {

		int size = carrito.getSelecciones().size();
		assertEquals(1, size);
	}

	@Test
	public void testGetSelecciones() {

		assertNotNull(carrito.getSelecciones());
	}

	@Test
	public void testHaySeleccion() {

		Carrito carritoVacio = new Carrito();
		assertFalse(carritoVacio.haySeleccion());

		assertTrue(carrito.haySeleccion());
	}

	@Test
	public void testReiniciarCarrito() {

		carrito.reiniciarCarrito();

		int size = carrito.getSelecciones().size();

		assertFalse(carrito.haySeleccion());
		assertEquals(0, size);
	}

	@Test
	public void testCalcularSubtotal() {

		assertEquals(20.0, carrito.calcularSubtotal(), 0.01);
	}

	@Test
	public void testContarEntradas() {

		assertEquals(2, carrito.contarEntradas());
	}

	@Test
	public void testContarPeliculasDistintas() {

		assertEquals(1, carrito.contarPeliculasDistintas());
	}

	@Test
	public void testCalcularDescuento() {

		assertEquals(0.0, carrito.calcularDescuento(), 0.01);
	}

	@Test
	public void testGenerarLineasResumen() {

		int size = carrito.generarLineasResumen().size();
		assertEquals(1, size);
	}
}
