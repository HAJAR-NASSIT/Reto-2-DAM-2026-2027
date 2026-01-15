package VentaEntradasCenima.Modelo.Pojos;

public class Genero {

	private String nombreGenero;

	
	public Genero() {
		
	
	}


	public Genero(String nombreGenero) {
	
		this.nombreGenero = nombreGenero;
	}


	public String getNombreGenero() {
		return nombreGenero;
	}


	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}


	@Override
	public String toString() {
		return "Genero [nombreGenero=" + nombreGenero + "]";
	}
	
	
}
	

