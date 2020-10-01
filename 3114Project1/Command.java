
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Description of Class
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.27
 */
public class Command {

    private CommandEnum commandType;

    private ArrayList<String> args;


    /**
     * creates a new command object
     * 
     * @param type
     *            the type of the command
     * @param args
     *            the arguements of each command
     */
    public Command(CommandEnum type, ArrayList<String> args) {
        this.commandType = type;
        this.args = args;
    }


    /**
     * @return the commandType
     */
    public CommandEnum getCommandType() {
        return commandType;
    }


    /**
     * @return the args
     */
    public ArrayList<String> getArgs() {
        return args;
    }


    /**
     * runs the command depending on type
     * 
     * @param data
     *            Map of the data to be run
     * @return boolean
     */
    public boolean run(Map<String, CovidData> data) {
        switch (commandType) {
            case LOAD:
                this.load(data);
                break;
            case SEARCH:
                if (args.size() <= 1) {
                    this.searchDate(data);
                }
                else {
                    this.searchState(data);
                }
                break;
            case SUMMARY:
                this.summary(data);
                break;
            case DUMP:
                this.dataDump(data);
                break;
            case ERROR:
                System.out.println("Discard invalid command name");
                break;
            default:
                break;
        }
        return true;
    }


    /**
     * loads the data
     * 
     * @param data
     *            the hashmap of data
     */
    private void load(Map<String, CovidData> data) {
        if (args.size() != 1) {
            System.out.println("Discard invalid command name");
            return;
        }
        String csvFilePath = args.get(0);
        try {
            CSVReader reader = new CSVReader(csvFilePath, data);
            reader.loadData();
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + csvFilePath + " was not found");
        }
    }


    /**
     * searchs the data by state
     * 
     * @param data
     *            the hashmap of data
     */
    private void searchState(Map<String, CovidData> data) {
        // the last arguement is always number of numbers
        int lastIndex = args.size() - 1;
        Integer numOfRecords = Integer.parseInt(args.get(lastIndex));
        if (numOfRecords <= 0) {
            System.out.println(
                "Invalid command. # of records has to be positive");
            return;
        }
        args.remove(lastIndex);
        // the remaining arguements are the parts of the state name
        String stateName = String.join(" ", args);
        StateSearcher searcher = new StateSearcher(data, stateName,
            numOfRecords);
        searcher.search();
    }


    /**
     * searchs the data by state
     * 
     * @param data
     *            the hashmap of data
     */
    private void searchDate(Map<String, CovidData> data) {
        if (args.size() == 0) {
            DateSearcher searcher = new DateSearcher(null, null, data);
            searcher.search();
            return;
        }
        try {
            DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
            format.setLenient(false);
            String date = args.get(0);
            if (date.length() != 10) {
                throw new ParseException(date, 0);
            }
            Date dateData = format.parse(date);
            format = new SimpleDateFormat("yyyymmdd");
            String searchable = format.format(dateData);
            DateSearcher searcher = new DateSearcher(searchable, date, data);
            searcher.search();
        }
        catch (ParseException e) {
            System.out.println("Discard invalid command name");
        }
    }


    /**
     * runs the summary command
     * 
     * @param data
     *            the hashmap of data
     */
    private void summary(Map<String, CovidData> data) {
        if (args.size() != 0) {
            System.out.println("Discard invalid command name");
            return;
        }
        Summary summaryReporter = new Summary(data);
        summaryReporter.reportSummary();
    }


    /**
     * runs the data dump command
     * 
     * @param data
     *            the hashmap of data
     */
    private void dataDump(Map<String, CovidData> data) {
        if (args.size() != 1) {
            System.out.println("Discard invalid command name");
            return;
        }
        Dumper dumpTruck = new Dumper(data, args.get(0));
        dumpTruck.dump();
    }
}
