
public class StaticArrayList {
    private String[] elements;
    private int size;
    private final int capacity;

    public StaticArrayList(int capacity) {
        this.capacity = capacity;
        this.elements = new String[capacity];
        this.size = 0;
    }

    public void add(String element) {
        if (size < capacity) {
            elements[size++] = element;
        } else {
            throw new RuntimeException("Capacidad alcanzada");
        }
    }

    public String get(int index) {
        if (index >= 0 && index < size) {
            return elements[index];
        }
        throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }
}