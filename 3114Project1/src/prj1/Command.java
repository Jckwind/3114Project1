package prj1;

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
     */
    public void run(Map<String, CovidData> data) {
        switch (commandType) {
            case LOAD:
                this.load(data);
                break;
            case SEARCH:
                if (args.size() == 1) {
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
            default:
                break;
        }
    }


    /**
     * loads the data
     * 
     * @param data
     *            the hashmap of data
     */
    private void load(Map<String, CovidData> data) {
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
     * @param args
     *            the args
     */
    private void searchState(Map<String, CovidData> data) {

    }


    /**
     * searchs the data by state
     * 
     * @param data
     *            the hashmap of data
     * @param args
     *            the args
     */
    private void searchDate(Map<String, CovidData> data) {
        DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        try {
            String date = args.get(0);
            Date dateData = format.parse(date);
            format = new SimpleDateFormat("yyyymmdd");
            String searchableDate = format.format(dateData);
            DateSearcher searcher = new DateSearcher(searchableDate, date,
                data);
            searcher.search();
        }
        catch (ParseException e) {
            System.out.println("There are no records on " + args.get(0));
        }
    }


    /**
     * runs the summary command
     * 
     * @param data
     *            the hashmap of data
     */
    private void summary(Map<String, CovidData> data) {

    }


    /**
     * runs the data dump command
     * 
     * @param data
     *            the hashmap of data
     */
    private void dataDump(Map<String, CovidData> data) {

    }
}
