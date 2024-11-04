
import controller.tda.array.CustomArrayList;

public class CustomArrayList<T> {
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        elements = new Object[10]; // Tamaño inicial
        size = 0;
    }

    public void addLast(T element) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = element;
    }

    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elements[index];
        }
        throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }
}