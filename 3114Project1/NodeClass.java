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
 * @param <T>
 *            the key type
 * @param <K>
 *            the value type
 */
public class NodeClass<T, K> {

    private T key;

    private K value;

    private NodeClass<T, K> left;

    private NodeClass<T, K> right;

    /**
     * creates a new node class obj
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public NodeClass(T key, K value) {
        this.key = key;
        this.value = value;
    }


    /**
     * @return the data
     */
    public T getKey() {
        return key;
    }


    /**
     * @return the value
     */
    public K getValue() {
        return value;
    }


    /**
     * @return the left
     */
    public NodeClass<T, K> getLeft() {
        return left;
    }


    /**
     * @return the right
     */
    public NodeClass<T, K> getRight() {
        return right;
    }


    /**
     * @param key
     *            the key to set
     */
    public void setKey(T key) {
        this.key = key;
    }


    /**
     * @param value
     *            the value to set
     */
    public void setValue(K value) {
        this.value = value;
    }


    /**
     * @param left
     *            the left to set
     */
    public void setLeft(NodeClass<T, K> left) {
        this.left = left;
    }


    /**
     * @param right
     *            the right to set
     */
    public void setRight(NodeClass<T, K> right) {
        this.right = right;
    }


    /**
     * gets the key for this mode
     * 
     * @param mode
     *            the mode
     * @return the key
     */
    public String getKey(int mode) {
        if (value == null) {
            return "<>";
        }
        CovidData data = (CovidData)value;
        return data.getKey(mode);
    }


    /**
     * gets the value
     * 
     * @return the positive cases
     */
    public int getPos() {
        if (value == null) {
            return 0;
        }

        CovidData data = (CovidData)value;
        return data.getPos().intValue();
    }

}
