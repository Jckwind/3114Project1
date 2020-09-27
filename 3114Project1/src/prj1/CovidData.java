package prj1;

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
 * Description of Class
 *
 * @author Jack Windham (jckwind11)
 * @version 2020.09.27
 */
public class CovidData {

    private String date;

    private String state;

    private String dataQuality;

    private double pos, neg, hosp, onVentCurr, onVentTotal, recovered, death;


    /**
     * creates a new covid data object
     * 
     * @param date
     * @param state
     * @param pos
     * @param neg
     * @param hosp
     * @param onVentCurr
     * @param onVentTotal
     * @param recovered
     * @param dataQuality
     * @param death
     */
    public CovidData(String[] rawData) {
        this.date = rawData[0];
        this.state = rawData[1];
        this.dataQuality = rawData[8];
        ////// converts every string value to double
        try {
            pos = Double.parseDouble(rawData[2]);
        }
        catch (Exception e) {
            pos = 0;
        }
        //////
        try {
            neg = Double.parseDouble(rawData[3]);
        }
        catch (Exception e) {
            neg = 0;
        }
        //////
        try {
            hosp = Double.parseDouble(rawData[4]);
        }
        catch (Exception e) {
            hosp = 0;
        }
        //////
        try {
            onVentCurr = Double.parseDouble(rawData[5]);
        }
        catch (Exception e) {
            onVentCurr = 0;
        }
        //////
        try {
            onVentTotal = Double.parseDouble(rawData[6]);
        }
        catch (Exception e) {
            onVentTotal = 0;
        }
        //////
        try {
            recovered = Double.parseDouble(rawData[7]);
        }
        catch (Exception e) {
            recovered = 0;
        }
        //////
        try {
            death = Double.parseDouble(rawData[9]);
        }
        catch (Exception e) {
            death = 0;
        }
    }


    /**
     * returns the key for this data point
     * 
     * @return the key
     */
    public String getKey() {
        return state + "-" + date;
    }


    @Override
    public String toString() {
        return date + ", " + state + ", " + pos + ", " + neg + ", "
            + dataQuality + "\n";
    }
}
