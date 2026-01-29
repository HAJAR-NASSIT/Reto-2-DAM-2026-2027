package VentaEntradasCinema.Modelo.ClasesAxiliares;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;

public class SeleccionTest {

	private Pelicula crearPelicula() {

		Pelicula p = new Pelicula();
		p.setIdPelicula(1);
		p.setTitulo("Pelicula Test");
		
		return p;
	}

	private Sala crearSala() {
		
		Sala s = new Sala();
		
		s.setIdSala(1);
		s.setNombre("Sala 1");
		s.setCapacidad(100);
		
		return s;
	}

	private Sesion crearSesion(Pelicula p, Sala s) {
		
		Sesion sesion = new Sesion();
		sesion.setIdSesion(1);
		sesion.setPelicula(p);
		sesion.setSala(s);
		sesion.setFecha(LocalDate.now());
		sesion.setHoraInicio(LocalTime.of(18, 0));
		sesion.setHoraFin(LocalTime.of(20, 0));
		sesion.setPrecio(5.5);
		
		
		return sesion;
	}

	@Test
	public void testSeleccion() {
		
		Pelicula p = crearPelicula();
		Sala s = crearSala();
		Sesion sesion = crearSesion(p, s);

		Seleccion seleccion = new Seleccion(p, sesion, s, 2);

		assertNotNull(seleccion);
	}

	@Test
	public void testGetPelicula() {
		
		Pelicula p = crearPelicula();
		Seleccion seleccion = new Seleccion(p, crearSesion(p, crearSala()), crearSala(), 1);

		assertEquals(p, seleccion.getPelicula());
	}

	@Test
	public void testSetPelicula() {
		
		Pelicula p1 = crearPelicula();
		Pelicula p2 = new Pelicula();
		p2.setIdPelicula(2);
		p2.setTitulo("Otra pelicula");

		Seleccion seleccion = new Seleccion(p1, crearSesion(p1, crearSala()), crearSala(), 1);
		seleccion.setPelicula(p2);

		assertEquals(p2, seleccion.getPelicula());
	}

	@Test
	public void testGetSesion() {
		
		Pelicula p = crearPelicula();
		Sala s = crearSala();
		Sesion sesion = crearSesion(p, s);

		Seleccion seleccion = new Seleccion(p, sesion, s, 1);

		assertEquals(sesion, seleccion.getSesion());
	}

	@Test
	public void testGetSala() {
		
		Pelicula p = crearPelicula();
		Sala s = crearSala();

		Seleccion seleccion = new Seleccion(p, crearSesion(p, s), s, 1);

		assertEquals(s, seleccion.getSala());
	}

	@Test
	public void testGetEspectadores() {
		
		Seleccion seleccion = new Seleccion(crearPelicula(), crearSesion(crearPelicula(), crearSala()), crearSala(), 3);

		assertEquals(3, seleccion.getEspectadores());
	}
}
