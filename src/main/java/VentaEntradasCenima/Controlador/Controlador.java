package VentaEntradasCenima.Controlador;


import java.time.LocalDate;
import java.util.ArrayList;

import VentaEntradasCinema.Controlador.Ficheros.GestorFactura;
import VentaEntradasCinema.Modelo.BBDD.GestorDAO.*;
import VentaEntradasCinema.Modelo.ClasesAxiliares.Carrito;
import VentaEntradasCinema.Modelo.ClasesAxiliares.Seleccion;
import VentaEntradasCinema.Modelo.Pojos.*;
import VentaEntradasCinema.Vista.Menu;

public class Controlador {
	
	private final Menu  menu ;
    private final PeliculaDAO peliculaDAO ;
    private final SesionDAO sesionDAO ;
    private final SalaDAO  salaDAO ;
    private final ClienteDAO clienteDAO ;
    private final CompraDAO  compraDAO ;
    private final EntradaDAO entradaDAO ;


 public Controlador(Menu menu, PeliculaDAO peliculaDAO, SesionDAO sesionDAO, SalaDAO salaDAO, ClienteDAO clienteDAO,
			        CompraDAO compraDAO, EntradaDAO entradaDAO) {

		this.menu = menu;
		this.peliculaDAO = peliculaDAO;
		this.sesionDAO = sesionDAO;
		this.salaDAO = salaDAO;
		this.clienteDAO = clienteDAO;
		this.compraDAO = compraDAO;
		this.entradaDAO = entradaDAO;		
	}

/**
 * 
 */ 
    public void  iniciar() {
    	
    	Carrito  carrito  = new  Carrito();
    	GestorFactura gestorFactura= new GestorFactura() ;
    	Cliente  clienteActual = null ;
    	
         menu.pantallaBienvenida()  ;
         boolean salir= false ;

        while ( salir == false ){
        	
            ArrayList < Pelicula> peliculas = peliculaDAO.listaPeliculasPorSecionMasCerca() ;
            boolean  hayCarrito = carrito.haySeleccion() ;
            
            int opcion = menu.mostrarMenuPeliculas( peliculas , hayCarrito ) ;

            switch ( opcion) {
                 case 99 :
                    System.out.println( " Saliendo del programa... Hasta pronto! ") ;
                    salir = true ;
                    break ;

                case 0 :
                	clienteActual = opcionFinalizarCompra( carrito, gestorFactura, clienteActual)  ;
                    break;

                default:
                     opcionSeleccionarPelicula (opcion, peliculas, carrito );
                    break ;
            }
        }
    }
    
/**
 * 
 * @param carrito
 * @param gestorFactura
 * @param clienteActual
 * @return
 */
    private  Cliente opcionFinalizarCompra( Carrito carrito, GestorFactura  gestorFactura, Cliente clienteActual ) {

    	Cliente cliente = clienteActual ;
    	
        if ( carrito.haySeleccion() == false) {
            System.out.println( "No hay selecciones !!! ") ;
        }else {
        	cliente = pantallaResumen( carrito, gestorFactura, clienteActual) ;
        }
        
        return cliente ;
    }
    
/**
 * 
 * @param opcion
 * @param peliculas
 * @param carrito
 */
    private  void opcionSeleccionarPelicula (int opcion , ArrayList <Pelicula> peliculas , Carrito carrito ){
    	
        if (opcion >= 1 && opcion<= peliculas.size() ) {
        	
            Pelicula pelicula = peliculas.get( opcion-1); 
            pantallaFechas( pelicula, carrito) ;
            
        }else {
            System.out.println(" Opcion invalida ") ;
        }
    }

  /**
   * 
   * @param pelicula
   * @param carrito
   */
    private void  pantallaFechas( Pelicula pelicula , Carrito  carrito) {
    	
        ArrayList <LocalDate> fechas = sesionDAO.listarFechas( pelicula.getIdPelicula() );

        if ( fechas.size()== 0 ) {
            System.out.println("No hay fechas para esta pelicula   ") ;    
        }else  {
        	boolean volver = false ;
        	boolean seleccionHecha =  false ;

        	while (volver == false && seleccionHecha == false) {
	            int opcionFecha = menu.mostrarMenuFechas( pelicula, fechas );
	
	            switch ( opcionFecha) {
	                case 0 : 
	                		volver = true ; // salir de Pantalla Fechas Y volver a Peliculas
	                		break;
	                     
	                default:
		                    if ( opcionFecha >= 1 && opcionFecha<= fechas.size() ) {
		                        LocalDate fechaElegida = fechas.get( opcionFecha-1);
		                        seleccionHecha = pantallaSesiones( pelicula , fechaElegida , carrito ) ;
		                        
		                    } else{
		                        System.out.println(" Opcion invalida      ");
		                    }
		                    break;
		        }
	        }
        }
        
   }
    
