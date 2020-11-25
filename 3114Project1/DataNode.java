// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
//
// -- Jack Windham (jckwind11)
// -- Michael Gannon (mgannon3500)
/**
 * the node in my bst - this one has data
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 24, 2020
 */
public class DataNode<T, K> extends NodeClass<T, K> {

    private LeafNode<T, K> leafNode;

    /**
     * creates a new data node class
     * 
     * @param data
     */
    public DataNode(T key, K value, LeafNode<T, K> leafNode) {
        super(key, value);
        this.leafNode = leafNode;
    }


    @Override
    public NodeClass<T, K> getLeft() {
        if (super.getLeft() == null) {
            return leafNode;
        }
        return super.getLeft();
    }


    @Override
    public NodeClass<T, K> getRight() {
        if (super.getRight() == null) {
            return leafNode;
        }
        return super.getRight();
    }


    @Override
    public void setLeft(NodeClass<T, K> left) {
        if (left != null) {
            super.setLeft(left);
            return;
        }
        super.setLeft(leafNode);
    }


    @Override
    public void setRight(NodeClass<T, K> right) {
        if (right != null) {
            super.setRight(right);
            return;
        }
        super.setRight(leafNode);
    }


    @Override
    public int compareTo(T o) {
        // TODO Auto-generated method stub
        return super.compareTo(o);
    }
}
