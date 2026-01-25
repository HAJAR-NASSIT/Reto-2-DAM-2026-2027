package VentaEntradasCenima.Modelo.BBDD.GestorDAO;

public class EntradaDAO {

/**
 * 
 * @param idSesion
 * @param idCompra
 * @param precio
 * @param descuento
 */
	public void   insertarEntrada( int idSesion, int idCompra  , double  precio, double descuento ) {

        String sql = SQLQuerys.INSERT_NUEVA_ENTRADA;
        
        Connection connection =null ;
        PreparedStatement preparedStatement = null ;

        try{
        	connection = DBUtils.getConnection() ;
        	preparedStatement = connection.prepareStatement(sql );

        	preparedStatement.setInt(1 , idSesion);
        	preparedStatement.setInt(2 , idCompra)  ;
        	preparedStatement.setDouble(3, precio) ;
        	preparedStatement.setDouble(4 , descuento);

        	preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al  insertar Entrada : " + e.getMessage());
        } finally{
        	try{
                
                if (preparedStatement != null ) {
                	preparedStatement.close()  ;
                }
            }catch (SQLException e ) {	
            }
            DBUtils.cerrarConexion( connection) ;
        }
    }
}
