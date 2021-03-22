package src;
import java.io.IOException;
import java.util.*;

/**
 * Computer Science ICS4U
 * Culminating Activity - High School Life
 * January 2021
 * @author Barbod Habibi
 */

public class ClientCode {
    // global variables
    static Player player;
    static int semester = 1;
    static String[] months = {"September", "October", "November", "December", "January",
            "February", "March", "April", "May", "June"};
    static int currentMonth = 0;
    static TeachAssist teachAssist;
    static ArrayList<Program> programs = new ArrayList<>();
    
    public static void main(String[] args) throws IOException, Exception {
        //declaration
        Scanner input = new Scanner(System.in);
        int choice;
        String response;
        
        // display guide
        guide();
        System.out.println("Would you like to view program admission requirements?");
        while (true) {
            System.out.println("Enter your choice: ");
            response = input.next();
            
            if (response.equalsIgnoreCase("yes")) {
                Research computer = new Research();
                computer.researchProgram();
                break;
            }
            else if (response.equalsIgnoreCase("no"))
                break;
            else
                System.out.println("Invalid input! Please try again.");
        }
        
        // create character
        System.out.println("* Create your player.");
        createCharacter();
        System.out.println(player + "\n");
        System.in.read();
        // initialize necessary data
        courseSelection(player);
        System.in.read();
        setSchedule();
        teachAssist = new TeachAssist(player);
        
        while (true) {
            dashboard();
            for (int i = 0; i < (int) calendar(semester).get(months[currentMonth]); i++) {
                teachAssist.updateMarks(semester);
            }
            currentMonth++;
            
            if (currentMonth == 5 || currentMonth == 10) {
                if (semester == 1) {
                    System.out.println("\n* Semester 1 has ended.");
                    semester++;
                    System.in.read();
                }
                else {
                    System.out.println("Semester 2 has ended.");
                    break;
                }
            }
        }
        
        System.out.println("\n* Congradulations! You have finished the 2020/21 school year.");
        System.out.println("\n*** FINAL MARKS***");
        teachAssist.open(1);
        teachAssist.open(2);
    }
    
    public static void dashboard() throws IOException, Exception {
        // declaration
        Scanner input = new Scanner(System.in);
        int choice;
        boolean application = false;
        int s; // semester - required for opening Teach Assist
        
        // display the dashboard to the user
        if (currentMonth == 9)
            admissionRound();
        
        do {
            System.out.println("\nCurrent month: " + months[currentMonth] + "\t\tSemester: " + semester);
            System.out.println("  # of assessments this month: " + calendar(semester).get(months[currentMonth]));
            if (programs.size() < 3 && currentMonth == 4)
                System.out.print("  REMINDER: January is the deadline for university application on OUAC.");
            
            System.out.println("\n* Available actions:");
            System.out.println("  0. Proceed to the next month."
                    + "\n  1. View monthly schedule."
                    + "\n  2. Modify monthly schedule."
                    + "\n  3. Open Teach Assist and view marks."
                    + "\n  4. Research universities & programs."
                    + "\n  5. View university applications.");
            if (currentMonth <= 4 && programs.size() < 3) {
                System.out.println("  6. Apply to university on OUAC.");
                application = true;
            }
            
            while (true) {
                // data obtainment from the user
                System.out.println("Enter your choice: ");
                choice = input.nextInt();
                
                // process data
                if (choice == 0)
                    break;
                else if (choice == 1) {
                    viewSchedule();
                    System.in.read();
                    break;
                }
                else if (choice == 2) {
                    setSchedule();
                    break;
                }
                else if (choice == 3) {
                    while (true) {
                        System.out.println("Enter the semester: ");
                        s = input.nextInt();
                        
                        if (s == 1 || s == 2)
                            break;
                        else
                            System.out.println("Invalid input! Please try again.");
                    }
                    teachAssist.open(s);
                    break;
                }
                else if (choice == 4) {
                    doResearch();
                    break;
                }
                else if (choice == 5) {
                    displayApplications();
                    break;
                }
                else if (choice == 6 && application) {
                    applyToUniversity();
                    application = false;
                    break;
                }
                else
                    System.out.println("Invalid input! Please try again.");
            }
        } while (choice != 0);
    }
    
    public static void viewSchedule() {
        System.out.println("\n* Current Monthly Schedule:");
        System.out.printf("%-40s%-40s", "Sleep (grade fluctuation)", repeatStars(player.getEffort()[0]));
        System.out.println();
        for (int i = 0; i < 4; i++)
            System.out.printf("%-40s%-40s\n", player.getTimetable()[semester - 1][i].getName(),
                    repeatStars(player.getEffort()[i+1]));
    }
    
    public static void createCharacter() {
        // declaration
        Scanner input = new Scanner(System.in);
        String firstName, lastName;
        
        // data obtainment from the user
        System.out.println("Enter the player's first name: ");
        firstName = input.next();
        System.out.println("Enter the player's last name: ");
        lastName = input.next();
        player = new Player(firstName, lastName);
    }
    
    public static void guide() throws IOException {
        System.out.println("Welcome to Waterfall Virtual High School!");
        System.in.read();
        
        System.out.println("You are a grade 12 student in the 2020/21 school year.");
        System.out.println("You will then be prompted to select your courses, set your abilities & monthly schedule and begin the school year!");
        System.out.println("However, you are highly encouraged to explore available programs.");
        System.in.read();
        
        System.out.println("Note that each natural ability results in a boost in its corresponding course.");
        System.in.read();
    }
    
