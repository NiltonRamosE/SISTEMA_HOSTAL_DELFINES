package dominio;

import datos.AbstractDaoFactory;
import datos.Dao;
import datos.FactoryType;
import datos.RegistroReservaDaoMySQL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BusinessReserva {
    Dao<Reserva> reservaDAO = AbstractDaoFactory.getFactory(FactoryType.MySQL_DAO_FACTORY).getReservaDAO();
    
    public Reserva crear(Reserva obj){
        return reservaDAO.crear(obj);
    }
    
    public Reserva actualizar(Reserva obj) {
        return reservaDAO.actualizar(obj);
    }
    
    public List<Reserva> listado() {
        return reservaDAO.listado();
    }
    
    public List<Reserva> listadoFecha(String fechaSeleccionada) {
        List<Reserva> reservasFiltradas = new ArrayList<>();
        List<Reserva> todasLasReservas = listado();
        for (Reserva reserva : todasLasReservas) {
            int idRegistroReserva = reserva.getRegistroreserva_id();
            RegistroReserva registroReserva = obtenerRegistroReservaPorId(idRegistroReserva);
            if (registroReserva != null && registroReserva.getFecha().equals(fechaSeleccionada)) {
                reservasFiltradas.add(reserva);
            }
        }

        return reservasFiltradas;
    }

    private RegistroReserva obtenerRegistroReservaPorId(int idRegistroReserva) {
        RegistroReservaDaoMySQL registroReservaDao = new RegistroReservaDaoMySQL();
        return registroReservaDao.buscar(idRegistroReserva);
    }
    
    public Reserva eliminar(Reserva obj) {
        return reservaDAO.eliminar(obj);
    }
    
    public Reserva buscar(int id) {
        return reservaDAO.buscar(id);
    }
    
    
}
