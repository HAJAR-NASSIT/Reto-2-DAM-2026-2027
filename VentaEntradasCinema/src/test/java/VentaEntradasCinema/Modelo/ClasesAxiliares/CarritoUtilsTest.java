package VentaEntradasCinema.Modelo.ClasesAxiliares;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;

public class CarritoUtilsTest {

	private Sesion sesion;
	private Sala sala;

	@Before
	public void setUp() {

		Pelicula pelicula = new Pelicula();
		pelicula.setIdPelicula(1);
		pelicula.setTitulo("Test");

		sala = new Sala();
		sala.setIdSala(1);
		sala.setNombre("Sala 1");
		sala.setCapacidad(100);

		sesion = new Sesion();
		sesion.setIdSesion(1); 
		sesion.setPelicula(pelicula);
		sesion.setSala(sala);
		sesion.setPrecio(5.50);
	}

	@Test
	public void testHayEntradasDisponiblesTrue() {

		boolean disponible = CarritoUtils.hayEntradasDisponibles(sesion, sala, 1);
		assertTrue(disponible);
	}

	@Test
	public void testHayEntradasDisponiblesFalse() {

		boolean disponible = CarritoUtils.hayEntradasDisponibles(sesion, sala, 1000);
		assertFalse(disponible);
	}
}
