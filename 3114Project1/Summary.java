

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
 * Prints out a summary of curr
 *
 * @author Jack Windham (jckwind11)
 * @version 2020.09.29
 */
public class Summary {

    private Map<String, CovidData> rawData;


    /**
     * creates a new summary object
     * 
     * @param rawData
     *            in map form
     */
    public Summary(Map<String, CovidData> rawData) {
        this.rawData = rawData;
    }


    /**
     * prints the summary data
     * 
     * @return boolean
     */
    public boolean reportSummary() {
        ArrayList<CovidData> condensedData = condensedData();
        Object[] headerStrings = { "State", "Total Case", "Total Death",
            "Total Hospitalized" };
        System.out.println("Data Summary for " + condensedData.size()
            + " states:");
        System.out.format("%s%13s%13s%21s\n", headerStrings);
        Integer totalCases = 0;
        Integer totalDeaths = 0;
        Integer totalHospital = 0;
        for (CovidData data : condensedData) {
            totalCases += data.getPos().intValue();
            totalDeaths += data.getDeath().intValue();
            totalHospital += data.getHosp().intValue();
            System.out.format("%-8s", data.getState());
            System.out.format("%,-12d", data.getPos().intValue());
            System.out.format("%,-14d", data.getDeath().intValue());
            System.out.format("%,-12d\n", data.getHosp().intValue());
        }
        System.out.format("Total Cases: %,d\n", totalCases);
        System.out.format("Total Death: %,d\n", totalDeaths);
        System.out.format("Total Hospitalized: %,d\n", totalHospital);
        return true;
    }


    /**
     * makes sure if there are multiple data points from a state, we use the
     * most recent data
     */
    private ArrayList<CovidData> condensedData() {
        // create new hashmap, so each state can have one entry
        Map<String, CovidData> condensedMap = new HashMap<String, CovidData>();
        for (CovidData data : rawData.values()) {
            String state = data.getState();
            // if the state has no entry, insert it in hashmap
            if (!condensedMap.containsKey(state)) {
                condensedMap.put(state, data);
            }
            else {
                // state already has entry, combine data
                CovidData existingCovidData = condensedMap.get(state);
                existingCovidData.combineObjects(data);
            }
        }
        ArrayList<CovidData> result = new ArrayList<CovidData>(condensedMap
            .values());
        // sort the resulting arraylist
        Collections.sort(result);
        return result;
    }
}
