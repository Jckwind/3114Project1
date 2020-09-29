package prj1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

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
 * Searches the data based on date
 *
 * @author Jack Windham (jckwind11)
 * @version 2020.09.29
 */
public class DateSearcher {

    private String date;

    private String fancyData;

    private Map<String, CovidData> data;


    /**
     * creates a new date searcher object
     * 
     * @param date
     *            the date to search
     * @param data
     *            the data to search through
     */
    public DateSearcher(
        String date,
        String fancyDate,
        Map<String, CovidData> data) {
        this.date = date;
        this.fancyData = fancyDate;
        this.data = data;
    }


    /**
     * searches the data
     */
    public void search() {
        ArrayList<CovidData> correspondingData = getMatchingDateData();
        System.out.println("There are " + correspondingData.size()
            + " records on " + fancyData);
        // add to split up the header cause of webcat
        Object[] headerStrings = { "state", "positive", "negative",
            "hospitalized", "onVentilatorCurrently", "onVentilatorCumulative",
            "recovered", "dataQualityGrade", "death" };
        System.out.format("%s%11s%12s%16s%24s%26s%12s%19s%8s\n", headerStrings);
        for (CovidData data : correspondingData) {
            System.out.format("%-8s", data.getState());
            System.out.format("%,-12d", data.getPos().intValue());
            System.out.format("%,-12d", data.getNeg().intValue());
            System.out.format("%,-15d", data.getHosp().intValue());
            System.out.format("%,-25d", data.getOnVentCurr().intValue());
            System.out.format("%,-25d", data.getOnVentTotal().intValue());
            System.out.format("%,-12d", data.getRecovered().intValue());
            System.out.format("%-19s", data.getDataQuality());
            System.out.format("%,-12d\n", data.getDeath().intValue());
        }
    }


    private ArrayList<CovidData> getMatchingDateData() {
        ArrayList<CovidData> list = new ArrayList<CovidData>();
        for (String key : data.keySet()) {
            if (key.endsWith(date)) {
                list.add(data.get(key));
            }
        }
        Collections.sort(list, Comparator.comparing(CovidData::getState));
        return list;
    }
}