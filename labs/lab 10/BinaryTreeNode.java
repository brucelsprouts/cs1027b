/**
 * BinaryTreeNode represents a node in a binary tree with a left and 
 * right child.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @author: CS1027
 * @version 1.0, 8/19/08
 */

 public class BinaryTreeNode<T> {
    private T dataItem;
    private BinaryTreeNode<T> left, right;

    // Constructor
    BinaryTreeNode(T theData) {
        dataItem = theData;
        left = null;
        right = null;
    }

    // Getter for dataItem
    public T getData() {
        return dataItem;
    }

    // Getter for left child
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    // Getter for right child
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    // Setter for dataItem
    public void setData(T newData) {
        dataItem = newData;
    }

    // Setter for left child
    public void setLeft(BinaryTreeNode<T> newLeft) {
        left = newLeft;
    }

    // Setter for right child
    public void setRight(BinaryTreeNode<T> newRight) {
        right = newRight;
    }
}

