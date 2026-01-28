package VentaEntradasCenima.Modelo.ClasesAxiliares;

import java.util.ArrayList;

import VentaEntradasCenima.Modelo.Pojos.Pelicula;
import VentaEntradasCenima.Modelo.Pojos.Sala;
import VentaEntradasCenima.Modelo.Pojos.Sesion;

public class Carrito {

	 private ArrayList < Seleccion > selecciones =  new ArrayList<>();


    public  void addSeleccion( Pelicula pelicula, Sesion sesion, Sala sala, int espectadores ){
    	
        Seleccion nueva =  new Seleccion (pelicula , sesion, sala , espectadores );
        selecciones.add( nueva) ;
    }

    public ArrayList < Seleccion > getSelecciones() {
    	
     return  selecciones   ;
    }
 
    public  boolean haySeleccion() {
    	
    	boolean haySeleccion = false ;
      
        if ( selecciones.size() > 0) {
        	haySeleccion= true ; 
        	
        } else {
        	haySeleccion = false ;  
        }
        
     return haySeleccion ;
    }

    public void  reiniciarCarrito() {
    	
        selecciones.clear() ;
    }

    public double  calcularSubtotal() {
    	
        double subtotal = 0;

        for( Seleccion  seleccion : selecciones ) {
            double precio = seleccion.getSesion().getPrecio();
            int numEntradas = seleccion.getEspectadores() ;
            subtotal= subtotal + (precio * numEntradas) ;
        }

      return subtotal ;
    }

    public  int contarEntradas() {
    	
        int totalEntradas = 0;

        for ( Seleccion seleccion :  selecciones ) {
        	totalEntradas = totalEntradas + seleccion.getEspectadores() ;
        }

      return totalEntradas ;
    }

    public  int contarPeliculasDistintas() {
    	
        ArrayList < Integer > ids = new ArrayList<>() ;
         int numPeliculas = 0 ;
        
        for (Seleccion seleccion  : selecciones ){
            int id = seleccion.getPelicula().getIdPelicula();

            boolean existe =false ;

            for( int i = 0; i<ids.size() ; i++ ) {
            	
                if(ids.get(i) == id ) {
                    existe=  true ;
                }
            }

            if (existe == false ) {
                ids.add(id) ;
            }
        }
        numPeliculas = ids.size() ;

      return numPeliculas ;
    }

    public  double calcularDescuento()  {
    	
        double subtotal = calcularSubtotal() ;
        int  distintas = contarPeliculasDistintas() ;
        double descuento= 0 ;

        if( distintas == 2) {
        	descuento = (subtotal * 0.20) ;
        }
        if (distintas >= 3) {
        	descuento=  (subtotal * 0.30 ) ;
        }
        
      return descuento;
    }


// TEXTO  RESUMEN / FACTURA-------------------------------------------------------------

     public  ArrayList < String> generarLineasResumen(){
    	 
        ArrayList < String > lineas = new ArrayList<>() ;
        int numero = 1 ;

        for( Seleccion seleccion  : selecciones ) {
            double precioLinea = seleccion.getSesion().getPrecio()  * seleccion.getEspectadores() ;

            String linea =" "+numero + ". "
                    +seleccion.getPelicula().getTitulo()
                    + " - " +seleccion.getSesion().getFecha()
                    +" "+ seleccion.getSesion().getHoraInicio()
                    + " (" + seleccion.getSala().getNombre()+ ")"
                    +" x " +seleccion.getEspectadores()
                    + " --> "+ precioLinea +" EUR" ;

	            lineas.add(linea) ;
	            numero++ ;
        }

      return lineas ;
    }
    
}