/**
 * This class is used to represent a positive whole number of any number system stored in a doubly linked list such that each digit of the number is stored in a separate node.
 * 
 * @author Bruce Lin
 */
public class ArrayUniquePriorityQueue<T> implements UniquePriorityQueueADT<T> {
    private T[] queue;
    private double[] priority;
    private int count;
    
    @SuppressWarnings("unchecked")
    public ArrayUniquePriorityQueue() {
        queue = (T[]) new Object[10];
        priority = new double[10];
        count = 0;
    }

    @Override
    public void add(T data, double prio) {
        if (contains(data))
            return;
        
        if (count == queue.length)
            expandCapacity();
        
        int index = 0;
        while (index < count && prio >= priority[index])
            index++;
        
        for (int i = count; i > index; i--) {
            queue[i] = queue[i - 1];
            priority[i] = priority[i - 1];
        }
        
        queue[index] = data;
        priority[index] = prio;
        count++;
    }

    @Override
    public boolean contains(T data) {
        for (int i = 0; i < count; i++) {
            if (queue[i].equals(data))
                return true;
        }
        return false;
    }

    @Override
    public T peek() throws CollectionException {
        if (isEmpty())
            throw new CollectionException("PQ is empty");
        
        return queue[0];
    }

    @Override
    public T removeMin() throws CollectionException {
        if (isEmpty())
            throw new CollectionException("PQ is empty");
        
        T min = queue[0];
        for (int i = 1; i < count; i++) {
            queue[i - 1] = queue[i];
            priority[i - 1] = priority[i];
        }
        count--;
        return min;
    }

    @Override
    public void updatePriority(T data, double newPrio) throws CollectionException {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (queue[i].equals(data)) {
                index = i;
                break;
            }
        }

        if (index == -1)
            throw new CollectionException("Item not found in PQ");

        // Remove the item and its priority
        for (int i = index; i < count - 1; i++) {
            queue[i] = queue[i + 1];
            priority[i] = priority[i + 1];
        }
        count--;

        // Re-add the item with the new priority
        add(data, newPrio);
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    public int getLength() {
        return queue.length;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "The PQ is empty";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(queue[i]).append(" [").append(priority[i]).append("], ");
        }
        sb.setLength(sb.length() - 2); // Remove trailing comma and space
        return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] newQueue = (T[]) new Object[queue.length + 5];
        double[] newPriority = new double[priority.length + 5];
    
        // Copy elements from the old arrays to the new arrays
        for (int i = 0; i < queue.length; i++) {
            newQueue[i] = queue[i];
        }
    
        for (int i = 0; i < priority.length; i++) {
            newPriority[i] = priority[i];
        }
    
        // Assign the new arrays to the instance variables
        queue = newQueue;
        priority = newPriority;
    }   
}
