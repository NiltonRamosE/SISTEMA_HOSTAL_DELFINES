package presentacion;

import javax.swing.JTable;

public interface IModuloProducto extends IModulos{
    
    public void setPresentador(PresentadorProducto pProducto);
    
    public JTable getListadoTable2();
}
