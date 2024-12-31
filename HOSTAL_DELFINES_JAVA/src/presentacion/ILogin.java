package presentacion;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public interface ILogin {
    
    public static final String LOGIN = "LOGIN";
    
    public void setPresentador(Presentador presentador);
    
    public void iniciar();
    
    public JPasswordField getClaveField();

    public JTextField getUsuarioField();
    
}
