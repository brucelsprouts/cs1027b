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
     * Constructs a LinkedNumber object with the given number string and base.
     * 
     * @param num The string representation of the number.
     * @param baseNum The base of the number system.
     * @throws LinkedNumberException if the given number string is empty.
     */
    public LinkedNumber(String num, int baseNum) {
        base = baseNum;
        
        if (num.isEmpty()) {
            throw new LinkedNumberException("no digits given");
        }
        
        char[] digits = num.toCharArray();
        
        front = null;
        rear = null;
        for (char digit : digits) {
            Digit d = new Digit(digit);
            
            DLNode<Digit> newNode = new DLNode<>(d);
            
            if (front == null) {
                front = newNode;
                rear = newNode;
            } else {
                rear.setNext(newNode);
                newNode.setPrev(rear);
                rear = newNode;
            }
        }
    }

    /**
     * Constructs a LinkedNumber object with the given integer value and base 10.
     * 
     * @param num The integer value.
     */
    public LinkedNumber(int num) {
        this(String.valueOf(num), 10);
    }

    /**
     * Checks if the number stored in this LinkedNumber object is valid according to its base.
     * 
     * @return true if the number is valid, false otherwise.
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
     * @return The base of the number system.
     */
    public int getBase() {
        return base;
    }

    /**
     * Gets the front node of the doubly linked list representing the number.
     * 
     * @return The front node of the list.
     */
    public DLNode<Digit> getFront() {
        return front;
    }

    /**
     * Gets the rear node of the doubly linked list representing the number.
     * 
     * @return The rear node of the list.
     */
    public DLNode<Digit> getRear() {
        return rear;
    }

    /**
     * Gets the number of digits in the number.
     * 
     * @return The number of digits.
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
     * Returns a string representation of the number.
     * 
     * @return The string representation of the number.
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
     * Checks if this LinkedNumber object is equal to another LinkedNumber object.
     * 
     * @param other The other LinkedNumber object to compare.
     * @return true if the two objects are equal, false otherwise.
     */
    public boolean equals(LinkedNumber other) {
        if (this.base != other.getBase() || this.getNumDigits() != other.getNumDigits()) {
            return false;
        }
        DLNode<Digit> currentThis = this.front;
        DLNode<Digit> currentOther = other.getFront();
        while (currentThis != null) {
            if (!currentThis.getElement().equals(currentOther.getElement())) {
                return false;
            }
            currentThis = currentThis.getNext();
            currentOther = currentOther.getNext();
        }
        return true;
    }

    /**
     * Converts the number to a new base.
     * 
     * @param newBase The new base to convert the number to.
     * @return A new LinkedNumber object representing the converted number.
     * @throws LinkedNumberException if the number is not valid for conversion.
     */
    public LinkedNumber convert(int newBase) {
        if (!isValidNumber()) {
            throw new LinkedNumberException("cannot convert invalid number");
        }
    
        int value = 0;
        int position = 0;
        DLNode<Digit> current = rear;
    
        while (current != null) {
            value += current.getElement().getValue() * Math.pow(base, position);
            current = current.getPrev();
            position++;
        }
    
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            int remainder = value % newBase;
            if (remainder >= 10) {
                char hexChar = (char) ('A' + (remainder - 10));
                sb.insert(0, hexChar);  
            } else {
                sb.insert(0, remainder);
            }
            value /= newBase;
        }
    
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
            throw new LinkedNumberException("Invalid position");
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
     * @param position The position of the digit to remove.
     * @return The value of the removed digit.
     * @throws LinkedNumberException if the position is invalid.
     */
    public int removeDigit(int position) {
        if (position < 0 || position >= getNumDigits()) {
            throw new LinkedNumberException("Invalid position");
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