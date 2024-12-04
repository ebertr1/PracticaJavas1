package pruebas;



import java.util.Arrays;
import java.util.Random;

public class pruebasBusqueda {
    public static void main(String[] args) {
        // Arreglo de tamaños
        int[] sizes = { 10000, 20000, 25000 };

        // Imprimir encabezado de la tabla
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%n", "Tamaño", "Secuencial (ns)", "Binaria (ns)", "Binaria (Ordenada) (ns)", "Secuencial Ordenado (ns)");
        System.out.println("---------------------------------------------------------------");

        for (int size : sizes) {
            // Crear arreglo con números aleatorios
            int[] array = generateRandomArray(size);

            // Medir el tiempo de búsqueda secuencial
            long startTime = System.nanoTime();
            sequentialSearch(array, array[array.length / 2]); // Buscar el valor del medio
            long endTime = System.nanoTime();
            long sequentialTime = endTime - startTime;

            // Medir el tiempo de búsqueda binaria en arreglo desordenado (no recomendado, solo para medir)
            startTime = System.nanoTime();
            binarySearch(array, array[array.length / 2]);
            endTime = System.nanoTime();
            long binaryTime = endTime - startTime;

            // Medir el tiempo de búsqueda binaria en arreglo ordenado
            Arrays.sort(array); // Ordenamos el arreglo para la búsqueda binaria
            startTime = System.nanoTime();
            binarySearch(array, array[array.length / 2]);
            endTime = System.nanoTime();
            long binarySortedTime = endTime - startTime;

            // Medir el tiempo de búsqueda secuencial en arreglo ordenado
            startTime = System.nanoTime();
            sequentialSearch(array, array[array.length / 2]);
            endTime = System.nanoTime();
            long sequentialSortedTime = endTime - startTime;

            // Mostrar los resultados en formato de tabla
            System.out.printf("%-10d%-20d%-20d%-20d%-20d%n", size, sequentialTime, binaryTime, binarySortedTime, sequentialSortedTime);
        }

        System.out.println("---------------------------------------------------------------");
    }

    // Método para generar un arreglo de números aleatorios
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt();
        }
        return array;
    }

    // Método de búsqueda secuencial
    private static int sequentialSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // No encontrado
    }

    // Método de búsqueda binaria
    private static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // No encontrado
    }
}
