package VentaEntradasCenima.Modelo.BBDD.Utils;


public class SQLQuerys {

// PELICULAS -------------------------------------------------------------------------------------------------------------------	
	public static  final String SELECT_PELICULAS_ORDENADAS_POR_SESION_MAS_CERCANA =
            "SELECT p.id_pelicula, p.titulo, p.duracion, p.idioma, p.descripcion, g.nombre_genero, p.imagen " +
	        "FROM pelicula p JOIN genero g on p.id_genero =g.id_genero " +
            "JOIN sesion s ON p.id_pelicula = s.id_pelicula " +
	        "WHERE TIMESTAMP(s.fecha, s.hora_inicio) > NOW() "+
            "GROUP BY p.id_pelicula, p.titulo, p.duracion, p.idioma, p.descripcion, g.nombre_genero, p.imagen " +
            "ORDER BY MIN(TIMESTAMP(s.fecha, s.hora_inicio)) ASC";
	
// FECHAS -----------------------------------------------------------------------------------------------------------------------
    public  static final String SELECT_FECHAS_POR_ID_PELICULA  =
            "SELECT DISTINCT fecha FROM sesion WHERE id_pelicula = ? ORDER BY fecha ASC";

// SESIONES --------------------------------------------------------------------------------------------------------------------
     public static final String SELECT_SESIONES_POR_ID_PELICULA_Y_FECHA =
            "SELECT id_sesion, id_pelicula, id_sala, fecha, hora_inicio, hora_fin, precio " +
            "FROM sesion WHERE id_pelicula = ? AND fecha = ? ORDER BY hora_inicio ASC";

// SALA ------------------------------------------------------------------------------------------------------------------------
    public static final  String SELECT_SALA_POR_ID  =  "SELECT * FROM sala WHERE id_sala = ?";

// CLIENTE ---------------------------------------------------------------------------------------------------------------------
    public  static final String SELECT_LOGIN_CLIENTE =   "SELECT DNI FROM cliente WHERE DNI = ? AND password = SHA2(?, 256)";

    public static  final String SELECT_CLIENTE_POR_DNI =  "SELECT DNI, nombre, email FROM cliente WHERE DNI = ?";
    
    public  static final  String SELECT_EXISTE_DNI  = "SELECT DNI FROM cliente WHERE DNI = ?";

     public static final String  INSERT_CLIENTE  = "INSERT INTO cliente (DNI, nombre, email, password) VALUES (?, ?, ?, SHA2(?, 256))";

// COMPRA ------------------------------------------------------------------------------------------------------------------------
    public  static final String  INSERT_NUEVA_COMPRA =
            "INSERT INTO compra (DNI, fecha_compra, hora_compra, precio_total, descuento_total, tipo_compra, numero_entradas) " +
            "VALUES (?, ?, ?, ?, ?, 'directo', ?)";
    
    public   static final String SELECT_ULTIMO_ID_COMPRA = "SELECT MAX(id_compra) AS id FROM compra" ;
    
    public   static final String DELETE_COMPRA="DELETE FROM compra WHERE id_compra= ? ";

// ENTRADA -----------------------------------------------------------------------------------------------------------------------
    public  static final  String INSERT_NUEVA_ENTRADA  =  "INSERT INTO entrada (id_sesion, id_compra, precio, descuento) VALUES (?, ?, ?, ?)";    
    
}