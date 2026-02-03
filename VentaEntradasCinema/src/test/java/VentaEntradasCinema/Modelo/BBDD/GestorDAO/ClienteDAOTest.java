package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VentaEntradasCinema.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCinema.Modelo.Pojos.Cliente;

public class ClienteDAOTest {

	private ClienteDAO clienteDAO;

	@Before
	public void setUp() {
		clienteDAO = new ClienteDAO();
	}

	@After
	public void cleanUp() {
	    String DNI = "11111111H";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = DBUtils.getConnection();
	        preparedStatement = connection.prepareStatement("DELETE FROM cliente WHERE dni = ?");
	        
	      
	        preparedStatement.setString(1, DNI);

	        preparedStatement.executeUpdate();
	        
	    } catch (SQLException e) {
	        System.out.println("Error cleaning up test client: " + e.getMessage());
	    } finally {
	        // Close resources in finally block
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }
	}
	@Test
	public void testObtenerClientePorDni() {

		String dniExist = "00000000T";

		Cliente cliente = clienteDAO.obtenerClientePorDni(dniExist);

		assertNotNull(cliente);

		assertEquals(dniExist, cliente.getDni());
		assertNotNull(cliente.getNombre());
		assertNotNull(cliente.getEmail());

	}

	@Test
	public void testLogin() {

		String DNI1 = "00000000T";
		String password1 = "12345678";

		boolean result = clienteDAO.login(DNI1, password1);

		assertTrue(result);
		String DNI2 = "1234567kA";
		String password2 = "12345655555";

		boolean result2 = clienteDAO.login(DNI2, password2);

		assertFalse(result2);

	}

	@Test
	public void testExisteDni() {
		String DNIexist = "00000000T";
		String DNINoexist = "zzzzzzzzz";

		boolean result1 = clienteDAO.existeDni(DNIexist);
		boolean result2 = clienteDAO.existeDni(DNINoexist);

		assertTrue(result1);
		assertFalse(result2);

	}

	@Test
	public void testInsertarCliente() {

		Cliente cliente = new Cliente();
		String DNI = "11111111H";
		String nombre = "test";
		String apellido="user";
		String email = "test1@gmail.com";
		String password = "1234567893";

		cliente.setDni(DNI);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setEmail(email);
		cliente.setPassword(password);

		boolean insertado = clienteDAO.insertarCliente(cliente);

		assertTrue(insertado);

		boolean existe = clienteDAO.existeDni(DNI);
		assertTrue(existe);

	}

}
