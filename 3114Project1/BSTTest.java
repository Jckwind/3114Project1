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
        bst.add("test-5", 5);
        bst.add("test-10", 10);
        bst.add("test-12", 12);
        bst.add("test-3", 3);
        bst.add("test-28", 28);
    }


    /**
     * test add method
     */
    public void testAdd() {
        assertEquals(bst.getSize(), 5);
        bst.add("test-4", 7);
        bst.add("test-2", 7);
        bst.add("test-7", 7);
        assertEquals(bst.getSize(), 8);
    }


    /**
     * tests the contains method
     */
    public void testContains() {
        assertEquals(false, bst.contains("test-11"));
        bst.add("test-11", 11);
        assertEquals(true, bst.contains("test-11"));
    }


    /**
     * tests the remove method
     */
    public void testRemove() {
        bst.remove("test-5");
        assertEquals(4, bst.getSize());
        assertEquals("test-10", bst.getRoot().getKey());
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
        assertEquals(28, bst.get("test-28").intValue());
        assertNull(bst.get("test-29"));
    }

}
