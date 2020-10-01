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
 * tests our covid data objects
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannong3500)
 * @version 2020.10.01
 */
public class CovidDataTest extends TestCase {

    private CovidData myCovidData;
    private CovidData otherCovidData;
    private CovidData nullData;
    private String[] rawData = { "20200305", "MA", "25.0", "21.0", "5.0", "4.0",
        "0.0", "20.0", "A", "3.0" };


    /**
     * @throws FileNotFoundException
     * 
     *             Used for Test setup
     */
    public void setUp() {
        String[] otherData = { "20200305", "MA", "25.0", "21.0", "5.0", "4.0",
            "0.0", "20.0", "B", "3.0" };
        String[] tempData = { null, null, null, null, null, null, null, null,
            null };
        nullData = new CovidData(tempData);
        myCovidData = new CovidData(rawData);
        otherCovidData = new CovidData(otherData);
    }


    /**
     * tests the getKey function from CovidData
     */
    public void testGetKey() {
        assertEquals("MA-20200305", myCovidData.getKey());
    }


    /**
     * tests the fancyData function in CovidData
     */
    public void testFancyData() {
        assertEquals("03/05/2020", myCovidData.fancyDate());
    }


    /**
     * tests the getDataQuality function in CovidData
     */
    public void testGetDataQuality() {
        assertEquals("A", myCovidData.getDataQuality());
        assertEquals("B", otherCovidData.getDataQuality());
        assertEquals(1, myCovidData.getDataQualityRaw());
        assertEquals(3, otherCovidData.getDataQualityRaw());
        rawData[8] = "B+";
        CovidData dataB = new CovidData(rawData);
        assertEquals(2, dataB.getDataQualityRaw());
        rawData[8] = "C+";
        CovidData dataC = new CovidData(rawData);
        assertEquals(4, dataC.getDataQualityRaw());
        rawData[8] = "D+";
        CovidData dataD = new CovidData(rawData);
        assertEquals(6, dataD.getDataQualityRaw());
        rawData[8] = "Z";
        CovidData dataZ = new CovidData(rawData);
        assertEquals(-1, dataZ.getDataQualityRaw());
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
        String[] tempData = { "20200305", "MA", "25.0", "21.0", "5.0", "4.0",
            "0.0", "20.0", "", "3.0" };
        CovidData testData = new CovidData(tempData);
        assertTrue(myCovidData.isValid());
        assertFalse(testData.isValid());
    }


    /**
     * tests the compare to method for covidData
     */
    public void testCompareTo() {
        assertEquals(1, myCovidData.compareQuality(otherCovidData));
        assertEquals(-1, otherCovidData.compareQuality(myCovidData));
        int num = myCovidData.compareTo(otherCovidData);
        assertEquals(num, myCovidData.compareTo(otherCovidData));
    }


    /**
     * tests the toString method for CovidData
     */
    public void testToStringCovidData() {
        String str = myCovidData.toString();
        assertEquals(str, myCovidData.toString());
        String str2 = nullData.toString();
        assertEquals(str2, nullData.toString());
    }


    /**
     * tests the combineObjects method in CovidData
     */
    public void testCombineObjects() {
        assertTrue(myCovidData.combineObjects(otherCovidData));
        assertTrue(myCovidData.combineObjects(nullData));
        assertTrue(nullData.combineObjects(myCovidData));
    }


    /**
     * tests the getter methods in CovidData
     * 
     */
    public void testGetMethodsCovidData() {
        String state = myCovidData.getFullState();
        assertEquals(state, myCovidData.getFullState());
        assertEquals("MA", myCovidData.getState());
        assertEquals("A", myCovidData.getDataQuality());
        assertEquals(25.0, myCovidData.getPos(), 0.01);
        assertEquals(21.0, myCovidData.getNeg(), 0.01);
        assertEquals(5.0, myCovidData.getHosp(), 0.01);
        assertEquals(4.0, myCovidData.getOnVentCurr(), 0.01);
        assertEquals(0.0, myCovidData.getOnVentTotal(), 0.01);
        assertEquals(20.0, myCovidData.getRecovered(), 0.01);
        assertEquals(3.0, myCovidData.getDeath(), 0.01);
    }


    /**
     * tests a successful update data command
     */
    public void testUpdateData() {
        String[] tempData = { "20200305", "MA", "", "", "", "", "", "", "A",
            "" };
        CovidData testData = new CovidData(tempData);
        assertTrue(testData.isValid());
        assertTrue(testData.updatedData(myCovidData));
        assertEquals(25.0, testData.getPos(), 0.1);
        assertEquals(21.0, testData.getNeg(), 0.1);
        assertEquals(5.0, testData.getHosp(), 0.1);
        assertEquals(4.0, testData.getOnVentCurr(), 0.1);
        assertEquals(0.0, testData.getOnVentTotal(), 0.1);
        assertEquals(20.0, testData.getRecovered(), 0.1);
        assertEquals(3.0, testData.getDeath(), 0.1);
    }


    /**
     * tests a failed update data command
     */
    public void testFailedUpdateData() {
        String[] tempData = { "20200305", "MA", "25.0", "21.0", "5.0", "4.0",
            "0.0", "20.0", "A", "3.0" };
        CovidData testData = new CovidData(tempData);
        assertFalse(testData.updatedData(myCovidData));
        assertFalse(myCovidData.updatedData(testData));
    }
    
    /**
     * tests the deep copy method
     */
    public void testCovidData() {
        CovidData newData = new CovidData(myCovidData);
        assertTrue(newData.isValid());
    }

}
