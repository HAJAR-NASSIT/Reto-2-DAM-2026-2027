package VentaEntradasCenima.Modelo.Pojos;


public class Pelicula {

	private int id_pelicula;
	private String titulo;
	private int duracion;
	private String clasificacion;
	private String idioma;
	private String descripcion;
	
	

	public Pelicula(int id_pelicula, String titulo, int duracion, String clasificacion, String idioma,
			String descripcion) {
		
		this.id_pelicula = id_pelicula;
		this.titulo = titulo;
		this.duracion = duracion;
		this.clasificacion = clasificacion;
		this.idioma = idioma;
		this.descripcion = descripcion;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Pelicula [id_pelicula=" + id_pelicula + ", titulo=" + titulo + ", duracion=" + duracion
				+ ", clasificacion=" + clasificacion + ", idioma=" + idioma + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
}
