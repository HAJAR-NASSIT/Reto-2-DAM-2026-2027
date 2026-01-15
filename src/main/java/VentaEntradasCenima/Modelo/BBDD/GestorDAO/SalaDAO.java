package VentaEntradasCenima.Modelo.BBDD.GestorDAO;

import java.sql.*;

import VentaEntradasCenima.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCenima.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCenima.Modelo.Pojos.Sala;

public class SalaDAO {

	
	public Sala obtenerNombreSala(int idSala) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

    	Sala sala = new Sala() ;
        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_NOMBRE_SALA_POR_ID);
        	preparedStatement.setInt(1, idSala);
        	resultSet = preparedStatement.executeQuery();
        	
            if (resultSet.next()) {
            	sala.setIdSala(resultSet.getInt("id_sala"));
                sala.setNombre(resultSet.getString("nombre"));
                sala.setCapacidad(resultSet.getInt("capacidad"));
            }

        } catch (SQLException e) {
            System.out.println("Error obtenerNombreSala: " + e.getMessage());
        } finally {
           
            DBUtils.cerrarConexion(connection);
        }

        return sala ;
    }
	
}