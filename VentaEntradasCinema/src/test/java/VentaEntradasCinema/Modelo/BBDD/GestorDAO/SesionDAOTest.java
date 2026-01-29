package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;

public class SesionDAOTest {

	private SesionDAO sesionDAO = new SesionDAO();;

	@Test
	public void testListarFechas() {

		int idPelicula = 1;
		ArrayList<LocalDate> fechas = sesionDAO.listarFechas(idPelicula);

		assertNotNull(fechas);
		
		int idPeliculaNoExist = 9999; 
	    ArrayList<LocalDate> fechasNoExist = sesionDAO.listarFechas(idPeliculaNoExist);

	    assertTrue(fechasNoExist.isEmpty()); 
	}

	@Test
	public void testListarSesiones() {
		int idPelicula = 1;
		LocalDate fecha = LocalDate.of(2026, 6, 18);
		ArrayList<Sesion> sesiones = sesionDAO.listarSesiones(idPelicula, fecha);

		for (Sesion s : sesiones) {
			Pelicula pelicula = s.getPelicula();
			Sala sala = s.getSala();
			LocalDate fechaSesion = s.getFecha();
			LocalTime horaInicio = s.getHoraInicio();
			LocalTime horaFin = s.getHoraFin();
			double precio = s.getPrecio();

			assertNotNull(pelicula);
			assertNotNull(sala);
			assertNotNull(fechaSesion);
			assertNotNull(horaInicio);
			assertNotNull(horaFin);
			assertTrue(precio >= 0);

		}
		
		
		int idPeliculaNoExist = 9999;
		LocalDate fechaNoExist = LocalDate.of(2026, 6, 18);
		ArrayList<Sesion> Nosesiones = sesionDAO.listarSesiones(idPeliculaNoExist, fechaNoExist);

		assertTrue(Nosesiones.isEmpty());

	}
}
