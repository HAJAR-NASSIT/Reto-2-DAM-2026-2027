package VentaEntradasCinema.Controlador.Ficheros;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class GestorFacturaTest {

	@Test
	public void testGuardarFactura() {
		GestorFactura gestor = new GestorFactura();

        int idCompra = 1;
        String dni = "00000002W";
        String nombre = "TestCliente";
        String apellido = "Test";
        String email = "test@email.com";
        
        String linea="1. Pelicula Test - 2026-06-18 18:00 (Sala 1) x 2 --> 20 EUR";
        String Facture="TicketFacturas/factura_" + idCompra + ".txt";
        
        double subtotal = 20.0;
        double descuento = 0.0;
        double total = 20.0;
        
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add(linea);


        gestor.guardarFactura(idCompra, dni, nombre, apellido ,email, lineas, subtotal, descuento, total);

        File fichero = new File(Facture);

        assertTrue(fichero.exists());
        assertTrue(fichero.length() > 0);

        fichero.delete();
    }

}
