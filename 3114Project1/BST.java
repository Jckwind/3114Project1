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
 * my generic binary search tree class
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 24, 2020
 */
public class BST<T extends Comparable<T>> {

    private NodeClass<T> root;

    private int size;

    /**
     * adds the node to tree
     * 
     * @param node
     *            the node to add
     */
    public void add(T node) {

    }


    /**
     * removes node from tree
     * 
     * @param node
     *            node to remove
     */
    public void remove(T node) {

    }


    /**
     * removes every element in tree
     */
    public void clear() {

    }


    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }


    /**
     * @return the root
     */
    public NodeClass<T> getRoot() {
        return root;
    }


    /**
     * see if tree contains node
     * 
     * @param node
     *            the node to check
     * @return true if contains
     */
    public boolean contains(T node) {
        return false;
    }


    /**
     * is the tree empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

}
