package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import presentacion.vistas.Menu;
import dominio.Empleado;
public class PresentadorMenu implements ActionListener, MouseListener{

    private IMenu imenu;
    private PresentadorCliente pCliente;
    private PresentadorProducto pProducto;
    private PresentadorReserva pReserva;
    private PresentadorHabitacion pHabitacion;
    private PresentadorCompra pCompra;
    private PresentadorVenta pVenta;
    private Empleado empleadoSession;
    public PresentadorMenu(Empleado empleadoSession) {
        this.empleadoSession = empleadoSession;
        imenu = new Menu();
    }
    public void iniciarMenu(PresentadorMenu pMenu){
        imenu.getUsuarioField().setText(empleadoSession.getNombre() + " " + empleadoSession.getApellido());
        imenu.setPresentador(pMenu);
        imenu.iniciar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(imenu.SALIR)){
            imenu.cerrar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel labelClickeada = (JLabel) e.getSource();
        if (labelClickeada == imenu.getClienteLabel()) {
            pCliente = new PresentadorCliente();
            pCliente.iniciarModuloCliente(pCliente);
        } else if (labelClickeada == imenu.getProductoLabel()) {
            pProducto = new PresentadorProducto();
            pProducto.iniciarModuloProducto(pProducto);
        } else if (labelClickeada == imenu.getReservaLabel()) {
            pReserva = new PresentadorReserva(empleadoSession);
            pReserva.iniciarModuloReserva(pReserva);
        } else if (labelClickeada == imenu.getHabitacionLabel()) {
            pHabitacion = new PresentadorHabitacion();
            pHabitacion.iniciarModuloHabitacion(pHabitacion);
        }else if (labelClickeada == imenu.getCompraLabel()) {
            pCompra = new PresentadorCompra(empleadoSession);
            pCompra.iniciarModuloCompra(pCompra);
        }else if (labelClickeada == imenu.getVentaLabel()) {
            pVenta = new PresentadorVenta(empleadoSession);
            pVenta.iniciarModuloVenta(pVenta);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
