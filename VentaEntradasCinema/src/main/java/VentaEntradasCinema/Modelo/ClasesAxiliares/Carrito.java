package VentaEntradasCinema.Modelo.ClasesAxiliares;

import java.util.ArrayList;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;

/**
 * Clase Carrito. Representa el carrito de la compra del cliente. Contiene las
 * selecciones de películas, sesiones y número de entradas, y permite calcular
 * precios, descuentos y generar el resumen de la compra.
 */

public class Carrito {

	/**
	 * Lista de selecciones añadidas al carrito.
	 */

	private ArrayList<Seleccion> selecciones = new ArrayList<>();
	public boolean anadir =false;

	/**
	 * Añade una nueva selección al carrito.
	 *
	 * @param pelicula     película seleccionada
	 * @param sesion       sesión elegida
	 * @param sala         sala donde se proyecta
	 * @param espectadores número de entradas
	 */

	public boolean addSeleccion(Pelicula pelicula, Sesion sesion, Sala sala, int espectadores) {
		
		boolean disponible = CarritoUtils.hayEntradasDisponibles(sesion, sala, espectadores);

		if (disponible) {

			Seleccion nueva = new Seleccion(pelicula, sesion, sala, espectadores);
			selecciones.add(nueva);
			
		} else {
			System.out.println("No hay suficientes entradas disponibles para esta sesión.");
		}
		
		return disponible;
		
	}

	/**
	 * Devuelve la lista de selecciones del carrito.
	 *
	 * @return lista de selecciones
	 */

	public ArrayList<Seleccion> getSelecciones() {

		return selecciones;
	}

	/**
	 * Indica si el carrito contiene alguna selección.
	 *
	 * @return true si hay al menos una selección, false en caso contrario
	 */

	public boolean haySeleccion() {

		boolean haySeleccion = false;

		if (selecciones.size() > 0) {

			haySeleccion = true;

		} else {

			haySeleccion = false;
		}

		return haySeleccion;
	}

	/**
	 * Vacía el carrito eliminando todas las selecciones.
	 */

	public void reiniciarCarrito() {

		selecciones.clear();
	}

	/**
	 * Calcula el subtotal de la compra. Suma el precio de cada sesión multiplicado
	 * por el número de entradas.
	 *
	 * @return subtotal de la compra
	 */

	public double calcularSubtotal() {

		double subtotal = 0;

		for (Seleccion seleccion : selecciones) {

			double precio = seleccion.getSesion().getPrecio();
			int numEntradas = seleccion.getEspectadores();

			subtotal = subtotal + (precio * numEntradas);
		}

		return subtotal;
	}

	/**
	 * Cuenta el número total de entradas del carrito.
	 *
	 * @return número total de entradas
	 */

	public int contarEntradas() {

		int totalEntradas = 0;

		for (Seleccion seleccion : selecciones) {
			totalEntradas = totalEntradas + seleccion.getEspectadores();
		}

		return totalEntradas;
	}

	/**
	 * Cuenta el número de películas distintas en el carrito.
	 *
	 * @return número de películas distintas
	 */

	public int contarPeliculasDistintas() {

		ArrayList<Integer> ids = new ArrayList<>();

		int numPeliculas = 0;
		boolean existe = false;
		int id = 0;

		for (Seleccion seleccion : selecciones) {

			id = seleccion.getPelicula().getIdPelicula();

			for (int i = 0; i < ids.size(); i++) {

				if (ids.get(i) == id) {
					existe = true;
				}
			}

			if (existe == false) {
				ids.add(id);
			}
		}

		numPeliculas = ids.size();

		return numPeliculas;
	}

	/**
	 * Calcula el descuento aplicado según el número de películas distintas. - 2
	 * películas: 20% de descuento - 3 o más películas: 30% de descuento
	 *
	 * @return importe del descuento
	 */

	public double calcularDescuento() {

		double subtotal = calcularSubtotal();
		int distintas = contarPeliculasDistintas();
		double descuento = 0;

		if (distintas == 2) {
			descuento = (subtotal * 0.20);
		}
		if (distintas >= 3) {
			descuento = (subtotal * 0.30);
		}

		return descuento;
	}

	/**
	 * Genera las líneas de texto del resumen de la compra. Estas líneas se usan
	 * para mostrar el resumen y generar la factura.
	 *
	 * @return lista de líneas del resumen
	 */

	public ArrayList<String> generarLineasResumen() {

		ArrayList<String> lineas = new ArrayList<>();
		int numero = 1;

		for (Seleccion seleccion : selecciones) {
			double precioLinea = seleccion.getSesion().getPrecio() * seleccion.getEspectadores();

			String linea = " " + numero + ". " + seleccion.getPelicula().getTitulo() + " - "
					+ seleccion.getSesion().getFecha() + " " + seleccion.getSesion().getHoraInicio() + " ("
					+ seleccion.getSala().getNombre() + ")" + " x " + seleccion.getEspectadores() + " --> "
					+ precioLinea + " EUR";

			lineas.add(linea);
			numero++;
		}

		return lineas;
	}

}