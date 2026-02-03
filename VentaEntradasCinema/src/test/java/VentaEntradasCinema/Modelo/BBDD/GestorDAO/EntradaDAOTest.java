package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntradaDAOTest {

	private EntradaDAO entradaDAO;
	private CompraDAO compraDAO;

	@Before
	public void setUp() {
		entradaDAO = new EntradaDAO();
		compraDAO = new CompraDAO();
	}

	@Test
	public void testInsertarEntrada() {
		
		String dni = "00000001R";
		double precioTotal = 5.5;
		double descuentoTotal = 0;
		int idSesion = 1;
		int idCompra ;

		double importe = 5.5;


		compraDAO.insertarCompra(dni, precioTotal, descuentoTotal);

		idCompra = compraDAO.obtenerUltimoIdCompra();
		
		try {
			entradaDAO.insertarEntrada(idSesion, idCompra, importe);
			assertTrue(true);
			
			compraDAO.borrarUltimaCompra();


		} catch (Exception e) {
			fail(e.getMessage());
		} 
	}
}