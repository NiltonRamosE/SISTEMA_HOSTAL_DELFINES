package presentacion;

import dominio.BusinessCliente;
import dominio.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import presentacion.vistas.AddCliente;
import presentacion.vistas.UpdateCliente;
import presentacion.vistas.VistaModuloCliente;

public class PresentadorCliente implements ActionListener{

    private IModuloCliente imoduloCliente;
    private IModalCliente imodalCliente;
    private BusinessCliente clienteInformation;
    public PresentadorCliente() {
        imoduloCliente = new VistaModuloCliente();
        clienteInformation = new BusinessCliente();
    }
    
    private void cerrarModalCliente() {
        if (imodalCliente != null) {
            imodalCliente.cerrar();
            imodalCliente = null;
        }
    }
    
    public void iniciarModuloCliente(PresentadorCliente pCliente){
        llenarTablaClientes();
        cerrarModalCliente();
        imoduloCliente.setPresentador(pCliente);
        imoduloCliente.iniciar();
    }
    
    private void llenarTablaClientes(){
        imoduloCliente.getDt().setRowCount(0);
        String[] titulos = {"ID","Nombre", "Apellido", "DNI"};
        imoduloCliente.getDt().setColumnIdentifiers(titulos);
        Object fila[] = new Object[4];
        
        for (Cliente c : clienteInformation.listado()) {
            fila[0] = c.getId();
            fila[1] = c.getNombre();
            fila[2] = c.getApellido();
            fila[3] = c.getDni();
            imoduloCliente.getDt().addRow(fila);
        }
        imoduloCliente.getListadoTable().setModel(imoduloCliente.getDt());
        imoduloCliente.getListadoTable().getColumnModel().getColumn(0).setPreferredWidth(10);
        imoduloCliente.getListadoTable().setDefaultEditor(Object.class, null);
        imoduloCliente.getListadoTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int filaSelect = imoduloCliente.getListadoTable().getSelectedRow();
        String id ="";
        if(filaSelect >= 0){
            id = imoduloCliente.getListadoTable().getValueAt(filaSelect, 0).toString();
        }
        if(e.getActionCommand().equals(imoduloCliente.NUEVO)){
            if(imodalCliente == null){
                imoduloCliente.cerrar();
                imodalCliente = new AddCliente();
                imodalCliente.setPresentador(this);
                imodalCliente.iniciar();
            }
        }else if(e.getActionCommand().equals(imoduloCliente.MODIFICAR)){
            if(imodalCliente == null){
                Cliente c = clienteInformation.buscar(Integer.parseInt(id));
                if(c != null){
                    imoduloCliente.cerrar();
                    imodalCliente = new UpdateCliente();
                    imodalCliente.getIdField().setText(String.valueOf(c.getId()));
                    imodalCliente.getDniField().setText(c.getDni());
                    imodalCliente.getNombreField().setText(c.getNombre());
                    imodalCliente.getApellidoField().setText(c.getApellido());
                    imodalCliente.setPresentador(this);
                    imodalCliente.iniciar();
                    System.out.println("modificar");
                }else{
                    JOptionPane.showMessageDialog(null,"Cliente no encontrado o fila no seleccionada","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(e.getActionCommand().equals(imoduloCliente.ELIMINAR)){
            Cliente c = clienteInformation.buscar(Integer.parseInt(id));
            if(c != null){
                clienteInformation.eliminar(c);
            }else{
                JOptionPane.showMessageDialog(null,"Cliente no encontrado o fila no seleccionada","Error",JOptionPane.ERROR_MESSAGE);
            }
            llenarTablaClientes();
        }else if(e.getActionCommand().equals(imoduloCliente.REGRESAR)){
            imoduloCliente.cerrar();
        }else if(e.getActionCommand().equals(imodalCliente.REGRESAR_MODULO)){
            cerrarModalCliente();
            this.iniciarModuloCliente(this);
        }else if(e.getActionCommand().equals(imodalCliente.GUARDAR)){
            String dni = imodalCliente.getDniField().getText();
            String nombre = imodalCliente.getNombreField().getText();
            String apellido = imodalCliente.getApellidoField().getText();
            
            if(!dni.equals("") && !nombre.equals("") && !apellido.equals("")){
                clienteInformation.crear(new Cliente(dni, nombre, apellido));
            }
            //Limpiar fields
            imodalCliente.getDniField().setText("");
            imodalCliente.getNombreField().setText("");
            imodalCliente.getApellidoField().setText("");
        }else if(e.getActionCommand().equals(imodalCliente.ACTUALIZAR)){
            
            int idCliente = Integer.parseInt(imodalCliente.getIdField().getText());
            String dni = imodalCliente.getDniField().getText();
            String nombre = imodalCliente.getNombreField().getText();
            String apellido = imodalCliente.getApellidoField().getText();
            
            if(!imodalCliente.getIdField().equals("") && !dni.equals("") && !nombre.equals("") && !apellido.equals("")){
                Cliente obj = new Cliente();
                obj.setId(idCliente);
                obj.setDni(dni);
                obj.setNombre(nombre);
                obj.setApellido(apellido);
                clienteInformation.actualizar(obj);
            }
            cerrarModalCliente();
            this.iniciarModuloCliente(this);
        }
    }
}
