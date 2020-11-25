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
public class BST<T extends Comparable<Object>> {

    private NodeClass<T> root;

    private LeafNode<T> flyweight;

    private int size;

    /**
     * creates a new BST object
     */
    public BST() {
        size = 0;
        flyweight = new LeafNode<T>();
        root = flyweight;
    }


    /**
     * adds the node to tree
     * 
     * @param value
     *            the value to add
     */
    public void add(T value) {
        root = add(root, value);
        size++;
    }


    /**
     * does the recursive adding to the BST
     * 
     * @param node
     *            the root node
     * @param value
     *            the value to add
     */
    private NodeClass<T> add(NodeClass<T> node, T value) {
        if (node == flyweight) {
            return new DataNode<T>(value, flyweight);
        }

        if (value.compareTo(node.getKey()) < 0) {
            node.setLeft(add(node.getLeft(), value));
        }
        else if (value.compareTo(node.getKey()) > 0) {
            node.setRight(add(node.getRight(), value));
        }

        return node;
    }


    /**
     * removes node from tree
     * 
     * @param value
     *            value to remove
     */
    public void remove(T value) {
        root = remove(root, value);
    }


    /**
     * does the recursive removal
     * 
     * @param node
     *            the root node
     * @param value
     *            the value to remove
     * @return the new root
     */
    private NodeClass<T> remove(NodeClass<T> node, T value) {
        if (node == flyweight) {
            return flyweight;
        }

        if (value.compareTo(node.getKey()) < 0) {
            node.setLeft(remove(node.getLeft(), value));
        }
        else if (value.compareTo(node.getKey()) > 0) {
            node.setRight(remove(node.getRight(), value));
        }
        else {
            if (node.getLeft() == flyweight) {
                return node.getRight();
            }
            else if (node.getRight() == flyweight) {
                return node.getLeft();
            }
            else {
                NodeClass<T> minNode = findMin(node.getRight());
                node.setKey(minNode.getKey());
                node.setRight(remove(node.getRight(), node.getKey()));
            }
        }
        return node;
    }


    /**
     * finds the minimum in the given BST
     * 
     * @param node
     *            the root of the tree
     * @return the min node
     */
    private NodeClass<T> findMin(NodeClass<T> node) {
        NodeClass<T> temp = node;
        while (temp.getLeft() != flyweight) {
            temp = temp.getLeft();
        }
        return temp;
    }


    /**
     * removes every element in tree
     */
    public void clear() {
        root = flyweight;
        size = 0;
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
     * @param value
     *            the value to check
     * @return true if contains
     */
    public boolean contains(T value) {
        return contains(root, value);
    }


    /**
     * does the recursive checking if we contain our shit
     * 
     * @param node
     *            the node to start w
     * @param value
     *            the value to check for
     * @return yes
     */
    private boolean contains(NodeClass<T> node, T value) {
        if (node == flyweight) {
            return false;
        }
        if (value.compareTo(node.getKey()) == 0) {
            return true;
        }
        return (node.getKey().compareTo(value) > 0)
            ? contains(node.getLeft(), value)
            : contains(node.getRight(), value);
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
