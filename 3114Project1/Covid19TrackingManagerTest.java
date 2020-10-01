import java.io.File;
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
/**
 * Tests the main project runner to ensure the .csv dump file is created
 *
 * @author Jack Windham (jckwind11)
 * @version 2020.10.01
 */
public class Covid19TrackingManagerTest extends TestCase {

    /**
     * tests the Covid19TrackingManger main method
     * 
     * @throws FileNotFoundException
     */
    public void testMain() throws FileNotFoundException {
        String[] tempArgs = { "input_1.txt" };
        Covid19TrackingManager.main(tempArgs);
        File f = new File("xyz13da.csv");
        assertTrue(f.exists());
    }

}
