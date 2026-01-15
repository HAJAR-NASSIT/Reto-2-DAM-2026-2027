package VentaEntradasCenima.Modelo.Pojos;

public class PeliculasGenero {

	private String nombreGenero;
	private int idPelicula;
	
	public PeliculasGenero() {
		
		
	}
	
	
	


	public String getNombreGenero() {
		return nombreGenero;
	}
	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}
	public int getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	@Override
	public String toString() {
		return "PeliculasGenero [nombreGenero=" + nombreGenero + ", idPelicula=" + idPelicula + "]";
	}
	
	
	
}
