package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompraDAOTest {

	private CompraDAO compraDAO;

	@Test
	public void testInsertarCompra() {

		compraDAO = new CompraDAO();
		String[] DNI = { "00000002W", "00000001R", "00000000T" };
		double[] total = { 25.50, 30.75, 15.25 };
		double[] descuento = { 2.50, 0.0, 1.25 };
		
		for (int i = 0; i < DNI.length; i++) {
			try {
				compraDAO.insertarCompra(DNI[i], total[i], descuento[i]);
				assertTrue(true);

				compraDAO.borrarUltimaCompra();
				assertTrue(true);

			} catch (Exception e) {
				fail(e.getMessage());

			}
		}

	}

	@Test
	public void testObtenerUltimoIdCompra() {

		compraDAO = new CompraDAO();
		String DNI = "00000000T";
		double total = 5;
		double descuento = 0;

		try {
			int id_compra_antes = compraDAO.obtenerUltimoIdCompra();

			compraDAO.insertarCompra(DNI, total, descuento);

			compraDAO = new CompraDAO();

			int id_compra_despuis = compraDAO.obtenerUltimoIdCompra();

			assertTrue(id_compra_antes < id_compra_despuis);
			
			compraDAO.borrarUltimaCompra();
			assertTrue(true);

			
			
		} catch (Exception e) {
			fail(e.getMessage());

		}

	}
}