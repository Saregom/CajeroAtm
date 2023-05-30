import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

class VentanaCambiarClave extends Ventana{
    private JPanel pnlClaves;
    private JLabel lblNuevaClave, lblConfirmaClave;
    private JPasswordField txtNuevaClave, txtConfirmaClave;
    private JButton btnAceptar;

    public VentanaCambiarClave(){
        super("Cambia la clave");
        showComponents();
    }
    
    private void showComponents(){
        //Paneles
        pnlClaves = new JPanel();
        pnlClaves.setLayout(new GridLayout(3,2,10,25));
        pnlClaves.setPreferredSize(new Dimension(350,180));
        pnlClaves.setBorder(new EmptyBorder(10,15,10,15));
        
        //Labels
        lblNuevaClave = new JLabel("Digita la nueva clave");
        lblConfirmaClave = new JLabel("Confirma la clave");

        //campos de texto
        txtNuevaClave = new JPasswordField(4);
        txtNuevaClave.setFont(new Font("", Font.BOLD, 15));
        txtConfirmaClave = new JPasswordField(4);
        txtConfirmaClave.setFont(new Font("", Font.BOLD, 15));

        //Botones
        btnAceptar = new JButton("Aceptar");
        stilizeButton(btnAceptar);

        //Añadir componentes
        pnlClaves.add(lblNuevaClave);
        pnlClaves.add(txtNuevaClave);
        pnlClaves.add(lblConfirmaClave);
        pnlClaves.add(txtConfirmaClave);
        pnlClaves.add(btnAceptar);

        //Action listener
        btnAceptar.addActionListener((ActionEvent evt) -> {
            String txtCla1 = "";
            String txtCla2 = "";

            for(char chr : txtNuevaClave.getPassword()){
                txtCla1 += (String.valueOf(chr));
            }
            for(char chr : txtConfirmaClave.getPassword()){
                txtCla2 += (String.valueOf(chr));
            }
            
            if(txtCla1.isEmpty() || txtCla2.isEmpty() || !txtCla1.equals(txtCla2)){
                JOptionPane.showMessageDialog(this, "Las claves no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            }else if(txtCla1.length()!=4 || txtCla2.length()!=4){
                JOptionPane.showMessageDialog(this, "La clave tiene que ser de 4 digitos", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                cuentaActual.getTarjeta().cambiarClave(txtCla1);
                JOptionPane.showMessageDialog(this, "Se ha cambiado la clave con exito", "Operacion completada", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            }
        });
        
        this.add(pnlClaves);
        this.setLayout(new FlowLayout());
        this.setSize(350, 220);
        this.setVisible(true);
    }
}