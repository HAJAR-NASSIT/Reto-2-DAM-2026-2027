package VentaEntradasCenima.Modelo.Pojos;

public class Compra {

	private int idCompra;
	private String dni;
	private int fechaCompra;
	private int horaCompra;
	private int prcioTotal;
	private double descuentoTotal;
	private int tipoCompra;
	private int numeroEntradas;
	
	public Compra() {
		
	}
	
	public Compra(int idCompra, String dniCliente, int fechaCompra, int horaCompra, int prcioTotal,
			double descuentoTotal, int tipoCompra, int numeroEntradas) {
		
		this.idCompra = idCompra;
		this.dni = dniCliente;
		this.fechaCompra = fechaCompra;
		this.horaCompra = horaCompra;
		this.prcioTotal = prcioTotal;
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

	public String getDniCliente() {
		return dni;
	}

	public void setDniCliente(String dniCliente) {
		this.dni = dniCliente;
	}

	public int getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(int fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getHoraCompra() {
		return horaCompra;
	}

	public void setHoraCompra(int horaCompra) {
		this.horaCompra = horaCompra;
	}

	public int getPrcioTotal() {
		return prcioTotal;
	}

	public void setPrcioTotal(int prcioTotal) {
		this.prcioTotal = prcioTotal;
	}

	public double getDescuentoTotal() {
		return descuentoTotal;
	}

	public void setDescuentoTotal(double descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}

	public int getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(int tipoCompra) {
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
		return "Compra [idCompra=" + idCompra + ", dniCliente=" + dni + ", fechaCompra=" + fechaCompra
				+ ", horaCompra=" + horaCompra + ", prcioTotal=" + prcioTotal + ", descuentoTotal=" + descuentoTotal
				+ ", tipoCompra=" + tipoCompra + ", numeroEntradas=" + numeroEntradas + "]";
	}
	
	
	
	
	
	
	
	
}
