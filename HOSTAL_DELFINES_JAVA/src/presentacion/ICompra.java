package presentacion;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public interface ICompra extends IModulos{
    
    public static final String AGREGAR = "Agregar";
    public static final String ENVIAR = "Enviar";
    
    public void setPresentador(PresentadorCompra pCompra);
    
    public JTextField getCantidadField();

    public JComboBox<String> getProductoBox();

    public JComboBox<String> getProveedorBox();
    
    public JTextField getTurnoField();
}
