package src;
import java.util.ArrayList;
import java.util.Random;

/**
 * Computer Science ICS4U
 * Culminating Project - Subject Class
 * January 2021
 * @author Barbod Habibi
 */

public abstract class Subject {
    double median;
    String[] talents = new String[2];
    String courseName, courseCode;
    double mark;
    ArrayList<Double> history = new ArrayList<>();
    
    /**
     * Constructor
     * pre: none
     * post: subject object is created with no parameters
     */
    public Subject() {
        median = 0;
        mark = 0;
    }
    
    /**
     * Constructor overload
     * pre: none
     * post: subject class is created
     * @param difficulty
     * @param courseName
     * @param courseCode 
     */
    public Subject(String difficulty, String courseName, String courseCode) {
        /*
        - difficulty: easy      median = 80 ± 2%
        - difficulty: medium    median = 75 ± 2%
        - difficulty: hard      median = 70 ± 2%
        */
        setMedian(difficulty);
        this.courseName = courseName;
        this.courseCode = courseCode;
        mark = 0;
    }
    /**
     * Helper method - fluctuate the median mark
     * pre: none
     * post: median is fluctuated and returned
     * @param mid
     * @param fluctuation
     * @return 
     */
    private double fluctuate(int mid, int fluctuation) {
        // declaration
        Random rand = new Random();
        
        /*
        min + fluctuation = mid = max - fluctuation
        min = mid - fluctuation
        max = min + 2 * fluctuation
        */
        return rand.nextInt(2 * fluctuation) + (mid - fluctuation);
    }
    
    /**
     * Modifier method
     * pre: none
     * post: median of a subject is set according to difficulty
     * @param difficulty 
     */
    private void setMedian(String difficulty) {
        if (difficulty.equals("easy"))
            median = fluctuate(80, 2);
        else if (difficulty.equals("medium"))
            median = fluctuate(75, 2);
        else
            median = fluctuate(75, 2);
    }
    
    /**
     * 
     * @param credits
     * @param code 
     */
    public void pass(ArrayList<String> credits, String code) {
        credits.add(code); // keep track of passed courses and earned credits
    } // ******************************************************************
    
    /**
     * Modifier method
     * pre: none
     * post: new mark is added to the grade history
     * @param mark 
     */
    public void addMark(double mark) {
        history.add(mark);
    }
    
    /**
     * Modifier method
     * pre: none
     * post: median of the course is set manually
     * @param mark 
     */
    public void setMark(double mark) {
        this.mark = mark;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: advantage index of student is returned according to course requirements and natural talents
     * @param player
     * @param t1
     * @param t2
     * @return 
     */
    public double getBenefits(Player player, int t1, int t2) { // t1 & t2 are subject-specific talents
        // declaration
        double benefitIndex = 0;
        
        // processing data
        if (player.getTalents()[0])
            benefitIndex += 0.5; // critical thinking benefits all subjects with half the effect
        if (player.getTalents()[t1])
            benefitIndex++;
        if (player.getTalents()[t2])
            benefitIndex++;
        
        return benefitIndex;
    }
    
    /**
     * Accessor method overload
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    public double getBenefits(Player player) {
        return getBenefits(player, 0, 0);
    }
    
    /**
     * Accessor method
     * pre: none
     * post: median of subject is returned
     * @return 
     */
    public double getMedian() {
        return median;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: name of subject is returned
     * @return 
     */
    public String getName() {
        return courseName;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: subject code is returned
     * @return 
     */
    public String getCode() {
        return courseCode;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: HashMap containing grade history of subject is returned
     * @return 
     */
    public ArrayList<Double> getHistory() {
        return history;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: current mark of course is returned according to grade history
     * @return 
     */
    public double getMark() {
        // average all the marks in the history
        return calculateAverage(history);
    }
    /**
     * Helper method - calculate the average of marks in grade history
     * pre: none
     * post: average of marks in grade history is returned
     * @param h
     * @return 
    
    // ORIGINAL METHOD
    private double calculateAverage(ArrayList<Double> h) {
        Double sum = 0.0;
        if (!marks.isEmpty()) {
            for (Double m : h)
                sum += m;
            return sum / h.size();
        }
        return sum;
    }
    */
    private double calculateAverage(ArrayList<Double> h) {
        Double sum = 0.0;
        if (!h.isEmpty()) {
            sum = h.stream().map((m) -> m).reduce(sum, (accumulator, _item) -> accumulator + _item);
            return sum / h.size();
        }
        return sum;
    }
}

class AdvancedFunctions extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Advanced Functions subject is created
     */
    public AdvancedFunctions() {
        super("hard", "Advanced Functions", "MHF4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned 
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 1, 2);
    }
}

class Calculus extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Calculus & Vectors subject is created
     */
    public Calculus() {
        super("hard", "Calculus & Vectors", "MCV4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 1, 2);
    }
}

class DataManagement extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Mathematics of Data Management subject is created
     */
    public DataManagement() {
        super("medium", "Mathematics of Data Management", "MDM4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 1, 5);
    }
}

class Physics extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Physics subject is created
     */
    public Physics() {
        super("hard", "Physics", "SPH4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 1, 3);
    }
}

class Chemistry extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Chemistry subject is created
     */
    public Chemistry() {
        super("medium", "Chemistry", "SCH4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 2, 3);
    }
}

class Biology extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Biology subject is created
     */
    public Biology() {
        super("hard", "Biology", "SBI4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 3, 5);
    }
}

class ComputerScience extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Computer Science subject is created
     */
    public ComputerScience() {
        super("medium", "Computer Science", "ICS4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 1, 2);
    }
}

class French extends Subject {
    /**
     * Constructor
     * pre: none
     * post: French subject is created
     */
    public French() {
        super("medium", "French", "FSF4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 4, 5);
    }
}

class English extends Subject {
    /**
     * Constructor
     * pre: none
     * post: English subject is created
     */
    public English() {
        super("hard", "English", "ENG4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 4, 7);
    }
}

class Architecture extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Architectural Design Class is created
     */
    public Architecture() {
        super("easy", "Architectural Design", "TDA4M");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 3, 6);
    }
}

class EngineeringFundamentals extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Engineering Fundamentals subject is created
     */
    public EngineeringFundamentals() {
        super("easy", "Engineering Fundamentals", "TDJ4M");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 1, 6);
    }
}

class Economics extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Analyzing Current Economic Issues subject is created
     */
    public Economics() {
        super("easy", "Analyzing Current Economic Issues", "CIA4U");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 5, 6);
    }
}

class Psychology extends Subject {
    /**
     * Constructor
     * pre: none
     * post: Psychology of Human Development subject is created
     */
    public Psychology() {
        super("medium", "Psychology of Human Development", "HHG4M");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 5, 7);
    }
}

class BusinessFundamentals extends Subject {
    /**
     * Constructor
     * pre: none
     * post: International Business Fundamentals
     */
    public BusinessFundamentals() {
        super("easy", "International Business Fundamentals", "BBB4M");
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player and talents required for incrementing advantage index are returned
     * @param player
     * @return 
     */
    @Override
    public double getBenefits(Player player) {
        return getBenefits(player, 6, 7);
    }
}