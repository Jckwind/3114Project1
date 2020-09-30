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
// -- Michael Gannon (mgannon3500)
/**
 * Reads the command file
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.27
 */
public class CommandReader {

    private ArrayList<Command> commands;

    private String commandPath;


    /**
     * 
     * @param commands
     * @param commandPath
     */
    public CommandReader(ArrayList<Command> commands, String commandPath) {
        this.commands = commands;
        this.commandPath = commandPath;
    }


    /**
     * reads the command file and populates the command list
     * 
     * @throws FileNotFoundException
     * @param boolean
     */
    public boolean read() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(commandPath));
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
        return true;
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
