package VentaEntradasCenima.Modelo.BBDD.GestorDAO;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import VentaEntradasCenima.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCenima.Modelo.BBDD.Utils.SQLQuerys;

public class CompraDAO {
	
	public  void insertarCompra (String dni, double total, double descuento, int numEntradas)  {

        String sql =SQLQuerys.INSERT_NUEVA_COMPRA;

	        Connection connection = null ;
	        PreparedStatement preparedStatement = null ;

        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement(sql);

        	preparedStatement.setString(1, dni);
        	preparedStatement.setDate(2, Date.valueOf(LocalDate.now())) ;
        	preparedStatement.setTime(3, Time.valueOf(LocalTime.now())) ;
        	preparedStatement.setDouble(4, total ) ;
        	preparedStatement.setDouble(5, descuento);
        	preparedStatement.setInt(6,numEntradas );

        	preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar Compra: " + e.getMessage());
        }finally {
        	try{
             
                if (preparedStatement != null) {
                	preparedStatement.close();
                }
            }catch (SQLException e) {
            	
            }
            DBUtils.cerrarConexion(connection);
        }

    }
	
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
	        System.out.println("Error obtenerUltimoIdCompra: " + e.getMessage());
	    } finally {
	    	try {
                if (resultSet != null) {
                	resultSet.close();
                }
                if(preparedStatement != null) {
                	preparedStatement.close();
                }
            }catch (SQLException e) {
            	
            }
             DBUtils.cerrarConexion(connection);
	    }
	   return idCompra;
	}
	
	
}