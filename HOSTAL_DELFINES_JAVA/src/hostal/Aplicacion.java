package hostal;

import presentacion.Presentador;

public class Aplicacion {
    public static void main(String[] args) {
        Presentador presentador = new Presentador();
        presentador.iniciarLogin(presentador);
    }
}
