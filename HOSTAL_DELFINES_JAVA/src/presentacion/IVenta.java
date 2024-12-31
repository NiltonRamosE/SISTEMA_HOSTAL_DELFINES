package presentacion;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public interface IVenta extends IModulos{
    
    public static final String AGREGAR = "Agregar";
    public static final String GUARDAR = "Guardar";
    public static final String FACTURA = "Factura";
    public static final String BOLETA = "Boleta";
    public void setPresentador(PresentadorVenta pVenta);
    
    public JTextField getCantidadField();

    public JComboBox<String> getClienteBox();

    public JComboBox<String> getProductoBox();

    public JLabel getTotalLabel();

    public JTextField getTurnoField();
}
