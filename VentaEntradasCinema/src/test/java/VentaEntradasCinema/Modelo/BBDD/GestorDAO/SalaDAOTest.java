package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import static org.junit.Assert.*;

import org.junit.Test;

import VentaEntradasCinema.Modelo.Pojos.Sala;

public class SalaDAOTest {

	private SalaDAO salaDAO = new SalaDAO();

	@Test
	public void testObtenerSala() {

		int idSalaExistente = 1; 
		
		Sala sala = salaDAO.obtenerSala(idSalaExistente);
		int id_sala=sala.getIdSala();

		assertNotNull(sala);
		assertEquals(idSalaExistente, id_sala);
		assertNotNull(sala.getNombre());
		assertTrue(sala.getCapacidad() > 0);
		
		
		int idSala_NO_exist = 9999; 
		Sala salaNoExist = salaDAO.obtenerSala(idSala_NO_exist);

        assertNull(salaNoExist);
       
	}
	
	
	

}
