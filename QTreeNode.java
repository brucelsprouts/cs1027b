/**
 * Represents a node in a quadrant tree, used to store and manage a hierarchical, spatial representation of data.
 * Each node corresponds to a square region in a two-dimensional space, potentially subdividing into four child nodes.
 *
 * @author Bruce Lin
 */

public class QTreeNode {
    private int x, y; // Coordinates of the upper left corner of the quadrant
    private int size; // Size of the quadrant
    private int color; // Average color of the quadrant
    private QTreeNode parent; // Parent of the node
    private QTreeNode[] children; // Children of the node

    /**
     * Constructs a new QTreeNode with default parameters. Initializes the node with no parent, four null children,
     * and sets all numerical properties to zero.
     */
    public QTreeNode() {
        this.parent = null;
        this.children = new QTreeNode[4]; // Each node can have up to 4 children
        this.x = this.y = this.size = this.color = 0;
    }

    /**
     * Constructs a new QTreeNode with specified parameters.
     *
     * @param theChildren Array of child nodes, can be null for leaf nodes.
     * @param xcoord X-coordinate of the upper left corner of the node's region.
     * @param ycoord Y-coordinate of the upper left corner of the node's region.
     * @param theSize Size of the node's region (assuming square regions).
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
     * Determines if a point defined by (xcoord, ycoord) is within the region represented by this node.
     *
     * @param xcoord The x-coordinate of the point to check.
     * @param ycoord The y-coordinate of the point to check.
     * @return true if the point is within the node's region; false otherwise.
     */
    public boolean contains(int xcoord, int ycoord) {
        return xcoord >= x && xcoord < x + size && ycoord >= y && ycoord < y + size;
    }

    // Getters
    // Each getter method returns the respective property of the node.
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
    // Each setter method updates the respective property of the node.
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
    
    /**
     * Retrieves a specific child of this node by index.
     *
     * @param index The index of the child to retrieve, ranging from 0 to 3.
     * @return The child node at the specified index.
     * @throws QTreeException if the index is out of bounds or the children array is null.
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
     * @param index    The index at which to set the new child, ranging from 0 to 3.
     * @throws QTreeException if the index is out of bounds.
     */
    public void setChild(QTreeNode newChild, int index) throws QTreeException {
        if (index < 0 || index > 3) {
            throw new QTreeException("Invalid child index");
        }
        children[index] = newChild;
    }

    /**
     * Determines if this node is a leaf node, i.e., it has no children.
     *
     * @return true if the node is a leaf (has no children), false otherwise.
     */
    public boolean isLeaf() {
        if (children == null) return true;
        for (QTreeNode child : children) {
            if (child != null) return false;
        }
        return true;
    }
}