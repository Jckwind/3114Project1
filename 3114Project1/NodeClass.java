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
 * the default node in my bst
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 24, 2020
 */
public class NodeClass<T> implements Comparable<T> {

    private T key;

    private NodeClass<T> left;

    private NodeClass<T> right;

    /**
     * 
     */
    public NodeClass(T data) {
        this.key = data;
    }


    /**
     * @return the data
     */
    public T getKey() {
        return key;
    }


    /**
     * @return the left
     */
    public NodeClass<T> getLeft() {
        return left;
    }


    /**
     * @return the right
     */
    public NodeClass<T> getRight() {
        return right;
    }


    /**
     * @param data
     *            the data to set
     */
    public void setKey(T key) {
        this.key = key;
    }


    /**
     * @param left
     *            the left to set
     */
    public void setLeft(NodeClass<T> left) {
        this.left = left;
    }


    /**
     * @param right
     *            the right to set
     */
    public void setRight(NodeClass<T> right) {
        this.right = right;
    }


    @Override
    public int compareTo(T o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