    public static void courseSelection(Player player) {
        // data obtainment from the user
        System.out.println("\n* Course selection for the upcoming school year.");
        player.setCourses(); // select courses for the school year
        player.setTimetable(); // create and set a timetable for the selected courses
        player.printTimetable(); // print the timetable
    }
    
    public static void setSchedule() {
        // display the guide to the user
        System.out.println("* Create your monthly schedule.\tTotal energy points = " + player.getEnergy());
        System.out.println("GUIDE");
        System.out.printf("%-20s%-20s\n", repeatStars(1), "very low energy input");
        System.out.printf("%-20s%-20s\n", repeatStars(2), "low energy input");
        System.out.printf("%-20s%-20s\n", repeatStars(3), "average energy input");
        System.out.printf("%-20s%-20s\n", repeatStars(4), "high energy input");
        System.out.printf("%-20s%-20s\n", repeatStars(5), "very high energy input");
        System.out.println("Sleep determines the amount of grade fluctuation." +
            "\nREMEMBER: Good sleep allows for tendency towards higher marks.\n");
        
        // data obtainment from the user
        player.setEffort(semester);
    }
    // helper method
    public static String repeatStars(int i) {
        if (i == 1) // base case
            return "* ";
        else // recursive case
            return repeatStars(i  - 1) + "* ";
    }
    
    // update grades on Teach Assist
    public static void takeAssessment(TeachAssist teachAssist, int semester) {
        teachAssist.updateMarks(semester);
    }
    
    // schoolyear calendar
    public static HashMap calendar(int semester) {
        // declaration
        HashMap<String, Integer> calendar = new HashMap<>();
        
        // key: month   value: # of assessments per course
        if (semester == 1) {
            calendar.put("September", 2);
            calendar.put("October", 3);
            calendar.put("November", 4);
            calendar.put("December", 2);
            calendar.put("January", 4);
        }
        else {
            calendar.put("February", 2);
            calendar.put("March", 2);
            calendar.put("April", 3);
            calendar.put("May", 4);
            calendar.put("June", 4);
        }
        
        return calendar;
    }
    
    public static void doResearch() throws Exception {
        // declaration
        Research computer = new Research();
        Scanner input = new Scanner(System.in);
        int choice;
        
        // data obtainment from the user
        while (true) {
            researchMenu();
            
            while (true) {
                System.out.println("Enter your choice: ");
                choice = input.nextInt();

                if (choice == 1) {
                    computer.researchProgram();
                    break;
                }
                else if (choice == 2) {
                    computer.researchUniversity();
                    break;
                }
                else if (choice == 3) {
                    computer.rankPrograms();
                    System.in.read();
                    break;
                }
                else if (choice == 4) {
                    computer.rankUniversities();
                    System.in.read();
                    break;
                }
                else if (choice == 0) {
                    break;
                }
                else
                    System.out.println("Invalid input! Please try again.");
            }
            
            if (choice == 0)
                break;
        }
        
    }
    // helper method
    private static void researchMenu() {
        System.out.println("\n1. Research available programs.");
        System.out.println("2. Research available universities.");
        System.out.println("3. View ranking of universities by program.");
        System.out.println("4. View university rankings.");
        System.out.println("0. RETURN");
    }
    
    public static void applyToUniversity() {
        OUAC ouac = new OUAC(player);
        
        System.out.println("\n* You have applied to " + programs.size() + "/3 universities so far");
        if (programs.size() > 3)
            System.out.println("The maximum capacity is reached. You cannot apply to universities anymore :(");
        else
            ouac.startApplication(programs);
    }
    
    /*
    // ORIGINAL METHOD
    public static void displayApplications() {
        for (Program p : programs)
            System.out.printf("%-40s%-40s\n", p.getUniversity(), p.getProgramName());
    }
    */
    public static void displayApplications() throws IOException {
        if (programs.isEmpty()) {
            System.out.println("\nYou have not applied to any university programs yet.");
            System.in.read();
        }
        else {
            System.out.println("\n* Current application(s):");
            programs.stream().forEach((p) -> {
                System.out.printf("%-40s%-40s\n", p.getUniversity(), p.getProgramName());
            });
            System.in.read();
        }
    }
    
    /**
     * Admission round
     * pre: none
     * post: displays whether the applicant is accepted or rejected from the program he/she applied
     * 
    // ORIGINAL METHOD
    public static void admissionRound() {
        for (Program p : programs) {
            if (p.apply(player.getCredits(), teachAssist.getTranscript()))
                System.out.println("\n* Congratulations! You have been accepted to "
                        + p.getProgramName() + " in " + p.getUniversity());
            else
                System.out.println("\n* Unfortunately, you did not meet the requirements for "
                        + p.getProgramName() + " in " + p.getUniversity());
        }
    }
     */
    public static void admissionRound() {
        programs.stream().forEach((p) -> {
            if (p.apply(player.getCredits(), teachAssist.getTranscript()))
                System.out.println("\n* Congratulations! You have been accepted to "
                        + p.getProgramName() + " in " + p.getUniversity());
            else
                System.out.println("\n* Unfortunately, you did not meet the requirements for "
                        + p.getProgramName() + " in " + p.getUniversity());
        });
    }
}