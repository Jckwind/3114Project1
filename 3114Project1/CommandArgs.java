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
 * holds the different parameters for commands
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 24, 2020
 */
public class CommandArgs {

    private ParameterEnum type;

    private ArrayList<String> args;

    /**
     * creates a new command arg object
     * 
     * @param type
     *            the type of object
     * @param args
     *            the args
     */
    public CommandArgs(ParameterEnum type) {
        this.type = type;
        this.args = new ArrayList<String>();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("type: " + type.toString() + " args: ");
        for (String arg : args) {
            builder.append(arg + " ");
        }
        return builder.toString();
    }


    /**
     * adds an arg
     * 
     * @param arg
     *            the arg to add
     */
    public void addArg(String arg) {
        args.add(arg);
    }


    /**
     * @return the type
     */
    public ParameterEnum getType() {
        return type;
    }


    /**
     * @return the args
     */
    public String[] getArgs() {
        String[] arguments = new String[0];
        return args.toArray(arguments);
    }

}
