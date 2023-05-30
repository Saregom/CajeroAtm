import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class VentanaRecibo extends Ventana{
    private JPanel pnlInfo;
    private JLabel lblIdTransaccion, lblIdCajero, lblBanco, lblTipo, lblNombre, lblNumCuenta, lblNumCuentaDestino, lblSaldo, lblDinero, lblFecha;
    String cantDinero, numCuenta, numCuenta2, strTiempo;
    LocalDateTime tiempo;

    public VentanaRecibo(String cantDinero){
        super("Cajero Automatico - Recibo");
        this.cantDinero = cantDinero;
        this.tiempo = LocalDateTime.now();//.minusHours(5);
        this.strTiempo = tiempo.format( DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss"));
        this.numCuenta2 = "0000000000";
        showComponents();
    }
    
    private void showComponents(){
        //Procesos
        if(operacion != 1 && operacion != 4){
            numCuenta2 = cuentaConsignar.getNumCuenta();
        }
        
        // Panels
        pnlInfo = new JPanel();
        pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS));
        pnlInfo.setBorder(new EmptyBorder(0, 30, 20, 0));
        pnlInfo.setPreferredSize(new Dimension(600, 450));

        // Labels
        lblTitle = new JLabel();
        lblTitle.setText("RECIBO - DATOS DE TRANSACCION");
        lblTitle.setFont(new Font("", Font.BOLD, 20));
        lblTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblIdTransaccion = new JLabel();
        lblIdTransaccion.setText("Numero de transaccion: " + ((int)(Math.random()*1000000)+1));
        lblIdTransaccion.setFont(new Font("", Font.BOLD, 18));
        lblIdTransaccion.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblIdCajero = new JLabel();
        lblIdCajero.setText("Numero del cajero: 23");
        lblIdCajero.setFont(new Font("", Font.BOLD, 18));
        lblIdCajero.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblBanco = new JLabel();
        lblBanco.setText("Institucion financiera: Bancolombia");
        lblBanco.setFont(new Font("", Font.BOLD, 18));
        lblBanco.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        lblTipo = new JLabel();
        lblTipo.setText("Tipo transaccion: " + tipoTransaccion);
        lblTipo.setFont(new Font("", Font.BOLD, 18));
        lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblNombre = new JLabel();
        lblNombre.setText("Nombre: " + cliente1.getNombre());
        lblNombre.setFont(new Font("", Font.BOLD, 18));
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblNumCuenta = new JLabel();
        lblNumCuenta.setText("Numero cuenta: " + formattNumero(cuentaActual.getNumCuenta()));
        lblNumCuenta.setFont(new Font("", Font.BOLD, 18));
        lblNumCuenta.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblNumCuentaDestino = new JLabel();
        lblNumCuentaDestino.setText("Numero cuenta destino: " + formattNumero(numCuenta2));
        lblNumCuentaDestino.setFont(new Font("", Font.BOLD, 18));
        lblNumCuentaDestino.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblDinero = new JLabel();
        lblDinero.setText("Monto de dinero: " + cantDinero);
        lblDinero.setFont(new Font("", Font.BOLD, 18));
        lblDinero.setAlignmentX(Component.LEFT_ALIGNMENT);
  
        lblSaldo = new JLabel();
        lblSaldo.setText("Saldo actual: "+ cuentaActual.getSaldo());
        lblSaldo.setFont(new Font("", Font.BOLD, 18));
        lblSaldo.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblFecha = new JLabel();
        lblFecha.setText("Fecha y hora: " + strTiempo);
        lblFecha.setFont(new Font("", Font.BOLD, 18));
        lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Añadir componentes
        pnlInfo.add(lblIdTransaccion); 
        pnlInfo.add(lblIdCajero); 
        pnlInfo.add(lblBanco); 
        pnlInfo.add(lblTipo); 
        pnlInfo.add(lblNombre);
        if(operacion != 2){
            pnlInfo.add(lblNumCuenta);
        }
        if(operacion == 2 || operacion == 3){
            pnlInfo.add(lblNumCuentaDestino);
        }
        if(operacion != 4){
            pnlInfo.add(lblDinero);
        }
        pnlInfo.add(lblSaldo);
        if(operacion == 2 && !cuentaConsignar.getNumCuenta().equals( cuentaActual.getNumCuenta())){
            pnlInfo.remove(lblSaldo);
        }
        pnlInfo.add(lblFecha);

        this.add(lblTitle);
        this.add(pnlInfo);
        
        this.setLayout(new FlowLayout());
        this.setSize(600, 450);
        this.setVisible(true);
    }

    public String formattNumero(String num){
        String numFormat = "";
        for(int i=0;i<num.length();i++){
            if(i>3 && i<num.length()-1){
                numFormat = numFormat + "*";
            }else{
                numFormat = numFormat + num.charAt(i);
            }
        }
        return numFormat;
    }
}