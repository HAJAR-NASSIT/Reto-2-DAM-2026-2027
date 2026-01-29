package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import VentaEntradasCinema.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCinema.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;


/**
 * Clase SesionDAO.
 * Se encarga de gestionar el acceso a la base de datos
 * relacionado con las sesiones de cine.
 * Permite listar fechas disponibles y sesiones de películas.
 */

public class SesionDAO {

	/**
	 * Lista todas las fechas disponibles para una película concreta.
	 *
	 * @param idPelicula el identificador de la película
	 * @return un {@link ArrayList} de {@link LocalDate} con las fechas disponibles
	 */
	
	
	public  ArrayList <LocalDate > listarFechas( int idPelicula) {

        ArrayList < LocalDate > fechas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;

        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement( SQLQuerys.SELECT_FECHAS_POR_ID_PELICULA);
        	preparedStatement.setInt( 1 , idPelicula );
        	resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {
            	
                fechas.add(resultSet.getDate("fecha").toLocalDate());
            }

        } catch (SQLException e) {
            System.out.println("Error listarFechas: " + e.getMessage());
        } finally{
        	
        	try {
                if (resultSet != null) {
                	resultSet.close();
                }
                if (preparedStatement != null) {
                	preparedStatement.close();
                }
            } catch( SQLException  e) {
            	
            }
            DBUtils.cerrarConexion( connection );
        }

      return fechas ;
    }
	
	

	/**
	 * Lista todas las sesiones de una película en una fecha concreta.
	 *
	 * @param idPelicula el identificador de la película
	 * @param fecha la fecha para la que se quieren obtener las sesiones
	 * @return un {@link ArrayList} de {@link Sesion} con las sesiones disponibles
	 */
	
	
    public ArrayList <Sesion > listarSesiones( int idPelicula, LocalDate  fecha) {

        ArrayList < Sesion > sesiones = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;

        try {
        	connection = DBUtils.getConnection();
        	preparedStatement = connection.prepareStatement( SQLQuerys.SELECT_SESIONES_POR_ID_PELICULA_Y_FECHA);
        	preparedStatement.setInt( 1 , idPelicula) ;
        	preparedStatement.setDate( 2 , Date.valueOf(fecha)) ;
        	resultSet = preparedStatement.executeQuery() ;

            while ( resultSet.next()) {
                Sesion sesion =  new Sesion();
                Pelicula pelicula = new  Pelicula();
                Sala sala =  new Sala();

                sesion.setIdSesion(resultSet.getInt("id_sesion"));
                
                pelicula.setIdPelicula(resultSet.getInt("id_pelicula")) ;
                sesion.setPelicula( pelicula );
                
                sala.setIdSala(resultSet.getInt("id_sala"));
                sesion.setSala( sala );
                
                sesion.setFecha(resultSet.getDate("fecha").toLocalDate());
                sesion.setHoraInicio(resultSet.getTime("hora_inicio").toLocalTime());
                sesion.setHoraFin(resultSet.getTime("hora_fin").toLocalTime());
                sesion.setPrecio(resultSet.getDouble("precio"));

                sesiones.add( sesion );
            }

        } catch ( SQLException  e) {
            System.out.println("Error al listar Sesiones: " + e.getMessage());
        } finally{
           
        	try {
                if (resultSet != null) {
                	resultSet.close();
                }
                if(preparedStatement !=null) {
                	preparedStatement.close();
                }
            } catch( SQLException e ) {
            	
            }
            DBUtils.cerrarConexion(connection);
        }

      return sesiones;
    }
}
