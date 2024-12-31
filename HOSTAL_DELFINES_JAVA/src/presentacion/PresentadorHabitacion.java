package presentacion;

import dominio.BusinessHabitacion;
import dominio.Habitacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import presentacion.vistas.AddHabitacion;
import presentacion.vistas.UpdateHabitacion;
import presentacion.vistas.VistaModuloHabitacion;

public class PresentadorHabitacion implements ActionListener{
    private IModuloHabitacion imoduloHabitacion;
    private IModalHabitacion imodalHabitacion;
    private BusinessHabitacion habitacionInformation;
    public PresentadorHabitacion() {
        imoduloHabitacion = new VistaModuloHabitacion();
        habitacionInformation = new BusinessHabitacion();
    }

    public void iniciarModuloHabitacion(PresentadorHabitacion pHabitacion){
        llenarTablaHabitacion();
        cerrarModalHabitacion();
        imoduloHabitacion.setPresentador(pHabitacion);
        imoduloHabitacion.iniciar();
    }
    
    private void cerrarModalHabitacion() {
        if (imodalHabitacion != null) {
            imodalHabitacion.cerrar();
            imodalHabitacion = null;
        }
    }
    
    private void llenarTablaHabitacion(){
        imoduloHabitacion.getDt().setRowCount(0);
        String[] titulos = {"ID","Nº Habitacion", "Nº Camas", "Disponibilidad", "Ventana"};
        imoduloHabitacion.getDt().setColumnIdentifiers(titulos);
        Object fila[] = new Object[5];
        
        for (Habitacion h : habitacionInformation.listado()) {
            
            fila[0] = h.getId();
            fila[1] = h.getNumero();
            fila[2] = h.getNumeroCamas();
            if(h.isDisponible()){
                fila[3] = "Disponible";
            }else{
                fila[3] = "No Disponible";
            }
            if(h.isVentanaAfuera()){
                fila[4] = "SI";
            }else{
                fila[4] = "NO";
            }
            imoduloHabitacion.getDt().addRow(fila);
        }
        imoduloHabitacion.getListadoTable().setModel(imoduloHabitacion.getDt());
        imoduloHabitacion.getListadoTable().getColumnModel().getColumn(0).setPreferredWidth(10);
        imoduloHabitacion.getListadoTable().setDefaultEditor(Object.class, null);
        imoduloHabitacion.getListadoTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int filaSelect = imoduloHabitacion.getListadoTable().getSelectedRow();
        String id ="";
        if(filaSelect >= 0){
            id = imoduloHabitacion.getListadoTable().getValueAt(filaSelect, 0).toString();
        }
        if(e.getActionCommand().equals(imoduloHabitacion.NUEVO)){
            if(imodalHabitacion == null){
                imoduloHabitacion.cerrar();
                imodalHabitacion = new AddHabitacion();
                imodalHabitacion.setPresentador(this);
                imodalHabitacion.iniciar();
            }
        }else if(e.getActionCommand().equals(imoduloHabitacion.MODIFICAR)){
            if(imodalHabitacion == null){
                Habitacion h = habitacionInformation.buscar(Integer.parseInt(id));
                if(h != null){
                    imoduloHabitacion.cerrar();
                    imodalHabitacion = new UpdateHabitacion();
                    imodalHabitacion.getIdField().setText(String.valueOf(h.getId()));
                    imodalHabitacion.getNumeroField().setText(String.valueOf(h.getNumero()));
                    imodalHabitacion.getCamasBox().setSelectedItem(String.valueOf(h.getNumeroCamas()));
                    
                    String ventanarpt= "NO";
                    if(h.isVentanaAfuera()){
                        ventanarpt = "SI";
                    }
                    String disporpt= "NO";
                    if(h.isDisponible()){
                        disporpt = "SI";
                    }
                    imodalHabitacion.getVentanaBox().setSelectedItem(ventanarpt);
                    imodalHabitacion.getDisponibleBox().setSelectedItem(disporpt);
                    imodalHabitacion.setPresentador(this);
                    imodalHabitacion.iniciar();
                }else{
                    JOptionPane.showMessageDialog(null,"Cliente no encontrado o fila no seleccionada","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(e.getActionCommand().equals(imoduloHabitacion.ELIMINAR)){
            Habitacion h = habitacionInformation.buscar(Integer.parseInt(id));
            if(h != null){
                habitacionInformation.eliminar(h);
            }else{
                JOptionPane.showMessageDialog(null,"Cliente no encontrado o fila no seleccionada","Error",JOptionPane.ERROR_MESSAGE);
            }
            llenarTablaHabitacion();
        }else if(e.getActionCommand().equals(imoduloHabitacion.REGRESAR)){
            imoduloHabitacion.cerrar();
        }else if(e.getActionCommand().equals(imodalHabitacion.REGRESAR_MODULO)){
            this.cerrarModalHabitacion();
            this.iniciarModuloHabitacion(this);
        }else if(e.getActionCommand().equals(imodalHabitacion.GUARDAR)){
            int numero = Integer.parseInt(imodalHabitacion.getNumeroField().getText());
            int camas = Integer.parseInt(String.valueOf(imodalHabitacion.getCamasBox().getSelectedItem()));
            String ventanarpt = String.valueOf(imodalHabitacion.getVentanaBox().getSelectedItem());
            boolean ventana = false;
            if(ventanarpt.equals("SI")){
                ventana = true;
            }
            if(numero > 0 && camas>=1 && !ventanarpt.equals("")){
                habitacionInformation.crear(new Habitacion(numero, camas, ventana, true));
            }
            //Limpiar fields
            imodalHabitacion.getNumeroField().setText("");
        }else if(e.getActionCommand().equals(imodalHabitacion.ACTUALIZAR)){
            int idHabitacion = Integer.parseInt(imodalHabitacion.getIdField().getText());
            int numero = Integer.parseInt(imodalHabitacion.getNumeroField().getText());
            int camas = Integer.parseInt(String.valueOf(imodalHabitacion.getCamasBox().getSelectedItem()));
            String ventanarpt = String.valueOf(imodalHabitacion.getVentanaBox().getSelectedItem());
            boolean ventana = false;
            if(ventanarpt.equals("SI")){
                ventana = true;
            }
            
            String disporpt = String.valueOf(imodalHabitacion.getDisponibleBox().getSelectedItem());
            boolean dispo = false;
            if(disporpt.equals("SI")){
                dispo = true;
            }
            
            if(numero > 0 && camas>=1 && !ventanarpt.equals("")){
                Habitacion htemp = new Habitacion();
                htemp.setId(idHabitacion);
                htemp.setNumero(numero);
                htemp.setNumeroCamas(camas);
                htemp.setVentanaAfuera(ventana);
                htemp.setDisponible(dispo);
                habitacionInformation.actualizar(htemp);
            }
            this.cerrarModalHabitacion();
            this.iniciarModuloHabitacion(this);
        }
    }
}
