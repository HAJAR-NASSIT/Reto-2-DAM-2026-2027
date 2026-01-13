package VentaEntradasCenima.Modelo.Pojos;

public class Compra {

	private int id_compra;
	private String dni_cliente;
	private int fecha_compra;
	private int hora_compra;
	private int prcio_total;
	private double descuento_total;
	private int tipo_compra;
	private int numero_entradas;
	
	
	
	
	public Compra(int id_compra, String dni_cliente, int fecha_compra, int hora_compra, int prcio_total,
			double descuento_total, int tipo_compra, int numero_entradas) {
		
		this.id_compra = id_compra;
		this.dni_cliente = dni_cliente;
		this.fecha_compra = fecha_compra;
		this.hora_compra = hora_compra;
		this.prcio_total = prcio_total;
		this.descuento_total = descuento_total;
		this.tipo_compra = tipo_compra;
		this.numero_entradas = numero_entradas;
	
	}
	public int getId_compra() {
		return id_compra;
	}
	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	public String getDni_cliente() {
		return dni_cliente;
	}
	public void setDni_cliente(String dni_cliente) {
		this.dni_cliente = dni_cliente;
	}
	public int getFecha_compra() {
		return fecha_compra;
	}
	public void setFecha_compra(int fecha_compra) {
		this.fecha_compra = fecha_compra;
	}
	public int getHora_compra() {
		return hora_compra;
	}
	public void setHora_compra(int hora_compra) {
		this.hora_compra = hora_compra;
	}
	public int getPrcio_total() {
		return prcio_total;
	}
	public void setPrcio_total(int prcio_total) {
		this.prcio_total = prcio_total;
	}
	public double getDescuento_total() {
		return descuento_total;
	}
	public void setDescuento_total(double descuento_total) {
		this.descuento_total = descuento_total;
	}
	public int getTipo_compra() {
		return tipo_compra;
	}
	public void setTipo_compra(int tipo_compra) {
		this.tipo_compra = tipo_compra;
	}
	public int getNumero_entradas() {
		return numero_entradas;
	}
	public void setNumero_entradas(int numero_entradas) {
		this.numero_entradas = numero_entradas;
	}
	@Override
	public String toString() {
		return "Compra [id_compra=" + id_compra + ", dni_cliente=" + dni_cliente + ", fecha_compra=" + fecha_compra
				+ ", hora_compra=" + hora_compra + ", prcio_total=" + prcio_total + ", descuento_total="
				+ descuento_total + ", tipo_compra=" + tipo_compra + ", numero_entradas=" + numero_entradas + "]";
	}
	
	
	
}
