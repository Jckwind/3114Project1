import java.util.ArrayList;

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
public class BST<K> {

    private NodeClass<String, K> root;

    private LeafNode<String, K> flyweight;

    private StateOrderedBST<K> stateData;

    private int size;

    /**
     * creates a new BST object
     */
    public BST() {
        size = 0;
        flyweight = new LeafNode<String, K>();
        root = flyweight;
    }


    /**
     * copys a bst
     * 
     * @param tree
     *            tree to copy
     */
    public BST(BST<K> tree) {
        flyweight = tree.getFlyweight();
        root = flyweight;
        add(tree.getRoot().getKey(), tree.getRoot().getValue());
        copy(root, tree.getRoot());
        size = tree.getSize();
    }


    /**
     * does the copying
     * 
     * @param node
     *            the root of new tree
     * @param oldRoot
     *            the root of old tree
     */
    private void copy(NodeClass<String, K> node, NodeClass<String, K> oldRoot) {
        if (oldRoot == flyweight) {
            return;
        }
        if (oldRoot.getLeft() != flyweight) {
            DataNode<String, K> newLeft = new DataNode<String, K>(oldRoot
                .getLeft().getKey(), oldRoot.getLeft().getValue(), flyweight);
            node.setLeft(newLeft);
            copy(node.getLeft(), oldRoot.getLeft());
        }

        if (oldRoot.getRight() != flyweight) {
            DataNode<String, K> newRight = new DataNode<String, K>(oldRoot
                .getRight().getKey(), oldRoot.getRight().getValue(), flyweight);
            node.setRight(newRight);
            copy(node.getRight(), oldRoot.getRight());
        }
    }


    /**
     * adds the node to tree
     * 
     * @param key
     *            the key to add
     * @param value
     *            the value to add
     */
    public void add(String key, K value) {
        if (stateData != null) {
            stateData.add(key, value);
        }
        root = add(root, key, value);
        size++;
    }


    /**
     * does the recursive adding to the BST
     * 
     * @param node
     *            the root node
     * @param key
     *            the key to add
     * @param value
     *            the value to add
     */
    private NodeClass<String, K> add(
        NodeClass<String, K> node,
        String key,
        K value) {
        if (node == flyweight) {
            return new DataNode<String, K>(key, value, flyweight);
        }

        if (compare(key, node.getKey()) < 0) {
            node.setLeft(add(node.getLeft(), key, value));
        }
        else if (compare(key, node.getKey()) > 0) {
            node.setRight(add(node.getRight(), key, value));
        }
        else {
            size--;
            node.setValue(value);
            node.setKey(key);
        }

        return node;
    }


    /**
     * removes node from tree
     * 
     * @param key
     *            key to remove
     */
    public void remove(String key) {
        if (stateData != null) {
            stateData.remove(key);
        }
        root = remove(root, key);
        size--;
    }


    /**
     * does the recursive removal
     * 
     * @param node
     *            the root node
     * @param key
     *            the key to remove
     * @return the new root
     */
    private NodeClass<String, K> remove(NodeClass<String, K> node, String key) {
        if (node == flyweight) {
            return flyweight;
        }

        if (compare(key, node.getKey()) < 0) {
            node.setLeft(remove(node.getLeft(), key));
        }
        else if (compare(key, node.getKey()) > 0) {
            node.setRight(remove(node.getRight(), key));
        }
        else {
            if (node.getLeft() == flyweight) {
                return node.getRight();
            }
            else if (node.getRight() == flyweight) {
                return node.getLeft();
            }
            else {
                NodeClass<String, K> minNode = findMin(node.getRight());
                node.setKey(minNode.getKey());
                node.setValue(minNode.getValue());
                node.setRight(remove(node.getRight(), node.getKey()));
            }
        }
        return node;
    }


    /**
     * finds the min in the given BST
     * 
     * @param node
     *            the root of the tree
     * @return the min node
     */
    private NodeClass<String, K> findMin(NodeClass<String, K> node) {
        NodeClass<String, K> temp = node;
        while (temp.getLeft() != flyweight) {
            temp = temp.getLeft();
        }
        return temp;
    }


    /**
     * finds the max in the given BST
     * 
     * @return the max node
     */
    public K findMax() {
        NodeClass<String, K> temp = this.root;
        while (temp.getRight() != flyweight) {
            temp = temp.getRight();
        }
        return temp.getValue();
    }


    /**
     * prints the tree in an inOrder output
     * 
     * @param mode
     *            the print mode
     */
    public void inOrder(int mode) {
        if (isEmpty()) {
            System.out.println("No data available");
            return;
        }
        if (mode == 2 && stateData != null) {
            stateData.inOrder(2);
            return;
        }
        inOrder(this.root, mode, "");
        System.out.println(this.size + " records have been printed");
    }


