import model.DynamicArrayList;
import model.StaticArrayList;

public class MainController {
    public static void main(String[] args) {
        // Prueba del sistema con arreglos estáticos
        StaticArrayList staticList = new StaticArrayList(1000);
        long startTimeStatic = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            staticList.add("Elemento " + i);
        }
        long endTimeStatic = System.nanoTime();
        long durationStatic = endTimeStatic - startTimeStatic;

        // Prueba del sistema con listas dinámicas
        DynamicArrayList dynamicList = new DynamicArrayList();
        long startTimeDynamic = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            dynamicList.add("Elemento " + i);
        }
        long endTimeDynamic = System.nanoTime();
        long durationDynamic = endTimeDynamic - startTimeDynamic;

        // Imprimir resultados
        System.out.println("Tiempo con arreglos estáticos: " + durationStatic + " ns");
        System.out.println("Tiempo con listas dinámicas: " + durationDynamic + " ns");

        // Aquí puedes implementar la lógica para medir la memoria
    }
}