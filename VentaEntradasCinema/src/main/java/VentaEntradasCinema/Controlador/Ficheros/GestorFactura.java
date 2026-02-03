package VentaEntradasCinema.Controlador.Ficheros;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * Clase GestorFactura.
 * Se encarga de generar y guardar la factura de una compra en un fichero TXT.
 * La factura incluye los datos del cliente, el detalle de la compra y el resumen final.
 */


public class GestorFactura  {
	
	/**
	 * Guarda la factura de una compra en un archivo de texto.
	 * El fichero se crea con el nombre "factura_idCompra.txt" dentro de la carpeta "TicketFacturas".
	 *
	 * @param idCompra identificador de la compra
	 * @param dni DNI del cliente
	 * @param nombreCliente nombre del cliente
	 * @param emailCliente email del cliente
	 * @param lineas líneas con el detalle de la compra
	 * @param subtotal importe subtotal de la compra
	 * @param descuento descuento aplicado
	 * @param total importe total final
	 */
	   public void  guardarFactura( int idCompra  , String dni, String nombreCliente , String apelllidoCliente,String emailCliente,
                         ArrayList <String > lineas, double subtotal, double descuento, double total ){

		File carpeta = new File("TicketFacturas") ;
        File fichero = null ;
        FileWriter writer = null ;
        PrintWriter pw =null ;
		
		try{
			fichero = new File( carpeta , "factura_" +idCompra +".txt" ) ; 
			writer =  new FileWriter(fichero )  ;
			pw =  new PrintWriter( writer) ;
			
			pw.println("=================================================================") ;
			pw.println( "                   ELORRIETA CINEMA - FACTURA                    ");
			pw.println ("=================================================================");
			
			pw.println("ID Compra : "+idCompra ) ;
			
			String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") ) ;
			
			pw.println("Fecha/Hora Compra : " + fechaHora);
			
			pw.println("-----------------------------------------------------------------" ) ;

			pw.println( "DATOS DEL CLIENTE :  " );
			pw.println("DNI : " +dni) ;
			pw.println("Nombre : " +nombreCliente )  ;
			pw.println("Nombre : " +apelllidoCliente )  ;
			pw.println("Email : " +emailCliente) ;
			
			pw.println("------------------------------------------------------------------"  );

			pw.println("------------------------------------------------------------------") ;
			pw.println("                  DETALLE DE LA COMPRA            ");
			pw.println("------------------------------------------------------------------");

			for( String linea  : lineas ){
			    pw.println( linea) ;
			}

			pw.println("------------------------------------------------------------------");

			pw.println("RESUMEN  :  ")  ;
			pw.printf("Subtotal  : %.2f EUR%n", subtotal );
			pw.printf("Descuento : %.2f EUR%n", descuento );
			pw.printf("TOTAL     : %.2f EUR%n", total )  ;	
			
			pw.println( "==================================================================" )   ;
			pw.println("       Gracias por su compra. Disfrute de la pelicula   ") ;
			pw.println( "==================================================================") ;
		
		} catch(Exception e  ) {
			
			System.out.println( " Error creando factura TXT") ;
			e.printStackTrace() ;
		
		}finally {
			
			try{
				if (pw != null ){
					pw.close() ; 
				}
			} catch (Exception e2  ) {
				e2.printStackTrace() ;
			}
		}
	}
		
	
}
