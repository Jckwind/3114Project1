
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * Searches the data based on state data
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.29
 */
public class StateSearcher {

    private String stateName;

    private String stateAbbr;

    private Integer numOfRecords;

    private Map<String, CovidData> data;


    /**
     * creates a new state searcher object
     * 
     * @param data
     *            the data to search
     * @param stateName
     *            the states name or abbrv
     * @param numOfRecords
     *            number of records to return
     */
    public StateSearcher(
        Map<String, CovidData> data,
        String stateName,
        Integer numOfRecords) {
        this.data = data;
        this.stateName = stateName;
        this.numOfRecords = numOfRecords;
        this.getStateAbbr();
    }


    /**
     * searches the data given the state
     */
    public void search() {
        if (stateAbbr == null) {
            return;
        }
        // sorts the data so most recent first
        Comparator<CovidData> comparator = Comparator.comparing(point -> point
            .getDate());
        comparator = comparator.reversed();
        // filters the data to get data only from state
        // sorts by date and limits by number of records
        Stream<CovidData> stream = data.values().stream().filter(point -> point
            .getState().equals(stateAbbr)).sorted(comparator).limit(
                numOfRecords);
        List<CovidData> dataPoints = stream.collect(Collectors.toList());
        if (dataPoints.size() == 0) {
            System.out.println("There are no records from " + stateName);
            return;
        }
        System.out.println(dataPoints.size()
            + " records are printed out for the state of " + stateName);
        Object[] headerStrings = { "date", "positive", "negative",
            "hospitalized", "onVentilatorCurrently", "onVentilatorCumulative",
            "recovered", "dataQualityGrade", "death" };
        System.out.format("%s%19s%12s%16s%24s%26s%12s%19s%8s   \n",
            headerStrings);
        for (CovidData myData : dataPoints) {
            System.out.format("%-15s", myData.fancyDate());
            System.out.format("%,-12d", myData.getPos().intValue());
            System.out.format("%,-12d", myData.getNeg().intValue());
            System.out.format("%,-15d", myData.getHosp().intValue());
            System.out.format("%,-25d", myData.getOnVentCurr().intValue());
            System.out.format("%,-25d", myData.getOnVentTotal().intValue());
            System.out.format("%,-12d", myData.getRecovered().intValue());
            System.out.format("%-19s", myData.getDataQuality());
            System.out.format("%,-8d\n", myData.getDeath().intValue());
        }

    }


    /**
     * checks to populate the state abbr field for data lookup
     */
    private void getStateAbbr() {
        if (State.STATE_ABBR_LIST.contains(stateName.toUpperCase())) {
            // statename is already abbr
            this.stateAbbr = this.stateName.toUpperCase();
        }
        else if (State.STATE_NAME_LIST.contains(stateName.toUpperCase())) {
            // state name is a full name state
            this.stateAbbr = State.stateAbbr(stateName);
        }
        else {
            // state does not exist
            System.out.println("State of " + stateName + " does not exist!");
        }
    }
}
