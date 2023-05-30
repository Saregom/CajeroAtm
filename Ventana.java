import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

class Ventana extends JFrame{
    protected JLabel lblTitle;
    protected static Cliente cliente1, cliente2;
    protected static int operacion;
    protected static Cuenta cuentaActual;
    protected static Cuenta cuentaConsignar;
    protected static Ventana ventanaOperaciones;
    protected static String tipoTransaccion;

    public Ventana(String Title){
        super(Title);
    }

    public boolean pedirClave(){
        JPasswordField claveField = new JPasswordField(10);
        String clave = "";
    
        int i = 0;
        while(clave != null && i<3){
            Object[] message = {"Ingresa la clave:", claveField};
            int opc = JOptionPane.showConfirmDialog(this, message, "Autenticacion", JOptionPane.OK_CANCEL_OPTION);
            
            clave = "";
            for(char chr : claveField.getPassword()){
                clave += (String.valueOf(chr));
            }

            if(opc == 0){
                if(clave.isEmpty()){
                    JOptionPane.showMessageDialog(this, "El espacio no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(clave.equals(cuentaActual.getTarjeta().getClave())){
                        JOptionPane.showMessageDialog(this, "Acceso concedido", "Clave correcta", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(this, "Acceso denegado", "Clave incorrecta", JOptionPane.ERROR_MESSAGE);
                        i++;
                    }
                }
            }else{
                return false;
            }
        }
        
        JOptionPane.showMessageDialog(this, "Ha excedido el numero de intentos", "TARJETA BLOQUEADA", JOptionPane.ERROR_MESSAGE);
        cuentaActual.getTarjeta().bloquear();

        this.setVisible(false);
        if(ventanaOperaciones != null){
            ventanaOperaciones.setVisible(false);
        }
        return false;
    }

    public void doTransaction(String txtCantidad){
        float cantidad = Float.parseFloat(txtCantidad.replaceAll("\\.", ""));
        if(operacion == 1 || operacion == 3){
            if(pedirClave()){
                if(cuentaActual.verificarSaldo(cantidad)){
                    if(!cuentaActual.maxRetirosSuperada(cantidad)){
                        if(pedirConfirmacion()){
                            if(operacion == 1){
                                new VentanaDinero(txtCantidad, cantidad).setLocationRelativeTo(this);
                            }else{
                                cuentaActual.retirar(cantidad);
                                cuentaConsignar.consignar(cantidad);
                                new VentanaRecibo(txtCantidad).setLocationRelativeTo(this);
                            }
                            this.setVisible(false);
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Cantidad max. de retiros superada, intenta retirar menos o vuelve mañana", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "La transaccion supera el saldo actual", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        }else if(operacion == 2 && pedirConfirmacion()){
            new VentanaDinero(txtCantidad, cantidad).setLocationRelativeTo(this);
            this.setVisible(false);
        }
    }

    public boolean pedirConfirmacion(){
        int opt = JOptionPane.showConfirmDialog(this, "Deseas confirmar la transaccion", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if(opt == 0){
            JOptionPane.showMessageDialog(this, "Operacion confirmada", "Confirmada", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

    public void stilizeButton(JButton btn){
        btn.setFont(new Font("", 0, 17));
        btn.setBackground(Color.decode("#C3ECF4"));
        btn.setFocusable(false);
        btn.setBorderPainted(false);
    }
}