import student.TestCase;

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
// -- Michael Gannong (mgannon3500
/**
 * Tests the state functions
 *
 * @author Jack Windham (jckwind11)
 * @author Michael gannon (mgannong3500)
 * @version 2020.10.01
 */
public class StateTest extends TestCase {

    /**
     * tests to make sure given a abbr it returns the state name
     */
    public void testAbrrtoFullname() {
        assertEquals("Alabama", State.fullState("AL"));
        assertEquals("Alaska", State.fullState("AK"));
        assertEquals("Arkansas", State.fullState("AR"));
        assertEquals("American Samoa", State.fullState("AS"));
        assertEquals("Arizona", State.fullState("AZ"));
        assertEquals("California", State.fullState("CA"));
        assertEquals("Colorado", State.fullState("CO"));
        assertEquals("Connecticut", State.fullState("CT"));
        assertEquals("Delaware", State.fullState("DE"));
        assertEquals("District of Columbia", State.fullState("DC"));
        assertEquals("Florida", State.fullState("FL"));
        assertEquals("Federated States of Micronesia", State.fullState("FM"));
        assertEquals("Georgia", State.fullState("GA"));
        assertEquals("Guam", State.fullState("GU"));
        assertEquals("Hawaii", State.fullState("HI"));
        assertEquals("Idaho", State.fullState("ID"));
        assertEquals("Illinois", State.fullState("IL"));
        assertEquals("Indiana", State.fullState("IN"));
        assertEquals("Iowa", State.fullState("IA"));
        assertEquals("Kansas", State.fullState("KS"));
        assertEquals("Kentucky", State.fullState("KY"));
        assertEquals("Louisiana", State.fullState("LA"));
        assertEquals("Maine", State.fullState("ME"));
        assertEquals("Maryland", State.fullState("MD"));
        assertEquals("Massachusetts", State.fullState("MA"));
        assertEquals("Michigan", State.fullState("MI"));
        assertEquals("Marshall Islands", State.fullState("ML"));
        assertEquals("Minnesota", State.fullState("MN"));
        assertEquals("Northern Mariana Islands", State.fullState("MP"));
        assertEquals("Mississippi", State.fullState("MS"));
        assertEquals("Missouri", State.fullState("MO"));
        assertEquals("Montana", State.fullState("MT"));
        assertEquals("Nebraska", State.fullState("NE"));
        assertEquals("Nevada", State.fullState("NV"));
        assertEquals("New Hampshire", State.fullState("NH"));
        assertEquals("New Jersey", State.fullState("NJ"));
        assertEquals("New Mexico", State.fullState("NM"));
        assertEquals("New York", State.fullState("NY"));
        assertEquals("North Carolina", State.fullState("NC"));
        assertEquals("North Dakota", State.fullState("ND"));
        assertEquals("Ohio", State.fullState("OH"));
        assertEquals("Oklahoma", State.fullState("OK"));
        assertEquals("Oregon", State.fullState("OR"));
        assertEquals("Pennsylvania", State.fullState("PA"));
        assertEquals("Puerto Rico", State.fullState("PR"));
        assertEquals("Palau", State.fullState("PW"));
        assertEquals("Rhode Island", State.fullState("RI"));
        assertEquals("South Carolina", State.fullState("SC"));
        assertEquals("South Dakota", State.fullState("SD"));
        assertEquals("Tennessee", State.fullState("TN"));
        assertEquals("Texas", State.fullState("TX"));
        assertEquals("Utah", State.fullState("UT"));
        assertEquals("Virgin Islands", State.fullState("VI"));
        assertEquals("Vermont", State.fullState("VT"));
        assertEquals("Virginia", State.fullState("VA"));
        assertEquals("Washington", State.fullState("WA"));
        assertEquals("West Virginia", State.fullState("WV"));
        assertEquals("Wisconsin", State.fullState("WI"));
        assertEquals("Wyoming", State.fullState("WY"));
    }


    /**
     * makes sure giving a abbr we dont have returns the given name
     */
    public void testAbbrStateNameError() {
        assertEquals("error", State.fullState("error"));
    }


