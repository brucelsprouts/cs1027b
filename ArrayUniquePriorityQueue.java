/**
 * Represents a priority queue where each element has a unique priority.
 * Elements are stored in a doubly linked list structure.
 * 
 * @param <T> the type of elements stored in the priority queue
 * @author Bruce Lin
 */
public class ArrayUniquePriorityQueue<T> implements UniquePriorityQueueADT<T> {
    private T[] queue;
    private double[] priority;
    private int count;
    
    /**
     * Constructs an empty priority queue with initial capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public ArrayUniquePriorityQueue() {
        queue = (T[]) new Object[10];
        priority = new double[10];
        count = 0;
    }

    /**
     * Adds a new element with its priority to the priority queue.
     * If the element already exists, it is not added again.
     * 
     * @param data the element to be added
     * @param prio the priority of the element
     */
    @Override
    public void add(T data, double prio) {
        // If the element already exists, do nothing
        if (contains(data))
            return;
        
        // If the capacity is reached, expand the capacity
        if (count == queue.length)
            expandCapacity();
        
        // Find the correct position to insert the new element based on its priority
        int index = 0;
        while (index < count && prio >= priority[index])
            index++;
        
        // Shift elements to make space for the new element
        for (int i = count; i > index; i--) {
            queue[i] = queue[i - 1];
            priority[i] = priority[i - 1];
        }
        
        // Insert the new element and its priority
        queue[index] = data;
        priority[index] = prio;
        count++;
    }

    /**
     * Checks if the priority queue contains a specific element.
     * 
     * @param data the element to search for
     * @return true if the element is found, false otherwise
     */
    @Override
    public boolean contains(T data) {
        for (int i = 0; i < count; i++) {
            if (queue[i].equals(data))
                return true;
        }
        return false;
    }

    /**
     * Retrieves the element with the lowest priority without removing it.
     * 
     * @return the element with the lowest priority
     * @throws CollectionException if the priority queue is empty
     */
    @Override
    public T peek() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("PQ is empty");
        }
        return queue[0];
    }

    /**
     * Removes and returns the element with the lowest priority.
     * 
     * @return the element with the lowest priority
     * @throws CollectionException if the priority queue is empty
     */
    @Override
    public T removeMin() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("PQ is empty");
        }
        T min = queue[0];
        for (int i = 1; i < count; i++) {
            queue[i - 1] = queue[i];
            priority[i - 1] = priority[i];
        }
        count--;
        return min;
    }

    /**
     * Updates the priority of a specific element in the priority queue.
     * 
     * @param data the element whose priority needs to be updated
     * @param newPrio the new priority value
     * @throws CollectionException if the element is not found in the priority queue
     */
    @Override
    public void updatePriority(T data, double newPrio) throws CollectionException {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (queue[i].equals(data)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new CollectionException("Item not found in PQ");
        }
            
        // Remove the item and its priority
        for (int i = index; i < count - 1; i++) {
            queue[i] = queue[i + 1];
            priority[i] = priority[i + 1];
        }
        count--;

        // Re-add the item with the new priority
        add(data, newPrio);
    }

    /**
     * Checks if the priority queue is empty.
     * 
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements in the priority queue.
     * 
     * @return the number of elements
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns the current capacity of the priority queue.
     * 
     * @return the current capacity
     */
    public int getLength() {
        return queue.length;
    }

    /**
     * Converts the priority queue to a string representation.
     * 
     * @return a string representation of the priority queue
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "The PQ is empty";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(queue[i]).append(" [").append(priority[i]).append("], ");
        }
        sb.setLength(sb.length() - 2); // Remove trailing comma and space
        return sb.toString();
    }
    
    /**
     * Expands the capacity of the priority queue when necessary.
     */
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