// import controller.tda.stack.Stack;
// import controller.tda.list.ListEmptyException;

// import java.util.Scanner;

// public class MainStack {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.print("Ingrese el tamaño máximo de la pila: ");
//         int maxSize = scanner.nextInt();

//         Stack<Integer> stack = new Stack<>(maxSize);

//         while (true) {
//             System.out.println("\n--- Menú ---");
//             System.out.println("1. Agregar elemento (push)");
//             System.out.println("2. Eliminar elemento (pop)");
//             System.out.println("3. Mostrar tamaño de la pila");
//             System.out.println("4. Salir");
//             System.out.print("Seleccione una opción: ");
//             int option = scanner.nextInt();

//             switch (option) {
//                 case 1: // Push
//                     System.out.print("Ingrese un número para agregar a la pila: ");
//                     int elementToAdd = scanner.nextInt();
//                     try {
//                         stack.push(elementToAdd);
//                         System.out.println("Elemento agregado: " + elementToAdd);
//                     } catch (ListEmptyException e) {
//                         System.out.println(e.getMessage());
//                     }
//                     break;

//                 case 2: // Pop
//                     try {
//                         int removedElement = stack.pop();
//                         System.out.println("Elemento eliminado: " + removedElement);
//                     } catch (ListEmptyException e) {
//                         System.out.println(e.getMessage());
//                     }
//                     break;

//                 case 3: // Mostrar tamaño
//                     System.out.println("Tamaño de la pila: " + stack.getSize());
//                     break;

//                 case 4: // Salir
//                     System.out.println("Saliendo...");
//                     scanner.close();
//                     return;

//                 default:
//                     System.out.println("Opción no válida, intente de nuevo.");
//             }
//         }
//     }
// }


import controller.tda.stack.Stack;
import controller.tda.list.ListEmptyException;

public class MainStack {
    // Códigos de color ANSI
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        // Se define un tamaño máximo para la pila
        int maxSize = 5; // Este valor puede ajustarse según sea necesario

        // Se crea la pila
        Stack<Integer> stack = new Stack<>(maxSize);

        // Se prueban los métodos de la pila
        try {
            // Se agregan elementos (push)
            System.out.println(GREEN + "Agregando elementos a la pila:" + RESET);
            String[] nombres = {"Pierre", "Jean", "Marie", "Luc", "Sophie"};
            for (String nombre : nombres) {
                stack.push(nombre.length()); // Se usa la longitud del nombre como valor
                System.out.println(BLUE + "Elemento agregado: " + nombre + RESET);
            }

            // Se muestra el tamaño de la pila
            System.out.println(YELLOW + "Tamaño de la pila: " + stack.getSize() + RESET);

            // Se obtiene el elemento en la parte superior
            System.out.println(CYAN + "Elemento en la parte superior: " + stack.top() + RESET);

            // Se eliminan elementos (pop)
            System.out.println(GREEN + "Eliminando elementos de la pila:" + RESET);
            for (int i = 0; i < maxSize; i++) {
                int removedElement = stack.pop();
                System.out.println(RED + "Elemento eliminado: " + removedElement + RESET);
            }

            // Se intenta obtener el elemento en la parte superior de una pila vacía
            try {
                System.out.println(YELLOW + "Intentando obtener el elemento en la parte superior de una pila vacía:" + RESET);
                System.out.println(CYAN + "Elemento en la parte superior: " + stack.top() + RESET);
            } catch (ListEmptyException e) {
                System.out.println(RED + "Error: " + e.getMessage() + RESET);
            }

        } catch (ListEmptyException e) {
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        }

        // Se limpia la pila
        stack.clear();
        System.out.println(GREEN + "La pila ha sido limpiada. Tamaño actual: " + stack.getSize() + RESET);
    }
}