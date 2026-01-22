package VentaEntradasCenima.Modelo.Pojos;


import java.time.LocalDate;
import java.time.LocalTime;

public class Compra {

	private int idCompra;
	private Cliente cliente;
	private LocalDate fechaCompra;
	private LocalTime horaCompra;
	private double precioTotal;
	private double descuentoTotal;
	private String tipoCompra;
	private int numeroEntradas;
	
	public Compra() {
		
	}

	public Compra(int idCompra, Cliente cliente, LocalDate fechaCompra, LocalTime horaCompra, double precioTotal,
			double descuentoTotal, String tipoCompra, int numeroEntradas) {
	
		this.idCompra = idCompra;
		this.cliente = cliente;
		this.fechaCompra = fechaCompra;
		this.horaCompra = horaCompra;
		this.precioTotal = precioTotal;
		this.descuentoTotal = descuentoTotal;
		this.tipoCompra = tipoCompra;
		this.numeroEntradas = numeroEntradas;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public LocalTime getHoraCompra() {
		return horaCompra;
	}

	public void setHoraCompra(LocalTime horaCompra) {
		this.horaCompra = horaCompra;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public double getDescuentoTotal() {
		return descuentoTotal;
	}

	public void setDescuentoTotal(double descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public int getNumeroEntradas() {
		return numeroEntradas;
	}

	public void setNumeroEntradas(int numeroEntradas) {
		this.numeroEntradas = numeroEntradas;
	}

	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", cliente=" + cliente + ", fechaCompra=" + fechaCompra
				+ ", horaCompra=" + horaCompra + ", precioTotal=" + precioTotal + ", descuentoTotal=" + descuentoTotal
				+ ", tipoCompra=" + tipoCompra + ", numeroEntradas=" + numeroEntradas + "]";
	}
	

	
}