package VentaEntradasCenima.Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Panel_bienvenida extends JFrame {
	private Image imagen;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel_bienvenida frame = new Panel_bienvenida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Panel_bienvenida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		// PANEL PRINCIPAL
		contentPane = new PanelConFondo();
		contentPane.setLayout(null);
		//contentPane.setBackground(new Color(128, 0, 0)); // COLOR FONDO
		setContentPane(contentPane);

		// TÍTULO
		JLabel Titulo = new JLabel("CINE ELORRIETA");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Tahoma", Font.BOLD, 27));
		Titulo.setForeground(new Color(255, 140, 0));
		Titulo.setBounds(-38, 26, 323, 40);
		contentPane.add(Titulo);

		// SUBTÍTULO
		JLabel lblNewLabel = new JLabel("Bienvenido a la venta de entradas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(-19, 77, 323, 17);
		contentPane.add(lblNewLabel);

		// TEXTO DESCRIPTIVO
		JLabel lblNewLabel_1 = new JLabel("Disfruta de las mejores peliculas al mejor precio");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.ITALIC, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 105, 300, 40);
		contentPane.add(lblNewLabel_1);

		// BOTÓN
		JButton btnNewButton = new JButton("CONTINUAR");
		btnNewButton.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(245, 255, 250));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(69, 160, 149, 40);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Continuar...");
			}
		});
		contentPane.add(btnNewButton);

	}
}
