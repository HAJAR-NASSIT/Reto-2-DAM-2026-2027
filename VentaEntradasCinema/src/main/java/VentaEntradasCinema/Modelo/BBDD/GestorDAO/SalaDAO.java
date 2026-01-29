package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import java.sql.*;

import VentaEntradasCinema.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCinema.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCinema.Modelo.Pojos.Sala;


/**
 * Clase SalaDAO.
 * Se encarga de gestionar el acceso a la base de datos
 * relacionado con las salas de cine.
 */


public class SalaDAO {

	/**
	 * Obtiene una sala según su ID.
	 *
	 * @param idSala el identificador de la sala a buscar
	 * @return un objeto {@link Sala} si existe la sala con el ID proporcionado,
	 *         o {@code null} si no se encuentra
	 */
	
	
	public Sala  obtenerSala( int idSala ){
		String sql= SQLQuerys.SELECT_SALA_POR_ID ;
		
        Connection connection = null ;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;

    	Sala sala = null;
    	
        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement(sql) ;
        	preparedStatement.setInt( 1 , idSala );
        	resultSet = preparedStatement.executeQuery() ;
        	
            if( resultSet.next()) {
            	sala = new Sala() ;
            	sala.setIdSala(resultSet.getInt("id_sala")) ;
                sala.setNombre(resultSet.getString("nombre")) ;
                sala.setCapacidad(resultSet.getInt("capacidad")) ;
            }
        } catch ( SQLException  e) {
            System.out.println("Error al  obtener Nombre Sala: " + e.getMessage());
        } finally{
           
        	try{
                if (resultSet != null ){
                	resultSet.close();
                }
                if (preparedStatement != null ) {
                	preparedStatement.close() ;
                }
            }catch (SQLException  e) {
            	
            }
            DBUtils.cerrarConexion( connection);
        }

      return sala ;
    }
	
}
