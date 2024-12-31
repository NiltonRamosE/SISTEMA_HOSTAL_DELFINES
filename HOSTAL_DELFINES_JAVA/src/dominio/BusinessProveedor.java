package dominio;

import datos.AbstractDaoFactory;
import datos.Dao;
import datos.FactoryType;
import java.util.List;

public class BusinessProveedor {
    Dao<Proveedor> proveedorDAO = AbstractDaoFactory.getFactory(FactoryType.MySQL_DAO_FACTORY).getProveedorDAO();
    
    public List<Proveedor> listado() {
        return proveedorDAO.listado();
    }
    
    public Proveedor getProveedorByRazonSocial(String razonSocial){
        for(Proveedor pv : proveedorDAO.listado()){
            if(pv.getRazonSocial().equals(razonSocial)){
                return pv;
            }
        }
        return null;
    }
}
