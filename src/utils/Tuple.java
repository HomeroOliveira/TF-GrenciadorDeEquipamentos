package utils;

/**
 * Created by Junior on 20/05/2016.
 */
public class Tuple<T, O, U> {

    private final T item1;
    private final O item2;
    private final U item3;

    public Tuple(T item1, O item2, U item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public T item1() {
        return item1;
    }

    public O item2() {
        return item2;
    }

    public U item3() {
        return item3;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "item1=" + item1 +
                ", item2=" + item2 +
                ", item3=" + item3 +
                '}';
    }
}
