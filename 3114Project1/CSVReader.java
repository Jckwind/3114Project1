
import java.io.File;
import java.io.FileNotFoundException;
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
 * Reads the CSV file provided by a command
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.27
 */
public class CSVReader {

    private String filePath;

    private BST<CovidData> data;

    /**
     * creates a new csv reader0
     * 
     * @param filePath
     *            String of the file input
     * @param data
     *            Map of the data to read
     */
    public CSVReader(String filePath, BST<CovidData> data) {
        this.filePath = filePath;
        this.data = data;
    }


    /**
     * reads the data from the csv file
     * 
     * @return boolean
     * @throws FileNotFoundException
     */
    public boolean loadData() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        // skips the column names
        scanner.nextLine();
        int count = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",", -1);
            Boolean allEmpty = true;
            for (String part : parts) {
                if (!part.isEmpty()) {
                    allEmpty = false;
                    break;
                }
            }
            if (parts.length == 0 || parts.length != 10 || allEmpty) {
                continue;
            }
            CovidData dataPoint = new CovidData(parts);
            if (addToList(dataPoint)) {
                count++;
            }
        }
        scanner.close();
        System.out.println("Finished loading " + filePath + " file");
        System.out.println(count + " records have been loaded");
        return true;
    }


    /**
     * adds the data point to our data
     * 
     * @param dataPoint
     *            the data to add
     * @return if to increment count
     */
    private boolean addToList(CovidData dataPoint) {
        if (dataPoint.isValid()) {
            if (dataPoint.stateIsValid()) {
                String key = dataPoint.getKey();

                if (!data.contains(key) || shouldAdd(key, dataPoint)) {
                    data.add(key, dataPoint);
                    return true;
                }
                else if (shouldUpdate(key, dataPoint)) {
                    return true;
                }
            }
            else {
                System.out.println("State of " + dataPoint.getState()
                    + " does not exist!");
            }
        }
        else {
            System.out.println("Discard invalid record");
        }
        return false;
    }


    /**
     * decided if should add new data point to data
     * 
     * @param key
     *            the key
     * @param dataPoint
     * @count the count, to add to it if need to increment
     * @return if to add it
     */
    private Boolean shouldAdd(String key, CovidData newData) {
        CovidData currentData = data.get(key);
        if (currentData.compareQuality(newData) == 1) {
            System.out.println("Data has been updated for " + newData.getState()
                + " " + newData.fancyDate());
            return true;
        }
        return false;
    }


    /**
     * if to update the data
     * 
     * @param key
     *            the key
     * @param newData
     *            the new data to update with
     * @return if we updated
     */
    private boolean shouldUpdate(String key, CovidData newData) {
        CovidData currentData = data.get(key);
        boolean didUpdate = currentData.updatedData(newData);
        if (didUpdate) {
            String state = newData.getState();
            System.out.println("Data has been updated for the missing data in "
                + state);
            return true;
        }
        else {
            String state = newData.getState();
            System.out.println("Low quality data rejected for " + state);
            return false;
        }
    }


    /**
     * @return the data
     */
    public BST<CovidData> getData() {
        return data;
    }

}
