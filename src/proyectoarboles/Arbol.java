package proyectoarboles;

import java.util.Scanner;

public class Arbol {

    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void insertarDerecho(Nodo nodo, String dato) {
        if (nodo != null) {
            Nodo nuevoNodo = new Nodo(dato);
            nodo.setHijoDerecho(nuevoNodo);
            System.out.println("+" + dato);
        } else {
            System.out.println("No se puede insertar en un nodo nulo.");
        }
    }

    public void insertarIzquierdo(Nodo nodo, String dato) {
        if (nodo != null) {
            Nodo nuevoNodo = new Nodo(dato);
            nodo.setHijoIzquierdo(nuevoNodo);
            System.out.println("-" + dato);
        } else {
            System.out.println("No se puede insertar en un nodo nulo.");
        }
    }

    public static boolean Hoja(Nodo nodo) {
        return nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null;
    }


    public void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            preorden(nodo.getHijoIzquierdo());
            preorden(nodo.getHijoDerecho());
        }
    }

    public void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.getHijoIzquierdo());
            System.out.print(nodo.getDato() + " ");
            inorden(nodo.getHijoDerecho());
        }
    }

    public void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.getHijoIzquierdo());
            postorden(nodo.getHijoDerecho());
            System.out.print(nodo.getDato() + " ");
        }
    }

    public int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            return (contarNodos(nodo.getHijoIzquierdo()) + 1 + contarNodos(nodo.getHijoDerecho()));
        }
    }

    public void eliminarNodo(Nodo padre, Nodo actual, String datoEliminar) {
        if (actual == null) {
            return;
        }
        if (actual.getDato().equals(datoEliminar)) {
            if (actual.getHijoIzquierdo() == null && actual.getHijoDerecho() == null) {
                if (padre == null) {
                    raiz = null;
                } else if (padre.getHijoIzquierdo() == actual) {
                    padre.setHijoIzquierdo(null);
                } else {
                    padre.setHijoDerecho(null);
                }
            } else if (actual.getHijoIzquierdo() == null) {
                if (padre == null) {
                    raiz = actual.getHijoDerecho();
                } else if (padre.getHijoIzquierdo() == actual) {
                    padre.setHijoIzquierdo(actual.getHijoDerecho());
                } else {
                    padre.setHijoDerecho(actual.getHijoDerecho());
                }
            } else if (actual.getHijoDerecho() == null) {
                if (padre == null) {
                    raiz = actual.getHijoIzquierdo();
                } else if (padre.getHijoIzquierdo() == actual) {
                    padre.setHijoIzquierdo(actual.getHijoIzquierdo());
                } else {
                    padre.setHijoDerecho(actual.getHijoIzquierdo());
                }
            } else {
                Nodo sucesor = encontrarSucesor(actual);
                eliminarNodo(actual, sucesor, sucesor.getDato());
                actual.setDato(sucesor.getDato());
            }
        } else if (actual.getDato().compareTo(datoEliminar) > 0) {
            eliminarNodo(actual, actual.getHijoIzquierdo(), datoEliminar);
        } else {
            eliminarNodo(actual, actual.getHijoDerecho(), datoEliminar);
        }
    }


    public void insertarNodoBinario(Nodo nodo, char dato) {
        if (nodo == null) {
            return;
        }
        if (dato < nodo.getDato().charAt(0)) {
            if (nodo.getHijoIzquierdo() == null) {
                nodo.setHijoIzquierdo(new Nodo(String.valueOf(dato)));
            } else {
                insertarNodoBinario(nodo.getHijoIzquierdo(), dato);
            }
        } else {
            if (nodo.getHijoDerecho() == null) {
                nodo.setHijoDerecho(new Nodo(String.valueOf(dato)));
            } else {
                insertarNodoBinario(nodo.getHijoDerecho(), dato);
            }
        }
    }

    public void mostrarArbol(Nodo nodo, int espacio) {
        if (nodo == null) {
            return;
        }
        espacio += 10;

        mostrarArbol(nodo.getHijoDerecho(), espacio);

        System.out.print("\n");
        for (int i = 10; i < espacio; i++) {
            System.out.print(" ");
        }
        System.out.print(nodo.getDato() + "\n");

        // Procesa el nodo izquierdo
        mostrarArbol(nodo.getHijoIzquierdo(), espacio);
    }


    public int mostrarHojas(Nodo nodo) {
        int cont = 0;
        if (nodo != null) {
            if (nodo.getHijoDerecho() == null && nodo.getHijoIzquierdo() == null) {
                System.out.println("Hoja: " + nodo.getDato() + " ");
                cont++;
            }
            cont += mostrarHojas(nodo.getHijoIzquierdo());
            cont += mostrarHojas(nodo.getHijoDerecho());
        }
        return cont;
    }

    public int mostrarPadres(Nodo nodo) {
        int cont = 0;
        if (nodo != null) {
            if (!Hoja(nodo)) {
                System.out.println("Nodo padre: " + nodo.getDato());
                cont++;
            }
            cont += mostrarPadres(nodo.getHijoIzquierdo());
            cont += mostrarPadres(nodo.getHijoDerecho());
        }
        return cont;
    }

    public void mostrarHijos(Nodo nodo) {
        int total = 0;
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null || nodo.getHijoDerecho() != null) {
                if (nodo.getHijoIzquierdo() != null) {
                    System.out.println("Nodo hijo izquierdo: " + nodo.getHijoIzquierdo().getDato());
                    total++;
                }
                if (nodo.getHijoDerecho() != null) {
                    System.out.println("Nodo hijo derecho: " + nodo.getHijoDerecho().getDato());
                    total++;
                }
            }
            mostrarHijos(nodo.getHijoIzquierdo());
            mostrarHijos(nodo.getHijoDerecho());
        }
    }

    public int nivelDato(Nodo nodo, String dato, int nivel) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.getDato().equals(dato)) {
            return nivel;
        }
        int nivelIzquierdo = nivelDato(nodo.getHijoIzquierdo(), dato, nivel + 1);
        if (nivelIzquierdo != -1) {
            return nivelIzquierdo;
        }
        return nivelDato(nodo.getHijoDerecho(), dato, nivel + 1);
    }

    public int alturaDato(Nodo nodo, String dato) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.getDato().equals(dato)) {
            return altura(nodo);
        }
        int alturaIzquierdo = alturaDato(nodo.getHijoIzquierdo(), dato);
        if (alturaIzquierdo != -1) {
            return alturaIzquierdo;
        }
        return alturaDato(nodo.getHijoDerecho(), dato);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int alturaIzquierdo = altura(nodo.getHijoIzquierdo());
        int alturaDerecho = altura(nodo.getHijoDerecho());
        return Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }


    public void insertarNodo(Nodo nodo, String datoIngresar) {
        if (nodo == null) {
            System.out.println("El padre es null");
            return;
        }
        if (nodo.getDato().compareTo(datoIngresar) > 0) {
            if (nodo.getHijoIzquierdo() == null) {
                insertarIzquierdo(nodo, datoIngresar);
            } else {
                insertarNodo(nodo.getHijoIzquierdo(), datoIngresar);
            }
        } else {
            if (nodo.getHijoDerecho() == null) {
                insertarDerecho(nodo, datoIngresar);
            } else {
                insertarNodo(nodo.getHijoDerecho(), datoIngresar);
            }
        }
    }


    private Nodo encontrarSucesor(Nodo nodo) {
        Nodo sucesor = nodo.getHijoDerecho();
        while (sucesor.getHijoIzquierdo() != null) {
            sucesor = sucesor.getHijoIzquierdo();
        }
        return sucesor;
    }

    public void buscarHermanos(Nodo nodo) {
        if (nodo != null && nodo != raiz) {
            Nodo padre = mostrarPadre(raiz, nodo);
            if (padre != null) {
                System.out.println("Hermanos de " + nodo.getDato() + ":");
                if (padre.getHijoIzquierdo() != null && !padre.getHijoIzquierdo().getDato().equals(nodo.getDato())) {
                    System.out.println(padre.getHijoIzquierdo().getDato());
                }
                if (padre.getHijoDerecho() != null && !padre.getHijoDerecho().getDato().equals(nodo.getDato())) {
                    System.out.println(padre.getHijoDerecho().getDato());
                } else {
                    System.out.println("El nodo: " + nodo.getDato() + " NO tiene Hermanos");
                }
            }
        }
    }

    private Nodo mostrarPadre(Nodo actual, Nodo nodo) {
        if (actual == null || actual == nodo) {
            return null;
        }
        if ((actual.getHijoIzquierdo() != null && actual.getHijoIzquierdo() == nodo) || (actual.getHijoDerecho() != null && actual.getHijoDerecho() == nodo)) {
            return actual;
        }
        Nodo izquierdo = mostrarPadre(actual.getHijoIzquierdo(), nodo);
        if (izquierdo != null) {
            return izquierdo;
        }
        return mostrarPadre(actual.getHijoDerecho(), nodo);
    }

    public Nodo buscarNodo(Nodo nodo, String dato) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getDato().equals(dato)) {
            return nodo;
        }
        Nodo izquierda = buscarNodo(nodo.getHijoIzquierdo(), dato);
        if (izquierda != null) {
            return izquierda;
        }
        return buscarNodo(nodo.getHijoDerecho(), dato);
    }

    private boolean hermano(Nodo nodo, String datoBuscado) {
        Nodo padre = mostrarPadre(raiz, buscarNodo(raiz, datoBuscado));
        return (padre != null && (padre.getHijoIzquierdo() == nodo || padre.getHijoDerecho() == nodo));
    }

    public void buscarPrimos(Nodo nodo, String datoBuscado) {
        if (nodo != null) {
            int nivelDatoBuscado = nivelDato(raiz, datoBuscado, 0);
            boolean tienePrimos = primos(raiz, datoBuscado, nivelDatoBuscado, null);
            if (!tienePrimos) {
                System.out.println("El nodo " + datoBuscado + " no tiene primos.");
            }
        }
    }

    private boolean primos(Nodo nodo, String datoBuscado, int nivelBuscado, Nodo padre) {
        boolean tienePrimos = false;
        if (nodo != null) {
            int nivelNodo = nivelDato(raiz, nodo.getDato(), 0);
            if (nivelNodo == nivelBuscado && !nodo.getDato().equalsIgnoreCase(datoBuscado) && !hermano(nodo, datoBuscado)) {
                System.out.println("Primo de " + datoBuscado + ": " + nodo.getDato());
                tienePrimos = true;
            }
            tienePrimos |= primos(nodo.getHijoIzquierdo(), datoBuscado, nivelBuscado, padre);
            tienePrimos |= primos(nodo.getHijoDerecho(), datoBuscado, nivelBuscado, padre);
        }
        return tienePrimos;
    }

    public void mostrarAncestros(Nodo nodo, String dato) {
        if (nodo != null) {
            if (buscarNodo(nodo, dato) != null) {
                System.out.println("Ancestros de " + dato + ":");
                ancestros(raiz, dato);
            }
        }

    }

    private boolean ancestros(Nodo nodo, String dato) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getDato().equals(dato)) {
            return true;
        }
        if (ancestros(nodo.getHijoIzquierdo(), dato) || ancestros(nodo.getHijoDerecho(), dato)) {
            System.out.println(nodo.getDato());
            return true;
        }
        return false;
    }

    public Nodo rotacioSimpleDerecha(Nodo nodo) {
        Nodo aux = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(aux.getHijoDerecho());
        aux.setHijoDerecho(nodo);
        return aux;
    }

    public Nodo rotacionSimpleIzquierda(Nodo nodo) {
        Nodo aux = nodo.getHijoDerecho();
        nodo.setHijoDerecho(aux.getHijoIzquierdo());
        aux.setHijoIzquierdo(nodo);
        return aux;

    }

    public void crearArbolAVL(Scanner arbol){
        System.out.println("Ingrese los datos de los nodos separados por espacios:");
        String[] datos = arbol.nextLine().split(" ");

        raiz = new Nodo(datos[0]);
        for (int i = 1; i < datos.length; i++) {
            raiz = insertarAVL(raiz, datos[i].charAt(0));

            System.out.println("\n Recorridos del árbol AVL");
            System.out.println("\nInorden");
            inorden(raiz);
            System.out.println("\nPreorden");
            preorden(raiz);
            System.out.println("\nPostorden");
            postorden(raiz);
        }
    }

    private Nodo insertarAVL(Nodo nodo, char dato){
        if(nodo == null){
            return new Nodo(String.valueOf(dato));
        }
        if(dato < nodo.getDato().charAt(0)){
            nodo.setHijoIzquierdo(insertarAVL(nodo.getHijoIzquierdo(), dato));
        }else{
            nodo.setHijoDerecho(insertarAVL(nodo.getHijoDerecho(), dato));
        }
        int balance = balance(nodo);
        if(balance > 1 && dato < nodo.getHijoIzquierdo().getDato().charAt(0)){
            return rotacioSimpleDerecha(nodo);
        }
        if(balance < -1 && dato > nodo.getHijoDerecho().getDato().charAt(0)){
            return rotacionSimpleIzquierda(nodo);
        }
        if(balance > 1 && dato > nodo.getHijoIzquierdo().getDato().charAt(0)){
            nodo.setHijoIzquierdo(rotacionSimpleIzquierda(nodo.getHijoIzquierdo()));
            return rotacioSimpleDerecha(nodo);
        }
        if(balance < -1 && dato < nodo.getHijoDerecho().getDato().charAt(0)){
            nodo.setHijoDerecho(rotacioSimpleDerecha(nodo.getHijoDerecho()));
            return rotacionSimpleIzquierda(nodo);
        }
        return nodo;
    }


    private int balance(Nodo nodo){
        if(nodo == null){
            return 0;
        }
        return altura(nodo.getHijoIzquierdo()) - altura(nodo.getHijoDerecho());
    }

}