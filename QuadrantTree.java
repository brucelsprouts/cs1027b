/**
 * 
 * 
 * @author Bruce Lin
 */

 public class QuadrantTree {
    private QTreeNode root;

    // Constructor: builds the quadrant tree from a 2D pixel array
    public QuadrantTree(int[][] thePixels) {
        root = buildTree(thePixels, 0, 0, thePixels.length);
    }

    // Recursive method to build the tree
    private QTreeNode buildTree(int[][] pixels, int x, int y, int size) {
        if (size == 1) { // Base case: single pixel
            int color = pixels[y][x]; // Assuming pixels are stored in row-major order
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

    // Getter for the root node
    public QTreeNode getRoot() {
        return root;
    }

    // Returns a list containing all the nodes at the specified level
    public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
        if (r == null || theLevel < 0) return null;

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

    // Returns a Duple object containing nodes with colors similar to theColor and at the specified level
    public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
        if (r == null || theLevel < 0) return new Duple();

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

    // Returns a node at the specified level that contains the point (x, y)
    public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
        if (r == null || !r.contains(x, y)) return null;

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

        return null; // No matching node found
    }
}
