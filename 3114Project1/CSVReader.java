
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
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

    private Map<String, CovidData> data;


    /**
     * creates a new csv reader0
     * 
     * @param filePath
     *            String of the file input
     * @param data
     *            Map of the data to read
     */
    public CSVReader(String filePath, Map<String, CovidData> data) {
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
            if (parts.length == 0 || parts.length != 10) {
                continue;
            }
            CovidData dataPoint = new CovidData(parts);
            if (dataPoint.isValid()) {
                if (dataPoint.stateIsValid()) {
                    String key = dataPoint.getKey();
                    if (!data.containsKey(key) || shouldAdd(key, dataPoint)) {
                        count++;
                        data.put(key, dataPoint);
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
        }
        scanner.close();
        System.out.println("Finished loading " + filePath + " file");
        System.out.println(count + " records have been loaded");
        return true;
    }


    /**
     * decided if should add new data point to data
     * 
     * @param dataPoint
     * @return if to add it
     */
    private Boolean shouldAdd(String key, CovidData newData) {
        CovidData currentData = data.get(key);
        if (currentData.compareQuality(newData) == 1) {
            System.out.println("Data has been updated for " + newData.getState()
                + newData.fancyDate());
            return true;
        }
        else {
            boolean didUpdate = currentData.updatedData(newData);
            if (didUpdate) {
                System.out.println(
                    "Data has been updated for the missing data in " + newData
                        .getState());
            }
            else {
                System.out.println("Low quality data rejected for " + newData
                    .getState());
            }
        }
        return false;
    }


    /**
     * @return the data
     */
    public Map<String, CovidData> getData() {
        return data;
    }

}
