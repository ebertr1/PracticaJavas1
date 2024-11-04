public class DynamicArrayList {
    private CustomArrayList<String> list;

    public DynamicArrayList() {
        list = new CustomArrayList<>();
    }

    public void add(String element) {
        list.addLast(element);
    }

    public String get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}