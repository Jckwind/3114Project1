/**
 * 
 */
package prj1;

import student.TestCase;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
//
//-- Jack Windham (jckwind11)
//-- Michael Gannon (mgannon3500)
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
    private CommandHub myHub;
    private String commandFile = "input_1.txt";
    private ArrayList<Command> commands;
    private CommandReader myReader;
    private CovidData myCovidData;
    private CovidData otherCovidData;
    private CovidData nullData;
    private Covid19TrackingManager myMan;
    
    /**
     * @throws FileNotFoundException 
     * @throws java.lang.Exception
     * 
     * Used for Test setup 
	 */
    public void setUp() throws FileNotFoundException {
        String[] rawData = {"03052020", "MA", "25.0", "21.0", 
            "5.0", "4.0", "0.0", "20.0", "A", "3.0"};
        String[] otherData = {"03052020", "MA", "25.0", "21.0", 
            "5.0", "4.0", "0.0", "20.0", "B", "3.0"};
        String[] tempData = {null, null, null, null, null, null, null, null, null};
        nullData = new CovidData(tempData);
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
        myHub = new CommandHub(commandFile);
        commands = new ArrayList<Command>(2);
        commands.add(myCom);
        myReader = new CommandReader(commands, commandFile);
        myCovidData = new CovidData(rawData);
        otherCovidData = new CovidData(otherData);
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
     * tests the execute function for commandHub
     * @throws FileNotFoundException 
     */
    public void testExecute() throws FileNotFoundException {
        assertTrue(myHub.execute());
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
     * @throws FileNotFoundException
     */
    public void testRead() throws FileNotFoundException {
       assertTrue(myReader.read());
    } 
    
    /**
     * tests the getKey function from CovidData
     */
    public void testGetKey() {
        String tempString = myCovidData.getKey();
        assertEquals(tempString, myCovidData.getKey());
    }
    
    /**
     * tests the fancyData function in CovidData
     */
    public void testFancyData() {
        String fancy = myCovidData.fancyDate();
        assertEquals(fancy, myCovidData.fancyDate());
    }
    
    /**
     * tests the getDataQualityRaw function in CovidData
     */
    public void testGetDataQualityRaw() {
        assertEquals(1, myCovidData.getDataQualityRaw());
    }
    
    /**
     * tests the compareQuality function
     */
    public void testCompareQuality() {
        assertEquals(0, myCovidData.compareQuality(myCovidData));
        //assertEquals(-1, myCovidData.compareQuality(otherCovidData));
        //assertEquals(1, otherCovidData.compareQuality(myCovidData));
    }
    
    /**
     * tests the isValid method in CovidData
     */
    public void testIsValid() {
        assertTrue(myCovidData.isValid());
    }
    
    /**
     * tests the compare to method for covidData
     */
    public void testCompareTo() {
        int num = myCovidData.compareTo(otherCovidData);
        assertEquals(num, myCovidData.compareTo(otherCovidData));
    }
    
    /**
     * tests the toString method for CovidData
     */
    public void testToStringCovidData() {
        String str = myCovidData.toString();
        assertEquals(str, myCovidData.toString());
    }
    
    /**
     * tests the combineObjects method in CovidData
     */
    public void testCombineObjects() {
        assertTrue(myCovidData.combineObjects(otherCovidData));
        assertTrue(myCovidData.combineObjects(nullData));
    }
    
    /**
     * tests the getter methods in CovidData
     * 
     */
    public void testGetMethodsCovidData() {
        String state = myCovidData.getFullState();
        assertEquals(state, myCovidData.getFullState());
        assertEquals("MA", myCovidData.getState());
        Integer date = myCovidData.getDate();
        //assertEquals(date, myCovidData.getDate());
        assertEquals(25.0, myCovidData.getPos());
        Double pos = myCovidData.getPos();
        assertEquals(0.0, nullData.getPos());
        assertEquals(21.0, myCovidData.getNeg());
        assertEquals(0.0, nullData.getNeg());
        assertEquals(5.0, myCovidData.getHosp());
        assertEquals(0.0, nullData.getHosp());
        assertEquals(4.0, myCovidData.getOnVentCurr());
        assertEquals(0.0, nullData.getOnVentCurr());
        assertEquals(0.0, myCovidData.getOnVentTotal());
        assertEquals(0.0, nullData.getOnVentTotal());
        assertEquals(20.0, myCovidData.getRecovered());
        assertEquals(0.0, nullData.getRecovered());
        assertEquals("A", myCovidData.getDataQuality());
        assertEquals(3.0, myCovidData.getDeath());
        assertEquals(0.0, nullData.getDeath());
    } 
    
    
    /**
     * tests the Covid19TrackingManger main method
     * @throws FileNotFoundException
     
    public void testMain() throws FileNotFoundException {
        String[] tempArgs = {"intput_1.txt"};
        assertTrue(myMan.main(tempArgs));
    } */
    
    
} 
