package VentaEntradasCenima.Modelo.Pojos;


public class Sala {

	
	private int idSala;
	private String nombre;
	private int capacidad;
	

	public Sala() {
		
<<<<<<< HEAD
		
	}

	public Sala(int idSala, String nombre, String capacidad) {
=======
	}


	public Sala(int idSala, String nombre, int capacidad) {
>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
		
		this.idSala = idSala;
		this.nombre = nombre;
		this.capacidad = capacidad;
	}
<<<<<<< HEAD
	
=======


>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
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


<<<<<<< HEAD
	public String getCapacidad() {
		return Capacidad;
	}


	public void setCapacidad(String capacidad) {
		Capacidad = capacidad;
=======
	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
	}


	@Override
	public String toString() {
<<<<<<< HEAD
		return "Sala [idSala=" + idSala + ", nombre=" + nombre + ", Capacidad=" + Capacidad + "]";
	}


	
=======
		return "Sala [idSala=" + idSala + ", nombre=" + nombre + ", capacidad=" + capacidad + "]";
	}

>>>>>>> 94b37301267835b884a330ad4b0ab3d004c2ed5e
	
	
}