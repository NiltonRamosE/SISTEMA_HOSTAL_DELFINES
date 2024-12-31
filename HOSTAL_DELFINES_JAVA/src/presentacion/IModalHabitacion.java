package presentacion;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public interface IModalHabitacion extends IModuloHabitacion{
    public static final String GUARDAR = "GUARDAR";
    public static final String REGRESAR_MODULO = "REGRESAR";
    public static final String ACTUALIZAR = "ACTUALIZAR";
    
    public JComboBox<String> getCamasBox();

    public JTextField getNumeroField();

    public JComboBox<String> getVentanaBox();
    
    public JComboBox<String> getDisponibleBox();
    
    public JTextField getIdField();
}
