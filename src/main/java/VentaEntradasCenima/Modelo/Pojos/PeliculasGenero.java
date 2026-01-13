package VentaEntradasCenima.Modelo.Pojos;

public class PeliculasGenero {

	private String nombre_genero;
	private int id_pelicula;
	
	public PeliculasGenero(String nombre_genero, int id_pelicula) {
		
		this.nombre_genero = nombre_genero;
		this.id_pelicula = id_pelicula;
	}

	public String getNombre_genero() {
		return nombre_genero;
	}

	public void setNombre_genero(String nombre_genero) {
		this.nombre_genero = nombre_genero;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	@Override
	public String toString() {
		return "PeliculasGenero [nombre_genero=" + nombre_genero + ", id_pelicula=" + id_pelicula + "]";
	}
	
	
}
