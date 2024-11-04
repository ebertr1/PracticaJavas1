import controller.tda.queque.Queuque;
import controller.tda.list.ListEmptyException;

public class MainQueque {
    // Definición de códigos de color
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        // Crear una cola con un tamaño máximo de 5
        Queuque<String> queue = new Queuque<>(5);

        try {

            System.out.println(GREEN + "Agregando elementos a la pila:" + RESET);
            String[] nombres = {"Pierre", "Jean", "Marie", "Luc", "Sophie"};
            for (String nombre : nombres) {
                queue.queuque(nombre); 
                System.out.println(BLUE + "Elemento agregado: " + nombre + RESET);
            }

            try {
                queue.queuque(RED + "Elemento 6" + RESET);
            } catch (ListEmptyException e) {
                System.out.println(RED + e.getMessage() + " No se puede agregar más elementos" + RESET);
            }

            // Imprimir el tamaño de la cola
            System.out.println(YELLOW + "Tamaño de la cola: " + queue.getSize() + RESET);


            String elementoEliminado = queue.dequeuque();
            System.out.println(YELLOW + "Elemento eliminado: " + elementoEliminado + RESET);
            String elementoEliminado2 = queue.dequeuque();
            System.out.println(YELLOW + "Elemento eliminado: " + elementoEliminado2 + RESET);

            // Imprimir el tamaño de la cola después de eliminaciones
            System.out.println(YELLOW + "Tamaño de la cola después de eliminaciones: " + queue.getSize() + RESET);
            
            queue.clear();
            System.out.println(YELLOW + "Tamaño de la cola después de eliminaciones: " + queue.getSize() + RESET);


        } catch (ListEmptyException e) {
            System.out.println(RED + e.getMessage() + RESET);
        }
    }
}
