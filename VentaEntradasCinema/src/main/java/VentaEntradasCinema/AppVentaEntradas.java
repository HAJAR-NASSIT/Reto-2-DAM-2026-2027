package VentaEntradasCinema;


import VentaEntradasCinema.Controlador.Controlador;
import VentaEntradasCinema.Modelo.BBDD.GestorDAO.*;
import VentaEntradasCinema.Vista.Menu;

public class AppVentaEntradas {

	public static void main(String[] args) {

		
		
		Menu  menu = new Menu();
        PeliculaDAO peliculaDAO =  new PeliculaDAO();
        SesionDAO sesionDAO = new SesionDAO();
        SalaDAO salaDAO = new  SalaDAO();
        ClienteDAO clienteDAO =  new ClienteDAO();
        CompraDAO compraDAO = new  CompraDAO();
        EntradaDAO  entradaDAO =  new EntradaDAO();

        Controlador controlador =  new Controlador( menu, peliculaDAO, sesionDAO , salaDAO, clienteDAO, compraDAO , entradaDAO ) ;

        
        controlador.iniciar() ;

	}
	

}
