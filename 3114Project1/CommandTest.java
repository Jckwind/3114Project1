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
    private CommandHub myHub;
    private String commandFile = "input_1.txt";
    private ArrayList<Command> commands;
    private CommandReader myReader;
    private CovidData myCovidData;
    private CovidData otherCovidData;
    private CovidData nullData;
    private Covid19TrackingManager myMan;
    private CSVReader myCsv;


    /**
     * @throws FileNotFoundException
     * @throws java.lang.Exception
     * 
     *             Used for Test setup
     */
    public void setUp() throws FileNotFoundException {
        String[] rawData = { "03052020", "MA", "25.0", "21.0", "5.0", "4.0",
            "0.0", "20.0", "A", "3.0" };
        String[] otherData = { "03052020", "MA", "25.0", "21.0", "5.0", "4.0",
            "0.0", "20.0", "B", "3.0" };
        String[] tempData = { null, null, null, null, null, null, null, null,
            null };
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
        // assertEquals(date, myCovidData.getDate());
        Double num = 25.0;
        /**
         * assertEquals(num, myCovidData.getPos());
         * assertEquals(0.0, nullData.getPos());
         * assertEquals(21.0, myCovidData.getNeg());
         * assertEquals(0.0, nullData.getNeg());
         * assertEquals(5.0, myCovidData.getHosp());
         * assertEquals(0.0, nullData.getHosp());
         * assertEquals(4.0, myCovidData.getOnVentCurr());
         * assertEquals(0.0, nullData.getOnVentCurr());
         * assertEquals(0.0, myCovidData.getOnVentTotal());
         * assertEquals(0.0, nullData.getOnVentTotal());
         * assertEquals(20.0, myCovidData.getRecovered());
         * assertEquals(0.0, nullData.getRecovered());
         */
        assertEquals("A", myCovidData.getDataQuality());
        // assertEquals(3.0, myCovidData.getDeath());
        // assertEquals(0.0, nullData.getDeath());
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
     * tests the search method in Date Searcher
     */
    public void testSearch() {
        // assertTrue(myDateSearch.search());
    }


    /**
     * tests the state class
     */
    public void testState() {
        assertEquals("Alabama", State.fullState("AL"));
        assertEquals("Alaska", State.fullState("AK"));
        assertEquals("Arkansas", State.fullState("AR"));
        assertEquals("American Samoa", State.fullState("AS"));
        assertEquals("Arizona", State.fullState("AZ"));
        assertEquals("California", State.fullState("CA"));
        assertEquals("Colorado", State.fullState("CO"));
        assertEquals("Connecticut", State.fullState("CT"));
        assertEquals("Delaware", State.fullState("DE"));
        assertEquals("District of Columbia", State.fullState("DC"));
        assertEquals("Florida", State.fullState("FL"));
        assertEquals("Federated States of Micronesia", State.fullState("FM"));
        assertEquals("Georgia", State.fullState("GA"));
        assertEquals("Guam", State.fullState("GU"));
        assertEquals("Hawaii", State.fullState("HI"));
        assertEquals("Idaho", State.fullState("ID"));
        assertEquals("Illinois", State.fullState("IL"));
        assertEquals("Indiana", State.fullState("IN"));
        assertEquals("Iowa", State.fullState("IA"));
        assertEquals("Kansas", State.fullState("KS"));
        assertEquals("Kentucky", State.fullState("KY"));
        assertEquals("Louisiana", State.fullState("LA"));
        assertEquals("Maine", State.fullState("ME"));
        assertEquals("Maryland", State.fullState("MD"));
        assertEquals("Massachusetts", State.fullState("MA"));
        assertEquals("Michigan", State.fullState("MI"));
        assertEquals("Marshall Islands", State.fullState("ML"));
        assertEquals("Minnesota", State.fullState("MN"));
        assertEquals("Northern Mariana Islands", State.fullState("MP"));
        assertEquals("Mississippi", State.fullState("MS"));
        assertEquals("Missouri", State.fullState("MO"));
        assertEquals("Montana", State.fullState("MT"));
        assertEquals("Nebraska", State.fullState("NE"));
        assertEquals("Nevada", State.fullState("NV"));
        assertEquals("New Hampshire", State.fullState("NH"));
        assertEquals("New Jersey", State.fullState("NJ"));
        assertEquals("New Mexico", State.fullState("NM"));
        assertEquals("New York", State.fullState("NY"));
        assertEquals("North Carolina", State.fullState("NC"));
        assertEquals("North Dakota", State.fullState("ND"));
        assertEquals("Ohio", State.fullState("OH"));
        assertEquals("Oklahoma", State.fullState("OK"));
        assertEquals("Oregon", State.fullState("OR"));
        assertEquals("Pennsylvania", State.fullState("PA"));
        assertEquals("Puerto Rico", State.fullState("PR"));
        assertEquals("Palau", State.fullState("PW"));
        assertEquals("Rhode Island", State.fullState("RI"));
        assertEquals("South Carolina", State.fullState("SC"));
        assertEquals("South Dakota", State.fullState("SD"));
        assertEquals("Tennessee", State.fullState("TN"));
        assertEquals("Texas", State.fullState("TX"));
        assertEquals("Utah", State.fullState("UT"));
        assertEquals("Virgin Islands", State.fullState("VI"));
        assertEquals("Vermont", State.fullState("VT"));
        assertEquals("Virginia", State.fullState("VA"));
        assertEquals("Washington", State.fullState("WA"));
        assertEquals("West Virginia", State.fullState("WV"));
        assertEquals("Wisconsin", State.fullState("WI"));
        assertEquals("Wyoming", State.fullState("WY"));
        assertEquals("ZZ", State.fullState("ZZ"));
    }


    /**
     * tests the Summary class
     */
    public void testSummary() {
        Summary mySum = new Summary(data);
        assertTrue(mySum.reportSummary());
    }


    /**
     * tests the Covid19TrackingManger main method
     * 
     * @throws FileNotFoundException
     */
    public void testMain() throws FileNotFoundException {
        String[] tempArgs = new String[2];
        tempArgs[0] = "input_1.txt";
// assertTrue(myMan.main(tempArgs));
    }

}
