public class ReversibleArray<T> {

    private T[] array;
    private int count;

    public ReversibleArray(T[] array) {
        this.array = array;
        this.count = array.length;
    }

    public void reverse() {
        for (int i = 0; i < count / 2; i++) {
            T temp = array[i];
            array[i] = array[count - i - 1];
            array[count - i - 1] = temp;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(array[i]);
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}