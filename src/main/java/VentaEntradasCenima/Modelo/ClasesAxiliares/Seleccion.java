package VentaEntradasCenima.Modelo.ClasesAxiliares;

import VentaEntradasCenima.Modelo.Pojos.Pelicula;
import VentaEntradasCenima.Modelo.Pojos.Sala;
import VentaEntradasCenima.Modelo.Pojos.Sesion;

public class Seleccion {
	private Pelicula  pelicula ;
    private Sesion sesion    ;
    private Sala  sala ;
    private  int espectadores ;

  
	    public Seleccion(Pelicula pelicula , Sesion sesion, Sala sala , int espectadores) {
			
			this.pelicula = pelicula;
			this.sesion = sesion;
			this.sala = sala;
			this.espectadores = espectadores;
		}

		public Pelicula getPelicula() {
			return pelicula;
		}
	
		public void setPelicula(Pelicula pelicula) {
			this.pelicula = pelicula;
		}
	
		public Sesion getSesion() {
			return sesion;
		}
	
		public Sala getSala() {
			return sala;
		}
	
		public int getEspectadores() {
			return espectadores;
		}
}