 /**
  *   
  * @param pelicula
  * @param fechaElegida
  * @param carrito
  * @return
  */
    private boolean pantallaSesiones( Pelicula pelicula, LocalDate  fechaElegida, Carrito carrito) {
    	
    	boolean seleccionHecha = false;
        ArrayList <Sesion > sesiones = sesionDAO.listarSesiones( pelicula.getIdPelicula(), fechaElegida ) ;

        if (sesiones.size()== 0 ){
            System.out.println(" No hay sesiones para esta fecha ") ;
            
        }else {

	        ArrayList < Sala> salas =cargarSalasDeSesiones( sesiones) ;
	
	        boolean volver = false ;
	
	        while (volver == false ) {
	            int opcionSesion = menu.mostrarMenuSesiones(pelicula , fechaElegida , sesiones, salas );
	
	            switch(opcionSesion ) {
	                case 0 :
	                    volver = true ; // salir de Sesiones y volver a Fechas
	                    break ;
	
	                default:
	                    if (opcionSesion >= 1 && opcionSesion<= sesiones.size() ) {
	
	                        Sesion sesionElegida = sesiones.get( opcionSesion- 1);
	                        Sala salaElegida = salas.get(opcionSesion -1 );
	
	                        int espectadores = menu.pedirNumEspectadores() ;
	                        
	                        if (espectadores<= 0) {
	                            System.out.println("Debe ser mayor  que 0        ");
	                           
	                        }else {
		                        carrito.addSeleccion(pelicula , sesionElegida, salaElegida, espectadores );
		                        menu.mostrarSeleccion( pelicula, sesionElegida , salaElegida , espectadores) ;
		                        
		                        seleccionHecha = true ;	// avisar a pantallaFechas() para salir tambien y volver a peliculas
		                        volver = true ; // salir de Pantalla sesiones   
	                        }
	                    } else {
	                        System.out.println(" Opcion  invalida    ") ;
	                    }
	                    break ;
	            }
	        }
        }
        
       return seleccionHecha ;
    }

/**
 * 
 * @param sesiones
 * @return
 */
    private ArrayList < Sala> cargarSalasDeSesiones(ArrayList <Sesion > sesiones) {
    	
        ArrayList <Sala > salas = new ArrayList<>() ;

        for ( Sesion sesion : sesiones ){
            int idSala = sesion.getSala().getIdSala() ;
            
            salas.add(salaDAO.obtenerSala( idSala)) ;
        }

        return salas ;
    }
 
/**
 * 
 * @param carrito
 * @param gestorFactura
 * @param clienteActual
 * @return
 */
    private Cliente pantallaResumen(Carrito carrito, GestorFactura gestorFactura, Cliente clienteActual) {

        double subtotal = carrito.calcularSubtotal() ;
        double descuento = carrito.calcularDescuento(); 
        double total = subtotal - descuento ;

        ArrayList <String> lineas  = carrito.generarLineasResumen() ;
        menu.mostrarResumenCompra( lineas, subtotal , descuento, total ) ;

        int opcion = menu.opcionResumenCompra();

        Cliente cliente = clienteActual ;
        
        switch (opcion ) {
            case 0 :
                 // volver a peliculas
                break ;

            case 1:
                cliente = pantallaLoginYCompra(total , descuento , carrito , gestorFactura ) ;
                break;

             default :
                 System.out.println("  Opcion invalida");
                 break ;
        }
        
      return cliente ;  
   }
   
/**
 *   
 * @param total
 * @param descuento
 * @param carrito
 * @param gestorFactura
 * @return
 */
     private Cliente   pantallaLoginYCompra(double total, double descuento, Carrito carrito , GestorFactura gestorFactura ) {

        Cliente cliente = identificarCliente( carrito);

        if (cliente !=null ){
        	String dni = cliente.getDni() ;

            int idCompra = guardarCompraBD ( dni, total  , descuento , carrito) ;

            if(idCompra != 0) {

                guardarEntradasBD(idCompra, carrito) ;

                System.out.println("\nCOMPRA FINALIZADA Con exito   ");
                System.out.println("ID COMPRA : "+idCompra) ;

                 pantallaFactura (cliente, idCompra  , descuento, carrito, gestorFactura );

            }else {
                System.out.println("Error, al guardar la compra. Operacion cancelada ") ;
            }

        }else {
            System.out.println(  "Compra cancelada por el usuario ");
        }
        
       return cliente ;
    }

/**
 * 
 * @param carrito
 * @return
 */
    private  Cliente  identificarCliente(Carrito  carrito) {

        boolean hecho = false ;
        Cliente clienteFinal= null ;

        while(hecho== false) {
            int opcion= menu.menuLogin() ;

            switch(opcion ) {
                case 0:
                    System.out.println(" Compra cancelada. Volviendo al inicio...");
                    finalizarCompra(carrito ) ;
                    hecho =true;
                    clienteFinal = null ;
                    break ;

                case 1 :
                	clienteFinal = iniciarSesionCliente();
	                    if (clienteFinal != null ) {
	                       hecho = true ;
	                    }
                    break ;

                case 2:
                	clienteFinal = registrarCliente();
	                    if (clienteFinal != null ) {
	                    	hecho = true ;
	                    }
                    break ;

                default :
                    System.out.println(" Opcion invalida ")  ;
                    break ;
            }
        }

        return clienteFinal;
     }

/**
 * 
 * @return
 */
     private Cliente iniciarSesionCliente(){

        String dni = menu.pedirDni() ;
        String password = menu.pedirPasswordOculta();

        boolean accesoCorrecto = clienteDAO.login( dni, password);

        Cliente cliente = null;   

        if (accesoCorrecto  == true ) {
            menu.mensajeLoginCorrecto();
            cliente = clienteDAO.obtenerClientePorDni( dni);
            
        } else {
            menu.mensajeLoginIncorrecto();
            cliente = null;
        }

       return cliente ;
    }

     
/**
 * 
 * @return
 */
    private  Cliente registrarCliente() {

        Cliente nuevoCliente = menu.nuevoCliente();  

        boolean dniExiste = clienteDAO.existeDni( nuevoCliente.getDni());
        
        Cliente cliente = null ; 

        if ( dniExiste == true){
            System.out.println("El DNI ya existe ! ");
            cliente = null;
            
        } else{

            boolean registrado = clienteDAO.insertarCliente ( nuevoCliente) ;

            if(registrado ==true ) {
                System.out.println("Registro correcto. Ya puedes continuar con la compra");  
                cliente = clienteDAO.obtenerClientePorDni( nuevoCliente.getDni() );
                
            } else {
                System.out.println("No se pudo dar de alta " );
                cliente = null ;
            }
        }

       return cliente ;
    }

  
/**
 *  
 * @param dni
 * @param total
 * @param descuento
 * @param carrito
 * @return
 */
    private int guardarCompraBD( String dni, double total  , double descuento , Carrito  carrito) {

        int numEntradas = carrito.contarEntradas();
        compraDAO.insertarCompra( dni, total , descuento, numEntradas  );

        int idCompra = compraDAO.obtenerUltimoIdCompra();

        if(idCompra == 0 ) {
            System.out.println("Error: NO se puede obtener el ID de Compra");
        }

       return idCompra ;
    }

/**
 * 
 * @param idCompra
 * @param carrito
 */
    private void guardarEntradasBD (int idCompra ,Carrito carrito ) {

        for (Seleccion selecion : carrito.getSelecciones() ) {

            int idSesion = selecion.getSesion().getIdSesion() ;
            double precio = selecion.getSesion().getPrecio();
             int numEntradas = selecion.getEspectadores() ;

            for ( int i = 0; i <numEntradas; i++) {
            	
                entradaDAO.insertarEntrada( idSesion, idCompra , precio, 0)  ;
            }
        }
    }

    
/**
 *  
 * @param cliente
 * @param idCompra
 * @param descuento
 * @param carrito
 * @param gestorFactura
 */
    private  void  pantallaFactura(Cliente cliente, int idCompra, double descuento , Carrito  carrito , GestorFactura gestorFactura) {

        int opcion= menu.opcionFactura();

        switch( opcion) {
            case 0:
            	finalizarCompra(carrito );
                break;

            case 1 :
                crearFacturaTxt (cliente , idCompra, descuento, carrito, gestorFactura)  ;
                 menu.mensajeFacturaGuardada() ;
                finalizarCompra( carrito)   ;
                break ;

            default :
                System.out.println(" Opcion invalida ")   ;
                finalizarCompra(carrito) ;
                break ;
        }
    }
    
/**
 * 
 * @param cliente
 * @param idCompra
 * @param descuento
 * @param carrito
 * @param gestorFactura
 */
     private void  crearFacturaTxt ( Cliente cliente, int idCompra , double descuento, Carrito  carrito , GestorFactura gestorFactura ) {
	 
        if( cliente != null ){
        	
        	String dni = cliente.getDni() ;
        	
	        String nombre = "Cliente" ;
	        if(cliente.getNombre() != null  ) {
	            nombre = cliente.getNombre()  ;       
	        }
	
	        String email= "No disponible";
	        if(cliente.getEmail() != null  ) {
	        	email = cliente.getEmail()  ;       
	        }
	        
	        ArrayList < String > lineas = carrito.generarLineasResumen()  ;
	        double subtotal = carrito.calcularSubtotal();
	        double totalFinal = subtotal-descuento    ;
	
	        gestorFactura.guardarFactura( idCompra, dni , nombre, email  , lineas, subtotal , descuento , totalFinal)  ;
	        
        }else {
        	System.out.println(" No se puedo generar la factura, Cliente no disponible !!!");
        }
    }
    
    private  void finalizarCompra( Carrito carrito){
        carrito.reiniciarCarrito() ;
        
    }
    
}
