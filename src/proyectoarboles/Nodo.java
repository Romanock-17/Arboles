package proyectoarboles;

public class Nodo {

    Nodo hijoDerecho;
    Nodo hijoIzquierdo;
    String dato;

    public Nodo(String dato) {
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
        this.dato = dato;
    }

    public Nodo() {
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
        this.dato = "";
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String Dato) {
        this.dato = Dato;
    }

}
