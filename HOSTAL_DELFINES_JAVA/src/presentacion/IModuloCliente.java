package presentacion;

public interface IModuloCliente extends IModulos{
    
    public static final String GUARDAR = "GUARDAR";
    public static final String REGRESAR_MODULO = "CERRAR";
    public void setPresentador(PresentadorCliente pCliente);
}
