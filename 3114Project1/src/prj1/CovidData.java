package prj1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class CovidData implements Comparable<Object> {

    private String date;

    private String state;

    private String dataQuality;

    private Double pos, neg, hosp, onVentCurr, onVentTotal, recovered, death;


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
            pos = null;
        }
        //////
        try {
            neg = Double.parseDouble(rawData[3]);
        }
        catch (Exception e) {
            neg = null;
        }
        //////
        try {
            hosp = Double.parseDouble(rawData[4]);
        }
        catch (Exception e) {
            hosp = null;
        }
        //////
        try {
            onVentCurr = Double.parseDouble(rawData[5]);
        }
        catch (Exception e) {
            onVentCurr = null;
        }
        //////
        try {
            onVentTotal = Double.parseDouble(rawData[6]);
        }
        catch (Exception e) {
            onVentTotal = null;
        }
        //////
        try {
            recovered = Double.parseDouble(rawData[7]);
        }
        catch (Exception e) {
            recovered = null;
        }
        //////
        try {
            death = Double.parseDouble(rawData[9]);
        }
        catch (Exception e) {
            death = null;
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


    /**
     * returns the mm/dd/yyyy date formate
     * 
     * @return the date
     */
    public String fancyDate() {
        DateFormat format = new SimpleDateFormat("yyyymmdd");
        try {
            Date dateData = format.parse(this.date);
            format = new SimpleDateFormat("mm/dd/yyyy)");
            return format.format(dateData);
        }
        catch (ParseException e) {
            return "error";
        }
    }


    /**
     * returns a number value based on data quality
     * 
     * @return
     */
    public int getDataQualityRaw() {
        switch (dataQuality) {
            case "A+":
                return 0;
            case "A":
                return 1;
            case "B+":
                return 2;
            case "B":
                return 3;
            case "C+":
                return 4;
            case "C":
                return 5;
            case "D+":
                return 6;
            case "D":
                return 7;
            case "F":
                return 8;
            default:
                return -1;
        }
    }


    /**
     * compares the quality of two data points
     * 
     * @param otherData
     *            the other data point
     * @return 0 if equal, 1 if greater, -1 if less
     * 
     */
    public int compareQuality(CovidData otherData) {
        if (otherData.getDataQualityRaw() == this.getDataQualityRaw()) {
            return 0;
        }
        return (otherData.getDataQualityRaw() > this.getDataQualityRaw())
            ? 1
            : -1;
    }


    /**
     * if a data point is valid
     * 
     * @return true if valid
     */
    public boolean isValid() {
        return !date.isEmpty() && !state.isEmpty() && !dataQuality.isEmpty();
    }


    /**
     * decides if the state is true;
     * 
     * @return true if valid
     */
    public boolean stateIsValid() {
        return State.stateAbbrList.contains(this.state);
    }


    @Override
    public int compareTo(Object o) {
        CovidData otherObject = (CovidData)o;
        return this.getFullState().compareTo(otherObject.getFullState());
    }


    @Override
    public String toString() {
        return date + ", " + state + ", " + pos + ", " + neg + ", "
            + dataQuality + "\n";
    }


    /**
     * combines the death, hosp and cases of two objects
     * 
     * @param otherData
     *            the other data
     */
    public void combineObjects(CovidData otherData) {
        if (this.pos == null) {
            this.pos = 0.0;
        }
        if (this.death == null) {
            this.death = 0.0;
        }
        if (this.hosp == null) {
            this.hosp = 0.0;
        }
        this.pos += otherData.getPos();
        this.death += otherData.getDeath();
        this.hosp += otherData.getHosp();
    }


    /**
     * returns the full state given the apprievation
     * 
     * @return
     */
    public String getFullState() {
        return State.fullState(this.state);
    }


    /**
     * @return the state
     */
    public String getState() {
        return state;
    }


    /**
     * @return the date
     */
    public Integer getDate() {
        return Integer.parseInt(date);
    }


    /**
     * @return the pos
     */
    public Double getPos() {
        return pos != null ? pos : 0;
    }


    /**
     * @return the neg
     */
    public Double getNeg() {
        return neg != null ? neg : 0;
    }


    /**
     * @return the hosp
     */
    public Double getHosp() {
        return hosp != null ? hosp : 0;
    }


    /**
     * @return the onVentCurr
     */
    public Double getOnVentCurr() {
        return onVentCurr != null ? onVentCurr : 0;
    }


    /**
     * @return the onVentTotal
     */
    public Double getOnVentTotal() {
        return onVentTotal != null ? onVentTotal : 0;
    }


    /**
     * @return the recovered
     */
    public Double getRecovered() {
        return recovered != null ? recovered : 0;
    }


    /**
     * @return the dataQuality
     */
    public String getDataQuality() {
        return dataQuality;
    }


    /**
     * @return the death
     */
    public Double getDeath() {
        return death != null ? death : 0;
    }
}
