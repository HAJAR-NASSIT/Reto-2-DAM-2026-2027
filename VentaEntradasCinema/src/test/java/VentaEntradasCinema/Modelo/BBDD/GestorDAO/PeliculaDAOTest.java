package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;

public class PeliculaDAOTest {

	private PeliculaDAO peliculaDAO = new PeliculaDAO();

	@Test
	public void testListaPeliculasPorSecionMasCerca() {

		try {
			ArrayList<Pelicula> peliculas = peliculaDAO.listaPeliculasPorSecionMasCerca();

			assertNotNull(peliculas);
			assertTrue(peliculas.size() > 0);

			for (Pelicula p : peliculas) {
				assertTrue(p.getIdPelicula() > 0);
				assertNotNull(p.getTitulo());
				assertNotNull(p.getNombreGenero());
			}

		} catch (Exception e) {
		    System.out.println("Ocurrió un error al listar películas: " + e.getMessage());
		}
	}

}
