package VentaEntradasCenima.Modelo.BBDD.Utils;

/**
 * Esta clase contiene las sentencias SQL del proyecto
 */
public class SQLQuerys {

	public static final String SELECT_PELICULAS_ORDENADAS_POR_SESION_MAS_CERCANA =
	        "SELECT p.id_pelicula, p.titulo, p.duracion, p.idioma, p.descripcion, p.nombre_genero " +
	        "FROM pelicula p " +
	        "JOIN sesion s ON p.id_pelicula = s.id_pelicula " +
	        "GROUP BY p.id_pelicula, p.titulo, p.duracion, p.idioma, p.descripcion, p.nombre_genero " +
	        "ORDER BY MIN(CONCAT(s.fecha, ' ', s.hora_inicio)) ASC";

	    // Fechas disponibles para una película (solo las que existan en BD)
	    public static final String SELECT_FECHAS_POR_PELICULA =
	        "SELECT DISTINCT fecha " +
	        "FROM sesion " +
	        "WHERE id_pelicula = ? " +
	        "ORDER BY fecha ASC";

	    // Sesiones de una película en una fecha (incluye id_sala para luego sacar el nombre)
	    public static final String SELECT_SESIONES_POR_PELICULA_Y_FECHA =
	        "SELECT id_sesion, id_pelicula, id_sala, fecha, hora_inicio, hora_fin, precio " +
	        "FROM sesion " +
	        "WHERE id_pelicula = ? AND fecha = ? " +
	        "ORDER BY hora_inicio ASC";

	    // Nombre de sala (para poder mostrar “Sala principal” en pantalla)
	    public static final String SELECT_NOMBRE_SALA_POR_ID =
	    		 "SELECT id_sala, nombre, capacidad FROM sala WHERE id_sala = ?";

		public static final String SELECT_FECHAS_POR_ID_PELICULA = null;

		public static final String SELECT_SESIONES_POR_ID_PELICULA_Y_FECHA = null;
	
	
	
}