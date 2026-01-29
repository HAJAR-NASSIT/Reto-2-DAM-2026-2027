package VentaEntradasCinema.Modelo.ClasesAxiliares;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;

public class CarritoTest {

	private Carrito crearCarritotest() {
		
		Carrito carrito = new Carrito();
		Pelicula p = new Pelicula();
		Sala sala = new Sala();
		Sesion sesion = new Sesion();

		
		p.setIdPelicula(1);
		p.setTitulo("Test");
		
		sala.setIdSala(1);
		sala.setNombre("Sala 1");

		sesion.setIdSesion(1);
		sesion.setPelicula(p);
		sesion.setSala(sala);
		sesion.setFecha(LocalDate.now());
		sesion.setHoraInicio(LocalTime.of(18, 0));
		sesion.setHoraFin(LocalTime.of(20, 0));
		sesion.setPrecio(10);

		carrito.addSeleccion(p, sesion, sala, 2);

		return carrito;
	}

	@Test
	public void testAddSeleccion() {
		
		Carrito carrito = crearCarritotest();
		int size=carrito.getSelecciones().size();
		
		assertEquals(1,size);
	}

	@Test
	public void testGetSelecciones() {
		
		Carrito carrito = crearCarritotest();
		
		
		assertNotNull(carrito.getSelecciones());
	}

	@Test
	public void testHaySeleccion() {
		
		
		Carrito carrito = new Carrito();
		assertFalse(carrito.haySeleccion());

		carrito = crearCarritotest();
		assertTrue(carrito.haySeleccion());
	}

	@Test
	public void testReiniciarCarrito() {
		
		Carrito carrito = crearCarritotest();
		
		carrito.reiniciarCarrito();
		
		int size=carrito.getSelecciones().size();
		
		assertFalse(carrito.haySeleccion());
		assertEquals(0, size);
	}

	@Test
	public void testCalcularSubtotal() {
		
		
		Carrito carrito = crearCarritotest();
		assertEquals(20.0, carrito.calcularSubtotal(), 0.01);
	}

	@Test
	public void testContarEntradas() {
		Carrito carrito = crearCarritotest();
		assertEquals(2, carrito.contarEntradas());
	}

	@Test
	public void testContarPeliculasDistintas() {
		Carrito carrito = crearCarritotest();
		assertEquals(1, carrito.contarPeliculasDistintas());
	}

	@Test
	public void testCalcularDescuento() {
		Carrito carrito = crearCarritotest();
		assertEquals(0.0, carrito.calcularDescuento(), 0.01);
	}

	@Test
	public void testGenerarLineasResumen() {
		
		Carrito carrito = crearCarritotest();
		
		int size=carrito.generarLineasResumen().size();
		
		assertEquals(1, size);
	}
}
