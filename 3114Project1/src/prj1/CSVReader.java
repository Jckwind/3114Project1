package prj1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
 * Reads the CSV file provided by a command
 *
 * @author Jack Windham (jckwind11)
 * @version 2020.09.27
 */
public class CSVReader {

    private String filePath;

    private ArrayList<CovidData> data;


    /**
     * creates a new csv reader
     * 
     * @param filePath
     *            the path to the csv file
     * @throws FileNotFoundException
     */
    public CSVReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        data = new ArrayList<CovidData>();
    }


    /**
     * reads the data from the csv file
     * 
     * @throws FileNotFoundException
     */
    public void readData() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        // skips the column names
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(", *");
            CovidData dataPoint = new CovidData(parts);
            data.add(dataPoint);
        }
        scanner.close();
    }


    /**
     * @return the data
     */
    public ArrayList<CovidData> getData() {
        return data;
    }

}
