package VentaEntradasCenima.Vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import VentaEntradasCenima.Modelo.BBDD.Utils.DBUtils;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtEmail;
	private JPasswordField password;

	private String emailcliente = "";
	private String passwordcliente = "";
	private boolean registrado = false;

	public Login() {

		setLayout(null);
		setBounds(0, 0, 450, 300);

		JLabel LblLogin = new JLabel("CINE  ELORRIETA");
		LblLogin.setForeground(new Color(255, 0, 0));
		LblLogin.setFont(new Font("Awami Nastaliq", Font.ITALIC, 25));
		LblLogin.setBounds(109, 33, 287, 38);
		add(LblLogin);

		JLabel lblusuario = new JLabel("Usuario:");
		lblusuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblusuario.setBounds(43, 110, 65, 19);
		add(lblusuario);

		JLabel lblcontraseña = new JLabel("Contraseña:");
		lblcontraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcontraseña.setBounds(43, 142, 105, 24);
		add(lblcontraseña);

		txtEmail = new JTextField();
		txtEmail.setBounds(147, 111, 157, 20);
		add(txtEmail);
		txtEmail.setColumns(10);

		password = new JPasswordField();
		password.setBounds(147, 146, 157, 20);
		add(password);

		JButton btnRegistrar = new JButton("REGISTRARSE");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String pass = new String(password.getPassword());

				if (email.isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(Login.this, "Rellena todes los campos");
				}
				
		
				if (registrado && email.equals(emailcliente)) {
	
					
					JOptionPane.showMessageDialog(Login.this, "Usuario ya registrado");
				} else {
					emailcliente = email;
					passwordcliente = pass;
					registrado = true;
					JOptionPane.showMessageDialog(Login.this, "Usuario registrado");
				}
			}
		});
		btnRegistrar.setBackground(new Color(128, 0, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setForeground(new Color(0, 0, 0));
		btnRegistrar.setBounds(160, 251, 121, 24);
		btnRegistrar.setFocusPainted(false);
		add(btnRegistrar);

		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setBackground(new Color(128, 0, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBounds(160, 218, 121, 22);
		btnLogin.setFocusPainted(false);
		add(btnLogin);

	}

}


