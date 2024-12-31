package presentacion;

import dominio.BusinessCliente;
import dominio.BusinessHabitacion;
import dominio.BusinessRegistroReserva;
import dominio.BusinessReserva;
import dominio.Cliente;
import dominio.CostoReserva;
import dominio.Empleado;
import dominio.EstadoReserva;
import dominio.Habitacion;
import dominio.RegistroReserva;
import dominio.Reserva;
import dominio.Turno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import presentacion.vistas.AddReserva;
import presentacion.vistas.UpdateReserva;
import presentacion.vistas.VistaModuloReserva;

public class PresentadorReserva implements ActionListener {

    private IModuloReserva imoduloReserva;
    private IModalReserva imodalReserva;
    private BusinessReserva reservaInformation;
    private BusinessCliente clienteInformation;
    private BusinessHabitacion habitacionInformation;
    private BusinessRegistroReserva rreservaInformation;
    private Reserva reserva;
    private RegistroReserva rreserva;
    private Empleado empleadoSession;
    public PresentadorReserva(Empleado empleadoSession) {
        this.empleadoSession = empleadoSession;
        imoduloReserva = new VistaModuloReserva();
        reservaInformation = new BusinessReserva();
        clienteInformation = new BusinessCliente();
        habitacionInformation = new BusinessHabitacion();
        rreservaInformation = new BusinessRegistroReserva();
        //rreservaInformation.actualizarHabitacionesDispo();
        reserva = new Reserva();
    }

    public void iniciarModuloReserva(PresentadorReserva pReserva) {
        llenarTablaReservaActual();
        llenarTablaReservaHistorial();
        this.establecerTurnoyFecha();
        
        RegistroReserva rsvTemp = null;
        Turno turno = Turno.valueOf(imoduloReserva.getTurnoField().getText());
        rsvTemp = rreservaInformation.registroReservaDiario(turno);
        if(rsvTemp != null){
            rreserva = rsvTemp;
        }else{
            rsvTemp = new RegistroReserva();
            rsvTemp.setFecha(this.getFecha());
            rsvTemp.setTurno(turno);
            rsvTemp.setEmpleado(empleadoSession);
            rsvTemp.setLiquidacion(0);
            rreserva = rreservaInformation.crear(rsvTemp);
        }
        imoduloReserva.setPresentador(pReserva);
        imoduloReserva.iniciar();
    }
    
    private void establecerTurnoyFecha() {
        LocalTime horaActual = LocalTime.now();
        String turno = getTurnoByHora(horaActual);
        imoduloReserva.getTurnoField().setText(turno);
        imoduloReserva.getFechaField().setText(getFecha());
    }
    
    private void cerrarModalReserva() {
        if (imodalReserva != null) {
            imodalReserva.cerrar();
            imodalReserva = null;
        }
    }

    private void llenarTablaReservaHistorial() {
        DefaultTableModel dt = imoduloReserva.getDt();
        dt.setRowCount(0);
        String[] titulos = {"ID", "Cliente", "Nº Habitacion", "Hora Ingreso", "Hora Salida"};
        dt.setColumnIdentifiers(titulos);
        Object fila[] = new Object[5];

        for (Reserva r : reservaInformation.listado()) {

            fila[0] = r.getId();
            fila[1] = r.getCliente().getNombre() + " " + r.getCliente().getApellido();
            fila[2] = r.getHabitacion().getNumero();
            fila[3] = r.getHora_ingreso();
            fila[4] = r.getHora_salida();
            dt.addRow(fila);
        }
        imoduloReserva.getListadoTable2().setModel(dt);
        imoduloReserva.getListadoTable2().getColumnModel().getColumn(0).setPreferredWidth(10);
        imoduloReserva.getListadoTable2().setEnabled(false);
    }

    private void llenarTablaReservaActual() {
        DefaultTableModel dt = imoduloReserva.getDt();
        //Filtrar por reservas del dia o  turno
        dt.setRowCount(0);
        String[] titulos = {"ID", "Cliente", "Nº Habitacion", "Hora Ingreso", "Hora Salida"};
        dt.setColumnIdentifiers(titulos);
        Object fila[] = new Object[5];
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);

        for (Reserva r : reservaInformation.listadoFecha(fechaFormateada)) {
            fila[0] = r.getId();
            fila[1] = r.getCliente().getNombre() + " " + r.getCliente().getApellido();
            fila[2] = r.getHabitacion().getNumero();
            fila[3] = r.getHora_ingreso();
            fila[4] = r.getHora_salida();
            dt.addRow(fila);
        }
        imoduloReserva.getListadoTable().setModel(dt);
        imoduloReserva.getListadoTable().getColumnModel().getColumn(0).setPreferredWidth(10);
        imoduloReserva.getListadoTable().setDefaultEditor(Object.class, null);
        imoduloReserva.getListadoTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

