package VentaEntradasCenima.Modelo.Pojos;


public class Pelicula {

	private int idpelicula;
	private String titulo;
	private int duracion;
	private String clasificacion;
	private String idioma;
	private String descripcion;
	

	public Pelicula() {
	
	}


	public Pelicula(int idPelicula, String titulo, int duracion, String clasificacion, String idioma,
			String descripcion) {
		this.idpelicula = idPelicula;
		this.titulo = titulo;
		this.duracion = duracion;
		this.clasificacion = clasificacion;
		this.idioma = idioma;
		this.descripcion = descripcion;
	}


	public int getIdPelicula() {
		return idpelicula;
	}


	public void setIdPelicula(int idPelicula) {
		this.idpelicula = idPelicula;
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
		return "Pelicula [idPelicula=" + idpelicula + ", titulo=" + titulo + ", duracion=" + duracion
				+ ", clasificacion=" + clasificacion + ", idioma=" + idioma + ", descripcion=" + descripcion + "]";
	}

	
	}