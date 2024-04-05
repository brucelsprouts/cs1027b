/**
 * This class defines a node in a quadrant tree, facilitating operations like region representation, child management, and point containment checks.
 *
 * @author Bruce Lin
 */

public class QTreeNode {
    private int x; // X-coordinates of the upper left corner of the quadrant.
    private int y; // Y-coordinates of the upper left corner of the quadrant.
    private int size; // Size of the quadrant.
    private int color; // Average color of the pixels in the quadrant.
    private QTreeNode parent; // Parent of the node.
    private QTreeNode[] children; // Children of the node.

    /**
     * Creates a new QTreeNode with no parent or children and all values set to zero.
     */
    public QTreeNode() {
        this.parent = null;
        this.children = new QTreeNode[4];
        this.x = this.y = this.size = this.color = 0;
    }

    /**
     * Constructs a new QTreeNode with parameters.
     *
     * @param theChildren Array of child nodes.
     * @param xcoord X-coordinate of the upper left corner of the node's region.
     * @param ycoord Y-coordinate of the upper left corner of the node's region.
     * @param theSize Size of the node's region.
     * @param theColor Average color of the node's region.
     */
    public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
        this.children = theChildren;
        this.x = xcoord;
        this.y = ycoord;
        this.size = theSize;
        this.color = theColor;
    }

    /**
     * Determines if a point defined by xcoord and ycoord is within the region represented by this node.
     *
     * @param xcoord The x-coordinate of the point to check.
     * @param ycoord The y-coordinate of the point to check.
     * @return true if the point is within the node's region; false otherwise.
     */
    public boolean contains(int xcoord, int ycoord) {
        boolean isXWithinBounds = (xcoord >= x) && (xcoord < (x + size));
        boolean isYWithinBounds = (ycoord >= y) && (ycoord < (y + size));
    
        return isXWithinBounds && isYWithinBounds;
    }
    

    // Getters methods.
    // Each getter method returns the respective property of the node.
    public int getx() {
        return x;
    }
    public int gety() {
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

    // Setters methods.
    // Each setter method updates the respective property of the node.
    public void setx(int newX) {
        x = newX;
    }
    public void sety(int newY) {
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
    
    /**
     * Returns the child stored at the index of array children.
     *
     * @param index The index of the child to retrieve.
     * @return The child node at the index.
     * @throws QTreeException if the index is out of bounds or if the children array is null.
     */
    public QTreeNode getChild(int index) throws QTreeException {
        if (children == null || index < 0 || index > 3) {
            throw new QTreeException("Invalid child index");
        }
        return children[index];
    }

    /**
     * Sets a specific child for this node at the given index.
     *
     * @param newChild The new child node to set.
     * @param index The index to set the new child.
     * @throws QTreeException if the index is out of bounds.
     */
    public void setChild(QTreeNode newChild, int index) throws QTreeException {
        if (index < 0 || index > 3) {
            throw new QTreeException("Invalid child index");
        }
        children[index] = newChild;
    }

    /**
     * Determines if children is null or if every entry of children is null.
     *
     * @return true if children is null or if every entry of children is null.
     */
    public boolean isLeaf() {
        if (children == null) return true;
        for (QTreeNode child : children) {
            if (child != null) return false;
        }
        return true;
    }
}