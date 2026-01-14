package VentaEntradasCenima.Modelo.BBDD.Utils;

import java.sql.*;
import java.io.*;

public class DBUtils {

	private static String url;
	private static String user;
	private static String password;
	private static String rutaRelativa = "src\\main\\resources\\ConexionDB.txt";
	private static boolean config = false;

	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		// Variables
		Connection conexion;
		
		// Leemos las variables de conexión desde un fichero de configuración
		if (!config) {
			try {
				leerFichero();
			} catch (IOException error_de_IO) {
				// TODO Auto-generated catch block
				System.out.println("Error al leer el fichero inicial.");
				error_de_IO.printStackTrace();
			}
			config = true;
		}
		
		try {
			conexion = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.out.println("Fallo en la conecion:" + e.getMessage());
			conexion = null;
		}
		
		return conexion;
	}

	private static void leerFichero() throws IOException {
		File ficheroDB = new File(rutaRelativa);
		BufferedReader br = null;
		
		if (!ficheroDB.exists()) {
			System.out.println("No se encontre el archivo de configuracion : " +rutaRelativa);

		}
		
		try{
			br = new BufferedReader(new FileReader(ficheroDB));
			
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
			
		} catch (IOException e) {
			System.out.println("Error al leer el fichero de base de datos ." + e.getMessage());
		}
		finally {
			br.close();
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
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		
		try{
			con = getConnection();
			 
            if (con != null) {
                System.out.println("Conexión OK!");
            } else {
                System.out.println("Error de conexión!");
            }
        } catch (Exception e) {
        	System.out.println("Fallo en la conexión");
            e.printStackTrace();
        }
		
		String sql = "Select * from cliente";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		

while (rs.next()) {
    // Procesar cada fila
    String DNI = rs.getString("DNI");
    String nombre = rs.getString("nombre");
  
    System.out.println("ID: " + DNI + ", Nombre: " + nombre);
}
	}

}