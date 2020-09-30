

import java.util.Arrays;
import java.util.List;

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
 * The class that handles the State abbrievation
 *
 * @author Jack Windham (jckwind11)
 * @author Michael Gannon (mgannon3500)
 * @version 2020.09.27
 */
public class State {

    static private String[] arrayOfAbbrv = { "AL", "AK", "AS", "AZ", "AR", "CA",
        "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN",
        "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO",
        "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK",
        "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI",
        "VA", "WA", "WV", "WI", "WY" };

    static private String[] arrayOfStateNames = { "ALABAMA", "ALASKA",
        "AMERICAN SAMOA", "ARIZONA", "ARKANSAS", "CALIFORNIA", "COLORADO",
        "CONNECTICUT", "DELAWARE", "DISTRICT OF COLUMBIA",
        "FEDERATED STATES OF MICRONESIA", "FLORIDA", "GEORGIA", "GUAM",
        "HAWAII", "IDAHO", "ILLINOIS", "INDIANA", "IOWA", "KANSAS", "KENTUCKY",
        "LOUISIANA", "MAINE", "MARSHALL ISLANDS", "MARYLAND", "MASSACHUSETTS",
        "MICHIGAN", "MINNESOTA", "MISSISSIPPI", "MISSOURI", "MONTANA",
        "NEBRASKA", "NEVADA", "NEW HAMPSHIRE", "NEW JERSEY", "NEW MEXICO",
        "NEW YORK", "NORTH CAROLINA", "NORTH DAKOTA",
        "NORTHERN MARIANA ISLANDS", "OHIO", "OKLAHOMA", "OREGON", "PALAU",
        "PENNSYLVANIA", "PUERTO RICO", "RHODE ISLAND", "SOUTH CAROLINA",
        "SOUTH DAKOTA", "TENNESSEE", "TEXAS", "UTAH", "VERMONT",
        "VIRGIN ISLAND", "VIRGINIA", "WASHINGTON", "WEST VIRGINIA", "WISCONSIN",
        "WYOMING" };

    /**
     * turns arrayOfAbbreviations into a list of strings
     */
    static public List<String> stateAbbrList = Arrays.asList(arrayOfAbbrv);

    /**
     * turns arrayOfStateNames into a list of strings
     */
    static public List<String> stateNameList = Arrays.asList(arrayOfStateNames);


    /**
     * returns the full state when given the abbrievation
     * 
     * @param abbrievation
     *            the states abbr
     * @return full state name
     */
    static public String fullState(String abbrievation) {
        switch (abbrievation) {
            case "AL":
                return "Alabama";
            case "AK":
                return "Alaska";
            case "AR":
                return "Arkansas";
            case "AS":
                return "American Samoa";
            case "AZ":
                return "Arizona";
            case "CA":
                return "California";
            case "CO":
                return "Colorado";
            case "CT":
                return "Connecticut";
            case "DE":
                return "Delaware";
            case "DC":
                return "District of Columbia";
            case "FL":
                return "Florida";
            case "FM":
                return "Federated States of Micronesia";
            case "GA":
                return "Georgia";
            case "GU":
                return "Guam";
            case "HI":
                return "Hawaii";
            case "ID":
                return "Idaho";
            case "IL":
                return "Illinois";
            case "IN":
                return "Indiana";
            case "IA":
                return "Iowa";
            case "KS":
                return "Kansas";
            case "KY":
                return "Kentucky";
            case "LA":
                return "Louisiana";
            case "ME":
                return "Maine";
            case "MD":
                return "Maryland";
            case "MA":
                return "Massachusetts";
            case "MI":
                return "Michigan";
            case "ML":
                return "Marshall Islands";
            case "MN":
                return "Minnesota";
            case "MP":
                return "Northern Mariana Islands";
            case "MS":
                return "Mississippi";
            case "MO":
                return "Missouri";
            case "MT":
                return "Montana";
            case "NE":
                return "Nebraska";
            case "NV":
                return "Nevada";
            case "NH":
                return "New Hampshire";
            case "NJ":
                return "New Jersey";
            case "NM":
                return "New Mexico";
            case "NY":
                return "New York";
            case "NC":
                return "North Carolina";
            case "ND":
                return "North Dakota";
            case "OH":
                return "Ohio";
            case "OK":
                return "Oklahoma";
            case "OR":
                return "Oregon";
            case "PA":
                return "Pennsylvania";
            case "PR":
                return "Puerto Rico";
            case "PW":
                return "Palau";
            case "RI":
                return "Rhode Island";
            case "SC":
                return "South Carolina";
            case "SD":
                return "South Dakota";
            case "TN":
                return "Tennessee";
            case "TX":
                return "Texas";
            case "UT":
                return "Utah";
            case "VI":
                return "Virgin Islands";
            case "VT":
                return "Vermont";
            case "VA":
                return "Virginia";
            case "WA":
                return "Washington";
            case "WV":
                return "West Virginia";
            case "WI":
                return "Wisconsin";
            case "WY":
                return "Wyoming";
            default:
                return abbrievation;
        }
    }


