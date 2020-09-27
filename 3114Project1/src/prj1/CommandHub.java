package prj1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
/**
 * Reads and runs each command from the command File
 *
 * @author Jack Windham (jckwind11)
 * @version 2020.09.27
 */
public class CommandHub {

    private String commandFile;

    private ArrayList<Command> commands;

    private Map<String, CovidData> data;


    /**
     * creates a new command reader object
     * 
     * @param commandFile
     *            the file w the commands
     * @throws FileNotFoundException
     */
    public CommandHub(String commandFile) throws FileNotFoundException {
        this.commandFile = commandFile;
        this.commands = new ArrayList<Command>();
        data = new HashMap<String, CovidData>();
    }


    /**
     * reads the command data and then runs each command
     */
    public void execute() throws FileNotFoundException {
        readCommandData();
        runCommands();
    }


    /**
     * reads the command file and populates the command list
     * 
     * @throws FileNotFoundException
     */
    private void readCommandData() throws FileNotFoundException {
        CommandReader reader = new CommandReader(commands, commandFile);
        reader.read();
    }


    /**
     * runs each command in the command list
     */
    private void runCommands() {
        for (Command command : commands) {
            command.run(data);
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Command command : commands) {
            builder.append(command.getCommandType() + ", " + command.getArgs()
                .toString() + "\n");
        }
        return builder.toString();
    }
}
