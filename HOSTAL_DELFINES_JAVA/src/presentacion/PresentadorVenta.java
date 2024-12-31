package presentacion;

import dominio.Boleta;
import dominio.BoletaVenta;
import dominio.BusinessCliente;
import dominio.BusinessProducto;
import dominio.BusinessRegistroVenta;
import dominio.BusinessRegistroReserva;
import dominio.BusinessVenta;
import dominio.Cliente;
import dominio.ComprobanteVenta;
import dominio.Empleado;
import dominio.LineaVenta;
import dominio.Producto;
import dominio.RegistroVenta;
import dominio.Turno;
import dominio.Venta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import presentacion.vistas.VistaVenta;

public class PresentadorVenta implements ActionListener{
    
    private IVenta iventa;
    private Venta venta;
    private BusinessProducto productoInformation;
    private BusinessCliente clienteInformation;
    private BusinessRegistroVenta rventaInformation;
    private BusinessVenta ventaInformation;
    private RegistroVenta rventa;
    private Empleado empleadoSession;
    private ComprobanteVenta comprobantev;
    public PresentadorVenta(Empleado empleadoSession) {
        this.empleadoSession = empleadoSession;
        iventa = new VistaVenta();
        productoInformation = new BusinessProducto();
        clienteInformation = new BusinessCliente();
        rventaInformation = new BusinessRegistroVenta();
        ventaInformation = new BusinessVenta();
        venta = new Venta();
        comprobantev = new ComprobanteVenta();
    }

    public void iniciarModuloVenta(PresentadorVenta pVenta){
        establecerTurno();
        //CREACION DEL REGISTRO DE VENTA DIARIO
        RegistroVenta rvTemp = null;
        Turno turno = Turno.valueOf(iventa.getTurnoField().getText());
        rvTemp = rventaInformation.registroVentaDiario(turno);
        if(rvTemp != null){
            rventa = rvTemp;
        }else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            rvTemp = new RegistroVenta();
            rvTemp.setFecha(fechaHoraActual.format(formatter));
            rvTemp.setTurno(turno);
            rvTemp.setEmpleado(empleadoSession);
            rvTemp.setLiquidacion(0);
            rventa = rventaInformation.crear(rvTemp);
        }
        
        rellenarBox();
        iventa.setPresentador(pVenta);
        iventa.iniciar();
    }
    private void rellenarBox(){
        iventa.getClienteBox().removeAllItems();
        for(Cliente c: clienteInformation.listado()){
            iventa.getClienteBox().addItem(c.getNombre()+" "+c.getApellido());
        }
        iventa.getProductoBox().removeAllItems();
        for(Producto p: productoInformation.listado()){
            iventa.getProductoBox().addItem(p.getNombre());
        }
    }
    private void establecerTurno() {
        LocalTime horaActual = LocalTime.now();
        String turno = getTurnoByHora(horaActual);
        iventa.getTurnoField().setText(turno);
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
        iventa.getDt().setRowCount(0);
        String[] titulos = {"ID","Cliente", "Producto", "Cantidad", "P. Venta", "SubTotal"};
        iventa.getDt().setColumnIdentifiers(titulos);
        Object fila[] = new Object[6];
        fila[0] = venta.getId();
        fila[1] = venta.getCliente().getNombre()+ " " +venta.getCliente().getApellido();
        
        for (LineaVenta lv : venta.getLineaVentas()) {

            fila[2] = lv.getProducto().getNombre();
            fila[3] = lv.getCantidad();
            fila[4] = lv.getPrecioUnitario();
            fila[5] = lv.subtotal();
            iventa.getDt().addRow(fila);
        }
        iventa.getListadoTable().setModel(iventa.getDt());
        iventa.getListadoTable().getColumnModel().getColumn(0).setPreferredWidth(10);
        iventa.getListadoTable().setDefaultEditor(Object.class, null);
        iventa.getListadoTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int filaSelect = iventa.getListadoTable().getSelectedRow();
        if(e.getActionCommand().equals(iventa.AGREGAR)){
            String clienteBox = String.valueOf(iventa.getClienteBox().getSelectedItem());
            Cliente c = clienteInformation.getClienteByNombre(clienteBox);
            int cantidad = Integer.parseInt(iventa.getCantidadField().getText());
            String producto = String.valueOf(iventa.getProductoBox().getSelectedItem());
            Producto p = productoInformation.getProductoByNombre(producto);
            venta.setId(1);
            venta.setCliente(c);
            venta.agregarLineaVenta(new LineaVenta(cantidad, p, p.getPrecioVenta()));
            iventa.getTotalLabel().setText(String.valueOf(venta.total()));
            System.out.println("agregar");
            llenarTablaLineaCompra();
        }else if(e.getActionCommand().equals(iventa.MODIFICAR)){
            try{
                int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto seleccionado"));
                venta.getLineaVentas().get(filaSelect).setCantidad(nuevaCantidad);
                iventa.getTotalLabel().setText(String.valueOf(venta.total()));
                llenarTablaLineaCompra();
            }catch(IndexOutOfBoundsException exc){
                JOptionPane.showMessageDialog(null, "No se ha seleccionado una fila","Error",JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("modificar");
            llenarTablaLineaCompra();
        }else if(e.getActionCommand().equals(iventa.ELIMINAR)){
            venta.removerLineaVenta(venta.getLineaVentas().get(filaSelect));
            System.out.println("eliminar");
            llenarTablaLineaCompra();
        }else if(e.getActionCommand().equals(iventa.REGRESAR)){
            venta = null;
            iventa.cerrar();
        }else if(e.getActionCommand().equals(iventa.GUARDAR)){
            if(!venta.getLineaVentas().isEmpty()){
                //Actualizar la liquidacion cada que se inserta una venta
                rventa.setLiquidacion(rventa.getLiquidacion() + venta.total());
                rventaInformation.actualizar(rventa);
                
                venta.setId_registroVenta(rventa.getId());
                ventaInformation.crear(venta);
                //Setteamos la venta actual para generar el comprobante
                comprobantev.setVenta(venta);
                //Creamos la nueva venta ya que la que usamos se acaba de cerrar
                venta = new Venta();
            }else{
                JOptionPane.showMessageDialog(null, "No se ha añadido ningun producto","Error",JOptionPane.ERROR_MESSAGE);
            }
            iventa.getTotalLabel().setText("");
            iventa.getCantidadField().setText("");
            iventa.getDt().setRowCount(0);
            System.out.println("guardar");
        }else if(e.getActionCommand().equals(iventa.FACTURA)){
            comprobantev.generarFacturaVenta();
        }else if(e.getActionCommand().equals(iventa.BOLETA)){
            comprobantev.generarBoletaVenta();
        }
    }
}
