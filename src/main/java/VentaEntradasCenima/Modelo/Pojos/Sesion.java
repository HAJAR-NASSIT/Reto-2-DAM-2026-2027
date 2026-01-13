package VentaEntradasCenima.Modelo.Pojos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesion {

	private int id_sesion;
	private int id_pelicula;
	private int id_sala;
	private LocalDate fecha;
	private LocalTime hora_inicio;
	private LocalTime hora_fin;
	private double precio;
	
	
	public Sesion(int id_sesion, int id_pelicula, int id_sala, LocalDate fecha, LocalTime hora_inicio,
			LocalTime hora_fin, double precio) {
		
		this.id_sesion = id_sesion;
		this.id_pelicula = id_pelicula;
		this.id_sala = id_sala;
		this.fecha = fecha;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.precio = precio;
	}

	public int getId_sesion() {
		return id_sesion;
	}

	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public LocalTime getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(LocalTime hora_fin) {
		this.hora_fin = hora_fin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Sesion [id_sesion=" + id_sesion + ", id_pelicula=" + id_pelicula + ", id_sala=" + id_sala + ", fecha="
				+ fecha + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin + ", precio=" + precio + "]";
	}
	
	
	
	
}
