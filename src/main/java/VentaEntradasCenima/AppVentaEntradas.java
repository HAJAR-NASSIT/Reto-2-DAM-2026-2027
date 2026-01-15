package VentaEntradasCenima;

import java.util.Scanner;

import VentaEntradasCenima.Controlador.Controlador;
import VentaEntradasCenima.Modelo.BBDD.GestorDAO.PeliculaDAO;
import VentaEntradasCenima.Modelo.BBDD.GestorDAO.SalaDAO;
import VentaEntradasCenima.Modelo.BBDD.GestorDAO.SesionDAO;
import VentaEntradasCenima.Vista.Menu;

public class AppVentaEntradas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);

        Menu menu = new Menu();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        SesionDAO sesionDAO = new SesionDAO();
        SalaDAO salaDAO = new SalaDAO();

        Controlador controlador = new Controlador(menu, peliculaDAO, sesionDAO, salaDAO);
        controlador.iniciar();

        teclado.close();

	}

}
