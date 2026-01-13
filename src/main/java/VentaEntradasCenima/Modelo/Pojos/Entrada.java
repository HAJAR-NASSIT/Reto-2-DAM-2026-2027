package VentaEntradasCenima.Modelo.Pojos;

public class Entrada {

	private int id_entrada;
	private int id_sesion;
	private int id_compra;
	private double precio;
	private double descuento;
	
	
	

	public Entrada(int id_entrada, int id_sesion, int id_compra, double precio, double descuento) {
		this.id_entrada = id_entrada;
		this.id_sesion = id_sesion;
		this.id_compra = id_compra;
		this.precio = precio;
		this.descuento = descuento;
	}


	public int getId_entrada() {
		return id_entrada;
	}


	public void setId_entrada(int id_entrada) {
		this.id_entrada = id_entrada;
	}


	public int getId_sesion() {
		return id_sesion;
	}


	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}


	public int getId_compra() {
		return id_compra;
	}


	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
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
		return "Entrada [id_entrada=" + id_entrada + ", id_sesion=" + id_sesion + ", id_compra=" + id_compra
				+ ", precio=" + precio + ", descuento=" + descuento + "]";
	}
	
	
	
	
}
