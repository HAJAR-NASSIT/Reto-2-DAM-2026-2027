package VentaEntradasCenima.Modelo.BBDD.GestorDAO;

import java.sql.*;

import VentaEntradasCenima.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCenima.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCenima.Modelo.Pojos.Sala;

public class SalaDAO {

	
	
/**
 * 
 * @param idSala
 * @return
 */
	public Sala  obtenerSala( int idSala ){
		String sql= SQLQuerys.SELECT_SALA_POR_ID ;
		
        Connection connection = null ;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;

    	Sala sala = new Sala() ;
        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement(sql) ;
        	preparedStatement.setInt( 1 , idSala );
        	resultSet = preparedStatement.executeQuery() ;
        	
            if( resultSet.next()) {
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