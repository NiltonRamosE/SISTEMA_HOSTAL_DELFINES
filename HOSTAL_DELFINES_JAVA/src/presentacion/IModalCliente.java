package presentacion;

import javax.swing.JTextField;

public interface IModalCliente extends IModuloCliente{
    
    public static final String ACTUALIZAR = "ACTUALIZAR";
    
    public JTextField getApellidoField();

    public JTextField getDniField();

    public JTextField getNombreField();
    
    public JTextField getIdField();
}
