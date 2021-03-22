package src;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

/**
 * Computer Science ICS4U
 * Culminating Activity - High School Life (Student Class)
 * January 2021
 * @author Barbod Habibi
 */

public class Student {
    // fields
    String firstName, lastName;
    int grade;
    HashMap<String, String> credits = new HashMap<>();
    Subject[] courses = new Subject[8];
    /*
    - dimension 1: semester
    - dimension 2: period
    */
    Subject[][] timetable = new Subject[2][4];
    
    // constructor: randomize first name, last name, grade, school, timetable
    /**
     * Constructor
     * pre: none
     * post: student object is created
     * Create a student object with no parameters.
     */
    public Student() {
        firstName = "";
        lastName = "";
        Random rand = new Random();
        grade = rand.nextInt(4) + 9;
    }
    /**
     * pre: none
     * post: student object is created
     * Create a student object.
     * @param firstName
     * @param lastName
     * @param grade
     * @param school 
     */
    public Student(String firstName, String lastName, int grade, String school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }
    
    /**
     * pre: none
     * post: all available courses are listed
     * Display a list of all available courses.
     */
    public void listCourses() {
        System.out.println("\nSocial Studies & Humanities");
        System.out.println("\tCIA4U: Analysing Current Economic Issues");
        System.out.println("\tHHG4M: Psychology of Human Development");
        System.out.println("\tBBB4M: International Business Fundamentals");
        
        System.out.println("\nComputer Studies");
        System.out.println("\tICS4U: Computer Science");
        
        System.out.println("\nLanguages");
        System.out.println("\tENG4U: English");
        System.out.println("\tFSF4U: French");
        
        System.out.println("\nMathematics");
        System.out.println("\tMHF4U: Advanced Functions");
        System.out.println("\tMCV4U: Calculus & Vectors");
        System.out.println("\tMDM4U: Mathematics of Data Management");
        
        System.out.println("\nScience");
        System.out.println("\tSBI4U: Biology");
        System.out.println("\tSCH4U: Chemistry");
        System.out.println("\tSPH4U: Physics");
        
        System.out.println("\nTechnical Studies");
        System.out.println("\tTDA4M: Architectural Design");
        System.out.println("\tTDJ4M: Engineering Fundamentals");
    }
    
    /**
     * Modifier method
     * pre: none
     * post: first and last names of the student are set
     * @param firstName
     * @param lastName 
     */
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /**
     * Modifier method
     * pre: none
     * post: grade of the student is set
     * @param grade 
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    /**
     * Modifier method
     * pre: none
     * post: courses of the student are selected
     */
    public void setCourses() {
        // declaration
        Scanner input = new Scanner(System.in);
        String choice;
        int index = 1;
        
        // data obtainment from the user
        listCourses();
        System.out.println("\nEnter the code for the course you would like to take.");
        while(index <= 8) {
            System.out.println("Choice #" + index +  ": ");
            choice = input.next();
            choice = choice.toUpperCase();
            
            if(choice.equals("CIA4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Analysing Current Economic Issues");
                    courses[index - 1] = new Economics();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("HHG4M1")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Psychology of Human Development");
                    courses[index - 1] = new Psychology();
                }
                else {
                    System.out.println("You have alrady taken this course!");
                    continue;
                }
            }
            else if (choice.equals("BBB4M")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "International Business Fundamentals");
                    courses[index - 1] = new BusinessFundamentals();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
                
            }
            else if (choice.equals("ICS4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Computer Science");
                    courses[index - 1] = new ComputerScience();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("ENG4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "English");
                    courses[index - 1] = new English();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("FSF4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "French");
                    courses[index - 1] = new French();
                }
                else {
                    System.out.println("You have already takent this course!");
                    continue;
                }
            }
            else if (choice.equals("MHF4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Advanced Functions");
                    courses[index - 1] = new AdvancedFunctions();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("MCV4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Calculus & Vectors");
                    courses[index - 1] = new Calculus();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("MDM4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Mathematics of Data Management");
                    courses[index - 1] = new DataManagement();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("SBI4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Biology");
                    courses[index - 1] = new Biology();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("SCH4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Chemistry");
                    courses[index - 1] = new Chemistry();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("SPH4U")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Physics");
                    courses[index - 1] = new Physics();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else if (choice.equals("TDA4M")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Architectural Design");
                    courses[index - 1] = new Architecture();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
                
            }
            else if (choice.equals("TDJ4M")) {
                if (!repeatedCourse(credits, choice)) {
                    credits.put(choice, "Engineering Fundamentals");
                    courses[index - 1] = new EngineeringFundamentals();
                }
                else {
                    System.out.println("You have already taken this course!");
                    continue;
                }
            }
            else {
                System.out.println("Invalid input! Please try again.");
                continue;
            }
            
            index++;
        }
    }
    
    /**
     * Helper method - check to see if the course is repeated
     * pre: none
     * post: true is returned when the course is repeated and false when the course is not repeated
     * @param credits
     * @param choice
     * @return 
     */
    private boolean repeatedCourse(HashMap credits, String choice) {
        return credits.containsKey(choice);
    }
    
    /**
     * Modifier method
     * pre: none
     * post: a two-dimensional timetable of the selected courses is randomly created
     */
    public void setTimetable() {
        // declaration
        int row = 0, column = 0;
        courses = shuffle(courses);
        
        // convert the hashmap into a two dimensional array of strings
        for (Subject course : courses) {
            timetable[row][column] = course;
            if (column < 3)
                column++;
            else {
                if (row < 1) {
                    column = 0;
                    row = 1;
                }
                else
                    break;
            }
        }
    }
    
    /**
     * Helper method - shuffle courses for generating a random timetable
     * pre: none
     * post: the courses in the array are randomly shuffled
     * @param courses
     * @return 
     */
    private Subject[] shuffle(Subject[] courses) {
        for (int i = 0; i < courses.length; i++) {
            int index = (int)(Math.random() * courses.length);
            Subject temp = courses[i];
            courses[i] = courses[index];
            courses[index] = temp;
        }
        
        return courses;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: first and last names of the student are returned
     * @return 
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: only first name of the student is returned
     * @return 
     */
    public String getName() {
        return firstName;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: the grade of the student is returned
     * @return 
     */
    public int getGrade() {
        return grade;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: return a string, containing all information associated with student
     * @return 
     */
    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nGrade: " + getGrade();
    }
    
    /**
     * Accessor method
     * pre: none
     * post: return a HashMap of all the credits acquired by the student 
     * @return 
     */
    public HashMap<String, String> getCredits() {
        return credits;
    }
    
    /**
     * Print the timetable of the student
     * pre: none
     * post: the timetable of the student is printed.
     */
    public void printTimetable() {
        for (int i = 0; i < timetable.length; i++) {
            System.out.println("\nSemester " + (i+1) + ":");
            for (int j = 0; j < timetable[0].length; j++) {
                System.out.println(timetable[i][j].getName());
            }
        }
    }
    
    /**
     * Accessor method
     * pre: none
     * post: player timetable is returned
     * @return 
     */
    public Subject[][] getTimetable() {
        return timetable;
    }
}