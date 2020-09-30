package prj1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * A class with a fat dumper. Absolute wagon.
 * Nah jk, this class outputs the current data to a .csv file
 *
 * @author Jack Windham (jckwind11)
 * @author jack Windham (jckwind11)
 * @version 2020.09.30
 */
public class Dumper {

    private String fileName;

    private Map<String, CovidData> data;

    private BufferedWriter writer;


    public Dumper(Map<String, CovidData> data, String filename) {
        this.fileName = filename;
        this.data = data;
    }


    /**
     * dumps the data to the given file
     */
    public void dump() {
        try {
            openPortToFile();
            outputData();
        }
        catch (IOException e) {
            System.out.println("Error saving records to " + fileName);
            return;
        }

        System.out.println(data.size() + " records have been saved in the "
            + fileName + " file");
    }


    /**
     * allows us to write to the file, and create the file if needed
     * 
     * @throws IOException
     * 
     */
    private void openPortToFile() throws IOException {
        File file = new File(fileName);
        // will only create a new file if one does not already exist
        writer = new BufferedWriter(new FileWriter(file));
    }


    /**
     * populates the headers in the output file
     * 
     * @throws IOException
     */
    private void outputData() throws IOException {
        // output the headers
        StringBuilder headerBuilder = new StringBuilder();
        headerBuilder.append("date, state, positive, negative, hospitalized, ");
        headerBuilder.append("onVentilatorCurrently, onVentilatorCumulative, ");
        headerBuilder.append("recovered, dataQualityGrade, death");
        writer.write(headerBuilder.toString());
        writer.newLine();
        Comparator<CovidData> comparator = Comparator.comparing(point -> point
            .getDate());
        comparator = comparator.thenComparing(Comparator.comparing(
            dataPoint -> dataPoint.getFullState()));
        Stream<CovidData> stream = data.values().stream().sorted(comparator);
        List<CovidData> dataPoints = stream.collect(Collectors.toList());
        for (CovidData dataPoint : dataPoints) {
            writer.write(dataPoint.toString());
            writer.newLine();
        }
        writer.close();
    }
}
