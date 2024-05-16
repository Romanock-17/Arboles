package proyectoarboles;

import java.util.Scanner;

// D B F A C E G
// M J S D K Q Z A H T B

public class MenuArbol {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arbol arbol = new Arbol();
        int opcion;
        do {
            System.out.println("\n");
            System.out.println("----- MENÚ -----");
            System.out.println("1. Ingresar árbol y ver sus recorridos");
            System.out.println("2. Eliminar nodo");
            System.out.println("3. Insertar nodo");
            System.out.println("4. Mostrar árbol");
            System.out.println("5. Mostrar padre");
            System.out.println("6. Mostrar hojas");
            System.out.println("7. Nivel de un dato ingresado");
            System.out.println("8. Altura de un dato ingresado");
            System.out.println("9. Hermanos de un dato");
            System.out.println("10. Primos de un dato");
            System.out.println("11. Ancestros de un dato");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingresar Árbol:");
                    arbol.crearArbolAVL(scanner);
                    break;
                case 2:
                    System.out.print("Ingrese nodo a eliminar: ");
                    String datoEliminar = scanner.nextLine();
                    arbol.eliminarNodo(null, arbol.getRaiz(), datoEliminar);
                    System.out.println("Árbol con nodo eliminado:");
                    arbol.mostrarArbol(arbol.getRaiz(), 0);
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo nodo: ");
                    String nuevo = scanner.nextLine();
                    arbol.insertarNodo(arbol.getRaiz(), nuevo);
                    System.out.println("Árbol con el nuevo nodo:");
                    arbol.mostrarArbol(arbol.getRaiz(), 0);
                    break;
                case 4:
                    arbol.mostrarArbol(arbol.getRaiz(), 0);
                    break;
                case 5:
                    int totalPadres = arbol.mostrarPadres(arbol.getRaiz());
                    System.out.println("Total de padres: " + totalPadres);
                    break;
                case 6:
                    int totalHojas = arbol.mostrarHojas(arbol.getRaiz());
                    System.out.println("Cantidad de hojas: " + totalHojas);
                    break;
                case 7:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato para ver su nivel: ");
                    String datoNivel = scanner.nextLine();
                    int nivel = arbol.nivelDato(arbol.getRaiz(), datoNivel, 0);
                    if (nivel != -1) {
                        System.out.println("El nivel del dato '" + datoNivel + "' es: " + nivel);
                    } else {
                        System.out.println("El dato '" + datoNivel + "' no se encuentra en el árbol.");
                    }
                    System.out.println(" ");
                    break;
                case 8:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato para ver su altura: ");
                    String datoAltura = scanner.nextLine();
                    int altura = arbol.alturaDato(arbol.getRaiz(), datoAltura);
                    if (altura != -1) {
                        System.out.println("La altura del subárbol con raíz en '" + datoAltura + "' es: " + altura);
                    } else {
                        System.out.println("El dato '" + datoAltura + "' no se encuentra en el árbol.");
                    }
                    System.out.println(" ");
                    break;
                case 9:
                    System.out.println(" ");
                    System.out.println("Ingrese el dato para encontrar sus hermanos:");
                    String datoNodo = scanner.nextLine();
                    Nodo nodoBuscar = arbol.buscarNodo(arbol.getRaiz(), datoNodo);
                    if (nodoBuscar != null) {
                        arbol.buscarHermanos(nodoBuscar);
                    } else {
                        System.out.println("El nodo con el dato " + datoNodo + " no se encontró en el árbol.");
                    }
                    System.out.println(" ");
                    break;
                case 10:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato para encotrar sus primos-hermanos: ");
                    String datoPrimos = scanner.next();
                    arbol.buscarPrimos(arbol.getRaiz(), datoPrimos);
                    System.out.println(" ");
                    break;
                case 11:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato para encontrar sus ancestros: ");
                    String datoAncestros = scanner.nextLine();
                    arbol.mostrarAncestros(arbol.getRaiz(), datoAncestros);
                    System.out.println(" ");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }
}
