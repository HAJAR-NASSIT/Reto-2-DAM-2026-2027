package VentaEntradasCenima.Modelo.Pojos;



public class Cliente {
	
	private String dni;
	private String nombre;
	private String email;
	private String password;
	
	public Cliente() {
		
		
	}
	
	public Cliente(String dni, String nombre, String email, String password) {
	
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", password=" + password + "]";
	}
	
	
	 
	 }
	 
	

		

