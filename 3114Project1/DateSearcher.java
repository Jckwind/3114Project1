
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
// -- Michael Gannon (mgannon3500)
/**
 * Searches the data based on date
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon
 * @version 2020.09.29
 */
public class DateSearcher {

    private String date;

    private String fancyDate;

    private Map<String, CovidData> data;

    /**
     * creates a new date searcher object
     * 
     * @param date
     *            the date to search
     * @param data
     *            the data to search through
     * @param fancyDate
     *            the date in a different form
     */
    public DateSearcher(
        String date,
        String fancyDate,
        Map<String, CovidData> data) {
        this.date = date;
        this.fancyDate = fancyDate;
        this.data = data;
    }


    /**
     * searches the data
     */
    public void search() {
        if (data.size() == 0) {
            System.out.println("No available data");
        }
        ArrayList<CovidData> correspondingData = getMatchingDateData();
        if (correspondingData.size() == 0) {
            System.out.println("There are no records on " + fancyDate);
            return;
        }
        System.out.println("There are " + correspondingData.size()
            + " records on " + fancyDate);
        // add to split up the header cause of webcat
        Object[] headerStrings = { "state", "positive", "negative",
            "hospitalized", "onVentilatorCurrently", "onVentilatorCumulative",
            "recovered", "dataQualityGrade", "death" };
        System.out.format("%s%11s%12s%16s%24s%26s%12s%19s%8s   \n",
            headerStrings);
        for (CovidData myData : correspondingData) {
            System.out.format("%-8s", myData.getState());
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
     * goes through the data, checks if the key(state-date syntax)
     * contains the date we're searching, if it does, add the data point to the
     * list
     * then sorts the list of data alphabetically
     * 
     * @return a list of covid points from the given date
     */
    private ArrayList<CovidData> getMatchingDateData() {
        ArrayList<CovidData> list = new ArrayList<CovidData>();
        if (date == null) {
            CovidData maxDateData = data.values().stream().max(Comparator
                .comparing(CovidData::getDate)).get();
            date = maxDateData.getDate().toString();
            try {
                DateFormat format = new SimpleDateFormat("yyyymmdd");
                format.setLenient(false);
                Date dateData = format.parse(date);
                format = new SimpleDateFormat("mm/dd/yyyy");
                fancyDate = format.format(dateData);
            }
            catch (ParseException e) {
                // nothing lol, it'll never happen
            }
        }
        for (String key : data.keySet()) {
            if (key.endsWith(date)) {
                list.add(data.get(key));
            }
        }
// Collections.sort(list);
        return list;
    }
}
