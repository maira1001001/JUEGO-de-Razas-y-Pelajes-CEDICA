package com.laboratorio.entrega.razasypelejesdiazduhour;

public class Dificil extends Dificultad {
    @Override
    public int obtenerLayout(Interaccion interaccion) {
        return interaccion.obtenerLayoutDificil();
    }

    @Override
    public int cantidadDeElementos() { return 4; }

    /*
        Propósito: describe el identificador de una ImageView en el layout según una posicion, que representa la ubicación dentro de los elementos a interaccionar
    */
    protected int idImageViewSegunPosicion(int posicion) {
        switch (posicion) {
            case 0:
                return R.id.imagenInteraccion0;
            case 1:
                return R.id.imagenInteraccion1;
            case 2:
                return R.id.imagenInteraccion2;
            case 3:
                return R.id.imagenInteraccion3;
            default:
                throw new IllegalArgumentException("Posicion de item inválida");
        }
    }


    /*
        Propósito: describe el identificador de una Textview en el layout según una posicion, que representa la ubicación dentro de los elementos a interaccionar
    */
    protected int idTextViewSegunPosicion(int posicion) {
        switch (posicion) {
            case 0:
                return R.id.palabraInteraccion0;
            case 1:
                return R.id.palabraInteraccion1;
            case 2:
                return R.id.palabraInteraccion2;
            case 3:
                return R.id.palabraInteraccion3;
            default:
                throw new IllegalArgumentException("Posicion de item inválida");
        }
    }

}
