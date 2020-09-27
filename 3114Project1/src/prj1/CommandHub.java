package prj1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(new File(commandFile));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            // split the line into parts and convert parts to arraylist
            List<String> listOfParts = Arrays.asList(line.split("\\s+"));
            ArrayList<String> parts = new ArrayList<>(listOfParts);
            // cast the command type
            CommandEnum type = getType(parts.get(0));
            // remove the type of command from parts
            parts.remove(0);
            Command command = new Command(type, parts);
            commands.add(command);
        }
        scanner.close();
    }


    /**
     * runs each command in the command list
     */
    private void runCommands() {
        for (Command command : commands) {
            // jerk off each command
        }
    }


    /**
     * changes a string to a command type enum value
     * 
     * @param stringValue
     *            the type of the command
     * @return the command type
     */
    private CommandEnum getType(String stringValue) {
        if (stringValue == null) {
            return null;
        }
        else if (stringValue.equals("load")) {
            return CommandEnum.LOAD;
        }
        else if (stringValue.equals("search")) {
            return CommandEnum.SEARCH;
        }
        else if (stringValue.equals("summarydata")) {
            return CommandEnum.SUMMARY;
        }
        else if (stringValue.equals("dumpdata")) {
            return CommandEnum.DUMP;
        }
        else {
            return null;
        }
    }

}
