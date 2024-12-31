package presentacion;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public interface IModalProducto extends IModuloProducto{
    
    public static final String GUARDAR = "GUARDAR";
    public static final String REGRESAR_MODULO = "REGRESAR";
    public static final String ACTUALIZAR = "ACTUALIZAR";
    public JComboBox<String> getCategoriaBox();

    public JTextField getNombreField();

    public JTextField getPcompraField();

    public JTextField getPventaField();

    public JTextField getIdField();
}
