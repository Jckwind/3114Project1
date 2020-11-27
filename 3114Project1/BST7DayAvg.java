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
 * the 7 day average
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 27, 2020
 */
public class BST7DayAvg implements Comparable<BST7DayAvg> {

    private String state;

    private String startingDate;

    private String endDate;

    private Integer minAvg;

    private int counter = 0;

    /**
     * creates a new bst 7 day average object
     * 
     * @param state
     *            the state
     * @param startingDate
     *            the starting date
     * @param minAvg
     *            the minimum average requires for the 7 day run
     */
    public BST7DayAvg(String state, String startingDate, Integer minAvg) {
        this.state = state;
        this.startingDate = startingDate;
        this.minAvg = minAvg;
    }


    /**
     * @return the state
     */
    public String getState() {
        return state;
    }


    /**
     * @return the startingDate
     */
    public String getStartingDate() {
        return startingDate;
    }


    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }


    /**
     * @return the minAvg
     */
    public Integer getMinAvg() {
        return minAvg;
    }


    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }


    /**
     * @param counter
     *            the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }


    /**
     * @param counter
     *            the counter to set
     */
    public void incrementCounter() {
        this.counter++;
    }


    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    @Override
    public String toString() {
        return state + ": " + startingDate + ": " + counter;
    }


    @Override
    public int compareTo(BST7DayAvg o) {
        String state1 = state;
        String state2 = o.getState();
        int nameComp = state1.compareTo(state2);
        if (nameComp == 0) {
            Integer startDate1 = Integer.parseInt(startingDate);
            Integer startDate2 = Integer.parseInt(o.getStartingDate());
            return startDate1.compareTo(startDate2);
        }
        else {
            return nameComp;
        }
    }

}
