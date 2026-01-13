package VentaEntradasCenima.Modelo.BBDD.Utils;

import java.sql.*;
import java.io.*;

public class DBUtils {

	private static String url;
	private static String user;
	private static String password;
	private static String rutaRelativa = "src\\main\\resources\\ConexionDB.txt";
	private static boolean config = false;

	public static Connection getConnection() {
		if (!config) {
			leerFichero();
			config = true;
		}
		try {
			Connection conexion = DriverManager.getConnection(url, user, password);
			return conexion;

		} catch (SQLException e) {
			System.out.println("Fallo en la conecion:" + e.getMessage());
			return null;
		}
	}

	private static void leerFichero() {
		File ficheroDB = new File(rutaRelativa);
		if (!ficheroDB.exists()) {
			System.out.println("No se encontre el archivo de configuracion : " +rutaRelativa);

		}
		try (BufferedReader br = new BufferedReader(new FileReader(ficheroDB))) {
			String linea;

			while ((linea = br.readLine()) != null) {
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

	public static void cerrarConexion(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Erreur al cerrar la conexion : " + e.getMessage());
		}
	}
	 // Méthode de test
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