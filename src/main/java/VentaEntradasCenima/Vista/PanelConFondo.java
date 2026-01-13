package VentaEntradasCenima.Vista;


import javax.swing.*;
import java.awt.*;

public class PanelConFondo extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image imagen;

    public PanelConFondo() {
        ImageIcon icon = new ImageIcon(
            getClass().getResource("/img/fondo1.PNG")
        );
        imagen = icon.getImage();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}
