package VentaEntradasCinema.Modelo.BBDD.Utils;

public class SQLQuerys {

// PELICULAS -------------------------------------------------------------------------------------------------------------------	
	public static final String SELECT_PELICULAS_ORDENADAS_POR_SESION_MAS_CERCANA = "SELECT p.id_pelicula, p.titulo, p.duracion, p.idioma, p.descripcion, g.nombre_genero, p.imagen "
			+ "FROM pelicula p JOIN genero g on p.id_genero =g.id_genero "
			+ "JOIN sesion s ON p.id_pelicula = s.id_pelicula " + "WHERE TIMESTAMP(s.fecha, s.hora_inicio) > NOW() "
			+ "GROUP BY p.id_pelicula, p.titulo, p.duracion, p.idioma, p.descripcion, g.nombre_genero, p.imagen "
			+ "ORDER BY MIN(TIMESTAMP(s.fecha, s.hora_inicio)) ASC";

// FECHAS -----------------------------------------------------------------------------------------------------------------------
	public static final String SELECT_FECHAS_POR_ID_PELICULA = "SELECT DISTINCT s.fecha " + "FROM sesion s "
			+ "JOIN sala sa ON s.id_sala = sa.id_sala " + "WHERE s.id_pelicula = ? "
			+ "AND (SELECT COUNT(*) FROM entrada e WHERE e.id_sesion = s.id_sesion) < sa.capacidad "
			+ "ORDER BY s.fecha ASC";

// SESIONES --------------------------------------------------------------------------------------------------------------------
	public static final String SELECT_SESIONES_POR_ID_PELICULA_Y_FECHA = "SELECT s.id_sesion, s.id_pelicula, s.id_sala, s.fecha, s.hora_inicio, s.hora_fin, s.precio "
			+ "FROM sesion s " + "JOIN sala sa ON s.id_sala = sa.id_sala " + "WHERE s.id_pelicula = ? "
			+ "AND s.fecha = ? "
			+ "AND (SELECT COUNT(*) FROM entrada e WHERE e.id_sesion = s.id_sesion) < sa.capacidad "
			+ "ORDER BY s.hora_inicio ASC";
	
	// entradas_vendidas-------------------------------------------------------------------------------------------------------

	public static final String NUM_ENTRADAS_VENDIDAS = "SELECT COUNT(*) AS entradas_vendidas " + "FROM entrada " 
			+ "WHERE id_sesion = ? ";
// SALA ------------------------------------------------------------------------------------------------------------------------
	public static final String SELECT_SALA_POR_ID = "SELECT * FROM sala WHERE id_sala = ?";

// CLIENTE ---------------------------------------------------------------------------------------------------------------------
	public static final String SELECT_LOGIN_CLIENTE = "SELECT DNI FROM cliente WHERE DNI = ? AND password = SHA2(?, 256)";

	public static final String SELECT_CLIENTE_POR_DNI = "SELECT DNI, nombre,apellido, email FROM cliente WHERE DNI = ?";

	public static final String SELECT_EXISTE_DNI = "SELECT DNI FROM cliente WHERE DNI = ?";

	public static final String INSERT_CLIENTE = "INSERT INTO cliente (DNI, nombre, apellido, email, password) VALUES (?,?,?, ?, SHA2(?, 256))";

// COMPRA ------------------------------------------------------------------------------------------------------------------------
	public static final String INSERT_NUEVA_COMPRA = "INSERT INTO compra (DNI, fecha_compra, hora_compra, precio_total, descuento_total, tipo_compra) "
			+ "VALUES (?, ?, ?, ?, ?, 'directo')";

	public static final String SELECT_ULTIMO_ID_COMPRA = "SELECT MAX(id_compra) AS id FROM compra";

	public static final String DELETE_COMPRA = "DELETE FROM compra WHERE id_compra= ? ";

// ENTRADA -----------------------------------------------------------------------------------------------------------------------
	public static final String INSERT_NUEVA_ENTRADA = "INSERT INTO entrada (id_sesion, id_compra, importe) VALUES (?, ?, ?)";

}
