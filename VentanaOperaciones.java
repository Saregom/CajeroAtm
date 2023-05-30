import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class VentanaOperaciones extends Ventana{
    private JPanel pnlTitle, pnlOpciones, pnlOpciones2;
    private JLabel lblTitle2;
    private JButton btnServicio, btnCelular, btnEntradas, btnRegresar;

    public VentanaOperaciones(){
        super("Cajero Automatico - Operaciones");
        ventanaOperaciones = this;
        showComponents();
    }
    
    private void showComponents(){
        // Panels
        pnlTitle = new JPanel();
        pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitle.setPreferredSize(new Dimension(600, 100));
        pnlTitle.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlOpciones = new JPanel();
        pnlOpciones.setLayout(new GridLayout(3,2,50,20));
        pnlOpciones.setPreferredSize(new Dimension(500, 240));

        pnlOpciones2 = new JPanel();
        pnlOpciones2.setLayout(new GridLayout(2,2,50,20));
        pnlOpciones2.setPreferredSize(new Dimension(500, 160));
        
        // Labels
        lblTitle = new JLabel();
        lblTitle.setText("BIENVENIDO AL CAJERO AUTOMATICO");
        lblTitle.setFont(new Font("", Font.BOLD, 20));
        
        lblTitle2 = new JLabel();
        lblTitle2.setText("SELECCIONE UNA OPERACION");
        lblTitle2.setFont(new Font("", Font.BOLD, 18));

        //Buttons
        String[] listTextos = {"Retirar", "Consignar efectivo", "Transferir", "Ver saldo actual", "Cambiar clave", "Otros servicios"};
        
        for(String cant : listTextos){
            JButton btn = new JButton();
            stilizeButton(btn);
            btn.setText(cant);
            pnlOpciones.add(btn);
            
            btn.addActionListener((ActionEvent evt) -> {
                btnAction(btn.getText());
            });
        }
        
        // Buttons panel2
        btnServicio = new JButton("Pagar servicios");
        stilizeButton(btnServicio);

        btnCelular = new JButton("Recargar celular");
        stilizeButton(btnCelular);

        btnEntradas = new JButton("Comprar entradas");
        stilizeButton(btnEntradas);
        
        btnRegresar = new JButton("Regresar");
        stilizeButton(btnRegresar);
        btnRegresar.setBackground(Color.decode("#F7C2C2"));
        
        // Añadir componentes
        pnlTitle.add(lblTitle);
        pnlTitle.add(lblTitle2);

        pnlOpciones2.add(btnServicio);
        pnlOpciones2.add(btnCelular);
        pnlOpciones2.add(btnEntradas);
        pnlOpciones2.add(btnRegresar);
        pnlOpciones2.setVisible(false);
        
        this.add(pnlTitle);
        this.add(pnlOpciones);
        this.add(pnlOpciones2);
        
        this.setLayout(new FlowLayout());
        this.setSize(600, 450);
        this.setVisible(true);
        
        btnRegresar.addActionListener((ActionEvent evt) -> {
            pnlOpciones.setVisible(true);
            pnlOpciones2.setVisible(false);
        });
    }

    private void btnAction(String opc){
        switch(opc){
            case "Retirar":
                operacion = 1;
                tipoTransaccion = "Retiro";
                new VentanaCantidades().setLocationRelativeTo(this); break;
            case "Consignar efectivo":
                operacion = 2;
                tipoTransaccion = "Consignacion";
                pedirNumCuenta(0); break;
            case "Transferir":
                operacion = 3;
                tipoTransaccion = "Transferencia";
                pedirNumCuenta(1); break;
            case "Ver saldo actual":
                operacion = 4;
                tipoTransaccion = "Consulta de saldo";
                if(pedirClave()){
                    new VentanaRecibo("0").setLocationRelativeTo(this);
                } break;
            case "Cambiar clave":
                operacion = 5;
                if(pedirClave()){
                    new VentanaCambiarClave().setLocationRelativeTo(this);
                } break;
            case "Otros servicios":
                pnlOpciones.setVisible(false);
                pnlOpciones2.setVisible(true); break;
        }
    }

    private void pedirNumCuenta(int opc){
        cuentaConsignar = null;
        if(opc == 0){
            String[] opciones = {"Personal", "Diferente"};
            opc = JOptionPane.showOptionDialog(this,"Elige a cual cuenta deseas consignar","Elije una opcion",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        }

        switch(opc){
            case 0: 
                cuentaConsignar = cuentaActual;
                new VentanaCantidades().setLocationRelativeTo(this);
                break;
            case 1: 
                int opt;
                JTextField field = new JTextField();
                Object[] message = {"Ingresa el numero de cuenta:", field};

                do{
                    opt = JOptionPane.showConfirmDialog(this, message, "Atencion", JOptionPane.OK_CANCEL_OPTION);
                    String numCuenta = field.getText();

                    if(opt == 0){
                        if(numCuenta.isEmpty()){
                            JOptionPane.showMessageDialog(this, "El espacio no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            Cliente[] clientes = {cliente1, cliente2};
                            for(Cliente clnt : clientes){
                                for(Cuenta cnt : clnt.getCuentas()){
                                    if(cnt.getNumCuenta().equals(numCuenta)){
                                        cuentaConsignar = cnt;
                                        break;
                                    }
                                }
                            }
                            if(cuentaConsignar == null){
                                JOptionPane.showMessageDialog(this, "El numero de cuenta no existe", "Error", JOptionPane.ERROR_MESSAGE);
                            }else{
                                new VentanaCantidades().setLocationRelativeTo(this);
                                break;
                            }
                        }
                    }
                }while(opt == 0);
        }
    }
}
