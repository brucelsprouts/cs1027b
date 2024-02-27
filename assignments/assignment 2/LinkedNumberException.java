/**
 * This class represents an exception that relates to an invalid condition or operation while working with the LinkedNumber objects.
 * 
 * @author Bruce Lin
 */
public class LinkedNumberException extends RuntimeException {

    /**
     * Constructs a new LinkedNumberException with the specified message.
     *
     * @param msg Invoke the constructor of its parent class, RuntimeException.
     */
    public LinkedNumberException(String msg) {
        super(msg);
    }
}
