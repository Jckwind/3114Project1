import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * performs all the searching action
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 24, 2020
 */
public class Searcher {

    private ArrayList<CommandArgs> arguments;

    private BST<CovidData> data;

    private ArrayList<CovidData> results;

    private StringBuilder builder;

    /**
     * creates a new searcher object
     * 
     * @param arguments
     *            the arguments
     */
    public Searcher(ArrayList<CommandArgs> arguments, BST<CovidData> data) {
        this.arguments = arguments;
        this.data = new BST<CovidData>(data);
        this.builder = new StringBuilder();
        this.results = new ArrayList<CovidData>();
    }


    /**
     * searches the BST using these args
     */
    public void search() {
        if (data.isEmpty()) {
            System.out.println("search Failed! No data available");
        }
        for (CommandArgs argument : arguments) {
            if (!search(argument)) {
                return;
            }
        }
        printResults();
    }


    /**
     * searches using these specific params
     * 
     * @param argument
     *            yes
     */
    private boolean search(CommandArgs argument) {
        switch (argument.getType()) {
            case STATE:
                return searchState(argument.getArgs());
            case QUALITY:
                return searchQuality(argument.getArgs());
            case DATE:
                return searchDate(argument.getArgs());
            case DEFAULT:
                return searchNoDate();
            case DAYS:
                return searchDays(argument.getArgs());
            case AVERAGE:
                return searchAverage(argument.getArgs());
            case CONTINUOUSRUN:
                return searchContinuous(argument.getArgs());
            default:
                return false;
        }
    }


    /**
     * searchs the data based on state
     * 
     * @param args
     *            the arguements
     */
    private boolean searchState(String[] args) {
        String stateName = String.join(" ", args);
        String stateAbbr = getStateAbbr(stateName);
        if (stateAbbr == null) {
            return false;
        }
        results = data.getDataWithState(stateAbbr);
        builder.append(" for state " + stateAbbr);
        return true;
    }


    /**
     * searchs the data based on data quality
     * 
     * @param args
     *            the arguements
     */
    private boolean searchQuality(String[] args) {
        String quality = args[0];
        if (CovidData.getDataQualityRaw(quality) == -1) {
            System.out.println(quality + " is not a valid quality grade");
            return false;
        }
        results = data.getDataWithGrade(quality);

        builder.insert(0, " with better or equal than quality grade "
            + quality);
        return true;
    }


    /**
     * searches the data based on date
     * 
     * @param args
     *            the arguements
     */
    private boolean searchDate(String[] args) {
        String date = args[0];
        String searchableData = "";
        try {
            DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
            format.setLenient(false);
            if (date.length() != 10) {
                throw new ParseException(date, 0);
            }
            Date dateData = format.parse(date);
            format = new SimpleDateFormat("yyyymmdd");
            searchableData = format.format(dateData);
        }
        catch (ParseException e) {
            System.out.println("The date " + date + " is not valid");
            return false;
        }

        results = data.getDataWithDate(searchableData);
        builder.append(" on date " + date);
        return true;
    }


    /**
     * searches the data based on date
     * 
     * @param args
     *            the arguements
     */
    private boolean searchNoDate() {
        CovidData maxDateData = data.findMax();
        String date = maxDateData.getDate().toString();
        String[] args = new String[] { date };
        return searchDate(args);
    }


    /**
     * searches the data based on days ago
     * 
     * @param args
     *            the arguements
     */
    private boolean searchDays(String[] args) {
        return false;
    }


    /**
     * searches the data based on 7 day avergae
     * 
     * @param args
     *            the arguements
     */
    private boolean searchAverage(String[] args) {
        return false;
    }


    /**
     * searches the data based on continuous cases above
     * 
     * @param args
     *            the arguements
     */
    private boolean searchContinuous(String[] args) {
        return false;
    }


    /**
     * checks to populate the state abbr field for data lookup
     */
    private String getStateAbbr(String stateName) {
        String stateAbbr;
        if (State.STATE_ABBR_LIST.contains(stateName.toUpperCase())) {
            // statename is already abbr
            stateAbbr = stateName.toUpperCase();
        }
        else if (State.STATE_NAME_LIST.contains(stateName.toUpperCase())) {
            // state name is a full name state
            stateAbbr = State.stateAbbr(stateName);
        }
        else {
            // state does not exist
            System.out.println("The state " + stateName + " does not exist!");
            return null;
        }

        return stateAbbr;
    }


    private void printResults() {
        Object[] headerStrings = { "state", "date", "positive", "negative",
            "hospitalized", "onVentilatorCurrently", "onVentilatorCumulative",
            "recovered", "dataQualityGrade", "death" };
        System.out.format("%s%7s%17s%12s%16s%24s%26s%12s%19s%8s   \n",
            headerStrings);
        for (CovidData myData : results) {
            System.out.format("%-8s", myData.getState());
            System.out.format("%-13s", myData.fancyDate());
            System.out.format("%,-12d", myData.getPos().intValue());
            System.out.format("%,-12d", myData.getNeg().intValue());
            System.out.format("%,-15d", myData.getHosp().intValue());
            System.out.format("%,-25d", myData.getOnVentCurr().intValue());
            System.out.format("%,-25d", myData.getOnVentTotal().intValue());
            System.out.format("%,-12d", myData.getRecovered().intValue());
            System.out.format("%-19s", myData.getDataQuality());
            System.out.format("%,-8d\n", myData.getDeath().intValue());
        }
        System.out.println(results.size() + " records have been printed"
            + builder.toString());
    }

}
