package VentaEntradasCenima.Modelo.Pojos;


public class Sala {

	
	private int id_sala;
	private String nombre;
	private String Capacidad;
	

	public Sala(int id_sala, String nombre, String capacidad) {
		this.id_sala = id_sala;
		this.nombre = nombre;
		Capacidad = capacidad;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
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
		return "Sala [id_sala=" + id_sala + ", nombre=" + nombre + ", Capacidad=" + Capacidad + "]";
	}
	
	
	
	
	
}