    /**
     * returns the state abbrv when given the full name
     * 
     * @param fullName
     *            the states full name
     * @return state abbr
     */
    static public String stateAbbr(String fullName) {
        switch (fullName.toUpperCase()) {
            case "ALABAMA":
                return "AL";
            case "ALASKA":
                return "AK";
            case "AMERICAN SAMOA":
                return "AS";
            case "ARIZONA":
                return "AZ";
            case "ARKANSAS":
                return "AR";
            case "CALIFORNIA":
                return "CA";
            case "COLORADO":
                return "CO";
            case "CONNECTICUT":
                return "CT";
            case "DELAWARE":
                return "DE";
            case "DISTRICT OF COLUMBIA":
                return "DC";
            case "FEDERATED STATES OF MICRONESIA":
                return "FM";
            case "FLORIDA":
                return "FL";
            case "GEORGIA":
                return "GA";
            case "GUAM":
                return "GU";
            case "HAWAII":
                return "HI";
            case "IDAHO":
                return "ID";
            case "ILLINOIS":
                return "IL";
            case "INDIANA":
                return "IN";
            case "IOWA":
                return "IA";
            case "KANSAS":
                return "KS";
            case "KENTUCKY":
                return "KY";
            case "LOUISIANA":
                return "LA";
            case "MAINE":
                return "ME";
            case "MARSHALL ISLANDS":
                return "MH";
            case "MARYLAND":
                return "MD";
            case "MASSACHUSETTS":
                return "MA";
            case "MICHIGAN":
                return "MI";
            case "MINNESOTA":
                return "MN";
            case "MISSISSIPPI":
                return "MS";
            case "MISSOURI":
                return "MO";
            case "MONTANA":
                return "MT";
            case "NEBRASKA":
                return "NE";
            case "NEVADA":
                return "NV";
            case "NEW HAMPSHIRE":
                return "NH";
            case "NEW JERSEY":
                return "NJ";
            case "NEW MEXICO":
                return "NM";
            case "NEW YORK":
                return "NY";
            case "NORTH CAROLINA":
                return "NC";
            case "NORTH DAKOTA":
                return "ND";
            case "NORTHERN MARIANA ISLANDS":
                return "MP";
            case "OHIO":
                return "OH";
            case "OKLAHOMA":
                return "OK";
            case "OREGON":
                return "OR";
            case "PALAU":
                return "PW";
            case "PENNSYLVANIA":
                return "PA";
            case "PUERTO RICO":
                return "PR";
            case "RHODE ISLAND":
                return "RI";
            case "SOUTH CAROLINA":
                return "SC";
            case "SOUTH DAKOTA":
                return "SD";
            case "TENNESSEE":
                return "TN";
            case "TEXAS":
                return "TX";
            case "UTAH":
                return "UT";
            case "VERMONT":
                return "VT";
            case "VIRGIN ISLANDS":
                return "VI";
            case "VIRGINIA":
                return "VA";
            case "WASHINGTON":
                return "WA";
            case "WEST VIRGINIA":
                return "WV";
            case "WISCONSIN":
                return "WI";
            case "WYOMING":
                return "WY";
            default:
                return fullName;
        }
    }
}
