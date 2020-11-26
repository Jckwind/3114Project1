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

    /**
     * creates a new searcher object
     * 
     * @param arguments
     *            the arguments
     */
    public Searcher(ArrayList<CommandArgs> arguments, BST<CovidData> data) {
        this.arguments = arguments;
        this.data = new BST<CovidData>(data);
    }


    /**
     * searches the BST using these args
     */
    public void search() {
        for (CommandArgs argument : arguments) {
            search(argument);
        }
    }


    /**
     * searches using these specific params
     * 
     * @param argument
     *            yes
     */
    private void search(CommandArgs argument) {
        switch (argument.getType()) {
            case STATE:
                searchState(argument.getArgs());
                break;
            case QUALITY:
                searchQuality(argument.getArgs());
                break;
            case DATE:
                searchDate(argument.getArgs());
                break;
            case DEFAULT:
                searchNoDate();
                break;
            case DAYS:
                searchDays(argument.getArgs());
                break;
            case AVERAGE:
                searchAverage(argument.getArgs());
                break;
            case CONTINUOUSRUN:
                searchContinuous(argument.getArgs());
                break;
            default:
                return;
        }
    }


    /**
     * searchs the data based on state
     * 
     * @param args
     *            the arguements
     */
    private void searchState(String[] args) {

        String stateName = String.join(" ", args);
        String stateAbbr = getStateAbbr(stateName);
        if (stateAbbr == null) {
            return;
        }
        ArrayList<CovidData> dataPoints = data.getDataWithState(stateAbbr);
        if (dataPoints.size() == 0) {
            System.out.println("There are no records from " + stateAbbr);
            return;
        }
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
        System.out.println(dataPoints.size()
            + " records are printed out for the state of " + stateAbbr);
    }


    /**
     * searchs the data based on data quality
     * 
     * @param args
     *            the arguements
     */
    private void searchQuality(String[] args) {

    }


    /**
     * searches the data based on date
     * 
     * @param args
     *            the arguements
     */
    private void searchDate(String[] args) {

    }


    /**
     * searches the data based on date
     * 
     * @param args
     *            the arguements
     */
    private void searchNoDate() {
        CovidData maxDateData = data.findMax();
        String date = maxDateData.getDate().toString();
        String fancyDate = "";
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
        ArrayList<CovidData> dataPoints = data.getDataWithDate(date);
        if (dataPoints.size() == 0) {
            System.out.println("There are no records on " + fancyDate);
            return;
        }
        Object[] headerStrings = { "state", "positive", "negative",
            "hospitalized", "onVentilatorCurrently", "onVentilatorCumulative",
            "recovered", "dataQualityGrade", "death" };
        System.out.format("%s%11s%12s%16s%24s%26s%12s%19s%8s   \n",
            headerStrings);
        for (CovidData myData : dataPoints) {
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
        System.out.println(dataPoints.size()
            + " records have been printed on date " + fancyDate);
    }


    /**
     * searches the data based on days ago
     * 
     * @param args
     *            the arguements
     */
    private void searchDays(String[] args) {

    }


    /**
     * searches the data based on 7 day avergae
     * 
     * @param args
     *            the arguements
     */
    private void searchAverage(String[] args) {

    }


    /**
     * searches the data based on continuous cases above
     * 
     * @param args
     *            the arguements
     */
    private void searchContinuous(String[] args) {

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
            System.out.println("State of " + stateName + " does not exist!");
            return null;
        }

        return stateAbbr;
    }

}
