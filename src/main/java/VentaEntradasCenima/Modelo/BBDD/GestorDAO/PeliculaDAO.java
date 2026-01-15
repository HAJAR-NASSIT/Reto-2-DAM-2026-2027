package VentaEntradasCenima.Modelo.BBDD.GestorDAO;

import java.sql.*;
import java.util.ArrayList;

import VentaEntradasCenima.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCenima.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCenima.Modelo.Pojos.Pelicula;

public class PeliculaDAO {

	public ArrayList <Pelicula> listaPeliculasPorSecionMasCerca(){
		ArrayList <Pelicula> peliculas = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;

		try {
			connection = DBUtils.getConnection() ;
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_PELICULAS_ORDENADAS_POR_SESION_MAS_CERCANA);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Pelicula pelicula = new Pelicula();
		
				pelicula.setIdPelicula(resultSet.getInt("id_pelicula"));
				pelicula.setClasificacion(resultSet.getString("nombre_genero"));
				pelicula.setTitulo(resultSet.getString("titulo")) ;
				pelicula.setDuracion(resultSet.getInt("duracion")); 
				pelicula.setIdioma(resultSet.getString("idioma"));
				pelicula.setDescripcion(resultSet.getString("descripcion"));
				
				peliculas.add(pelicula);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al listar Peliculas : " + e.getMessage());
		} finally {
			DBUtils.cerrarConexion(connection);
		}
		
		return peliculas;
	
	}
	
}