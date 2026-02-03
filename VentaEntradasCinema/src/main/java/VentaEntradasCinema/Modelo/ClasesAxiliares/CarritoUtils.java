package VentaEntradasCinema.Modelo.ClasesAxiliares;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import VentaEntradasCinema.Modelo.BBDD.Utils.DBUtils;
import VentaEntradasCinema.Modelo.BBDD.Utils.SQLQuerys;
import VentaEntradasCinema.Modelo.Pojos.Sesion;
import VentaEntradasCinema.Modelo.Pojos.Sala;

public class CarritoUtils {

    /**
     * Verifica si se pueden añadir la cantidad de entradas deseadas
     * para una sesión concreta.
     *
     * @param sesion Sesión seleccionada
     * @param sala Sala de la sesión
     * @param cantidadDeseada Número de entradas que quiere comprar el usuario
     * @return true si se pueden añadir, false si excede la capacidad
     */
    public static boolean hayEntradasDisponibles(Sesion sesion, Sala sala, int cantidadDeseada) {
    	 boolean disponible = false; 
         int entradasVendidas = 0;   
         int capacidadSala = sala.getCapacidad(); 
         int entradasRestantes = 0; 

         try (Connection conexion = DBUtils.getConnection();
                 PreparedStatement ps = conexion.prepareStatement(SQLQuerys.NUM_ENTRADAS_VENDIDAS)) {

                ps.setInt(1, sesion.getIdSesion());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    entradasVendidas = rs.getInt("entradas_vendidas");
                }

                rs.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
         entradasRestantes = capacidadSala - entradasVendidas;

         if (cantidadDeseada <= entradasRestantes) {
             disponible = true;
         } else {
             disponible = false;
         }

         return disponible;
    }
}

