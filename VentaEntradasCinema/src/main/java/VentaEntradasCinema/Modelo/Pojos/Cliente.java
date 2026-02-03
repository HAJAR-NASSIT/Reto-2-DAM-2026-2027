package VentaEntradasCinema.Modelo.Pojos;


public class Cliente {

	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String password;

	public Cliente() {

	}

	public Cliente(String dni, String nombre, String email, String password) {

		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.password = password ;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public boolean verificarDNI(String dni) {

		boolean DNIValid = false;
		int NumDNI = 0;
		String subDNI = "";
		String letraCorrecta = "";
		String letraUsuario = "";

		String[] letras = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V",
				"H", "L", "C", "K", "E" };

		if (dni.length() == 9) {

			subDNI = dni.substring(0, 8);
			NumDNI = Integer.parseInt(subDNI);

			letraCorrecta = letras[(int)(NumDNI % 23)];

			letraUsuario = "" + dni.charAt(8);

			if (letraCorrecta.equalsIgnoreCase(letraUsuario)) {
				DNIValid = true;
			}

		} else {
			DNIValid = false;
		}
		return DNIValid;
	}

	public boolean verificarEmail(String email) {

	    boolean emailValid = false;

	    int posArroba = email.indexOf("@");
	    int posPunto = email.lastIndexOf(".");

	    if (posArroba > 0 && posPunto > posArroba) {
	        emailValid = true;
	    }

	    return emailValid;
	}

	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+  "]";
	}
	
	
	



}
