import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class VentanaTeclado extends Ventana{
    private JPanel pnlTeclado;
    private JTextField txtPadNumerico;

    public VentanaTeclado(){
        super("Cajero Automatico - Teclado");
        showComponents();
    }
    
    private void showComponents(){
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(600, 450);
    
        // Panels
        pnlTeclado = new JPanel();
        pnlTeclado.setLayout(new GridLayout(4,3,10,10));
        pnlTeclado.setMaximumSize(new Dimension(280,240));
        pnlTeclado.setPreferredSize(new Dimension(280,240));
        pnlTeclado.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTeclado.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Labels
        lblTitle = new JLabel();
        lblTitle.setText("INGRESA LA CANTIDAD DE DINERO");
        lblTitle.setFont(new Font("", Font.BOLD, 20));
        lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        //TextField
        txtPadNumerico = new JTextField();
        txtPadNumerico.setFont(new Font("", 0, 30));
        txtPadNumerico.setMaximumSize(new Dimension(280,55));
        txtPadNumerico.setPreferredSize(new Dimension(280,55));
        txtPadNumerico.setMargin(new Insets(0, 10, 0, 0));
        txtPadNumerico.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Buttons -> pnlTeclado
        for(int i=1;i<=12;i++){
            JButton btn = new JButton();
            stilizeButton(btn);
            
            String txt = ""+i+"";
            switch(i){
                case 10: 
                    txt = "del";
                    btn.setBackground(Color.decode("#F7C2C2"));break;
                case 11: txt = "0"; break;
                case 12: 
                    txt = "acept";
                    btn.setBackground(Color.decode("#BDF7BD"));
                    break;
            }
            btn.setText(txt);
            pnlTeclado.add(btn);

            //ActionListeners botones
            btn.addActionListener((ActionEvent evt) -> {
                setNumberAction(btn.getText());
            });
        }

        // Añadir componentes
        this.add(lblTitle);
        this.add(txtPadNumerico);
        this.add(pnlTeclado);
        this.setVisible(true);
    }

    // public void stilizeButton(JButton btn){
    //     btn.setFont(new Font("", 0, 17));
    //     btn.setBackground(Color.decode("#C3ECF4"));
    //     btn.setFocusable(false);
    //     btn.setBorderPainted(false);
    // }

    public void setNumberAction(String txt){
        String strPad = txtPadNumerico.getText();
        
        switch(txt){
            case "del": 
                if(!strPad.isEmpty()){
                    txtPadNumerico.setText(strPad.substring(0, strPad.length() - 1));
                }
                break;
            case "acept":
                if(strPad.equals("")){
                    JOptionPane.showMessageDialog(this, "El espacio no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(((Float.parseFloat(strPad)/10000) - (Integer.parseInt(strPad)/10000)) != 0 ){
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser multiplo de 10.000", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    doTransaction(strPad);
                }
                
                break;
                
            default: 
                if(strPad.isEmpty() && txt == "0"){
                    txtPadNumerico.setText(""); 
                }else{
                    txtPadNumerico.setText(strPad + txt);
                }
        }
    }

}