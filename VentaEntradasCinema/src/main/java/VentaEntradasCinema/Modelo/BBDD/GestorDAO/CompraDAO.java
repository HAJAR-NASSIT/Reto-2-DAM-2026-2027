package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import VentaEntradasCinema.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCinema.Modelo.BBDD.Utils.SQLQuerys;

/**
 * Clase CompraDAO.
 * Gestiona las operaciones de acceso a la base de datos relacionadas
 * con las compras realizadas por los clientes.
 */

public class CompraDAO {
	
	
	/**
	 * Inserta una nueva compra en la base de datos.
	 *
	 * @param dni DNI del cliente que realiza la compra
	 * @param total importe total de la compra
	 * @param descuento descuento aplicado a la compra
	 */
	
	
	public  void insertarCompra (String dni, double total, double descuento){

        String sql =SQLQuerys.INSERT_NUEVA_COMPRA ;

	        Connection  connection = null ;
	        PreparedStatement preparedStatement = null ;

        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement( sql);

        	preparedStatement.setString(1 , dni);
        	preparedStatement.setDate(2 , Date.valueOf(LocalDate.now())) ;
        	preparedStatement.setTime(3 , Time.valueOf(LocalTime.now())) ;
        	preparedStatement.setDouble(4 , total ) ;
        	preparedStatement.setDouble(5 , descuento) ;
        	//preparedStatement.setInt(6 ,numEntradas );

        	preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println("Error al insertar Compra: " + e.getMessage());
        }finally {
        	try{
             
                if (preparedStatement !=null) {
                	preparedStatement.close();
                }
            }catch (SQLException e ) {
            	
            }
            DBUtils.cerrarConexion( connection);
        }

    }

	/**
	 * Obtiene el identificador de la última compra registrada en la base de datos.
	 *
	 * @return id de la última compra o 0 si no se encuentra ninguna
	 */
	
	public  int obtenerUltimoIdCompra() {

	    int idCompra = 0 ;   

	    String sql = SQLQuerys.SELECT_ULTIMO_ID_COMPRA ;

	    Connection connection = null;
	    PreparedStatement preparedStatement = null ;
	    ResultSet  resultSet = null ;

	    try {
	    	connection = DBUtils.getConnection();
	    	preparedStatement = connection.prepareStatement(sql );
	    	
	    	resultSet = preparedStatement.executeQuery();

	        if( resultSet.next()) {
	            idCompra = resultSet.getInt("id") ;
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al  obtener Ultimo Id Compra: " + e.getMessage());
	    } finally {
	    	try {
                if(resultSet != null) {
                	resultSet.close();
                }
                if(preparedStatement != null){
                	preparedStatement.close();
                }
            }catch ( SQLException e ) {
            	
            }
             DBUtils.cerrarConexion(connection);
	    }
	   return idCompra;
	}
	
	
	/**
	 * Elimina de la base de datos la última compra registrada.
	 * Se utiliza principalmente en caso de error o cancelación del proceso.
	 */
	
	public void borrarUltimaCompra(){
		
	    String sql = SQLQuerys.DELETE_COMPRA;
	    
	    Connection connection = null;
	    PreparedStatement preparedStatement = null ;
	    int id_compra=obtenerUltimoIdCompra();

	    try {
	    	connection = DBUtils.getConnection();
	    	preparedStatement = connection.prepareStatement(sql );
	    	
	    	preparedStatement.setInt(1,id_compra);
	    	preparedStatement.executeUpdate();

	     

	    } catch (SQLException e) {
	        System.out.println("Error al borrar Compra: " + e.getMessage());
	    } finally {
	    	try {
                
                if(preparedStatement != null){
                	preparedStatement.close();
                }
            }catch ( SQLException e ) {
            	
            }
             DBUtils.cerrarConexion(connection);
	    }

	}
	
}
