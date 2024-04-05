/**
 * Implements a quadtree data structure for efficient management and querying of a two-dimensional space, typically used in applications such as image processing, spatial indexing, and geographical information systems. 
 * The quadtree subdivides a space into four quadrants recursively until each leaf node represents a single pixel or a homogeneous area within a specified tolerance.
 * This class provides methods for constructing the tree from a 2D pixel array, traversing the tree, and querying nodes based on spatial location, color similarity, and tree depth.
 * 
 * @author Bruce Lin
 */

public class QuadrantTree {
    // Root node of the quadtree
    private QTreeNode root;

    /**
     * Constructs a quadrant tree from a 2D array of pixel values. It initiates the recursive building process starting from the root.
     *
     * @param thePixels A 2D array representing pixel values of an image, where each element corresponds to the color value of a pixel.
     */
    public QuadrantTree(int[][] thePixels) {
        root = buildTree(thePixels, 0, 0, thePixels.length);
    }

    /**
     * Recursively constructs the quadtree by dividing the given region into four smaller quadrants until each leaf node corresponds to a single pixel.
     *
     * @param pixels A 2D array of pixel values for the entire image.
     * @param x The x-coordinate of the upper left corner of the current quadrant.
     * @param y The y-coordinate of the upper left corner of the current quadrant.
     * @param size The size of the current quadrant, assumed to be a square.
     * @return The root node of the constructed subtree for the current quadrant.
     */
    private QTreeNode buildTree(int[][] pixels, int x, int y, int size) {
        if (size == 1) {
            int color = pixels[y][x];
            return new QTreeNode(null, x, y, size, color);
        }
        
        // Recursively divide the current quadrant into four sub-quadrants
        int newSize = size / 2;
        QTreeNode[] children = new QTreeNode[4];
        children[0] = buildTree(pixels, x, y, newSize);
        children[1] = buildTree(pixels, x + newSize, y, newSize);
        children[2] = buildTree(pixels, x, y + newSize, newSize);
        children[3] = buildTree(pixels, x + newSize, y + newSize, newSize);
        
        // Compute the average color of the current quadrant
        int avgColor = Gui.averageColor(pixels, x, y, size);
        QTreeNode node = new QTreeNode(children, x, y, size, avgColor);
        for (QTreeNode child : children) child.setParent(node);
        return node;
    }

    /**
     * Getter for the root node of the quadtree.
     *
     * @return The root node of the quadtree.
     */
    public QTreeNode getRoot() {
        return root;
    }

    /**
     * Retrieves all nodes at a specified depth level of the tree.
     *
     * @param r The current node being examined in the recursive call.
     * @param theLevel The desired depth level to retrieve nodes from.
     * @return A linked list containing all nodes at the specified depth level.
     */
    public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
        if (r == null || theLevel < 0) {
            return null;
        }

        // If the desired level is reached or the node is a leaf, add the node to the list
        if (theLevel == 0 || r.isLeaf()) {
            return new ListNode<>(r);
        }

        ListNode<QTreeNode> list = null;
        ListNode<QTreeNode> last = null;

        // Recursively get pixels from children and concatenate the lists
        for (int i = 0; i < 4; i++) {
            ListNode<QTreeNode> childList = getPixels(r.getChild(i), theLevel - 1);
            if (childList != null) {
                if (list == null) {
                    list = childList;
                    last = list;
                } else {
                    last.setNext(childList);
                }

                // Move 'last' to the end of the list
                while (last.getNext() != null) {
                    last = last.getNext();
                }
            }
        }

        return list;
    }

    /**
     * Searches the quadtree to find all nodes at a specified depth level whose color is similar to a given target color.
     *
     * @param r The current node being examined.
     * @param theColor The target color to match.
     * @param theLevel The depth level at which to search for similar colors.
     * @return A Duple object containing a list of matching nodes and the count of such nodes.
     */
    public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
        if (r == null || theLevel < 0) {
            return new Duple();
        }        

        ListNode<QTreeNode> list = null;
        int count = 0;

        // Base case: at the desired level or a leaf node
        if (theLevel == 0 || r.isLeaf()) {
            if (Gui.similarColor(r.getColor(), theColor)) {
                list = new ListNode<>(r);
                count = 1;
            }
        } else {
            // Recursively search in children
            for (int i = 0; i < 4; i++) {
                Duple childDuple = findMatching(r.getChild(i), theColor, theLevel - 1);
                if (childDuple.getFront() != null) {
                    if (list == null) {
                        list = childDuple.getFront();
                    } else {
                        ListNode<QTreeNode> temp = list;
                        while (temp.getNext() != null) temp = temp.getNext();
                        temp.setNext(childDuple.getFront());
                    }
                    count += childDuple.getCount();
                }
            }
        }

        return new Duple(list, count);
    }

    /**
     * Finds a node at a specific depth level that contains a given point defined by its x and y coordinates.
     *
     * @param r The current node being examined.
     * @param theLevel The depth level at which to find the node.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return The node at the specified depth containing the point, or null if no such node exists.
     */
    public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
        if (r == null || !r.contains(x, y)) {
            return null;
        }

        // If the desired level is reached or the node is a leaf, return the node
        if (theLevel == 0 || r.isLeaf()) {
            return r;
        }

        // Recursively search in the appropriate child
        for (int i = 0; i < 4; i++) {
            QTreeNode child = r.getChild(i);
            if (child != null && child.contains(x, y)) {
                return findNode(child, theLevel - 1, x, y);
            }
        }
        
        return null;
    }
}
