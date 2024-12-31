package presentacion;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public interface IModalReserva extends IModuloReserva{
    
    public static final String ACTUALIZAR = "ACTUALIZAR";
    
    public static final String CANCELAR = "CANCELAR";
    
    public static final String CALCULAR = "CALCULAR COSTO";
    
    public static final String CALCULARNEW = "CALCULAR NUEVO COSTO";
    
    public JComboBox getClienteComboBox();
    
    public JComboBox getHabitacionComboBox();
    
    public JTextField getEfectivoField();

    public JComboBox<String> getEstadoComboBox();

    public JTextField getHoraIngresoField();

    public JTextField getHorasReservadasField();

    public JTextField getYapeField();
    
    public JSpinner getNroHuespedesSpinner();
    
    public JTextField getIdField();
    
    public JTextField getCostoreservaField();
    
    public JTextField getHoraAdicionalField();
}
