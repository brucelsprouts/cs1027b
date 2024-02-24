public class LinkedNumber {
    
    private int base;
    private DLNode<Digit> front;
    private DLNode<Digit> rear;

    public LinkedNumber(String num, int baseNum) {
            // Step 1: Assign baseNum to the instance variable base
        base = baseNum;
        
        // Step 2: Check if num is empty
        if (num.isEmpty()) {
            throw new LinkedNumberException("no digits given");
        }
        
        // Step 3: Convert the string num into an array of characters
        char[] digits = num.toCharArray();
        
        // Step 4: Initialize a doubly linked list
        front = null;
        rear = null;
        for (char digit : digits) {
            // Create a Digit object for each character
            Digit d = new Digit(digit);
            
            // Create a DLNode to store the Digit object
            DLNode<Digit> newNode = new DLNode<>(d);
            
            // Connect the new node to the existing linked list
            if (front == null) {
                // If the list is empty, set both front and rear to the new node
                front = newNode;
                rear = newNode;
            } else {
                // Otherwise, add the new node to the end of the list
                rear.setNext(newNode);
                newNode.setPrev(rear);
                rear = newNode;
            }
        }
    }

    public LinkedNumber(int num) {
        this(String.valueOf(num), 10);
    }

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

    public int getBase() {
        return base;
    }

    public DLNode<Digit> getFront() {
        return front;
    }

    public DLNode<Digit> getRear() {
        return rear;
    }

    public int getNumDigits() {
        int count = 0;
        DLNode<Digit> current = front;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        DLNode<Digit> current = front;
        while (current != null) {
            result.append(current.getElement());
            current = current.getNext();
        }
        return result.toString();
    }

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

    public LinkedNumber convert(int newBase) {
        if (!isValidNumber()) {
            throw new LinkedNumberException("cannot convert invalid number");
        }
        // Convert the number to decimal first
        LinkedNumber decimalNumber = new LinkedNumber(toString(), this.base);
        int value = 0;
        int position = 0;
        DLNode<Digit> current = decimalNumber.getRear();
        while (current != null) {
            value += current.getElement().getValue() * Math.pow(this.base, position);
            current = current.getNext();
            position++;
        }
        // Convert the decimal value to the new base
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.insert(0, value % newBase);
            value /= newBase;
        }
        // Handle the case where the value is zero
        if (sb.length() == 0) {
            sb.append(0);
        }
        return new LinkedNumber(sb.toString(), newBase);
    }

    public void addDigit(Digit digit, int position) {
        if (position < 0 || position > getNumDigits()) {
            throw new LinkedNumberException("invalid position");
        }
        DLNode<Digit> newNode = new DLNode<>(digit);
        if (position == 0) {
            if (front == null) {
                front = newNode;
                rear = newNode;
            } else {
                newNode.setNext(front);
                front.setPrev(newNode);
                front = newNode;
            }
        } else if (position == getNumDigits()) {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;
        } else {
            DLNode<Digit> current = front;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            DLNode<Digit> nextNode = current.getNext();
            current.setNext(newNode);
            newNode.setPrev(current);
            newNode.setNext(nextNode);
            nextNode.setPrev(newNode);
        }
    }

    public int removeDigit(int position) {
        if (position < 0 || position >= getNumDigits()) {
            throw new LinkedNumberException("invalid position");
        }
        DLNode<Digit> current = front;
        int value = 0;
        // Traverse to the node at the specified position
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        // Calculate the value of the removed digit
        value = current.getElement().getValue() * (int) Math.pow(this.base, getNumDigits() - position - 1);
        // Remove the node
        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext());
        } else {
            front = current.getNext();
        }
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        } else {
            rear = current.getPrev();
        }
        return value;
    }
}
