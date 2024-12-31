package presentacion;

import javax.swing.JLabel;
import javax.swing.JTextField;

public interface IMenu {
    
    public static final String SALIR = "Salir";
    
    public void setPresentador(PresentadorMenu pMenu);
    
    public void iniciar();
    
    public void cerrar();
    
    public JLabel getClienteLabel();

    public JLabel getFacturaLabel();

    public JLabel getInicioLabel();

    public JLabel getHabitacionLabel();

    public JLabel getProductoLabel();

    public JLabel getReservaLabel();
    
    public JTextField getUsuarioField();
    
    public JLabel getCompraLabel();

    public JLabel getVentaLabel();
}
