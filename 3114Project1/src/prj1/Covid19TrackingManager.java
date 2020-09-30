package prj1;

import java.io.FileNotFoundException;

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
 * The main class of the program
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.27
 */
public class Covid19TrackingManager {

    /**
     * Main method that runs the program
     * @param args
     * @return
     * @throws FileNotFoundException
     */
    public static boolean main(String[] args) throws FileNotFoundException {
        String commandFile = args[0];
        CommandHub hub = new CommandHub(commandFile);
        hub.execute();
        return true;
// System.out.println(hub.toString());
    }
}
