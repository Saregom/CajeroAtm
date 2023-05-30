import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class VentanaDinero extends Ventana {
    private JPanel pnlInicio;
    private JLabel lblImgTar;
    private JButton btnInsertar;
    private Image imgTarjeta;
    private String txtCantidad;
    private float cantidad;

    public VentanaDinero(String txtCantidad, float cantidad){
        super("Cajero Automatico - Dispensador dinero");
        this.txtCantidad = txtCantidad;
        this.cantidad = cantidad;
        showComponents();
    }

    private void showComponents() {
        // Panels (QUITAR)
        pnlInicio = new JPanel();
        pnlInicio.setLayout(new BoxLayout(pnlInicio, BoxLayout.Y_AXIS));
        pnlInicio.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Labels
        if(operacion == 1){
            lblTitle = new JLabel("POR FAVOR, RETIRA EL DINERO");
        }else{
            lblTitle = new JLabel("POR FAVOR, INSERTA EL DINERO");
        }
        lblTitle.setFont(new Font("", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        imgTarjeta = new ImageIcon("imagenes/dinero.png").getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        lblImgTar = new JLabel(new ImageIcon(imgTarjeta));
        lblImgTar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        if(operacion == 1){
            btnInsertar = new JButton("Retirar");
        }else{
            btnInsertar = new JButton("Insertar");
        }
        btnInsertar.setMaximumSize(new Dimension(220, 40)); 
        btnInsertar.setPreferredSize(new Dimension(220, 40)); 
        stilizeButton(btnInsertar);
        btnInsertar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // ActionListeners botones
        btnInsertar.addActionListener((ActionEvent evt) -> {
            if(operacion == 1){
                cuentaActual.retirar(cantidad);
            }else{
                cuentaConsignar.consignar(cantidad);
            }
            JOptionPane.showMessageDialog(this, "Ya puedes ver tu recibo", "Operacion completada", JOptionPane.INFORMATION_MESSAGE);
            new VentanaRecibo(txtCantidad).setLocationRelativeTo(this);
            this.setVisible(false);
        });

        // Añadir componentes
        pnlInicio.add(lblTitle);
        pnlInicio.add(lblImgTar);
        pnlInicio.add(btnInsertar);

        this.add(pnlInicio);
        this.setLayout(new FlowLayout());
        this.setSize(600, 450);
        this.setVisible(true);
    }
}