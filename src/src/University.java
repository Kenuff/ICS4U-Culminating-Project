package src;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

/**
 * Computer Science ICS4U
 * Culminating Project - University class
 * January 2021
 * @author Barbod Habibi
 */

public class University {
    // fields
    String university;
    String location;
    boolean residence;
    int ranking;
    String URL;
    
    /**
     * Constructor
     * pre: none
     * post: university object is created with no parameters
     */
    public University() {
        university = "";
        location = "";
        residence = false;
        ranking = 0;
        URL = "";
    }
    
    /**
     * Constructor overload
     * pre: none
     * post: university object is created
     * @param university 
     */
    public University(String university) {
        if (university.equalsIgnoreCase("University of Toronto")) {
            this.university = "University of Toronto";
            location = "Toronto, ON";
            residence = false;
            ranking = 1;
            URL = "https://www.utoronto.ca/";
        }
        else if (university.equalsIgnoreCase("University of Waterloo")) {
            this.university = "University of Waterloo";
            location = "Waterloo, ON";
            residence = true;
            ranking = 3;
            URL = "https://uwaterloo.ca/";
        }
        else if (university.equalsIgnoreCase("McMaster University")) {
            this.university = "McMaster University";
            location = "Hamilton, ON";
            residence = true;
            ranking = 5;
            URL = "https://www.mcmaster.ca/";
        }
        else if (university.equalsIgnoreCase("Ryerson University")) {
            this.university = "Ryerson University";
            location = "Toronto, ON";
            residence = false;
            ranking = 15;
            URL = "https://www.ryerson.ca/";
        }
        else if (university.equalsIgnoreCase("York University")) {
            this.university = "York University";
            location = "Toronto, ON";
            residence = false;
            ranking = 20;
            URL = "https://www.yorku.ca/";
        }
        else if (university.equalsIgnoreCase("Lakehead University")) {
            this.university = "Lakehead University";
            location = "Thunder Bay, ON";
            residence = true;
            ranking = 39;
            URL = "https://www.lakeheadu.ca/";
        }
    }
    
