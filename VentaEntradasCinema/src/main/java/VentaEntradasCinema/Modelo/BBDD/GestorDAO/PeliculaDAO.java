package VentaEntradasCinema.Modelo.BBDD.GestorDAO;

import java.sql.*;
import java.util.ArrayList;

import VentaEntradasCinema.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCinema.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCinema.Modelo.Pojos.Pelicula;

/**
 * Clase PeliculaDAO.
 * Se encarga de gestionar el acceso a la base de datos
 * relacionado con las películas.
 */

public class PeliculaDAO {

	/**
	 * Obtiene la lista de películas ordenadas por la sesión
	 * más cercana en el tiempo.
	 *
	 * @return lista de objetos {@link Pelicula} disponibles,
	 *         ordenadas según la fecha de su próxima sesión
	 */
	
	
	public  ArrayList < Pelicula> listaPeliculasPorSecionMasCerca() {
		
		 ArrayList < Pelicula > peliculas = new ArrayList<>();
		Connection  connection = null;
		PreparedStatement preparedStatement = null ; 
		ResultSet resultSet =null;

		try {
			connection = DBUtils.getConnection() ;
			preparedStatement = connection.prepareStatement( SQLQuerys.SELECT_PELICULAS_ORDENADAS_POR_SESION_MAS_CERCANA) ;
			
			resultSet = preparedStatement.executeQuery();
			
			while ( resultSet.next() ) {
				
				Pelicula pelicula =  new Pelicula();
		
				pelicula.setIdPelicula(resultSet.getInt("id_pelicula"))   ;
				pelicula.setNombreGenero(resultSet.getString("nombre_genero"));
				pelicula.setTitulo(resultSet.getString("titulo")) ;
				pelicula.setDuracion(resultSet.getInt("duracion") ) ; 
				pelicula.setIdioma(resultSet.getString("idioma"));
				pelicula.setDescripcion(resultSet.getString("descripcion"));
				pelicula.setImagen(resultSet.getString("imagen")) ;
				
				peliculas.add( pelicula ) ;
			}
			
		} catch (SQLException e) {
			System.out.println("Error  al listar Peliculas : " + e.getMessage());
		} finally{
			try{
                if (resultSet != null ) {
                	resultSet.close();
                }
                if (preparedStatement != null ) {
                	preparedStatement.close();
                }
            }catch ( SQLException  e ) {
            	
            }
            DBUtils.cerrarConexion( connection );
		}
		
	return peliculas;
	}
	
	
	
}
