/**
 * Implements a QuadrantTree for 2D space management, supporting construction, traversal, and querying by location, color, and depth.
 * 
 * @author Bruce Lin
 */

public class QuadrantTree {
    private QTreeNode root; //Root of the quadrant tree.

    /**
     * Constructs a QuadrantTree from pixel values, starting the build recursively from the root.
     *
     * @param thePixels A 2D array representing pixel values of an image, where each element corresponds to the color value of a pixel.
     */
    public QuadrantTree(int[][] thePixels) {
        root = buildTree(thePixels, 0, 0, thePixels.length);
    }

    /**
     * Recursively constructs the QuadrantTree by dividing the given region into four smaller quadrants until each leaf node corresponds to a single pixel.
     *
     * @param pixels A 2D array of pixel values.
     * @param x The x-coordinate of the upper left corner of quadrant.
     * @param y The y-coordinate of the upper left corner of quadrant.
     * @param size The size of quadrant.
     * @return The root node of the constructed subtree for quadrant.
     */
    private QTreeNode buildTree(int[][] pixels, int x, int y, int size) {
        // Creates a leaf node for a single pixel when the size is 1.
        if (size == 1) {
            int color = pixels[y][x];
            return new QTreeNode(null, x, y, size, color);
        }
        
        // Recursively divide the current quadrant into four sub quadrants.
        int newSize = size / 2;
        QTreeNode[] children = new QTreeNode[4];
        children[0] = buildTree(pixels, x, y, newSize);
        children[1] = buildTree(pixels, x + newSize, y, newSize);
        children[2] = buildTree(pixels, x, y + newSize, newSize);
        children[3] = buildTree(pixels, x + newSize, y + newSize, newSize);
        
        // Compute the average color of quadrant.
        int avgColor = Gui.averageColor(pixels, x, y, size);
        QTreeNode node = new QTreeNode(children, x, y, size, avgColor);
        for (QTreeNode child : children) child.setParent(node);
        return node;
    }

    /**
     * Getter for the root node of the QuadrantTree.
     *
     * @return The root node of the QuadrantTree.
     */
    public QTreeNode getRoot() {
        return root;
    }

    /**
     * Retrieves all nodes at specified level of the tree.
     *
     * @param r The current node in the recursive call.
     * @param theLevel The desired level to retrieve nodes from.
     * @return A linked list containing all nodes at the specified level.
     */
    public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
        // Ends early with null for a null node or a negative level
        if (r == null || theLevel < 0) {
            return null;
        }

        // Add the node to the list if the desired level is reached or the node is a leaf.
        if (theLevel == 0 || r.isLeaf()) {
            return new ListNode<>(r);
        }

        ListNode<QTreeNode> list = null;
        ListNode<QTreeNode> last = null;

        // Recursively get pixels from children and concatenate the lists.
        for (int i = 0; i < 4; i++) {
            ListNode<QTreeNode> childList = getPixels(r.getChild(i), theLevel - 1);
            if (childList != null) {
                if (list == null) {
                    list = childList;
                    last = list;
                } else {
                    last.setNext(childList);
                }

                // Move last to the end of the list.
                while (last.getNext() != null) {
                    last = last.getNext();
                }
            }
        }

        return list;
    }

    /**
     * Finds nodes at a given level with color similar to the target in the QuadrantTree.
     *
     * @param r The current node.
     * @param theColor The target color to match.
     * @param theLevel The level at which to search for similar colors.
     * @return a Duple with a list of matching nodes and their count.
     */
    public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
        // Return empty result for null node or invalid level
        if (r == null || theLevel < 0) {
            return new Duple();
        }

        ListNode<QTreeNode> list = null;
        int count = 0;
    
        // Check at target level or if node is a leaf
        if (theLevel == 0 || r.isLeaf()) {
            if (Gui.similarColor(r.getColor(), theColor)) {
                list = new ListNode<>(r);
                count = 1;
            }
        } else {
            // Recurse on children if not at target level or leaf
            for (int i = 0; i < 4; i++) {
                Duple childDuple = findMatching(r.getChild(i), theColor, theLevel - 1);
    
                // Append child matches to list
                if (childDuple.getFront() != null) {
                    if (list == null) list = childDuple.getFront();
                    else {
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
     * Finds a node at a specific level that contains a given point defined by its x and y coordinates.
     *
     * @param r The current node being examined.
     * @param theLevel The level at which to find node.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return The node at the specified level containing the point, or null if node exists.
     */
    public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
        // Stops if no node or (x,y) is out of bounds.
        if (r == null || !r.contains(x, y)) {
            return null;
        }

        // Return node if at desired level or node is a leaf.
        if (theLevel == 0 || r.isLeaf()) {
            return r;
        }

        // Recursively search in the appropriate child.
        for (int i = 0; i < 4; i++) {
            QTreeNode child = r.getChild(i);
            if (child != null && child.contains(x, y)) {
                return findNode(child, theLevel - 1, x, y);
            }
        }

        return null;
    }
}