    /**
     * Accessor method
     * pre: none
     * post: name of university is returned
     * @return 
     */
    public String getUniversity() {
        return university;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: location of university is returned
     * @return 
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: shows whether user (residing in Maple) requires on-campus residence
     * @return 
     */
    public boolean getResidence() {
        return residence;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: ranking of university is returned
     * @return 
     */
    public int getRanking() {
        return ranking;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: university website is opened if indicated by user
     * @throws Exception 
     */
    public void openWebsite() throws Exception {
        try {
            Desktop d = Desktop.getDesktop();
            d.browse(new URI(URL));
        }
        catch (URISyntaxException | IOException e) {
            System.out.println("Website not found :(");
        }
    }
    
    /**
     * Accessor method
     * pre: none
     * post: a string, containing all information associated with university, is returned
     * @return 
     */
    @Override
    public String toString() {
        String info = "\n" + university +
                "\n  📍 Location: " + getLocation() +
                "\n  💡 2021 Maclean's reputational rankings: " + getRanking() +
                "\n  🏠 Residence required? ";
        
        if (getResidence())
            return info + "Yes";
        else
            return info + "No";
    }
}

abstract class Program extends University {
    // fields
    String programName;
    int prestige;
    double admission;
    String[] requirements;
    
    /**
     * Constructor
     * pre: none
     * post: program object is created with no parameters
     */
    Program() {
        super();
        programName = "";
        prestige = 0;
        admission = 0;
    }
    
    /**
     * Constructor overload
     * pre: none
     * post: program object is created
     * @param university
     * @param programName 
     */
    Program(String university, String programName) {
        super(university);
        this.programName = programName;
    }
    
    /**
     * Modifier method
     * pre: none
     * post: required courses are set in a one-dimensional array
     * @param requirements 
     */
    public void setRequirements(String[] requirements) {
        this.requirements = requirements;
    }
    
    /**
     * Modifier method
     * pre: none
     * post: program name is set
     * @param programName 
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    
    /**
     * Helper method - fluctuate admission average by ±f
     * pre: none
     * post: admission average of program is fluctuated by ±f
     * @param f
     * @return 
     */
    public int fluctuation(int f) {
        Random rand = new Random();
        
        return rand.nextInt(2 * f) - f;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: program name is returned
     * @return 
     */
    public String getProgramName() {
        return programName;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: prestige score of program is returned
     * @return 
     */
    public int getPrestige() {
        return prestige;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: admission average is returned
     * @return 
     */
    public double getAdmission() {
        return admission;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: required courses of program are returned in a one-dimensional array
     * @return 
     */
    public String[] getRequirements() {
        return requirements;
    }
    
    /**
     * Program application
     * pre: none
     * post: application of user to program is accepted or rejected based on related factors
     * @param credits
     * @param transcript
     * @return 
     */
    public boolean apply(HashMap<String, String> credits, HashMap<String, Double> transcript) {
        double sum = 0.0, average;
        int numCourses = 6;
        
        if (!checkRequirements(credits))
            return false;
        // calculate academic average (top 6 including mandatory courses)
        else {
            for (String r : getRequirements()) {
                sum += transcript.get(r);
                transcript.remove(r);
                numCourses--;
            }
            ArrayList<Double> electives = convertTranscript(transcript);
            Collections.sort(electives); // sort electives
            
            for (int i = 0; i < numCourses; i++) {
                sum += electives.get(electives.size() - 1 -i);
            }
            
            average = sum / 6;
        }
        
        return average >= admission - 1;
    }
    
    /**
     * Helper method - save elective grades into one-dimensional array
     * pre: none
     * post: elective grades are saved into one-dimensional array
     * @param transcript
     * @return 
    
    // ORIGINAL METHOD
    private ArrayList<Double> convertTranscript(HashMap<String, Double> transcript) {
        // declaration
        ArrayList<Double> electives = new ArrayList<>();
    
        // processing & calculation
        for (Double value : transcript.values())
            electives.add(value);
    
        return electives;
    }
    */
    public ArrayList<Double> convertTranscript(HashMap<String, Double> transcript) {
        // declaration
        ArrayList<Double> electives = new ArrayList<>();
        
        // processing data
        transcript.values().stream().forEach((value) -> {
            electives.add(value);
        });
        
        return electives;
    }
    
    /**
     * Helper method - sort elective courses by grade to select for top electives (insertion sort)
     * pre: none
     * post: elective courses are sorted by grade
     * @param electives
     * @return 
     */
    public ArrayList<Double> insertionSort(ArrayList<Double> electives) {
        // processing & calculation
        for (int i = 1; i < electives.size(); i++) {
            Double key = electives.get(i);
            int j = i - 1;

            while (j >= 0 && electives.get(j) > key) {
                electives.add(j + 1, electives.get(j));
                j = j - 1;
            }
            electives.add(j + 1, key);
        }

        return electives;
    }
    
    /**
     * Helper method - check credits
     * pre: none
     * post: return true if required courses are present in credits
     * @param credits
     * @return 
     */
    public boolean checkRequirements(HashMap<String, String> credits) {
        // processing data
        for (String r : requirements) {
            if (!credits.containsKey(r))
                return false;
        }
        return true;
    }
}

class Science extends Program {
    String field;
    
    /**
     * Constructor
     * pre: none
     * post: science major object is created with no parameters
     */
    public Science() {
        super();
    }
    
    /**
     * Constructor overload
     * pre: none
     * post: science major is created
     * @param university 
     */
    public Science(String university) {
        super(university, "Science");
        // set prestige & admission average
        switch (getUniversity()) {
            case "University of Toronto":
                prestige = 5;
                admission = 90.4 + fluctuation(2);
                break;
            case "University of Waterloo":
                prestige = 5;
                admission = 87.5 + fluctuation(2);
                break;
            case "McMaster University":
                prestige = 5;
                admission = 90.1 + fluctuation(2);
                break;
            case "Ryerson University":
                prestige = 2;
                admission = 83.6 + fluctuation(2);
                break;
            case "York University":
                prestige = 2;
                admission = 82.3 + fluctuation(2);
                break;
            case "Lakehead University":
                prestige = 2;
                admission = 83.7 + fluctuation(2);
                break;
        }
    }
    
    /**
     * Modifier method
     * pre: none
     * post: set specific science field
     * @param field 
     */
    public void setField(String field) {
        this.field = field;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: specific science field is returned
     * @return 
     */
    public String getField() {
        return field;
    }
}

class IntegratedScience_Physics extends Science {
    String[] req = {"ENG4U", "SPH4U", "MHF4U", "MCV4U"};
    
    /**
     * Constructor
     * pre: none
     * post: integrated science physics major is created
     * @param university 
     */
    public IntegratedScience_Physics(String university) {
        super(university);
        setRequirements(req);
        setField("physics");
        setProgramName("Science - Physics");
    }
}

class IntegratedScience_Chemistry extends Science {
    String[] req = {"ENG4U", "SCH4U", "MHF4U", "MCV4U"};
    
    /**
     * Constructor
     * pre: none
     * post: integrated science chemistry major is created
     * @param university 
     */
    public IntegratedScience_Chemistry(String university) {
        super(university);
        setRequirements(req);
        setField("chemistry");
        setProgramName("Science - Chemistry");
    }
}

class IntegratedScience_Biology extends Science {
    String[] req = {"ENG4U", "SBI4U", "SCH4U", "MHF4U", "MCV4U"};
    
    /**
     * Constructor
     * pre: none
     * post: integrated science biology major is created
     * @param university 
     */
    public IntegratedScience_Biology(String university) {
        super(university);
        setRequirements(req);
        setField("biology");
        setProgramName("Science - Biology");
    }
}

class Humanities extends Program {
    String[] req = {"HHG4M", "ENG4U"};
    
    /**
     * Constructor
     * pre: none
     * post: humanities major is created
     * @param university 
     */
    public Humanities() {
        super();
        setRequirements(req);
    }
    
    /**
     * Constructor
     * pre: none
     * post: humanities major is created
     * @param univeristy 
     */
    public Humanities(String univeristy) {
        super(univeristy, "Humanities");
        setRequirements(req);
        
        // set prestige & admission average
        switch (getUniversity()) {
            case "University of Toronto":
                prestige = 5;
                admission = 90.0 + fluctuation(2);
                break;
            case "University of Waterloo":
                prestige = 3;
                admission = 87.0 + fluctuation(2);
                break;
            case "McMaster University":
                prestige = 4;
                admission = 89.8 + fluctuation(2);
                break;
            case "Ryerson University":
                prestige = 3;
                admission = 87.5 + fluctuation(2);
                break;
            case "York University":
                prestige = 3;
                admission = 83.3 + fluctuation(2);
                break;
            case "Lakehead University":
                prestige = 2;
                admission = 79.2 + fluctuation(2);
                break;
        }
    }
}

class Engineering extends Program {
    String[] req = {"MHF4U", "MCV4U", "SPH4U", "SCH4U", "ENG4U"};
    
    /**
     * Constructor
     * pre: none
     * post: engineering major is created with no parameters
     */
    public Engineering() {
        super();
        setRequirements(req);
    }
    
    /**
     * Constructor overload
     * pre: none
     * post: engineering major is created
     * @param university 
     */
    public Engineering(String university) {
        super(university, "Engineering");
        setRequirements(req);
        
        // set prestige & admission average
        switch (getUniversity()) {
            case "University of Toronto":
                prestige = 5;
                admission = 94.0 + fluctuation(2);
                break;
            case "University of Waterloo":
                prestige = 5;
                admission = 93.7 + fluctuation(2);
                break;
            case "McMaster University":
                prestige = 4;
                admission = 90.8 + fluctuation(2);
                break;
            case "Ryerson University":
                prestige = 3;
                admission = 86.9 + fluctuation(2);
                break;
            case "York University":
                prestige = 2;
                admission = 83.3 + fluctuation(2);
                break;
            case "Lakehead University":
                prestige = 1;
                admission = 81.9 + fluctuation(2);
                break;
        }
    }
}

class Business extends Program {
    /**
     * Constructor
     * pre: none
     * post: business major is created with no parameters
     */
    public Business() {
        super();
    }
    
    /**
     * Constructor
     * pre: none
     * post: business major is created
     * @param university 
     */
    public Business(String university) {
        super(university, "Business");
        // set prestige & admission average
        switch (getUniversity()) {
            case "University of Toronto":
                prestige = 5;
                admission = 92.2 + fluctuation(2);
                break;
            case "University of Waterloo":
                prestige = 3;
                admission = 85.0 + fluctuation(2);
                break;
            case "McMaster University":
                prestige = 3;
                admission = 85.2 + fluctuation(2);
                break;
            case "Ryerson University":
                prestige = 3;
                admission = 83.2 + fluctuation(2);
                break;
            case "York University":
                prestige = 4;
                admission = 89.6 + fluctuation(2);
                break;
            case "Lakehead University":
                prestige = 1;
                admission = 77.8 + fluctuation(2);
                break;
            default:
                prestige = 0;
                admission = 0;
                break;
        }
    }
    
    /**
     * Program application
     * pre: none
     * post: application of user to program is accepted or rejected based on related factors
     * @param credits
     * @param transcript
     * @return 
     */
    @Override
    public boolean apply(HashMap<String, String> credits, HashMap<String, Double> transcript) {
        // declaration
        double sum = 0.0, average;
        int numCourses = 6;
        
        // processing & calculation
        if (!checkRequirements(credits))
            return false;
        else {
            sum += transcript.get("ENG4U");
            transcript.remove("ENG4U");
            numCourses--;
            
            if (transcript.containsKey("MHF4U")) {
                sum += transcript.get("MHF4U");
                transcript.remove("MHF4U");
                numCourses--;
            }
            if (transcript.containsKey("MDM4U")) {
                sum += transcript.get("MDM4U");
                transcript.remove("MDM4U");
                numCourses--;
            }
            if (transcript.containsKey("CIA4U")) {
                sum += transcript.get("CIA4U");
                transcript.remove("CIA4U");
                numCourses--;
            }
            if (transcript.containsKey("BBB4M")) {
                sum += transcript.get("BBB4M");
                transcript.remove("BBB4M");
                numCourses--;
            }
            
            ArrayList<Double> electives = convertTranscript(transcript);
            Collections.sort(electives); // sort electives
            
            for (int i = 0; i < numCourses; i++) {
                sum += electives.get(electives.size() - 1 -i);
            }
            
            average = sum / 6;
        }
        
        return average >= admission - 1;
    }
    
    /**
     * Helper method - check credits
     * pre: none
     * post: return true if required courses are present in credits
     * @param credits
     * @return 
     */
    @Override
    public boolean checkRequirements(HashMap<String, String> credits) {
        return credits.containsKey("ENG4U") &&
                (credits.containsKey("MHF4U") || credits.containsKey("MDM4U")) &&
                (credits.containsKey("CIA4U") || credits.containsKey("BBB4M"));
    }
}