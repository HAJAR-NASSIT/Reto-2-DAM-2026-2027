package VentaEntradasCenima.Modelo.Pojos;


public class Sala {

	
	private int idSala;
	private String nombre;
	private String Capacidad;
	

	public Sala() {
		
		
	}

	public Sala(int idSala, String nombre, String capacidad) {
		
		this.idSala = idSala;
		this.nombre = nombre;
		Capacidad = capacidad;
	}
	
	public int getIdSala() {
		return idSala;
	}


	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCapacidad() {
		return Capacidad;
	}


	public void setCapacidad(String capacidad) {
		Capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Sala [idSala=" + idSala + ", nombre=" + nombre + ", Capacidad=" + Capacidad + "]";
	}


	
	
	
	
	
	
}
