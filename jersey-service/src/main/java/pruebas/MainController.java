package pruebas;

import controller.tda.list.LinkedList;
import controller.Dao.implement.CustomArrayList;

public class MainController {
    public static void main(String[] args) {
        // Prueba del sistema con arreglos estáticos
        CustomArrayList<String> staticList = new CustomArrayList<>();
        long startTimeStatic = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            staticList.addLast("Elemento " + i); // Usar addLast en lugar de add
        }
        long endTimeStatic = System.nanoTime();
        long durationStatic = endTimeStatic - startTimeStatic;

        // Prueba del sistema con listas dinámicas
        LinkedList<String> dynamicList = new LinkedList<>();
        long startTimeDynamic = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            dynamicList.add("Elemento " + i); // Mantener esto si LinkedList tiene el método add
        }
        long endTimeDynamic = System.nanoTime();
        long durationDynamic = endTimeDynamic - startTimeDynamic;

        // Imprimir resulrrrrrrtados
        System.out.println("Tiempo con arreglos: " + durationStatic + " ns");
        System.out.println("Tiempo con listas dinámicas: " + durationDynamic + " ns");

        // Aquí puedes implementar la lógica para medir la memoria si es necesario
    }
}
