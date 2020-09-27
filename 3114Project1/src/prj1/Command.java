package prj1;

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
/**
 * Description of Class
 *
 * @author Jack Windham (jckwind11)
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
}
