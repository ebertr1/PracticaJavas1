package controller.Dao.implement;

public class CustomArrayList<T> {
    private Object[] elements; // Array para almacenar elementos
    private int size; // Número de elementos actuales en la lista

    // Constructor con tamaño inicial
    public CustomArrayList() {
        elements = new Object[10]; // Tamaño inicial
        size = 0;
    }

    // Método para agregar un elemento al final
    public void addLast(T element) {
        ensureCapacity(); // Asegurarse de que hay espacio suficiente
        elements[size++] = element;
    }

    // Método para obtener un elemento por su índice
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        return (T) elements[index];
    }

    // Método para editar un elemento en un índice dado
    public void edit(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        elements[index] = element;
    }

    // Método para eliminar un elemento por su índice
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1]; // Desplazar elementos hacia la izquierda
        }
        elements[--size] = null; // Eliminar la referencia al último elemento
    }

    // Método para obtener el número de elementos en la lista
    public int size() {
        return size;
    }

    // Método para convertir `CustomArrayList` a un array
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size]; // Crear un nuevo array del tamaño correcto
        for (int i = 0; i < size; i++) {
            array[i] = (T) elements[i];
        }
        return array;
    }

    // Método privado para asegurar la capacidad del array
    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[size * 2]; // Duplicar el tamaño del array
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i]; // Copiar elementos al nuevo array
            }
            elements = newElements;
        }
    }
}
