package presentacion;

import dominio.BusinessCompra;
import dominio.BusinessProducto;
import dominio.BusinessProveedor;
import dominio.BusinessRegistroCompra;
import dominio.Compra;
import dominio.Empleado;
import dominio.LineaCompra;
import dominio.Producto;
import dominio.Proveedor;
import dominio.RegistroCompra;
import dominio.Turno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import presentacion.vistas.VistaCompra;

public class PresentadorCompra implements ActionListener{
    private ICompra icompra;
    private BusinessProveedor proveedorInformation;
    private BusinessProducto productoInformation;
    private BusinessCompra compraInformation;
    private BusinessRegistroCompra rcompraInformation;
    private Compra compra;
    private RegistroCompra rcompra;
    private Empleado empleadoSession;
    public PresentadorCompra(Empleado empleadoSession) {
        this.empleadoSession = empleadoSession;
        icompra = new VistaCompra();
        proveedorInformation = new BusinessProveedor();
        productoInformation = new BusinessProducto();
        compraInformation = new BusinessCompra();
        rcompraInformation = new BusinessRegistroCompra();
        compra = new Compra();
    }

    public void iniciarModuloCompra(PresentadorCompra pCompra){
        establecerTurno();
        //CREACION DEL REGISTRO DE COMPRA DIARIO
        RegistroCompra rcTemp = null;
        Turno turno = Turno.valueOf(icompra.getTurnoField().getText());
        rcTemp = rcompraInformation.registroCompraDiario(turno);
        if(rcTemp != null){
            rcompra = rcTemp;
        }else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            rcTemp = new RegistroCompra();
            rcTemp.setFecha(fechaHoraActual.format(formatter));
            rcTemp.setTurno(turno);
            rcTemp.setEmpleado(empleadoSession);
            rcompra = rcompraInformation.crear(rcTemp);
        }
        
        rellenarBox();
        icompra.setPresentador(pCompra);
        icompra.iniciar();
    }
    
    private void rellenarBox(){
        icompra.getProveedorBox().removeAllItems();
        for(Proveedor pv: proveedorInformation.listado()){
            icompra.getProveedorBox().addItem(pv.getRazonSocial());
        }
        icompra.getProductoBox().removeAllItems();
        for(Producto p: productoInformation.listado()){
            icompra.getProductoBox().addItem(p.getNombre());
        }
    }
    private void establecerTurno() {
        LocalTime horaActual = LocalTime.now();
        String turno = getTurnoByHora(horaActual);

        icompra.getTurnoField().setText(turno);
    }

    private String getTurnoByHora(LocalTime hora) {
        if (hora.isAfter(LocalTime.of(6, 0)) && hora.isBefore(LocalTime.of(12, 1))) {
            return Turno.MANANA.name();
        } else if (hora.isAfter(LocalTime.of(12, 0)) && hora.isBefore(LocalTime.of(18, 1))) {
            return Turno.TARDE.name();
        } else {
            return Turno.NOCHE.name();
        }
    }
    
    private void llenarTablaLineaCompra(){
        icompra.getDt().setRowCount(0);
        String[] titulos = {"ID","Proveedor", "Producto", "Cantidad", "P. Compra"};
        icompra.getDt().setColumnIdentifiers(titulos);
        Object fila[] = new Object[5];
        fila[0] = compra.getId();
        fila[1] = compra.getProveedor().getRazonSocial();
        
        for (LineaCompra lc : compra.getLineaCompras()) {

            fila[2] = lc.getProducto().getNombre();
            fila[3] = lc.getCantidad();
            fila[4] = lc.getPrecioUnitario();
            icompra.getDt().addRow(fila);
        }
        icompra.getListadoTable().setModel(icompra.getDt());
        icompra.getListadoTable().getColumnModel().getColumn(0).setPreferredWidth(10);
        icompra.getListadoTable().setDefaultEditor(Object.class, null);
        icompra.getListadoTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int filaSelect = icompra.getListadoTable().getSelectedRow();
        if(e.getActionCommand().equals(icompra.AGREGAR)){
            
            String proveedor = String.valueOf(icompra.getProveedorBox().getSelectedItem());
            Proveedor pv = proveedorInformation.getProveedorByRazonSocial(proveedor);
            
            String producto = String.valueOf(icompra.getProductoBox().getSelectedItem());
            Producto p = productoInformation.getProductoByNombre(producto);
            
            int cantidad = Integer.parseInt(icompra.getCantidadField().getText());
            compra.setId(1);
            compra.setProveedor(pv);
            compra.agregarLineaCompra(new LineaCompra(cantidad, p, p.getPrecioCompra()));
            llenarTablaLineaCompra();
        }else if(e.getActionCommand().equals(icompra.MODIFICAR)){
            try{
                int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto seleccionado"));
                compra.getLineaCompras().get(filaSelect).setCantidad(nuevaCantidad);
                llenarTablaLineaCompra();
            }catch(IndexOutOfBoundsException exc){
                JOptionPane.showMessageDialog(null, "No se ha seleccionado una fila","Error",JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("modificar");
        }else if(e.getActionCommand().equals(icompra.ELIMINAR)){
            compra.removerLineaCompra(compra.getLineaCompras().get(filaSelect));
            llenarTablaLineaCompra();
        }else if(e.getActionCommand().equals(icompra.REGRESAR)){
            compra = null;
            icompra.cerrar();
        }else if(e.getActionCommand().equals(icompra.ENVIAR)){
            //En este punto recien guardamos en la BD o AT
            
            if(!compra.getLineaCompras().isEmpty()){
                compra.setId_registroCompra(rcompra.getId());
                compraInformation.crear(compra);
                compra = new Compra();
            }else{
                JOptionPane.showMessageDialog(null, "No se ha añadido ningun producto","Error",JOptionPane.ERROR_MESSAGE);
            }
            icompra.getCantidadField().setText("");
            icompra.getDt().setRowCount(0);
            System.out.println("enviar");
        }
    }
}
