import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class VentanaCantidades extends Ventana{
    private JPanel pnlTitle, pnlOpciones;

    public VentanaCantidades(){
        super("Cajero Automatico - Seleccion Cantidad");
        showComponents();
    }
    
    private void showComponents(){
        // Panels
        pnlTitle = new JPanel();
        pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitle.setPreferredSize(new Dimension(600, 80));
        pnlTitle.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlOpciones = new JPanel();
        pnlOpciones.setLayout(new GridLayout(4,2,50,20));
        pnlOpciones.setPreferredSize(new Dimension(500, 240));
        
        // Labels
        lblTitle = new JLabel();
        lblTitle.setText("SELECCIONE UNA CANTIDAD DE DINERO");
        lblTitle.setFont(new Font("", Font.BOLD, 20));
        
        // Buttons
        String[] listCantidades = {"20.000", "50.000", "100.000", "200.000", "500.000", "800.000", "1.000.000", "Otra cantidad"};
        
        for(String cant : listCantidades){
            JButton btn = new JButton();
            stilizeButton(btn);
            
            btn.setText(cant);
            pnlOpciones.add(btn);

            //ActionListeners botones
            btn.addActionListener((ActionEvent evt) -> {
                if(btn.getText().equals("Otra cantidad")){
                    new VentanaTeclado().setLocationRelativeTo(this);
                    this.setVisible(false);
                }else{
                    doTransaction(btn.getText());
                }
            });
        }
        
        // Añadir componentes
        pnlTitle.add(lblTitle);
        
        this.add(pnlTitle);
        this.add(pnlOpciones);
        
        this.setLayout(new FlowLayout());
        this.setSize(600, 450);
        this.setVisible(true);
    }
}
