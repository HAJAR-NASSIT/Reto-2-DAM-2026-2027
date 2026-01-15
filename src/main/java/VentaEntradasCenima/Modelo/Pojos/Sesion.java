package VentaEntradasCenima.Modelo.Pojos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesion {

	private int idSesion;
	private int idPelicula;
	private int idSala;
	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private double precio;
	
	
	public Sesion() {
	
	}

	public int getIdSesion() {
		return idSesion;
	}


	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}


	public int getIdPelicula() {
		return idPelicula;
	}


	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}


	public int getIdSala() {
		return idSala;
	}


	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public LocalTime getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}


	public LocalTime getHoraFin() {
		return horaFin;
	}


	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Sesion [idSesion=" + idSesion + ", idPelicula=" + idPelicula + ", idSala=" + idSala + ", fecha=" + fecha
				+ ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", precio=" + precio + "]";
	}
	
	
	
}