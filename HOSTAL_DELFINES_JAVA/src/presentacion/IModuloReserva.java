package presentacion;

import javax.swing.JTable;
import javax.swing.JTextField;

public interface IModuloReserva extends IModulos{
    
    public static final String GUARDAR = "GUARDAR";
    public static final String REGRESAR_MODULO = "CERRAR";
    
    public void setPresentador(PresentadorReserva pReserva);
    
    public JTable getListadoTable2();
    
    public JTextField getTurnoField();
    
    public JTextField getFechaField();
}
