/**
 * This class is used to represent a positive whole number of any number system stored in a doubly linked list such that each digit of the number is stored in a separate node.
 * 
 * @author Bruce Lin
 */
public class LinkedNumber {
    private int base;
    private DLNode<Digit> front;
    private DLNode<Digit> rear;

    /**
     * Constructs a LinkedNumber object with a given number and base.
     * 
     * @param num The string of the number.
     * @param baseNum The base of the number system.
     * @throws LinkedNumberException if num is empty.
     */
    public LinkedNumber(String num, int baseNum) {
        if (num.isEmpty()) {
            throw new LinkedNumberException("no digits given");
        }
        
        base = baseNum;
        front = rear = null;
        
        for (char digit : num.toCharArray()) {
            DLNode<Digit> newNode = new DLNode<>(new Digit(digit));
            
            if (front == null) {
                front = rear = newNode;
            } else {
                rear.setNext(newNode);
                newNode.setPrev(rear);
                rear = newNode;
            }
        }
    }

    /**
     * Constructs a LinkedNumber object with a base of 10 and the given int value.
     * 
     * @param num The integer value.
     */
    public LinkedNumber(int num) {
        this(String.valueOf(num), 10);
    }

    /**
     * Determine whether or not the number stored in this linked list is a valid positive number for the base number system.
     * 
     * @return true if the number is valid, or false otherwise.
     */
    public boolean isValidNumber() {
        DLNode<Digit> current = front;
        while (current != null) {
            int value = current.getElement().getValue();
            if (value < 0 || value >= base) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Gets the base of the number system.
     * 
     * @return the value of base.
     */
    public int getBase() {
        return base;
    }

    /**
     * Gets the front node of the doubly linked list.
     * 
     * @return the front node
     */
    public DLNode<Digit> getFront() {
        return front;
    }

    /**
     * Gets the rear node of the doubly linked list.
     * 
     * @return the rear node.
     */
    public DLNode<Digit> getRear() {
        return rear;
    }

    /**
     * Gets the number of digits in the number.
     * 
     * @return the number of digits.
     */
    public int getNumDigits() {
        int count = 0;
        DLNode<Digit> current = front;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /**
     * Create and return a String containing all the digits of the number.
     * 
     * @return string version of the number.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        DLNode<Digit> current = front;
        while (current != null) {
            result.append(current.getElement());
            current = current.getNext();
        }
        return result.toString();
    }

    /**
     * Compare this LinkedNumber object and other to see if they are equivalent.
     * 
     * @param other The other LinkedNumber object to compare.
     * @return true if the two objects are equal and false if they are not.
     */
    public boolean equals(LinkedNumber other) {
        // Check base and number of digits
        if (this.base != other.getBase() || this.getNumDigits() != other.getNumDigits()) {
            return false;
        }
    
        // Check each digit
        DLNode<Digit> currentThis = this.front;
        DLNode<Digit> currentOther = other.getFront();
        while (currentThis != null) {
            if (!currentThis.getElement().equals(currentOther.getElement())) {
                return false;
            }
            currentThis = currentThis.getNext();
            currentOther = currentOther.getNext();
        }
    
        return true; // Numbers are equal
    }
    

    /**
     * Converts the number to a new base.
     * 
     * @param newBase The new base to convert to.
     * @return a new LinkedNumber object that represents the new converted number.
     * @throws LinkedNumberException if the number is invalid and cant be converted.
     */
        public LinkedNumber convert(int newBase) {
            if (!isValidNumber()) {
                throw new LinkedNumberException("Cannot convert invalid number");
            }
        
            int value = 0;
        int position = 0;
        DLNode<Digit> current = rear;
    
        // Convert to base 10
        while (current != null) {
            value += current.getElement().getValue() * Math.pow(base, position);
            current = current.getPrev();
            position++;
        }
    
        // Convert to new base
        StringBuilder sb = new StringBuilder();
        do {
            int remainder = value % newBase;
            if (remainder >= 10) {
                char hexChar = (char) ('A' + (remainder - 10));
                sb.insert(0, hexChar);
            } else {
                sb.insert(0, remainder);
            }
            value /= newBase;
        } while (value > 0);
    
        // Handle case when the value is 0
        if (sb.length() == 0) {
            sb.append(0);
        }
    
        return new LinkedNumber(sb.toString(), newBase);
    }
    
    
    /**
     * Adds a digit at the specified position in the number.
     * 
     * @param digit The digit to add.
     * @param position The position to add the digit.
     * @throws LinkedNumberException if the position is invalid.
     */
    public void addDigit(Digit digit, int position) {
        if (position < 0 || position > getNumDigits() + 1) {
            throw new LinkedNumberException("invalid position");
        }
    
        DLNode<Digit> newNode = new DLNode<>(digit);
    
        if (position == 0) {
            if (rear == null) {
                rear = newNode;
                front = newNode;
            } else {
                newNode.setPrev(rear);
                rear.setNext(newNode);
                rear = newNode;
            }
        } else if (position == getNumDigits()) {
            if (front == null) {
                front = newNode;
                rear = newNode;
            } else {
                newNode.setNext(front);
                front.setPrev(newNode);
                front = newNode;
            }
        } else {
            DLNode<Digit> current = front;
    
            for (int i = getNumDigits(); i > position; i--) {
                current = current.getNext();
            }
    
            DLNode<Digit> prevNode = current.getPrev();
            newNode.setNext(current);
            newNode.setPrev(prevNode);
            current.setPrev(newNode);
    
            if (prevNode != null) {
                prevNode.setNext(newNode);
            } else {
                front = newNode;
            }
        }
    }
    
    /**
     * Removes a digit from the specified position in the number.
     * 
     * @param position The position to remove the digit.
     * @return The value of the removed digit.
     * @throws LinkedNumberException if the position is invalid.
     */
    public int removeDigit(int position) {
        if (position < 0 || position >= getNumDigits()) {
            throw new LinkedNumberException("invalid position");
        }
    
        DLNode<Digit> nodeToRemove;

        if (position == 0) {
            nodeToRemove = rear;
            rear = rear.getPrev();
            if (rear != null) {
                rear.setNext(null);
            } else {
                front = null;
            }
        } else if (position == getNumDigits() - 1) {
            nodeToRemove = front;
            front = front.getNext();
            if (front != null) {
                front.setPrev(null);
            } else {
                rear = null;
            }
        } else {
            DLNode<Digit> current = rear;
            for (int i = 0; i < position; i++) {
                current = current.getPrev();
            }
            nodeToRemove = current;
            DLNode<Digit> prevNode = current.getPrev();
            DLNode<Digit> nextNode = current.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
    
        int value = nodeToRemove.getElement().getValue();
        for (int i = 0; i < position; i++) {
            value *= base;
        }
    
        return value;
    }
}