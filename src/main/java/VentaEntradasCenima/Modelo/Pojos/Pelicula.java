package VentaEntradasCenima.Modelo.Pojos;


public class Pelicula {

	private int idPelicula;
	private String nombreGenero;
	private String titulo;
	private int duracion;
	private String idioma;
	private String descripcion;
	private String imagen;

	public Pelicula() {
	
	}

	public Pelicula(int idPelicula, String nombreGenero, String titulo, int duracion, String idioma, String descripcion,
			String imagen) {
	
		this.idPelicula = idPelicula;
		this.nombreGenero = nombreGenero;
		this.titulo = titulo;
		this.duracion = duracion;
		this.idioma = idioma;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Pelicula [idPelicula=" + idPelicula + ", nombreGenero=" + nombreGenero + ", titulo=" + titulo
				+ ", duracion=" + duracion + ", idioma=" + idioma + ", descripcion=" + descripcion + ", imagen="
				+ imagen + "]";
	}

	
	
	}