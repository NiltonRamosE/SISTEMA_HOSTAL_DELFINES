package presentacion;

import dominio.BusinessEmpleado;
import dominio.Empleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import presentacion.vistas.Login;

public class Presentador implements ActionListener{

    private ILogin ilogin;
    private PresentadorMenu pMenu;
    private BusinessEmpleado empleadoInformation;
    public Presentador() {
        ilogin = new Login();
        empleadoInformation = new BusinessEmpleado();
    }
    
    public void iniciarLogin(Presentador presentador){
        ilogin.setPresentador(presentador);
        ilogin.iniciar();
    }
    
    private void limpiarFields(){
        ilogin.getClaveField().setText("");
        ilogin.getUsuarioField().setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(ilogin.LOGIN)){
            String user = ilogin.getUsuarioField().getText();
            String clave = String.valueOf(ilogin.getClaveField().getPassword());
            Empleado empleadoSession = empleadoInformation.login(user, clave);
            if( empleadoSession != null){
                pMenu = new PresentadorMenu(empleadoSession);
                pMenu.iniciarMenu(pMenu);
            }else{
                JOptionPane.showMessageDialog(null,"Credenciales incorrectas","Error",JOptionPane.ERROR_MESSAGE);
                limpiarFields();
            }
            
        }
    }
}
