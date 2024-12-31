package dominio;

import datos.AbstractDaoFactory;
import datos.Dao;
import datos.FactoryType;
import java.util.List;

public class BusinessEmpleado {
    Dao<Empleado> empleadoDAO = AbstractDaoFactory.getFactory(FactoryType.MySQL_DAO_FACTORY).getEmpleadoDAO();
    
    public Empleado login(String username, String clave){
        List<Empleado> listaEmpleados = empleadoDAO.listado();
        for(Empleado e: listaEmpleados){
            if(username.equals(e.getUsername()) && clave.equals(e.getClave())){
                return e;
            }
        }
        return null;
    }
}
