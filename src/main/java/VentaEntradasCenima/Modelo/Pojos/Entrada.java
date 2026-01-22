package VentaEntradasCenima.Modelo.Pojos;



public class Entrada {

	private int idEntrada;
	private Sesion sesion;
	private Compra compra;
	private double precio;
	private double descuento;
	
	public Entrada() {
		
	}

	public Entrada(int idEntrada, Sesion sesion, Compra compra, double precio, double descuento) {
		
		this.idEntrada = idEntrada;
		this.sesion = sesion;
		this.compra = compra;
		this.precio = precio;
		this.descuento = descuento;
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "Entrada [idEntrada=" + idEntrada + ", sesion=" + sesion + ", compra=" + compra + ", precio=" + precio
				+ ", descuento=" + descuento + "]";
	}

	
	

	
}

	
	
	

	