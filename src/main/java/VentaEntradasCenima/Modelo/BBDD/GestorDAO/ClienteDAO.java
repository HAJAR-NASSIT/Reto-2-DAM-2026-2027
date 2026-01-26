package VentaEntradasCenima.Modelo.BBDD.GestorDAO;


import java.sql.*;

import VentaEntradasCenima.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCenima.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCenima.Modelo.Pojos.Cliente;


/**
 * Clase ClienteDAO.
 * Gestiona el acceso a la base de datos para las operaciones relacionadas
 * con los clientes: login, registro y consultas por DNI.
 */

 public class ClienteDAO {

	 /**
	  * Obtiene un cliente de la base de datos a partir de su DNI.
	  *
	  * @param dni DNI del cliente
	  * @return objeto Cliente si existe, o null si no se encuentra
	  */
	 
	public Cliente  obtenerClientePorDni( String dni) {
		
        String sql = SQLQuerys.SELECT_CLIENTE_POR_DNI ;
        
	    Connection connection = null ;
	    PreparedStatement preparedStatement = null ;
	    ResultSet resultSet = null  ;
	    Cliente  cliente = null ;
	    
	    try {
	        connection = DBUtils.getConnection() ;
	        preparedStatement = connection.prepareStatement(sql );
	        preparedStatement.setString( 1, dni) ;
	        resultSet = preparedStatement.executeQuery();
	        
	        if ( resultSet.next()){
	            cliente =  new Cliente();
	            cliente.setDni(resultSet.getString("dni")) ;
	            cliente.setNombre(resultSet.getString("nombre"));
	            cliente.setEmail(resultSet.getString("email") );
	        }
	    } catch (SQLException e ) {
	        System.out.println("Error al obtener Cliente Por Dni: " + e.getMessage())  ;
	    } finally{
	        
	    	try {
	            if (resultSet != null ) {
	            	resultSet.close();
	            }
	            if (preparedStatement != null ) {
	            	preparedStatement.close();
	            }
	    	} catch (SQLException e) {
        	
	        }
	        	DBUtils.cerrarConexion( connection);
	    }
	    
	  return cliente;
	}
	
	
	/**
	 * Verifica si las credenciales de un cliente son correctas.
	 *
	 * @param dni DNI del cliente
	 * @param password contraseña del cliente
	 * @return true si el login es válido, false en caso contrario
	 */
	
	
	public  boolean  login( String dni, String  password){
		
        boolean loginEsValido =false ;
        
        String sql = SQLQuerys.SELECT_LOGIN_CLIENTE ;

        Connection  connection = null;
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null ;

        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement( sql);

        	preparedStatement.setString(1, dni.trim() );
        	
        	//String passHash = HashUtils.generarHash( password);
        	preparedStatement.setString(2, password );
        	

        	resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {  
            	loginEsValido= true ;
            }
            
        } catch( SQLException e) {
            System.out.println("Error login: " + e.getMessage());
        }finally {
        	
            try {
                if (resultSet != null){
                	resultSet.close();
                }
                if (preparedStatement !=null) {
                	preparedStatement.close();
                }
            } catch (SQLException e ) {
            	
            }
            DBUtils.cerrarConexion(connection);
        }

        return loginEsValido ;
    }
	
	/**
	 * Comprueba si un DNI ya existe en la base de datos.
	 *
	 * @param dni DNI a comprobar
	 * @return true si el DNI existe, false si no existe
	 */
	
	
	public  boolean existeDni( String dni){
		
		boolean existeDni = false ;
		
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null ;

	    try {
	    	connection = DBUtils.getConnection();
	    	preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_EXISTE_DNI );
	    	preparedStatement.setString( 1, dni);

	    	resultSet = preparedStatement.executeQuery();
	    	
	    	 if(resultSet.next()){ 
	    		 existeDni = true ;
	            }
	    	 


	    } catch (SQLException e) {
	        System.out.println("Error existe Dni: " + e.getMessage());
	        existeDni = false;

	    } finally {
	    	try {
                if(resultSet != null) {
                	resultSet.close();
                }
                if (preparedStatement != null) {
                	preparedStatement.close();
                }
            }catch (SQLException e ){
            	
            }
            DBUtils.cerrarConexion( connection);
	    }
	   return existeDni ;
	}

	/**
	 * Inserta un nuevo cliente en la base de datos.
	 *
	 * @param cliente objeto Cliente con los datos a insertar
	 * @return true si el cliente se insertó correctamente, false en caso contrario
	 */
	
	public  boolean insertarCliente(Cliente cliente) {

	    Connection connection = null;
	    PreparedStatement preparedStatement = null ;
	    boolean resultado = false ;
	    //String passHash = HashUtils.generarHash(cliente.getPassword());

	    try {
	        connection = DBUtils.getConnection();
	        preparedStatement = connection.prepareStatement( SQLQuerys.INSERT_CLIENTE);

	        preparedStatement.setString(1 , cliente.getDni());
	        preparedStatement.setString(2 , cliente.getNombre()) ;
	        preparedStatement.setString(3 , cliente.getEmail());
	        preparedStatement.setString(4 ,cliente.getPassword()); 

	        int filaInsertada = preparedStatement.executeUpdate();

	        if(filaInsertada == 1) {
	            resultado = true ;
	        } else{
	            resultado = false ;
	        }

	    } catch (SQLException e) {
	    	 e.printStackTrace();
	        System.out.println("Error al insertar Cliente");
	        resultado = false;

	    } finally {
	        try {
	            if (preparedStatement != null){
	                preparedStatement.close();
	            }
	        } catch(SQLException e ) {
	            
	        }

	        DBUtils.cerrarConexion( connection);
	    }

	    return resultado;
	}
	
}
