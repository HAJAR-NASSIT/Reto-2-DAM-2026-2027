package VentaEntradasCenima.Modelo.Pojos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesion {

	private int idSesion;
	private Pelicula pelicula;
	private Sala sala;
	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private double precio;
	
	
	public Sesion() {
	
	}


	public Sesion(int idSesion, Pelicula pelicula, Sala sala, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
			double precio) {
		this.idSesion = idSesion;
		this.pelicula = pelicula;
		this.sala = sala;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precio = precio;
	}


	public int getIdSesion() {
		return idSesion;
	}


	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}


	public Pelicula getPelicula() {
		return pelicula;
	}


	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}


	public Sala getSala() {
		return sala;
	}


	public void setSala(Sala sala) {
		this.sala = sala;
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
		return "Sesion [idSesion=" + idSesion + ", pelicula=" + pelicula + ", sala=" + sala + ", fecha=" + fecha
				+ ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", precio=" + precio + "]";
	}

	
	
	
	
	}