    /**
     * actually performs the recursive printing
     * 
     * @param node
     *            the root node
     */
    private void inOrder(NodeClass<String, K> node, int mode, String spaces) {
        if (node.getLeft() == flyweight && node.getRight() == flyweight) {
            System.out.println(spaces + node.getKey(mode) + node.getPos());
            return;
        }
        if (node == flyweight) {
            System.out.println(spaces + "E");
            return;
        }
        inOrder(node.getLeft(), mode, spaces + "  ");
        System.out.println(spaces + node.getKey(mode) + node.getPos());
        inOrder(node.getRight(), mode, spaces + "  ");
    }


    /**
     * removes every element in tree
     */
    public void clear() {
        if (stateData != null) {
            stateData.clear();
        }
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
    public NodeClass<String, K> getRoot() {
        return root;
    }


    /**
     * see if tree contains node
     * 
     * @param key
     *            the key to check
     * @return true if contains
     */
    public boolean contains(String key) {
        return contains(root, key);
    }


    /**
     * does the recursive checking if we contain our stuff
     * 
     * @param node
     *            the node to start w
     * @param key
     *            the key to check for
     * @return yes
     */
    private boolean contains(NodeClass<String, K> node, String key) {
        if (node == flyweight) {
            return false;
        }
        if (compare(key, node.getKey()) == 0) {
            return true;
        }
        return (compare(key, node.getKey()) < 0)
            ? contains(node.getLeft(), key)
            : contains(node.getRight(), key);
    }


    public K get(String key) {
        return get(root, key);
    }


    /**
     * does the recursive getting for node w key
     * 
     * @param node
     *            the node to start w
     * @param key
     *            the key to check for
     * @return value if found
     */
    private K get(NodeClass<String, K> node, String key) {
        if (node == flyweight) {
            return null;
        }
        if (compare(key, node.getKey()) == 0) {
            return node.getValue();
        }
        return (compare(key, node.getKey()) < 0)
            ? get(node.getLeft(), key)
            : get(node.getRight(), key);
    }


    /**
     * gets a list of the keys with a grade
     * 
     * @param grade
     *            the grade to check
     * @return array of keys
     */
    public String[] getKeysWithGrade(int grade) {
        String[] returnArray = new String[0];
        return getKeys(this.root, grade).toArray(returnArray);
    }


    /**
     * actually performs the key checking
     * 
     * @param grade
     *            the grade to check for
     * @return yes
     */
    private ArrayList<String> getKeys(NodeClass<String, K> node, int grade) {
        ArrayList<String> result = new ArrayList<String>();
        String[] parts = node.getKey().split("-", -1);
        Integer dataQualityRaw = Integer.parseInt(parts[2]);
        if (node.getLeft() != flyweight) {
            result.addAll(getKeys(node.getLeft(), grade));
        }

        if (node.getRight() != flyweight) {
            result.addAll(getKeys(node.getRight(), grade));
        }

        if (dataQualityRaw >= grade) {
            result.add(node.getKey());
        }
        return result;
    }


    /**
     * gets a list of the keys with a grade
     * 
     * @param grade
     *            the grade to check
     * @return array of keys
     */
    public ArrayList<K> getDataWithGrade(String quality) {
        return getGrades(this.root, CovidData.getDataQualityRaw(quality));
    }


    /**
     * actually performs the key checking
     * 
     * @param grade
     *            the grade to check for
     * @return yes
     */
    private ArrayList<K> getGrades(NodeClass<String, K> node, int quality) {
        ArrayList<K> result = new ArrayList<K>();
        if (node == flyweight) {
            return result;
        }
        String[] parts = node.getKey().split("-", -1);
        if (node.getLeft() != flyweight) {
            result.addAll(getGrades(node.getLeft(), quality));
        }

        if (node.getRight() != flyweight) {
            result.addAll(getGrades(node.getRight(), quality));
        }

        Integer rawQuality = Integer.parseInt(parts[2]);
        if (quality >= rawQuality) {
            result.add(node.getValue());
        }
        else {
            remove(node.getKey());
        }
        return result;
    }


    /**
     * gets a list of the keys with a grade
     * 
     * @param grade
     *            the grade to check
     * @return array of keys
     */
    public ArrayList<K> getDataWithState(String suffix) {
        String fullName = State.fullState(suffix.toUpperCase()).toLowerCase()
            .replaceAll("\\s", "");
        return getStates(this.root, fullName);
    }


    /**
     * actually performs the key checking
     * 
     * @param grade
     *            the grade to check for
     * @return yes
     */
    private ArrayList<K> getStates(NodeClass<String, K> node, String state) {
        ArrayList<K> result = new ArrayList<K>();
        if (node == flyweight) {
            return result;
        }
        String[] parts = node.getKey().split("-", -1);
        if (node.getLeft() != flyweight) {
            result.addAll(getStates(node.getLeft(), state));
        }

        if (node.getRight() != flyweight) {
            result.addAll(getStates(node.getRight(), state));
        }

        if (state.equalsIgnoreCase(parts[1])) {
            result.add(node.getValue());
        }
        else {
            remove(node.getKey());
        }
        return result;
    }


    /**
     * gets a list of the data points from a date
     * 
     * @param grade
     *            the grade to check
     * @return array of keys
     */
    public ArrayList<K> getDataWithDate(String date) {
        return getDates(this.root, date);
    }


    /**
     * actually performs the date checking
     * 
     * @param state
     *            the state to check for
     * @return yes
     */
    private ArrayList<K> getDates(NodeClass<String, K> node, String date) {
        ArrayList<K> result = new ArrayList<K>();
        if (node == flyweight) {
            return result;
        }
        String[] parts = node.getKey().split("-", -1);
        if (node.getLeft() != flyweight) {
            result.addAll(getDates(node.getLeft(), date));
        }

        if (node.getRight() != flyweight) {
            result.addAll(getDates(node.getRight(), date));
        }
        if (date.equals(parts[0])) {
            result.add(node.getValue());
        }
        else {
            remove(node.getKey());
        }
        return result;
    }


    /**
     * gets a list of the data points from a date
     * 
     * @param grade
     *            the grade to check
     * @return array of keys
     */
    public ArrayList<K> get7DayAverage(Integer avg) {
        return getKeysForDates(this.root, date);
    }


    /**
     * actually performs the date checking
     * 
     * @param state
     *            the state to check for
     * @return yes
     */
    private ArrayList<K> getAveragesAbove(NodeClass<String, K> node, int avg) {
        ArrayList<String> result = new ArrayList<String>();
        if (node == flyweight) {
            return result;
        }
        String[] parts = node.getKey().split("-", -1);
        if (node.getLeft() != flyweight) {
            result.addAll(getKeysForDates(node.getLeft(), date));
        }

        if (node.getRight() != flyweight) {
            result.addAll(getKeysForDates(node.getRight(), date));
        }
        if (date.equals(parts[0])) {
            result.add(node.getKey());
        }
        return result;
    }


    /**
     * gets a list of the data points from a date
     * 
     * @param grade
     *            the grade to check
     * @return array of keys
     */
    public ArrayList<K> getDataWithDates(ArrayList<String> dates) {
        return getDataFromDates(this.root, dates);
    }


    /**
     * actually performs the date checking
     * 
     * @param state
     *            the state to check for
     * @return yes
     */
    private ArrayList<K> getDataFromDates(
        NodeClass<String, K> node,
        ArrayList<String> dates) {

        ArrayList<K> result = new ArrayList<K>();
        if (node == flyweight) {
            return result;
        }
        String[] parts = node.getKey().split("-", -1);
        if (node.getLeft() != flyweight) {
            result.addAll(getDataFromDates(node.getLeft(), dates));
        }

        if (node.getRight() != flyweight) {
            result.addAll(getDataFromDates(node.getRight(), dates));
        }
        if (dates.contains(parts[0])) {
            result.add(node.getValue());
        }
        else {
            remove(node.getKey());
        }
        return result;
    }


    /**
     * gets a list of the data points from a date
     * 
     * @param grade
     *            the grade to check
     * @return array of keys
     */
    public ArrayList<K> getDataWithDates(ArrayList<String> dates) {
        return getDataFromDates(this.root, dates);
    }


    /**
     * actually performs the date checking
     * 
     * @param state
     *            the state to check for
     * @return yes
     */
    private ArrayList<K> getDataFromDates(
        NodeClass<String, K> node,
        ArrayList<String> dates) {

        ArrayList<K> result = new ArrayList<K>();
        if (node == flyweight) {
            return result;
        }
        String[] parts = node.getKey().split("-", -1);
        if (node.getLeft() != flyweight) {
            result.addAll(getDataFromDates(node.getLeft(), dates));
        }

        if (node.getRight() != flyweight) {
            result.addAll(getDataFromDates(node.getRight(), dates));
        }
        if (dates.contains(parts[0])) {
            result.add(node.getValue());
        }
        else {
            remove(node.getKey());
        }
        return result;
    }


    /**
     * is the tree empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * @return the flyweight
     */
    public LeafNode<String, K> getFlyweight() {
        return flyweight;
    }


    /**
     * @param stateData
     *            the stateData to set
     */
    public void setStateData(StateOrderedBST<K> stateData) {
        this.stateData = stateData;
    }


    /**
     * compares two strings
     * 
     * @param string1
     *            the first string to compare
     * @param string2
     *            the second string to compare
     */
    public int compare(String string1, String string2) {
        if (string1.equals(string2)) {
            return 0;
        }
        String[] parts1 = string1.split("-", -1);
        String[] parts2 = string2.split("-", -1);
        Integer part1Number = Integer.parseInt(parts1[0]);
        Integer part2Number = Integer.parseInt(parts2[0]);
        int compareDates = part1Number.compareTo(part2Number);
        if (compareDates != 0) {
            return compareDates;
        }
        else {
            return parts1[1].compareTo(parts2[1]);
        }
    }

}
