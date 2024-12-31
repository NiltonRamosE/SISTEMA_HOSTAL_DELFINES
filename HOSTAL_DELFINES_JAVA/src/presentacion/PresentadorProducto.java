package presentacion;

import dominio.BusinessProducto;
import dominio.Categoria;
import dominio.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import presentacion.vistas.AddProducto;
import presentacion.vistas.UpdateProducto;

import presentacion.vistas.VistaModuloProducto;

public class PresentadorProducto implements ActionListener{
    
    private IModuloProducto imoduloProducto;
    private IModalProducto imodalProducto;
    private BusinessProducto productoInformation;
    
    public PresentadorProducto() {
        imoduloProducto = new VistaModuloProducto();
        productoInformation = new BusinessProducto();
    }

    public void iniciarModuloProducto(PresentadorProducto pProducto){
        llenarTablaProductoAlimento();
        llenarTablaProductoPersonal();
        imoduloProducto.setPresentador(pProducto);
        imoduloProducto.iniciar();
    }
    
    private void llenarTablaProductoAlimento(){
        DefaultTableModel dt = imoduloProducto.getDt();
        dt.setRowCount(0);
        String[] titulos = {"ID","Nombre", "P. Compra", "P. Venta", "Stock"};
        dt.setColumnIdentifiers(titulos);
        Object fila[] = new Object[5];
        
        for (Producto p : productoInformation.getProductoAlimento()) {
            fila[0] = p.getId();
            fila[1] = p.getNombre();
            fila[2] = p.getPrecioCompra();
            fila[3] = p.getPrecioVenta();
            fila[4] = p.getStock();
            dt.addRow(fila);
        }
        imoduloProducto.getListadoTable2().setModel(dt);
        imoduloProducto.getListadoTable2().getColumnModel().getColumn(0).setPreferredWidth(10);
        imoduloProducto.getListadoTable2().setDefaultEditor(Object.class, null);
        imoduloProducto.getListadoTable2().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void llenarTablaProductoPersonal(){
        DefaultTableModel dt = imoduloProducto.getDt();
        dt.setRowCount(0);
        String[] titulos = {"ID","Nombre", "P. Compra", "P. Venta", "Stock"};
        dt.setColumnIdentifiers(titulos);
        Object fila[] = new Object[5];

        for (Producto p : productoInformation.getProductoPersonal()) {
            fila[0] = p.getId();
            fila[1] = p.getNombre();
            fila[2] = p.getPrecioCompra();
            fila[3] = p.getPrecioVenta();
            fila[4] = p.getStock();
            dt.addRow(fila);
        }
        imoduloProducto.getListadoTable().setModel(dt);
        imoduloProducto.getListadoTable().getColumnModel().getColumn(0).setPreferredWidth(10);
        imoduloProducto.getListadoTable().setDefaultEditor(Object.class, null);
        imoduloProducto.getListadoTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void cerrarModalProducto() {
        if (imodalProducto != null) {
            imodalProducto.cerrar();
            imodalProducto = null;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int filaSelect = imoduloProducto.getListadoTable().getSelectedRow();
        int filaSelect2 = imoduloProducto.getListadoTable2().getSelectedRow();
        String id ="";
        String id2 ="";
        if(filaSelect >= 0){
            id = imoduloProducto.getListadoTable().getValueAt(filaSelect, 0).toString();
            
        }
        if(filaSelect2 >= 0){
            id2 = imoduloProducto.getListadoTable2().getValueAt(filaSelect2, 0).toString();
        }
        if(e.getActionCommand().equals(imoduloProducto.NUEVO) || e.getActionCommand().equals(imoduloProducto.NUEVO2)){
            if(imodalProducto == null){
                imoduloProducto.cerrar();
                imodalProducto = new AddProducto();
                imodalProducto.getCategoriaBox().removeAllItems();
                for(Categoria c: Categoria.values()){
                    imodalProducto.getCategoriaBox().addItem(c.name());
                }
                imodalProducto.setPresentador(this);
                imodalProducto.iniciar();
            }
        }else if(e.getActionCommand().equals(imoduloProducto.MODIFICAR) || e.getActionCommand().equals(imoduloProducto.MODIFICAR2)){
            
            if(imodalProducto == null){
                Producto p = null, p2 = null;
                if(!id.equals("")){
                    p = productoInformation.buscar(Integer.parseInt(id));
                }else if(!id2.equals("")){
                    p2 = productoInformation.buscar(Integer.parseInt(id2));
                }
                if(p != null){
                    imoduloProducto.cerrar();
                    imodalProducto = new UpdateProducto();
                    imodalProducto.getIdField().setText(String.valueOf(p.getId()));
                    imodalProducto.getNombreField().setText(p.getNombre());
                    imodalProducto.getPventaField().setText(String.valueOf(p.getPrecioVenta()));
                    imodalProducto.getPcompraField().setText(String.valueOf(p.getPrecioCompra()));
                    imodalProducto.getCategoriaBox().removeAllItems();
                    for(Categoria c: Categoria.values()){
                        imodalProducto.getCategoriaBox().addItem(c.name());
                    }
                    imodalProducto.getCategoriaBox().setSelectedItem(p.getCategoria().name());
                    imodalProducto.setPresentador(this);
                    imodalProducto.iniciar();
                }else if(p2 != null){
                    imoduloProducto.cerrar();
                    imodalProducto = new UpdateProducto();
                    imodalProducto.getIdField().setText(String.valueOf(p2.getId()));
                    imodalProducto.getNombreField().setText(p2.getNombre());
                    imodalProducto.getPventaField().setText(String.valueOf(p2.getPrecioVenta()));
                    imodalProducto.getPcompraField().setText(String.valueOf(p2.getPrecioCompra()));
                    imodalProducto.getCategoriaBox().removeAllItems();
                    for(Categoria c: Categoria.values()){
                        imodalProducto.getCategoriaBox().addItem(c.name());
                    }
                    imodalProducto.getCategoriaBox().setSelectedItem(p2.getCategoria().name());
                    imodalProducto.setPresentador(this);
                    imodalProducto.iniciar();
                }
            }
        }else if(e.getActionCommand().equals(imoduloProducto.ELIMINAR) || e.getActionCommand().equals(imoduloProducto.ELIMINAR2)){
            Producto p = null, p2 = null;
            if(!id.equals("")){
                p = productoInformation.buscar(Integer.parseInt(id));
            }else if(!id2.equals("")){
                p2 = productoInformation.buscar(Integer.parseInt(id2));
            }

            if(p != null){
                productoInformation.eliminar(p);
            } else if(p2 != null){
                productoInformation.eliminar(p2);
            }
            llenarTablaProductoAlimento();
            llenarTablaProductoPersonal();
        }else if(e.getActionCommand().equals(imoduloProducto.REGRESAR) || e.getActionCommand().equals(imoduloProducto.REGRESAR2)){
            imoduloProducto.cerrar();
        }else if(e.getActionCommand().equals(imodalProducto.GUARDAR)){
            String nombreProducto = imodalProducto.getNombreField().getText();
            double precioV = Double.parseDouble(imodalProducto.getPventaField().getText());
            double precioC = Double.parseDouble(imodalProducto.getPcompraField().getText());
            String ctg = String.valueOf(imodalProducto.getCategoriaBox().getSelectedItem());
            Categoria categoria;
            if(ctg.equals("ALIMENTOS")){
                categoria = Categoria.ALIMENTOS;
            }else{
                categoria = Categoria.USO_PERSONAL;
            }
            if(!nombreProducto.equals("") && precioV > 0 && precioC > 0 && !ctg.equals("")){
                productoInformation.crear(new Producto(nombreProducto, 0, precioV, precioC, categoria));
                imodalProducto.getNombreField().setText("");
                imodalProducto.getPcompraField().setText("");
                imodalProducto.getPventaField().setText("");
            }
        }else if(e.getActionCommand().equals(imodalProducto.REGRESAR_MODULO)){
            cerrarModalProducto();
            this.iniciarModuloProducto(this);
        }else if(e.getActionCommand().equals(imodalProducto.ACTUALIZAR)){
            int idProducto = Integer.parseInt(imodalProducto.getIdField().getText());
            String nombreProducto = imodalProducto.getNombreField().getText();
            double precioV = Double.parseDouble(imodalProducto.getPventaField().getText());
            double precioC = Double.parseDouble(imodalProducto.getPcompraField().getText());
            String ctg = String.valueOf(imodalProducto.getCategoriaBox().getSelectedItem());
            Categoria categoria;
            if(ctg.equals("ALIMENTOS")){
                categoria = Categoria.ALIMENTOS;
            }else{
                categoria = Categoria.USO_PERSONAL;
            }
            
            if(!nombreProducto.equals("") && precioV > 0 && precioC > 0 && !ctg.equals("")){
                Producto producto = new Producto();
                producto.setId(idProducto);
                producto.setNombre(nombreProducto);
                producto.setPrecioVenta(precioV);
                producto.setPrecioCompra(precioC);
                producto.setCategoria(categoria);
                
                productoInformation.actualizar(producto);
            }
            cerrarModalProducto();
            this.iniciarModuloProducto(this);
        }
    }
}