    private String getFecha() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);
        return fechaFormateada;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int filaSelect = imoduloReserva.getListadoTable().getSelectedRow();
        String id = "";
        if (filaSelect >= 0) {
            id = imoduloReserva.getListadoTable().getValueAt(filaSelect, 0).toString();
        }
        if (e.getActionCommand().equals(imoduloReserva.NUEVO)) {
            nuevo();
        } else if (e.getActionCommand().equals(imoduloReserva.MODIFICAR)) {
            modificar(Integer.parseInt(id));
        } else if (e.getActionCommand().equals(imoduloReserva.ELIMINAR)) {
            eliminar(Integer.parseInt(id));
        } else if (e.getActionCommand().equals(imoduloReserva.REGRESAR)) {
            imoduloReserva.cerrar();
        } else if (e.getActionCommand().equals(imoduloReserva.REGRESAR_MODULO)) {
            this.cerrarModalReserva();
            this.iniciarModuloReserva(this);
        } else if (e.getActionCommand().equals(imoduloReserva.GUARDAR)) {
            guardar();
        } else if (e.getActionCommand().equals(imodalReserva.ACTUALIZAR)) {
            actualizar();
        } else if (e.getActionCommand().equals(imodalReserva.CANCELAR)) {
            cerrarModalReserva();
            this.iniciarModuloReserva(this);
        }else if (e.getActionCommand().equals(imodalReserva.CALCULAR)) {
            
            Habitacion habitacion = habitacionInformation.getHabitacionByNumero(Integer.parseInt(String.valueOf(imodalReserva.getHabitacionComboBox().getSelectedItem())));
            String horasReservadas = imodalReserva.getHorasReservadasField().getText();
            
            CostoReserva crsv = new CostoReserva();
            
            double costo = crsv.calcularCosto(horasReservadas, habitacion);
            imodalReserva.getCostoreservaField().setText(String.valueOf(costo));
        }else if (e.getActionCommand().equals(imodalReserva.CALCULARNEW)) {
            
            Habitacion habitacion = habitacionInformation.getHabitacionByNumero(Integer.parseInt(String.valueOf(imodalReserva.getHabitacionComboBox().getSelectedItem())));
            String horasReservadas = imodalReserva.getHorasReservadasField().getText();
            int hora_adicional = Integer.parseInt(imodalReserva.getHoraAdicionalField().getText());
            CostoReserva crsv = new CostoReserva();
            
            double costo = crsv.calcularCostoAdicional(hora_adicional, horasReservadas, habitacion);
            imodalReserva.getCostoreservaField().setText(String.valueOf(costo));
        }
    }

    private void nuevo() {
        if (imodalReserva == null) {
            imoduloReserva.cerrar();
            imodalReserva = new AddReserva();
            imodalReserva.setPresentador(this);
            imodalReserva.getTurnoField().setText(getTurnoByHora(LocalTime.now()));
            imodalReserva.getFechaField().setText(getFecha());
            for (Cliente c : clienteInformation.listado()) {
                imodalReserva.getClienteComboBox().addItem(c.getNombre() + " " + c.getApellido());
            }
            for (Habitacion h : habitacionInformation.getHabitacionesDisponibles()) {
                imodalReserva.getHabitacionComboBox().addItem(h.getNumero());
            }
            imodalReserva.iniciar();
        }
    }

    private void modificar(int id) {

        if (imodalReserva == null) {
            Reserva r = reservaInformation.buscar(id);
            if (r != null) {
                imoduloReserva.cerrar();
                imodalReserva = new UpdateReserva();
                imodalReserva.setPresentador(this);
                imodalReserva.getIdField().setText(String.valueOf(r.getId()));
                for (Habitacion h : habitacionInformation.listado()) {
                    imodalReserva.getHabitacionComboBox().addItem(h.getNumero());
                }
                imodalReserva.getHabitacionComboBox().setSelectedItem(r.getHabitacion().getNumero());
                imodalReserva.getHoraIngresoField().setText(r.getHora_ingreso());
                imodalReserva.getHorasReservadasField().setText(r.getHoras_reservadas());
                imodalReserva.getEfectivoField().setText(String.valueOf(r.getCostoEfectivo()));
                imodalReserva.getYapeField().setText(String.valueOf(r.getCostoYape()));
                for (Cliente c : clienteInformation.listado()) {
                    imodalReserva.getClienteComboBox().addItem(c.getNombre() + " " + c.getApellido());
                }
                imodalReserva.getClienteComboBox().setSelectedItem(r.getCliente().getNombre() + " " + r.getCliente().getApellido());
                imodalReserva.getNroHuespedesSpinner().setValue(r.getNumeroHuespedes());
                imodalReserva.getEstadoComboBox().setSelectedItem(r.getEstadoReserva());
                imodalReserva.iniciar();
            } else {
                JOptionPane.showMessageDialog(null, "Reserva no encontrada o fila no seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminar(int id) {
        Reserva r = reservaInformation.buscar(id);
        if (r != null) {
            reservaInformation.eliminar(r);
            llenarTablaReservaActual();
            llenarTablaReservaHistorial();
        } else {
            JOptionPane.showMessageDialog(null, "Reserva no encontrada o fila no seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardar() {

        int numero_huespedes = Integer.parseInt(String.valueOf(imodalReserva.getNroHuespedesSpinner().getValue()));
        double costoEfectivo;
        double costoYape;
        try {
            costoEfectivo = Double.parseDouble(imodalReserva.getEfectivoField().getText());
        } catch (Exception e) {
            costoEfectivo = 0;
        }
        try {
            costoYape = Double.parseDouble(imodalReserva.getYapeField().getText());
        } catch (Exception e) {
            costoYape = 0;
        }
        String horas_reservadas = imodalReserva.getHorasReservadasField().getText();
        String hora_ingreso = imodalReserva.getHoraIngresoField().getText();
        EstadoReserva estado_reserva = EstadoReserva.PENDIENTE;
        Cliente cliente = clienteInformation.getClienteByNombre(String.valueOf(imodalReserva.getClienteComboBox().getSelectedItem()));
        Habitacion habitacion = habitacionInformation.getHabitacionByNumero(Integer.parseInt(String.valueOf(imodalReserva.getHabitacionComboBox().getSelectedItem())));
        //La habitacion que acabamos de reservar ya no esta disponible
        habitacion.setDisponible(false);
        habitacionInformation.actualizar(habitacion);
        
        rreserva.setLiquidacion(rreserva.getLiquidacion()+costoEfectivo+costoYape);
        rreservaInformation.actualizar(rreserva);
        
        reserva.setNumeroHuespedes(numero_huespedes);
        reserva.setCostoEfectivo(costoEfectivo);
        reserva.setCostoYape(costoYape);
        reserva.setHoras_reservadas(horas_reservadas);
        reserva.setHora_ingreso(hora_ingreso);
        reserva.setEstadoReserva(estado_reserva);
        reserva.setCliente(cliente);
        reserva.setHabitacion(habitacion);
        reserva.setRegistroreserva_id(rreserva.getId());

        reservaInformation.crear(reserva);
        
        imodalReserva.getNroHuespedesSpinner().setValue(1);
        imodalReserva.getEfectivoField().setText("");
        imodalReserva.getYapeField().setText("");
        imodalReserva.getHorasReservadasField().setText("");
        imodalReserva.getHoraIngresoField().setText("");
        imodalReserva.getClienteComboBox().setSelectedIndex(0);
        imodalReserva.getHabitacionComboBox().setSelectedIndex(0);
        reserva = new Reserva();
    }

    private void actualizar() {
        int id = Integer.parseInt(imodalReserva.getIdField().getText());
        int numero_huespedes = Integer.parseInt(String.valueOf(imodalReserva.getNroHuespedesSpinner().getValue()));
        double costoEfectivo;
        double costoYape;
        try {
            costoEfectivo = Double.parseDouble(imodalReserva.getEfectivoField().getText());
        } catch (Exception e) {
            costoEfectivo = 0;
        }
        try {
            costoYape = Double.parseDouble(imodalReserva.getYapeField().getText());
        } catch (Exception e) {
            costoYape = 0;
        }
        String horas_reservadas = imodalReserva.getHorasReservadasField().getText();
        String hora_ingreso = imodalReserva.getHoraIngresoField().getText();
        EstadoReserva estado_reserva = EstadoReserva.valueOf(String.valueOf(imodalReserva.getEstadoComboBox().getSelectedItem()));
        Cliente cliente = clienteInformation.getClienteByNombre(String.valueOf(imodalReserva.getClienteComboBox().getSelectedItem()));
        Habitacion habitacion = habitacionInformation.getHabitacionByNumero(Integer.parseInt(String.valueOf(imodalReserva.getHabitacionComboBox().getSelectedItem())));
        
        if(estado_reserva.equals(EstadoReserva.CANCELADO) || estado_reserva.equals(EstadoReserva.CONFIRMADO)){
            habitacion.setDisponible(true);
            habitacionInformation.actualizar(habitacion);
        }
        
        Reserva r = new Reserva();
        r.setId(id);
        r.setNumeroHuespedes(numero_huespedes);
        r.setCostoEfectivo(costoEfectivo);
        r.setCostoYape(costoYape);
        r.setHoras_reservadas(horas_reservadas);
        r.setHora_ingreso(hora_ingreso);
        r.setEstadoReserva(estado_reserva);
        r.setCliente(cliente);
        r.setHabitacion(habitacion);
        r.setRegistroreserva_id(rreserva.getId());
        
        reservaInformation.actualizar(r);
        
        cerrarModalReserva();
        this.iniciarModuloReserva(this);
    }
}
