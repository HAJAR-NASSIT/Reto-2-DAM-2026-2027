package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import java.sql.*;

import VentaEntradasCinema.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCinema.Modelo.BBDD.Utils.SQLQuerys;

/**
 * Clase EntradaDAO. Gestiona las operaciones de acceso a la base de datos
 * relacionadas con las entradas de cine.
 */

public class EntradaDAO {

	/**
	 * Inserta una nueva entrada en la base de datos.
	 *
	 * @param idSesion  identificador de la sesión asociada a la entrada
	 * @param idCompra  identificador de la compra a la que pertenece la entrada
	 * @param importe    precio de la entrada

	 */
	
	
	public void insertarEntrada(int idSesion, int idCompra, double importe) {

		String sql = SQLQuerys.INSERT_NUEVA_ENTRADA;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, idSesion);
			preparedStatement.setInt(2, idCompra);
			preparedStatement.setDouble(3, importe);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al  insertar Entrada : " + e.getMessage());
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
			}
			DBUtils.cerrarConexion(connection);
		}
	}
}
