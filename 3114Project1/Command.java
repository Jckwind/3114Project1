
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
 * the command class
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.27
 */
public class Command {

    private CommandEnum commandType;

    private ArrayList<CommandArgs> args;

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
        this.args = new ArrayList<CommandArgs>();
        handleArgs(args);
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
    public ArrayList<CommandArgs> getArgs() {
        return args;
    }


    /**
     * converts the args to command arg objetc
     * 
     * @param args
     *            the args
     */
    public void handleArgs(ArrayList<String> args) {
        CommandArgs currentArg = null;
        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                if (currentArg != null) {
                    this.args.add(currentArg);
                }
                ParameterEnum name = stringToParam(arg.toLowerCase());
                currentArg = new CommandArgs(name);
            }
            else if (currentArg != null) {
                currentArg.addArg(arg);
            }
        }
        if (currentArg == null) {
            currentArg = new CommandArgs(ParameterEnum.DEFAULT);
            for (String arg : args) {
                currentArg.addArg(arg);
            }
            this.args.add(currentArg);

        }
        else {
            this.args.add(currentArg);
        }
    }


    /**
     * turns a parameter into a enum
     * 
     * @param value
     *            the string
     * @return the param enum type
     */
    public ParameterEnum stringToParam(String value) {
        if (value.equals("-q")) {
            return ParameterEnum.QUALITY;
        }
        else if (value.equals("-s")) {
            return ParameterEnum.STATE;
        }
        else if (value.equals("-n")) {
            return ParameterEnum.DAYS;
        }
        else if (value.equals("-d")) {
            return ParameterEnum.DATE;
        }
        else if (value.equals("-t")) {
            return ParameterEnum.AVERAGE;
        }
        else if (value.equals("-c")) {
            return ParameterEnum.CONTINUOUSRUN;
        }
        else {
            return ParameterEnum.DEFAULT;
        }
    }


    /**
     * runs the command depending on type
     * 
     * @param data
     *            Map of the data to be run
     * @return boolean
     */
    public void run(BST<CovidData> data) {
        switch (commandType) {
            case LOAD:
                this.load(data);
                break;
            case SEARCH:
                Searcher searcher = new Searcher(args);
                searcher.search();
                break;
            case DUMP:
                this.dataDump(data);
                break;
            case REMOVE:
                this.remove();
                break;
            case ERROR:
                System.out.println("Discard invalid command name");
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
    private void load(BST<CovidData> data) {
        if (args.size() != 1 && args.get(0).getArgs().length != 0) {
            System.out.println("Discard invalid command name");
            return;
        }
        String csvFilePath = args.get(0).getArgs()[0];
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
    private void searchState(BST<CovidData> data) {
        // the last arguement is always number of numbers
// int lastIndex = args.size() - 1;
// Integer numOfRecords = Integer.parseInt(args.get(lastIndex));
// if (numOfRecords <= 0) {
// System.out.println(
// "Invalid command. # of records has to be positive");
// return;
// }
// args.remove(lastIndex);
// // the remaining arguements are the parts of the state name
// String stateName = String.join(" ", args);
// StateSearcher searcher = new StateSearcher(data, stateName,
// numOfRecords);
// searcher.search();
    }


    /**
     * searchs the data by state
     * 
     * @param data
     *            the hashmap of data
     */
    private void searchDate(BST<CovidData> data) {
// DateSearcher searcher = new DateSearcher(null, null, data);
// searcher.search();
// return;
// try {
// DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
// format.setLenient(false);
// String date = args.get(0);
// if (date.length() != 10) {
// throw new ParseException(date, 0);
// }
// Date dateData = format.parse(date);
// format = new SimpleDateFormat("yyyymmdd");
// String searchable = format.format(dateData);
// DateSearcher searcher = new DateSearcher(searchable, date, data);
// searcher.search();
// }
// catch (ParseException e) {
// System.out.println("Discard invalid command name");
// }
    }


    /**
     * runs the data dump command
     * 
     * @param data
     *            the hashmap of data
     */
    private void dataDump(BST<CovidData> data) {
        if (args.size() != 1 || args.get(0).getArgs().length == 0) {
            System.out.println("Discard invalid command name");
            return;
        }
        String modeString = args.get(0).getArgs()[0];
        int mode = Integer.parseInt(modeString);
        data.getRoot().print();
// data.inOrder(mode);
    }


    /**
     * removes the specified data grade
     */
    private void remove() {

    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(commandType.toString() + " args: ");
        for (CommandArgs arg : args) {
            builder.append(arg + " ");
        }
        return builder.toString();
    }
}
