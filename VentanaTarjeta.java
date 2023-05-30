import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class VentanaTarjeta extends Ventana {
    private JPanel pnlInicio;
    private JLabel lblImgTar;
    private JButton btnInsertar;
    private Image imgTarjeta;
    private JComboBox<String> comboCuentas;

    public VentanaTarjeta() {
        super("Cajero Automatico - Insertar tarjeta");
        showComponents();
    }

    private void showComponents() {
        // Panels 
        pnlInicio = new JPanel();
        pnlInicio.setLayout(new BoxLayout(pnlInicio, BoxLayout.Y_AXIS));
        pnlInicio.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Labels
        lblTitle = new JLabel();
        lblTitle.setText("POR FAVOR, INSERTA LA TARJETA");
        lblTitle.setFont(new Font("", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        imgTarjeta = new ImageIcon("imagenes/tarjeta.png").getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        lblImgTar = new JLabel(new ImageIcon(imgTarjeta));
        lblImgTar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        btnInsertar = new JButton("Insertar");
        btnInsertar.setMaximumSize(new Dimension(220, 30)); // width
        btnInsertar.setPreferredSize(new Dimension(220, 30)); // heigth
        stilizeButton(btnInsertar);
        btnInsertar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ComboBox
        comboCuentas = new JComboBox<String>();
        for(Cuenta cnt : cliente1.getCuentas()){
            comboCuentas.addItem(cnt.getNumCuenta());
        }
        comboCuentas.setMaximumSize(new Dimension(220, 30));
        comboCuentas.setPreferredSize(new Dimension(220, 30));
        comboCuentas.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // ActionListeners botones
        btnInsertar.addActionListener((ActionEvent evt) -> {
            for(Cuenta cnt : cliente1.getCuentas()){
                if(cnt.getNumCuenta() == comboCuentas.getSelectedItem()){
                    if(cnt.getTarjeta().getBloqueada()){
                        JOptionPane.showMessageDialog(this, "La tarjeta esta bloqueada", "Acceso denegado", JOptionPane.WARNING_MESSAGE);
                    }else{
                        cuentaActual = cnt;
                        new VentanaOperaciones().setLocationRelativeTo(this);
                    }
                }
            }
        });

        // Añadir componentes
        pnlInicio.add(lblTitle);
        pnlInicio.add(lblImgTar);
        pnlInicio.add(comboCuentas);
        pnlInicio.add(Box.createRigidArea(new Dimension(0, 20)));
        pnlInicio.add(btnInsertar);

        this.add(pnlInicio);
        this.setLayout(new FlowLayout());
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}