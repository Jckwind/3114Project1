import java.io.FileNotFoundException;
import student.TestCase;

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
 * tests my bst class
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 25, 2020
 */
public class BSTTest extends TestCase {

    private BST<Integer> bst;

    /**
     * @throws FileNotFoundException
     * 
     *             Used for Test setup
     */
    public void setUp() {
        bst = new BST<Integer>();
        bst.add("5-test", 5);
        bst.add("10-test", 10);
        bst.add("12-test", 12);
        bst.add("3-test", 3);
        bst.add("28-test", 28);
    }


    /**
     * test add method
     */
    public void testAdd() {
        assertEquals(bst.getSize(), 5);
        bst.add("4-test", 7);
        bst.add("2-test", 7);
        bst.add("7-test", 7);
        assertEquals(bst.getSize(), 8);
    }


    /**
     * tests the contains method
     */
    public void testContains() {
        assertEquals(false, bst.contains("11-test"));
        bst.add("11-test", 11);
        assertEquals(true, bst.contains("11-test"));
    }


    /**
     * tests the remove method
     */
    public void testRemove() {
        bst.remove("5-test");
        assertEquals(4, bst.getSize());
        assertEquals("10-test", bst.getRoot().getKey());
        assertEquals(10, bst.getRoot().getValue().intValue());
    }


    /**
     * tests clear
     */
    public void testClear() {
        assertEquals(5, bst.getSize());
        bst.clear();
        assertEquals(0, bst.getSize());
        assertTrue(bst.isEmpty());
        assertEquals(bst.getFlyweight(), bst.getRoot());
    }


    /**
     * tests the git method
     */
    public void testGet() {
        assertEquals(28, bst.get("28-test").intValue());
        assertNull(bst.get("29-test"));
    }


    /**
     * tests copy bst
     */
    public void testSecondCon() {
        BST<Integer> bst2 = new BST<Integer>(bst);
        assertEquals(5, bst2.getSize());
        assertEquals(bst.getRoot().getLeft().getKey(), bst2.getRoot().getLeft()
            .getKey());
        assertEquals(bst.getRoot().getRight().getKey(), bst2.getRoot()
            .getRight().getKey());

        assertEquals(bst.getRoot().getLeft().getLeft(), bst2.getRoot().getLeft()
            .getLeft());
        assertEquals(bst.getRoot().getRight().getRight().getKey(), bst2
            .getRoot().getRight().getRight().getKey());
    }

}