    /**
     * tests the state class
     */
    public void testStateNameToAbbr() {
        assertEquals("AL", State.stateAbbr("ALABAMA"));
        assertEquals("AK", State.stateAbbr("ALASKA"));
        assertEquals("AR", State.stateAbbr("ARKANSAS"));
        assertEquals("AS", State.stateAbbr("AMERICAN SAMOA"));
        assertEquals("AZ", State.stateAbbr("ARIZONA"));
        assertEquals("CA", State.stateAbbr("CALIFORNIA"));
        assertEquals("CO", State.stateAbbr("COLORADO"));
        assertEquals("CT", State.stateAbbr("CONNECTICUT"));
        assertEquals("DE", State.stateAbbr("DELAWARE"));
        assertEquals("DC", State.stateAbbr("DISTRICT OF COLUMBIA"));
        assertEquals("FL", State.stateAbbr("FLORIDA"));
        assertEquals("FM", State.stateAbbr("FEDERATED STATES OF MICRONESIA"));
        assertEquals("GA", State.stateAbbr("GEORGIA"));
        assertEquals("GU", State.stateAbbr("GUAM"));
        assertEquals("HI", State.stateAbbr("HAWAII"));
        assertEquals("ID", State.stateAbbr("IDAHO"));
        assertEquals("IL", State.stateAbbr("ILLINOIS"));
        assertEquals("IN", State.stateAbbr("INDIANA"));
        assertEquals("IA", State.stateAbbr("IOWA"));
        assertEquals("KS", State.stateAbbr("KANSAS"));
        assertEquals("KY", State.stateAbbr("KENTUCKY"));
        assertEquals("LA", State.stateAbbr("LOUISIANA"));
        assertEquals("ME", State.stateAbbr("MAINE"));
        assertEquals("MD", State.stateAbbr("MARYLAND"));
        assertEquals("MA", State.stateAbbr("MASSACHUSETTS"));
        assertEquals("MI", State.stateAbbr("MICHIGAN"));
        assertEquals("MH", State.stateAbbr("MARSHALL ISLANDS"));
        assertEquals("MN", State.stateAbbr("MINNESOTA"));
        assertEquals("MP", State.stateAbbr("NORTHERN MARIANA ISLANDS"));
        assertEquals("MS", State.stateAbbr("MISSISSIPPI"));
        assertEquals("MO", State.stateAbbr("MISSOURI"));
        assertEquals("MT", State.stateAbbr("MONTANA"));
        assertEquals("NE", State.stateAbbr("NEBRASKA"));
        assertEquals("NV", State.stateAbbr("NEVADA"));
        assertEquals("NH", State.stateAbbr("NEW HAMPSHIRE"));
        assertEquals("NJ", State.stateAbbr("NEW JERSEY"));
        assertEquals("NM", State.stateAbbr("NEW MEXICO"));
        assertEquals("NY", State.stateAbbr("NEW YORK"));
        assertEquals("NC", State.stateAbbr("NORTH CAROLINA"));
        assertEquals("ND", State.stateAbbr("NORTH DAKOTA"));
        assertEquals("OH", State.stateAbbr("OHIO"));
        assertEquals("OK", State.stateAbbr("OKLAHOMA"));
        assertEquals("OR", State.stateAbbr("OREGON"));
        assertEquals("PA", State.stateAbbr("PENNSYLVANIA"));
        assertEquals("PR", State.stateAbbr("PUERTO RICO"));
        assertEquals("PW", State.stateAbbr("PALAU"));
        assertEquals("RI", State.stateAbbr("RHODE ISLAND"));
        assertEquals("SC", State.stateAbbr("SOUTH CAROLINA"));
        assertEquals("SD", State.stateAbbr("SOUTH DAKOTA"));
        assertEquals("TN", State.stateAbbr("TENNESSEE"));
        assertEquals("TX", State.stateAbbr("TEXAS"));
        assertEquals("UT", State.stateAbbr("UTAH"));
        assertEquals("VI", State.stateAbbr("VIRGIN ISLANDS"));
        assertEquals("VT", State.stateAbbr("VERMONT"));
        assertEquals("VA", State.stateAbbr("VIRGINIA"));
        assertEquals("WA", State.stateAbbr("WASHINGTON"));
        assertEquals("WV", State.stateAbbr("WEST VIRGINIA"));
        assertEquals("WI", State.stateAbbr("WISCONSIN"));
        assertEquals("WY", State.stateAbbr("WYOMING"));
    }


    /**
     * returns the given abbr if no abbr is found
     */
    public void testStateNameToAbbrFailed() {
        assertEquals("ZZ", State.stateAbbr("ZZ"));
    }
}
