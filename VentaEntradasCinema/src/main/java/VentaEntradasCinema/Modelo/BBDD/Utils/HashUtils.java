package VentaEntradasCinema.Modelo.BBDD.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtils {
	
	public static String generarHash(String texto) {

         String hashFinal = null ;

         try{        
             MessageDigest generador= MessageDigest.getInstance("SHA-256") ;      //Crear el generador SHA-256
            
            byte [] bytesTexto=texto.getBytes( StandardCharsets.UTF_8) ;          //Convertir el texto a bytes
       
            byte [] hashBytes= generador.digest(bytesTexto ) ;                  //Generar el hash
       
             StringBuilder resultado = new StringBuilder() ;                 //Pasar los bytes a texto hexadecimal

            for( int i = 0 ; i< hashBytes.length ; i++ ){
                  String hex  = String.format("%02x", hashBytes[i]) ;
                resultado.append(hex) ;
            }
       
             hashFinal = resultado.toString();                 // Guardar resultado hash final

        }catch (Exception e) {
            System.out.println("Error  generando hash SHA-256") ;
        }

      return hashFinal;
    }
	
	
}
