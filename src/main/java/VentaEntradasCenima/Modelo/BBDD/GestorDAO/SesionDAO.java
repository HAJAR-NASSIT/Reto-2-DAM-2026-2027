package VentaEntradasCenima.Modelo.BBDD.GestorDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import VentaEntradasCenima.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCenima.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCenima.Modelo.Pojos.Sesion;

public class SesionDAO {



    public ArrayList<Sesion> listarSesiones(int idPelicula, LocalDate fecha) {

        ArrayList<Sesion> sesiones = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_SESIONES_POR_PELICULA_Y_FECHA);
        	preparedStatement.setInt(1, idPelicula);
        	preparedStatement.setDate(2, Date.valueOf(fecha));
        	resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Sesion sesion = new Sesion();

                sesion.setIdSesion(resultSet.getInt("id_sesion"));
                sesion.setIdPelicula(resultSet.getInt("id_pelicula"));
                sesion.setIdSala(resultSet.getInt("id_sala"));
                sesion.setFecha(resultSet.getDate("fecha").toLocalDate());
                sesion.setHoraInicio(resultSet.getTime("hora_inicio").toLocalTime());
                sesion.setHoraFin(resultSet.getTime("hora_fin").toLocalTime());
                sesion.setPrecio(resultSet.getDouble("precio"));

                sesiones.add(sesion);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar Sesiones: " + e.getMessage());
        } finally {
           
            DBUtils.cerrarConexion(connection);
        }

        return sesiones;
    }

	public ArrayList<LocalDate> listarFechas(int idPelicula) {
		 ArrayList<LocalDate> fechas = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	        	connection = DBUtils.getConnection();
	        	preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_FECHAS_POR_PELICULA);
	        	preparedStatement.setInt(1, idPelicula);
	        	resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	
	                fechas.add(resultSet.getDate("fecha").toLocalDate());
	            }

	        } catch (SQLException e) {
	            System.out.println("Error listarFechas: " + e.getMessage());
	        } finally {
	          
	            DBUtils.cerrarConexion(connection);
	        }

	        return fechas;
	}
}