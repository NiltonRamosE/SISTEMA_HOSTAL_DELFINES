package presentacion;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface IModulos {
    
    public static final String NUEVO = "Nuevo";
    public static final String MODIFICAR = "Modificar";
    public static final String ELIMINAR = "Eliminar";
    public static final String REGRESAR = "Regresar";
    
    public static final String NUEVO2 = "Nuevo";
    public static final String MODIFICAR2 = "Modificar";
    public static final String ELIMINAR2 = "Eliminar";
    public static final String REGRESAR2 = "Regresar";
    public void iniciar();
    public void cerrar();
    
    public DefaultTableModel getDt();
    public JTable getListadoTable();
}
