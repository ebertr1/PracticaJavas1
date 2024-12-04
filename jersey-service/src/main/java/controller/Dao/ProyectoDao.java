package controller.Dao;


import models.Proyecto;

import java.util.Arrays;

import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;

@SuppressWarnings("unused")
public class ProyectoDao<E> extends AdapterDao<Proyecto> {
    private Proyecto proyecto; 
    private LinkedList<Proyecto> listAll;

    public ProyectoDao() {
        super(Proyecto.class);
        this.listAll = new LinkedList<>();
    }

    public Proyecto getProyecto() {
        if (proyecto == null) {
            proyecto = new Proyecto();
        }
        return this.proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LinkedList<Proyecto> getlistAll() {
        // Asegúrate de que `listAll()` devuelva una lista completa y correcta
        if (listAll == null || listAll.isEmpty()) {
            this.listAll = listAll(); // Verifica que listAll() funcione como se espera
        }
        return listAll;
    }

    
    public Boolean save() throws Exception {
        // Asegúrate de que `proyecto` esté inicializado
        if (proyecto == null) {
            throw new NullPointerException("El objeto 'proyecto' no ha sido inicializado.");
        }

        // Asignar un ID único
        Integer id = getlistAll().getSize() + 1;
        proyecto.setIdProyecto(id);

        // Guarda el proyecto
        this.persist(proyecto);

        // Actualiza la lista con los nuevos datos
        this.listAll = getlistAll();
        return true;
    }




    public Boolean update() throws Exception {
        try {
            if (proyecto == null) {
                throw new NullPointerException("El objeto 'proyecto' no ha sido inicializado.");
            }
            // Actualiza el proyecto existente
            this.merge(getProyecto(), getProyecto().getIdProyecto() - 1);
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Proyecto> list = getlistAll();
        Proyecto proyecto = get(id);
        if (proyecto != null) {
            list.remove(proyecto);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Proyecto con id " + id + " no encontrado.");
            return false;
        }
    }
    

        
    private Boolean verify(Proyecto a, Proyecto b, Integer type_order, String atributo) {
        if (a == null || b == null) return false;
        
        if (type_order == 1) { // Ascendente
            switch(atributo.toLowerCase()) {
                case "nombre":
                    return a.getNombre() != null && b.getNombre() != null && 
                            a.getNombre().compareToIgnoreCase(b.getNombre()) > 0;
                case "inversionistas":
                    return a.getInversionistas() != null && b.getInversionistas() != null && 
                            a.getInversionistas().compareToIgnoreCase(b.getInversionistas()) > 0;
                case "ubicacion":
                    return a.getUbicacion() != null && b.getUbicacion() != null && 
                            a.getUbicacion().compareToIgnoreCase(b.getUbicacion()) > 0;
                default:
                    return false;
            }
        } else { // Descendente
            switch(atributo.toLowerCase()) {
                case "nombre":
                    return a.getNombre() != null && b.getNombre() != null && 
                            a.getNombre().compareToIgnoreCase(b.getNombre()) < 0;
                case "inversionistas":
                    return a.getInversionistas() != null && b.getInversionistas() != null && 
                            a.getInversionistas().compareToIgnoreCase(b.getInversionistas()) < 0;
                case "ubicacion":
                    return a.getUbicacion() != null && b.getUbicacion() != null && 
                            a.getUbicacion().compareToIgnoreCase(b.getUbicacion()) < 0;
                default:
                    return false;
            }
        }
    }

    public LinkedList<Proyecto> buscar_nombre(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll();
        
        if (!listita.isEmpty() && texto != null) {
            Proyecto[] aux = listita.toArray();
            for (Proyecto proyecto : aux) {
                if (proyecto != null && proyecto.getNombre() != null && 
                    proyecto.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(proyecto);
                }
            }
        }
        return lista;
    }



    public LinkedList<Proyecto> buscar_inversionistas(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll();
        
        if (!listita.isEmpty() && texto != null) {
            Proyecto[] aux = listita.toArray();
            for (Proyecto proyecto : aux) {
                if (proyecto != null && proyecto.getInversionistas() != null && 
                    proyecto.getInversionistas().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(proyecto);
                }
            }
        }
        return lista;
    }


    public LinkedList<Proyecto> buscar_ubicacion(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll();
        
        if (!listita.isEmpty() && texto != null) {
            Proyecto[] aux = listita.toArray();
            for (Proyecto proyecto : aux) {
                if (proyecto != null && proyecto.getUbicacion() != null && 
                    proyecto.getUbicacion().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(proyecto);
                }
            }
        }
        return lista;
    }

    // quick
    public LinkedList<Proyecto> orderQuick(Integer type_order, String atributo) {
        LinkedList<Proyecto> listita = new LinkedList<>();
        
        // Crear una nueva lista con los elementos actuales
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (!listita.isEmpty()) {
            Proyecto[] lista = (Proyecto[]) listita.toArray(); // Convertir la lista enlazada a un arreglo
            quicksort(lista, 0, lista.length - 1, type_order, atributo); // Aplicar QuickSort
            listita = new LinkedList<>(); // Reiniciar la lista enlazada
            listita.toList(lista); // Convertir el arreglo ordenado de vuelta a la lista enlazada
        }
        
        return listita;
    }
    
    // Método QuickSort para un arreglo de proyectos
    private void quicksort(Proyecto[] lista, int low, int high, Integer type_order, String atributo) {
        if (low < high) {
            int pivotIndex = partition(lista, low, high, type_order, atributo); // Particionar el arreglo
            quicksort(lista, low, pivotIndex - 1, type_order, atributo); // Ordenar la parte izquierda
            quicksort(lista, pivotIndex + 1, high, type_order, atributo); // Ordenar la parte derecha
        }
    }
    
    // Método de partición para el QuickSort
    private int partition(Proyecto[] lista, int low, int high, Integer type_order, String atributo) {
        Proyecto pivot = lista[high]; // Elegir el último elemento como pivote
        int i = low - 1; // Índice del menor elemento
    
        for (int j = low; j < high; j++) {
            if (verify(lista[j], pivot, type_order, atributo)) {
                i++;
                Proyecto temp = lista[i];
                lista[i] = lista[j];
                lista[j] = temp;
            }
        }
    
        // Colocar el pivote en su lugar correcto
        Proyecto temp = lista[i + 1];
        lista[i + 1] = lista[high];
        lista[high] = temp;
    
        return i + 1;
    }
    
    





    public LinkedList<Proyecto> orderMerge(Integer type_order, String atributo) {
        LinkedList<Proyecto> listita = new LinkedList<>();
    
        // Crear una nueva lista con los elementos actuales
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        if (!listita.isEmpty()) {
            Proyecto[] lista = (Proyecto[]) listita.toArray(); // Convertir a arreglo
            lista = mergesort(lista, atributo, type_order); // Aplicar MergeSort
            listita = new LinkedList<>(); // Reiniciar la lista enlazada
            listita.toList(lista); // Convertir el arreglo ordenado de vuelta a la lista enlazada
        }
    
        return listita;
    }
    
    // Método MergeSort para ordenar un arreglo de proyectos
    private Proyecto[] mergesort(Proyecto[] lista, String atributo, Integer type_order) {
        if (lista.length <= 1) {
            return lista;
        }
    
        int middle = lista.length / 2;
        Proyecto[] left = Arrays.copyOfRange(lista, 0, middle); // Dividir en parte izquierda
        Proyecto[] right = Arrays.copyOfRange(lista, middle, lista.length); // Dividir en parte derecha
    
        left = mergesort(left, atributo, type_order); // Ordenar la parte izquierda
        right = mergesort(right, atributo, type_order); // Ordenar la parte derecha
    
        return merge(left, right, atributo, type_order); // Combinar las partes ordenadas
    }
    
    // Método para combinar dos subarreglos ordenados
    private Proyecto[] merge(Proyecto[] left, Proyecto[] right, String atributo, Integer type_order) {
        Proyecto[] result = new Proyecto[left.length + right.length];
        int i = 0, j = 0, k = 0;
    
        while (i < left.length && j < right.length) {
            if (verify(left[i], right[j], type_order, atributo)) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
    
        while (i < left.length) {
            result[k++] = left[i++];
        }
    
        while (j < right.length) {
            result[k++] = right[j++];
        }
    
        return result;
    }
    

    
    public LinkedList<Proyecto> orderShell(Integer type_order, String atributo) {
        LinkedList<Proyecto> listita = new LinkedList<>();
    
        // Crear una nueva lista con los elementos actuales
        for (int i = 0; i < listAll().getSize(); i++) {
            try {
                listita.add(listAll().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        if (!listita.isEmpty()) {
            Proyecto[] lista = (Proyecto[]) listita.toArray(); // Convertir a arreglo
            lista = shellSort(lista, atributo, type_order); // Aplicar Shell Sort
            listita = new LinkedList<>(); // Reiniciar la lista enlazada
            listita.toList(lista); // Convertir el arreglo ordenado de vuelta a la lista enlazada
        }
    
        return listita;
    }
    
    // Método Shell Sort para ordenar un arreglo de proyectos
    private Proyecto[] shellSort(Proyecto[] lista, String atributo, Integer type_order) {
        int n = lista.length;
    
        // Inicialización del intervalo de Shell Sort
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Proyecto temp = lista[i];
                int j;
    
                // Comparar elementos en el intervalo
                for (j = i; j >= gap && verify(lista[j - gap], temp, type_order, atributo); j -= gap) {
                    lista[j] = lista[j - gap];
                }
                lista[j] = temp;
            }
        }
        return lista;
    }
    

    public LinkedList<Proyecto> buscarNombreLineal(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll(); // Supone que listAll() devuelve todos los proyectos.
    
        if (!listita.isEmpty() && texto != null) {
            for (Proyecto proyecto : listita.toArray()) {
                if (proyecto != null && proyecto.getNombre() != null &&
                    proyecto.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(proyecto);
                }
            }
        }
        return lista;
    }

    public LinkedList<Proyecto> buscarNombreBinario(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll();
    
        if (!listita.isEmpty() && texto != null) {
            // Convertir la lista enlazada manualmente a un array.
            Proyecto[] aux = new Proyecto[listita.getSize()];
            int index = 0;
            for (Proyecto proyecto : listita.toArray()) {
                aux[index++] = proyecto;
            }
    
            // Realizar la búsqueda binaria.
            int izquierda = 0;
            int derecha = aux.length - 1;
    
            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                Proyecto proyectoMedio = aux[medio];
    
                if (proyectoMedio != null && proyectoMedio.getNombre() != null) {
                    String nombreMedio = proyectoMedio.getNombre().toLowerCase();
    
                    if (nombreMedio.contains(texto.toLowerCase())) {
                        lista.add(proyectoMedio);
                        // Expandir hacia ambos lados en caso de múltiples coincidencias.
                        int i = medio - 1;
                        while (i >= 0 && aux[i].getNombre().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[i]);
                            i--;
                        }
                        int j = medio + 1;
                        while (j < aux.length && aux[j].getNombre().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[j]);
                            j++;
                        }
                        break;
                    } else if (nombreMedio.compareTo(texto.toLowerCase()) < 0) {
                        izquierda = medio + 1;
                    } else {
                        derecha = medio - 1;
                    }
                } else {
                    break;
                }
            }
        }
        return lista;
    }



    public LinkedList<Proyecto> buscarInversionistasLineal(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll(); // Supone que listAll() devuelve todos los proyectos.
    
        if (!listita.isEmpty() && texto != null) {
            for (Proyecto proyecto : listita.toArray()) {
                if (proyecto != null && proyecto.getInversionistas() != null &&
                    proyecto.getInversionistas().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(proyecto);
                }
            }
        }
        return lista;
    }

    public LinkedList<Proyecto> buscarInversionistaBinario(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll();
    
        if (!listita.isEmpty() && texto != null) {
            // Convertir la lista enlazada manualmente a un array.
            Proyecto[] aux = new Proyecto[listita.getSize()];
            int index = 0;
            for (Proyecto proyecto : listita.toArray()) {
                aux[index++] = proyecto;
            }
    
            // Realizar la búsqueda binaria.
            int izquierda = 0;
            int derecha = aux.length - 1;
    
            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                Proyecto proyectoMedio = aux[medio];
    
                if (proyectoMedio != null && proyectoMedio.getInversionistas() != null) {
                    String nombreMedio = proyectoMedio.getInversionistas().toLowerCase();
    
                    if (nombreMedio.contains(texto.toLowerCase())) {
                        lista.add(proyectoMedio);
                        // Expandir hacia ambos lados en caso de múltiples coincidencias.
                        int i = medio - 1;
                        while (i >= 0 && aux[i].getInversionistas().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[i]);
                            i--;
                        }
                        int j = medio + 1;
                        while (j < aux.length && aux[j].getInversionistas().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[j]);
                            j++;
                        }
                        break;
                    } else if (nombreMedio.compareTo(texto.toLowerCase()) < 0) {
                        izquierda = medio + 1;
                    } else {
                        derecha = medio - 1;
                    }
                } else {
                    break;
                }
            }
        }
        return lista;
    }
    

    public LinkedList<Proyecto> buscarUbicacionLineal(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll(); // Supone que listAll() devuelve todos los proyectos.
    
        if (!listita.isEmpty() && texto != null) {
            for (Proyecto proyecto : listita.toArray()) {
                if (proyecto != null && proyecto.getUbicacion() != null &&
                    proyecto.getUbicacion().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(proyecto);
                }
            }
        }
        return lista;
    }

    public LinkedList<Proyecto> buscarUbicacionBinario(String texto) {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = listAll();
    
        if (!listita.isEmpty() && texto != null) {
            // Convertir la lista enlazada manualmente a un array.
            Proyecto[] aux = new Proyecto[listita.getSize()];
            int index = 0;
            for (Proyecto proyecto : listita.toArray()) {
                aux[index++] = proyecto;
            }
    
            // Realizar la búsqueda binaria.
            int izquierda = 0;
            int derecha = aux.length - 1;
    
            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                Proyecto proyectoMedio = aux[medio];
    
                if (proyectoMedio != null && proyectoMedio.getUbicacion() != null) {
                    String nombreMedio = proyectoMedio.getUbicacion().toLowerCase();
    
                    if (nombreMedio.contains(texto.toLowerCase())) {
                        lista.add(proyectoMedio);
                        // Expandir hacia ambos lados en caso de múltiples coincidencias.
                        int i = medio - 1;
                        while (i >= 0 && aux[i].getUbicacion().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[i]);
                            i--;
                        }
                        int j = medio + 1;
                        while (j < aux.length && aux[j].getUbicacion().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[j]);
                            j++;
                        }
                        break;
                    } else if (nombreMedio.compareTo(texto.toLowerCase()) < 0) {
                        izquierda = medio + 1;
                    } else {
                        derecha = medio - 1;
                    }
                } else {
                    break;
                }
            }
        }
        return lista;
    }
    
  

    

}
