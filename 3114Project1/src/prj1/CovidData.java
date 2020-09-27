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
public class CovidData {

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
     * @return the state
     */
    public String getState() {
        return state;
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
     * determines if this data point is missing any data
     * 
     * @return true if missing any data
     */
    private boolean hasEmptyData() {
        return pos == null || neg == null || hosp == null || onVentCurr == null
            || onVentTotal == null || recovered == null || death == null;
    }


    /**
     * decideds if this current data point has any missing data, and if there is
     * any missing data use the new data point to fill it in
     * 
     * @param newData
     *            new data point
     */
    public void fillMissingData(CovidData newData) {
        if (!hasEmptyData()) {
            return;
        }
        if (this.pos == null) {
            this.pos = newData.pos;
        }
        if (this.neg == null) {
            this.neg = newData.neg;
        }
        if (this.hosp == null) {
            this.hosp = newData.hosp;
        }
        if (this.onVentCurr == null) {
            this.onVentCurr = newData.onVentCurr;
        }
        if (this.onVentTotal == null) {
            this.onVentTotal = newData.onVentTotal;
        }
        if (this.recovered == null) {
            this.recovered = newData.recovered;
        }
        if (this.death == null) {
            this.death = newData.death;
        }
        System.out.println("Data has been updated for the missing data in "
            + this.state);
    }


    @Override
    public String toString() {
        return date + ", " + state + ", " + pos + ", " + neg + ", "
            + dataQuality + "\n";
    }
}
