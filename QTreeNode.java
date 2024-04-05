/**
 * 
 * 
 * @author Bruce Lin
 */

public class QTreeNode {
    private int x, y; // Coordinates of the upper left corner of the quadrant
    private int size; // Size of the quadrant
    private int color; // Average color of the quadrant
    private QTreeNode parent; // Parent of the node
    private QTreeNode[] children; // Children of the node

    // Default constructor
    public QTreeNode() {
        this.parent = null;
        this.children = new QTreeNode[4]; // Each node can have up to 4 children
        this.x = this.y = this.size = this.color = 0;
    }

    // Constructor with parameters
    public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
        this.children = theChildren;
        this.x = xcoord;
        this.y = ycoord;
        this.size = theSize;
        this.color = theColor;
    }

    // Check if a point is contained within the quadrant
    public boolean contains(int xcoord, int ycoord) {
        return xcoord >= x && xcoord < x + size && ycoord >= y && ycoord < y + size;
    }

    // Getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSize() {
        return size;
    }
    public int getColor() {
        return color;
    }
    public QTreeNode getParent() {
        return parent;
    }

    // Setters
    public void setX(int newX) {
        x = newX;
    }
    public void setY(int newY) {
        y = newY;
    }
    public void setSize(int newSize) {
        size = newSize;
    }
    public void setColor(int newColor) {
        color = newColor;
    }
    public void setParent(QTreeNode newParent) {
        parent = newParent;
    }
    // Get a specific child
    public QTreeNode getChild(int index) throws QTreeException {
        if (children == null || index < 0 || index > 3) {
            throw new QTreeException("Invalid child index");
        }
        return children[index];
    }

    // Set a specific child
    public void setChild(QTreeNode newChild, int index) throws QTreeException {
        if (index < 0 || index > 3) {
            throw new QTreeException("Invalid child index");
        }
        children[index] = newChild;
    }

    // Check if the node is a leaf (has no children)
    public boolean isLeaf() {
        if (children == null) return true;
        for (QTreeNode child : children) {
            if (child != null) return false;
        }
        return true;
    }
}
