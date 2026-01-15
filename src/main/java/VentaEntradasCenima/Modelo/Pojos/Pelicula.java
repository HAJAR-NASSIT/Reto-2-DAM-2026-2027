package VentaEntradasCenima.Modelo.Pojos;


public class Pelicula {

<<<<<<< HEAD
	private int idPelicula;
	private String nombreGenero;
=======
	private int idpelicula;
>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
	private String titulo;
	private int duracion;
	private String idioma;
	private String descripcion;
	
<<<<<<< HEAD
	public Pelicula() {
		
		
	}
	
	
	
	
	public Pelicula(int idPelicula, String nombreGenero, String titulo, int duracion, String idioma,
			String descripcion) {
		this.idPelicula = idPelicula;
		this.nombreGenero = nombreGenero;
=======

	public Pelicula() {
	
	}


	public Pelicula(int idPelicula, String titulo, int duracion, String clasificacion, String idioma,
			String descripcion) {
		this.idpelicula = idPelicula;
>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
		this.titulo = titulo;
		this.duracion = duracion;
		this.idioma = idioma;
		this.descripcion = descripcion;
	}

<<<<<<< HEAD



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
=======

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


>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


<<<<<<< HEAD

	@Override
	public String toString() {
		return "Pelicula [idPelicula=" + idPelicula + ", nombreGenero=" + nombreGenero + ", titulo=" + titulo
				+ ", duracion=" + duracion + ", idioma=" + idioma + ", descripcion=" + descripcion + "]";
=======
	@Override
	public String toString() {
		return "Pelicula [idPelicula=" + idpelicula + ", titulo=" + titulo + ", duracion=" + duracion
				+ ", clasificacion=" + clasificacion + ", idioma=" + idioma + ", descripcion=" + descripcion + "]";
>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
	}

	
<<<<<<< HEAD
	
	
}
    
	

=======
	}
>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
