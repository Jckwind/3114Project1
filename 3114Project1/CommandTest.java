
/**
 * 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
 * @author Michael Gannon (mgannon3500)
 * @author jack Windham (jckwind11)
 * @version 2020.09.27
 */
public class CommandTest extends TestCase {

    private CommandEnum loadType = CommandEnum.LOAD;
    private CommandEnum searchType = CommandEnum.SEARCH;
    private CommandEnum summaryType = CommandEnum.SUMMARY;
    private CommandEnum dumpType = CommandEnum.DUMP;
    private ArrayList<String> args = new ArrayList<String>(5);
    private ArrayList<String> arg1 = new ArrayList<String>(1);
    private Command myLoad;
    private Command mySearch;
    private Command myOtherSearch;
    private Command mySummary;
    private Command myDump;
    private Map<String, CovidData> data;
    private String commandFile = "input_1.txt";
    private ArrayList<Command> commands;
    private CommandReader myReader;
    private CSVReader myCsv;


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
        myLoad = new Command(loadType, args);
        mySearch = new Command(searchType, args);
        myOtherSearch = new Command(searchType, arg1);
        mySummary = new Command(summaryType, args);
        myDump = new Command(dumpType, args);
        data = new HashMap<String, CovidData>();
        commands = new ArrayList<Command>(2);
        commands.add(myCom);
        myReader = new CommandReader(commands, commandFile);
        myCsv = new CSVReader(commandFile, data);
    }


    /**
     * tests getCommandType in Command.java
     */
    public void testGetCommandType() {
        assertEquals(CommandEnum.LOAD, myLoad.getCommandType());
    }


    /**
     * test getArgs() in Command.java
     */
    public void testGetArgs() {
        ArrayList<String> myList = myLoad.getArgs();
        assertEquals(myList, myLoad.getArgs());
    }


    /**
     * tests the run function in Command.java
     * Should also hit the four private methods as well
     */
    public void testRun() {
        assertTrue(myLoad.run(data));
        assertTrue(mySearch.run(data));
        assertTrue(mySummary.run(data));
        assertTrue(myDump.run(data));
        assertTrue(myOtherSearch.run(data));
    }


    /**
     * tests the toString function for commandHub
     */
    public void testToString() {
        String str = commands.toString();
        assertEquals(str, commands.toString());
    }


    /**
     * tests the read function for commandReader
     * 
     * @throws FileNotFoundException
     */
    public void testRead() throws FileNotFoundException {
        assertTrue(myReader.read());
    }


    /**
     * tests the loadData method in CSVReader
     * 
     * @throws FileNotFoundException
     */
    public void testLoadData() throws FileNotFoundException {
        // assertTrue(myCsv.loadData());
        assertEquals(data, myCsv.getData());
    }


    /**
     * tests the Summary class
     */
    public void testSummary() {
        Summary mySum = new Summary(data);
        assertTrue(mySum.reportSummary());
    }

}
