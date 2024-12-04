import controller.tda.list.LinkedList;
import java.util.Random;

public class AMain {
    public static void main(String[] args) {
        // Arreglo de tamaños
        int[] sizes = {10000, 20000, 25000};
        
        for (int size : sizes) {
            // Crear lista aleatoria
            LinkedList<Integer> list = generateRandomList(size);
            
            // Medir el tiempo de QuickSort
            long startTime = System.nanoTime();
            list.quicksort(0); // Asumiendo que 0 es el tipo de orden
            long endTime = System.nanoTime();
            long quickSortTime = endTime - startTime;
            
            // Crear nueva lista para MergeSort
            list = generateRandomList(size);
            startTime = System.nanoTime();
            list.mergesort(0); // Asumiendo que 0 es el tipo de orden
            endTime = System.nanoTime();
            long mergeSortTime = endTime - startTime;
            
            // Crear nueva lista para ShellSort
            list = generateRandomList(size);
            startTime = System.nanoTime();
            list.shellsort(0, 0); // Asumiendo que 0 es el tipo de orden
            endTime = System.nanoTime();
            long shellSortTime = endTime - startTime;

            // Mostrar los tiempos
            System.out.println("Tamaño: " + size);
            System.out.println("QuickSort: " + quickSortTime + " ns");
            System.out.println("MergeSort: " + mergeSortTime + " ns");
            System.out.println("ShellSort: " + shellSortTime + " ns");
            System.out.println();
        }
    }

    // Método para generar una lista aleatoria de números enteros
    private static LinkedList<Integer> generateRandomList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            list.add(rand.nextInt());
        }
        return list;
    }
}
