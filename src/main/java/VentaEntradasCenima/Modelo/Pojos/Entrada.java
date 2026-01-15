package VentaEntradasCenima.Modelo.Pojos;

public class Entrada {

	private int idEntrada;
	private int idSesion;
	private int idCompra;
	private double precio;
	private double descuento;
	
	public Entrada() {
		
	}

	public Entrada(int idEntrada, int idSesion, int idCompra, double precio, double descuento) {
	
		this.idEntrada = idEntrada;
		this.idSesion = idSesion;
		this.idCompra = idCompra;
		this.precio = precio;
		this.descuento = descuento;
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public int getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
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
		return "Entrada [idEntrada=" + idEntrada + ", idSesion=" + idSesion + ", idCompra=" + idCompra + ", precio="
				+ precio + ", descuento=" + descuento + "]";
	}

	
	
	
	
	
	
}

	
	
	

	