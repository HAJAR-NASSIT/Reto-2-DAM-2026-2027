package VentaEntradasCinema.Modelo.BBDD.Utils;

import java.sql.*;
import java.io.*;

/**
 * Clase DBUtils. Gestiona la conexión a la base de datos. Lee la configuración
 * desde un archivo externo y proporciona métodos para obtener y cerrar
 * conexiones.
 */

public class DBUtils {

	private static String url;
	private static String user;
	private static String password;
	private static String rutaRelativa = "src\\main\\resources\\ConexionDB.txt";
	private static boolean config = false;

	/**
	 * Obtiene una conexión a la base de datos. Si no se ha cargado la
	 * configuración, lee primero el archivo de configuración.
	 *
	 * @return un objeto {@link Connection} si la conexión es exitosa, null en caso
	 *         contrario.
	 */

	public static Connection getConnection() {
		Connection connection = null;
		if (!config) {
			leerFichero();
			config = true;
		}
		try {
			Connection conexion = DriverManager.getConnection(url, user, password);
			connection = conexion;

		} catch (SQLException e) {
			System.out.println("Fallo en la conecion:" + e.getMessage());
		}
		return connection;
	}

	/**
	 * Lee la configuración de la base de datos desde el archivo definido en
	 * {@link #rutaRelativa}. El archivo debe contener las líneas: url=...,
	 * user=..., password=...
	 */

	private static void leerFichero() {

		BufferedReader br = null;
		FileReader fr = null;
		//String linea=;

		File ficheroDB = new File(rutaRelativa);

		if (!ficheroDB.exists()) {
			System.out.println("No se encontre el archivo de configuracion : " + rutaRelativa);

		}

		try  {

			fr = new FileReader(ficheroDB);
			br = new BufferedReader(fr);
			String linea ;

			while ((linea = br.readLine())!= null) {
				linea = linea.trim();

				if (linea.startsWith("url=")) {
					url = linea.substring(4);
				}
				if (linea.startsWith("user=")) {
					user = linea.substring(5);
				}
				if (linea.startsWith("password=")) {
					password = linea.substring(9);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error al leer el fichero de base de datos ." + e.getMessage());
		}
	}

	/**
	 * Cierra una conexión a la base de datos si está abierta.
	 *
	 * @param con la conexión {@link Connection} a cerrar
	 */

	public static void cerrarConexion(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Erreur al cerrar la conexion : " + e.getMessage());
		}
	}

	/**
	 * Método de prueba para verificar la conexión a la base de datos.
	 *
	 * @param args argumentos de línea de comandos
	 */

	public static void main(String[] args) {

		try (Connection con = getConnection()) {
			if (con != null) {
				System.out.println("Conexión OK!");
			} else {
				System.out.println("Error de conexión!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}