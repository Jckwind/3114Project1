
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
// -- Michael Gannon (mgannon3500)
/**
 * Description of Class
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.27
 */
public class CovidData implements Comparable<CovidData> {

    private String date;

    private String state;

    private String dataQuality;

    private Double pos;

    private Double neg;

    private Double hosp;

    private Double onVentCurr;

    private Double onVentTotal;

    private Double recovered;

    private Double death;

    /**
     * Creates a new CovidData object
     * 
     * @param rawData
     *            A string array of all the data raw
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
     * makes a deep copy
     * 
     * @param copy
     *            the copy
     */
    public CovidData(CovidData copy) {
        this.state = copy.state;
        this.date = copy.date;
        this.pos = copy.pos;
        this.neg = copy.neg;
        this.hosp = copy.hosp;
        this.onVentCurr = copy.onVentCurr;
        this.onVentTotal = copy.onVentTotal;
        this.recovered = copy.recovered;
        this.dataQuality = copy.dataQuality;
        this.death = copy.death;
    }


    /**
     * returns the key for this data point
     * 
     * @return the key
     */
    public String getKey() {
        String fullName = getFullState().toLowerCase().replaceAll("\\s", "");
        return date + "-" + fullName + "-" + getDataQualityRaw();
    }


    public String getKey(int mode) {
        StringBuilder builder = new StringBuilder();
        builder.append("<");
        if (mode == 2) {
            builder.append(state);
            builder.append(", " + fancyDate());
        }
        else {
            builder.append(fancyDate());
            builder.append(", " + state);
        }
        builder.append("> ");
        return builder.toString();
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
            format = new SimpleDateFormat("mm/dd/yyyy");
            return format.format(dateData);
        }
        catch (ParseException e) {
            return "error";
        }
    }


    /**
     * returns a number value based on data quality
     * 
     * @return int of Data quality raw
     */
    public int getDataQualityRaw() {
        return getDataQualityRaw(dataQuality);
    }


    /**
     * returns a number value based on data quality
     * 
     * @return int of Data quality raw
     */
    public static int getDataQualityRaw(String quality) {
        switch (quality) {
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
        return (otherData.getDataQualityRaw() < this.getDataQualityRaw())
            ? 1
            : -1;
    }


    /**
     * updates the object if it has empty fields
     * 
     * @param newData
     *            the object
     * @return if it was updated
     */
    public boolean updatedData(CovidData newData) {
        boolean updated = false;
        if (pos == null && newData.pos != null) {
            this.pos = newData.pos;
            updated = true;
        }
        if (neg == null && newData.neg != null) {
            this.neg = newData.neg;
            updated = true;
        }
        if (hosp == null && newData.hosp != null) {
            this.hosp = newData.hosp;
            updated = true;
        }
        if (onVentCurr == null && newData.onVentCurr != null) {
            this.onVentCurr = newData.onVentCurr;
            updated = true;
        }
        if (onVentTotal == null && newData.onVentTotal != null) {
            this.onVentTotal = newData.onVentTotal;
            updated = true;
        }
        if (recovered == null && newData.recovered != null) {
            this.recovered = newData.recovered;
            updated = true;
        }
        if (death == null && newData.death != null) {
            this.death = newData.death;
            updated = true;
        }
        return updated;
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
        return State.STATE_ABBR_LIST.contains(this.state.toUpperCase());
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(date + ",");
        builder.append(state + ",");
        builder.append(pos == null
            ? ","
            : String.format("%d,", pos.intValue()));
        builder.append(neg == null
            ? ","
            : String.format("%d,", neg.intValue()));
        builder.append(hosp == null
            ? ","
            : String.format("%d,", hosp.intValue()));
        builder.append(onVentCurr == null
            ? ","
            : String.format("%d,", onVentCurr.intValue()));
        builder.append(onVentTotal == null
            ? ","
            : String.format("%d,", onVentTotal.intValue()));
        builder.append(recovered == null
            ? ","
            : String.format("%d,", recovered.intValue()));
        builder.append(dataQuality + ",");
        builder.append(death == null
            ? ","
            : String.format("%d,", death.intValue()));
        return builder.toString();
    }


    /**
     * combines the death, hosp and cases of two objects
     * 
     * @param otherData
     *            The other data to be combined with this data
     * @return boolean
     */
    public boolean combineObjects(CovidData otherData) {
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
        return true;
    }


    /**
     * returns the full state given the apprievation
     * 
     * @return String of full state
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


    @Override
    public int compareTo(CovidData o) {
        String thisState = getFullState().toLowerCase().replaceAll("\\s", "");
        String thatState = o.getFullState().toLowerCase().replaceAll("\\s", "");
        if (thisState.compareTo(thatState) == 0) {
            return this.getDate().compareTo(o.getDate());
        }
        else {
            return thisState.compareTo(thatState);
        }
    }
}
