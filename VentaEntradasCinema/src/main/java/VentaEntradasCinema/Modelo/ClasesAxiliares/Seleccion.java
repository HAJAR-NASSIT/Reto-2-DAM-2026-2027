package VentaEntradasCinema.Modelo.ClasesAxiliares;

import VentaEntradasCinema.Modelo.Pojos.Pelicula;
import VentaEntradasCinema.Modelo.Pojos.Sala;
import VentaEntradasCinema.Modelo.Pojos.Sesion;


/**
 * Clase que representa una selección de película realizada por el usuario,
 * incluyendo la sesión, la sala y el número de espectadores.
 */


public class Seleccion {
	
	private Pelicula  pelicula ;
    private Sesion sesion    ;
    private Sala  sala ;
    private  int espectadores ;

    /**
     * Constructor de Seleccion.
     *
     * @param pelicula     La película seleccionada
     * @param sesion       La sesión elegida de la película
     * @param sala         La sala donde se proyecta la sesión
     * @param espectadores Número de entradas para esta selección
     */
    
    
	    public Seleccion(Pelicula pelicula , Sesion sesion, Sala sala , int espectadores) {
			
			this.pelicula = pelicula;
			this.sesion = sesion;
			this.sala = sala;
			this.espectadores = espectadores;
		}
	    
	    /**
	     * Obtiene la película seleccionada.
	     *
	     * @return La película
	     */

		public Pelicula getPelicula() {
			return pelicula;
		}
		 /**
	     * Establece la película seleccionada.
	     *
	     * @param pelicula La película a asignar
	     */
		
		public void setPelicula(Pelicula pelicula) {
			this.pelicula = pelicula;
		}
		  /**
	     * Obtiene la sesión elegida.
	     *
	     * @return La sesión
	     */
		
		public Sesion getSesion() {
			return sesion;
		}
		
		
		/**
	     * Obtiene la sala de la sesión.
	     *
	     * @return La sala
	     */
	
		public Sala getSala() {
			return sala;
		}
		
		
		  /**
	     * Obtiene el número de espectadores.
	     *
	     * @return Número de espectadores
	     */
		
	
		public int getEspectadores() {
			return espectadores;
		}

}
