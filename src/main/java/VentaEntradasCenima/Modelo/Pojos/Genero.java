package VentaEntradasCenima.Modelo.Pojos;

public class Genero {

	private String nombre_genero;

	public Genero(String nombre_genero) {
	
		this.nombre_genero = nombre_genero;
	}

	public String getNombre_genero() {
		return nombre_genero;
	}

	public void setNombre_genero(String nombre_genero) {
		this.nombre_genero = nombre_genero;
	}

	@Override
	public String toString() {
		return "genero [nombre_genero=" + nombre_genero + "]";
	}
	
	
	
	
	
}
