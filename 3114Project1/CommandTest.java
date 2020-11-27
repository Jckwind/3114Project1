
/**
 * 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
 * tests the command file
 * 
 * @author Michael Gannon (mgannon3500)
 * @author jack Windham (jckwind11)
 * @version 2020.09.27
 */
public class CommandTest extends TestCase {

    private ArrayList<String> args = new ArrayList<String>(5);
    private ArrayList<String> arg1 = new ArrayList<String>(1);
    private String commandFile = "input_1.txt";
    private ArrayList<Command> commands;
    private CommandReader myReader;

    /**
     * @throws FileNotFoundException
     * @throws java.lang.Exception
     * 
     *             Used for Test setup
     */
    public void setUp() throws FileNotFoundException {
        args.add("VA");
        args.add("1752");
        arg1.add("03/05/2020");
        Command myCom = new Command(CommandEnum.LOAD, args);
        commands = new ArrayList<Command>(2);
        commands.add(myCom);
        myReader = new CommandReader(commands, commandFile);
    }


    /**
     * tests the toString function for commandHub
     * 
     * @throws FileNotFoundException
     */
    public void testToString() throws FileNotFoundException {
        String str = commands.toString();
        assertEquals(str, commands.toString());
        CommandHub myHub = new CommandHub(commandFile);
        myHub.execute();
        String str2 = myHub.toString();
        assertEquals(str2, myHub.toString());
    }


    /**
     * tests the read function for commandReader
     * 
     * @throws FileNotFoundException
     */
    public void testRead() throws FileNotFoundException {
        assertTrue(myReader.read());
        assertEquals(CommandEnum.LOAD, myReader.getType("load"));
        assertEquals(CommandEnum.SEARCH, myReader.getType("search"));
        assertEquals(CommandEnum.DUMP, myReader.getType("dumpbst"));
        assertEquals(CommandEnum.ERROR, myReader.getType("other"));
    }

}
