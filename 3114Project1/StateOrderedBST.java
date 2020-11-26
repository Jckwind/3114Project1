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
 * details of the class
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version Nov 25, 2020
 */
public class StateOrderedBST<K> extends BST<K> {

    @Override
    public int compare(String string1, String string2) {
        if (string1.equals(string2)) {
            return 0;
        }
        String[] parts1 = string1.split("-", -1);
        String[] parts2 = string2.split("-", -1);
        int compareNames = parts1[1].compareTo(parts2[1]);
        if (compareNames != 0) {
            return compareNames;
        }
        else {
            Integer part1Number = Integer.parseInt(parts1[0]);
            Integer part2Number = Integer.parseInt(parts2[0]);
            return part1Number.compareTo(part2Number);
        }
    }
}
