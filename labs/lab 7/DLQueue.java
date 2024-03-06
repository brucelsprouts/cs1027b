
public class DLQueue<T> implements QueueADT<T> {

    private int count;
    private DoubleNode<T> front, rear;

    public DLQueue() {
        count = 0;
        front = rear = null;
    }

    public void enqueue(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;
        }
        count++;
    }

    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is empty");
        }
        T removedElement = front.getElement();
        front = front.getNext();
        if (front == null) {
            rear = null;
        } else {
            front.setPrev(null);
        }
        count--;
        return removedElement;
    }

    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is empty");
        }
        return front.getElement();
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public String toString() {
        if (isEmpty()) {
            return "The queue is empty.";
        } else {
            StringBuilder result = new StringBuilder("Queue: ");
            DoubleNode<T> current = front;
            while (current != null) {
                result.append(current.getElement());
                if (current.getNext() != null) {
                    result.append(", ");
                } else {
                    result.append(".");
                }
                current = current.getNext();
            }
            return result.toString();
        }
    }

    public DoubleNode<T> getFront() {
        return front;
    }

    public DoubleNode<T> getRear() {
        return rear;
    }
}
