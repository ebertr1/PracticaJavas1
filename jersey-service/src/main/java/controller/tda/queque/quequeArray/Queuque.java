package controller.tda.queque.quequeArray;

import controller.tda.array.ArrayPositionException;

public class Queuque<E> {
    private OperationQueuque<E> queuqu;

    public Queuque(Integer top) {
        this.queuqu = new OperationQueuque<>(top);
    }

    public Integer getTop() {
        return queuqu.getSize();
    }

    public Integer getSize() {
        return queuqu.getSize();
    }

    public Boolean verify() {
        return queuqu.isFull(); 
    }

    public void queuque(E data) throws ArrayPositionException {
        queuqu.queuque(data);
    }

    public void clear() {
        queuqu.reset(); 
    }

    public E dequeuque() throws ArrayPositionException {
        return queuqu.dequeuque();
    }
}
