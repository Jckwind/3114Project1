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
 * performs all the searching action
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 24, 2020
 */
public class Searcher {

    private ArrayList<CommandArgs> arguments;

    /**
     * creates a new searcher object
     * 
     * @param arguments
     *            the arguments
     */
    public Searcher(ArrayList<CommandArgs> arguments) {
        this.arguments = arguments;
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
            case DEFAULT:
                searchDate(argument.getArgs());
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
        if (args.length == 0) {

        }
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

}
