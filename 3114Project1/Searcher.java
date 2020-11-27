import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
     * @param data
     *            the bst tree to use as data
     */
    public Searcher(ArrayList<CommandArgs> arguments, BST<CovidData> data) {
        this.arguments = arguments;
        this.data = new BST<CovidData>(data);
        this.data.copyState(data.getStateData());
        this.builder = new StringBuilder();
        this.results = new ArrayList<CovidData>();
    }


    /**
     * searches the BST using these args
     */
    public void search() {
        if (data.isEmpty()) {
            System.out.println("search Failed! No data available");
            return;
        }
        for (CommandArgs argument : arguments) {
            if (argument.getType() == ParameterEnum.CONTINUOUSRUN) {
                searchContinuous(argument.getArgs());
                return;
            }
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
                boolean hasN = false;
                for (CommandArgs arg : arguments) {
                    if (arg.getType() == ParameterEnum.DAYS || arg
                        .getType() == ParameterEnum.AVERAGE) {
                        hasN = true;
                        break;
                    }
                }
                return searchDate(argument.getArgs(), hasN);
            case DEFAULT:
                return searchNoDate();
            case DAYS:
                return searchDays(argument.getArgs());
            case AVERAGE:
                return searchAverage(argument.getArgs());
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
    private boolean searchDate(String[] args, boolean ignore) {
        if (ignore) {
            return true;
        }
        String date = args[0];
        String searchableDate = getSearchableDate(date, "mm/dd/yyyy");
        if (searchableDate == null) {
            System.out.println("The date " + date + " is not valid");
            return false;
        }
        results = data.getDataWithDate(searchableDate);
        builder.append(" on date " + date);
        return true;
    }


    /**
     * searches the data based on most recent date
     */
    private boolean searchNoDate() {
        String date = getMostRecentDate();
        String[] args = new String[] { date };
        return searchDate(args, false);
    }


    /**
     * searches the data based on days ago
     * 
     * @param args
     *            the arguements
     */
    private boolean searchDays(String[] args) {
        Integer numberOfDays = Integer.parseInt(args[0]);
        String date = getMostRecentDate();
        for (CommandArgs arg : arguments) {
            if (arg.getType() == ParameterEnum.DATE) {
                date = arg.getArgs()[0];
            }
        }
        ArrayList<String> dates = new ArrayList<String>();
        for (int i = 0; i < numberOfDays; i++) {
            String newDate = getSearchableDate(addDaysToDate(date, i),
                "yyyy-mm-dd");
            if (newDate != null) {
                dates.add(newDate);
            }
        }
        if (dates.size() == 0) {
            System.out.println("The date " + date + " is not valid");
            return false;
        }
        results = data.getDataWithDates(dates);
        String minDate = dates.get(dates.size() - 1);
        builder.append(" from " + fancyDate(minDate) + " to " + date);
        return true;
    }


    /**
     * returns the mm/dd/yyyy date formate
     * 
     * @return the date
     */
    private String fancyDate(String oldDate) {
        DateFormat format = new SimpleDateFormat("yyyymmdd");
        try {
            Date dateData = format.parse(oldDate);
            format = new SimpleDateFormat("mm/dd/yyyy");
            return format.format(dateData);
        }
        catch (ParseException e) {
            return "error";
        }
    }


    /**
     * searches the data based on 7 day avergae
     * 
     * @param args
     *            the arguements
     */
    private boolean searchAverage(String[] args) {
        Integer numberOfDays = Integer.parseInt(args[0]);
        String date = getMostRecentDate();
        for (CommandArgs arg : arguments) {
            if (arg.getType() == ParameterEnum.DATE) {
                date = arg.getArgs()[0];
            }
        }
        ArrayList<String> dates = new ArrayList<String>();
        for (int i = 0; i < numberOfDays; i++) {
            String newDate = getSearchableDate(addDaysToDate(date, i),
                "yyyy-mm-dd");
            if (newDate != null) {
                dates.add(newDate);
            }
        }
        if (dates.size() == 0) {
            System.out.println("The date " + date + " is not valid");
            return false;
        }
        results = data.getDataWithDates(dates);
        Map<String, Double> map = new HashMap<String, Double>();
        for (CovidData dataPoint : results) {
            if (map.containsKey(dataPoint.getState())) {
                String key = dataPoint.getState();
                Double newValue = dataPoint.getPos() / numberOfDays;
                map.put(key, map.get(key) + newValue);
            }
            else {
                String key = dataPoint.getState();
                Double newValue = dataPoint.getPos() / numberOfDays;
                map.put(key, newValue);
            }
        }
        String minDate = dates.get(dates.size() - 1);
        int size = map.size() >= 10 ? 10 : map.size();
        System.out.println("Top " + size + " states with the highest average"
            + " daily positive cases from " + fancyDate(minDate) + " to " + date
            + ":");

        Comparator<Entry<String, Double>> comparator = (e1, e2) -> e1.getValue()
            .compareTo(e2.getValue());
        comparator = comparator.thenComparing((e1, e2) -> e2.getKey().compareTo(
            e1.getKey()));
        comparator = comparator.reversed();
        map.entrySet().stream().sorted(comparator).limit(10).forEach(
            entry -> System.out.println(entry.getKey() + " " + entry.getValue()
                .intValue()));
        return false;
    }


    /**
     * searches the data based on continuous cases above
     * 
     * @param args
     *            the arguements
     */
    private void searchContinuous(String[] args) {
        Integer avg = Integer.parseInt(args[0]);
        ArrayList<BST7DayAvg> avgs = data.getStateData().get7DayAverage(avg);
        ArrayList<String> stateSaid = new ArrayList<String>();
        for (BST7DayAvg average : avgs) {
            if (stateSaid.contains(average.getState())) {
                System.out.println(fancyDate(average.getStartingDate()) + " - "
                    + fancyDate(average.getEndDate()));
            }
            else {
                System.out.println("State " + average.getState());
                stateSaid.add(average.getState());
                System.out.println(fancyDate(average.getStartingDate()) + " - "
                    + fancyDate(average.getEndDate()));
            }
        }
        System.out.println(stateSaid.size()
            + " states have daily numbers of positive"
            + " cases greater than or equal to " + avg
            + " for at least 7 days continuously");
    }


    /**
     * returns the most recent date
     * 
     * @return most recent date
     */
    private String getMostRecentDate() {
        CovidData maxDateData = data.findMax();
        String date = maxDateData.fancyDate().toString();
        return date;
    }


    /**
     * gets a searchable form of the date
     * 
     * @param date
     *            the fancy date
     * @return the searchable date
     */
    private String getSearchableDate(String date, String dateFormat) {
        if (date == null) {
            return null;
        }
        try {
            DateFormat format = new SimpleDateFormat(dateFormat);
            format.setLenient(false);
            if (date.length() != 10) {
                throw new ParseException(date, 0);
            }
            Date dateData = format.parse(date);
            format = new SimpleDateFormat("yyyymmdd");
            format.setLenient(false);
            String returnValue = format.format(dateData);
            format = new SimpleDateFormat("yyyy-mm-dd");
            format.setLenient(false);
            LocalDate.parse(format.format(dateData));
            return returnValue;
        }
        catch (DateTimeParseException e) {
            return null;
        }
        catch (ParseException e) {
            return null;
        }
    }


    /**
     * adds days to a date
     * 
     * @param ogDate
     *            the date to add to
     * @param day2Add
     *            the num of days to add
     * @return new date
     */
    private String addDaysToDate(String ogDate, int day2Add) {
        String dateString = "2020-01-01";
        try {
            DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
            format.setLenient(false);
            if (ogDate.length() != 10) {
                throw new ParseException(ogDate, 0);
            }
            Date dateData = format.parse(ogDate);
            format = new SimpleDateFormat("yyyy-mm-dd");
            dateString = format.format(dateData);
            return LocalDate.parse(dateString).minusDays(day2Add).toString();
        }
        catch (DateTimeParseException e) {
            return null;
        }
        catch (ParseException e) {
            return null;
        }
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
            System.out.println("The state " + stateName + " does not exist");
            return null;
        }

        return stateAbbr;
    }


    /**
     * prints the results
     */
    private void printResults() {
        Collections.sort(results);
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
