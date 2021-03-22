package src;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Computer Science ICS4U
 * Culminating Project - OUAC class
 * January 2021
 * @author Barbod Habibi
 */

public class OUAC {
    Player applicant;
    
    /**
     * Constructor
     * pre: none
     * post: OUAC object is created with no parameters
     */
    public OUAC() {
        applicant = new Player();
    }
    
    /**
     * Constructor
     * pre: none
     * post: OUAC object is created
     * @param player 
     */
    public OUAC(Player player) {
        applicant = player;
    }
    
    /**
     * Start application to university program
     * pre: none
     * Application to a program in a university is completed or canceled by user
     * @param programs 
     */
    public void startApplication(ArrayList<Program> programs)  { // applied = false
        // declaration
        Scanner input = new Scanner(System.in);
        Program program;
        String university;
        String choice;
        boolean done = false;
        
        // data obtainment from the user
        System.out.println("Which university would you like to apply to?");
        System.out.println("  - Lakehead University" +
            "\n  - McMaster University" +
            "\n  - Ryerson University" +
            "\n  - University of Toronto" +
            "\n  - University of Waterloo" +
            "\n  - York University" +
            "\n  - CANCEL");
        
        while (true) {
            System.out.println("Enter your choice: ");
            choice = input.nextLine();

            if (validateResponse(choice)) {
                university = choice;
                break;
            }
            else if (choice.equalsIgnoreCase("cancel"))
                return;
            else
                System.out.println("Invalid input! Please try again.");
        }
        
        // data obtainnment from the user
        while (true) {
            System.out.println("\n* Enter your field of interest: ");
            System.out.println("  - Science" +
                    "\n  - Humanities" +
                    "\n  - Engineering" +
                    "\n  - Business" +
                    "\n  - CANCEL");
            
            do {
                System.out.println("Enter your choice: ");
                choice = input.next();

                if (choice.equalsIgnoreCase("science")) {
                    do {
                        System.out.println("Enter your field of interest:");
                        System.out.println("  - Physics"
                                + "\n  - Chemistry"
                                + "\n  - Biology"
                                + "\n  - RETURN");

                        while (true) {
                            System.out.println("Enter your choice:");
                            choice = input.next();

                            if (choice.equalsIgnoreCase("physics")) {
                                program = new IntegratedScience_Physics(university);
                                if (!repeatedProgram(programs, program)) {
                                    programs.add(program);
                                    return;
                                }
                                else
                                    System.out.println("\nError! You have already applied to this program.");
                            }
                            else if (choice.equalsIgnoreCase("chemistry")) {
                                program = new IntegratedScience_Chemistry(university);
                                if (!repeatedProgram(programs, program)) {
                                    programs.add(program);
                                    return;
                                }
                                else
                                    System.out.println("\nError! You have already applied to this program.");
                            }
                            else if (choice.equalsIgnoreCase("biology")) {
                                program = new IntegratedScience_Biology(university);
                                if (!repeatedProgram(programs, program)) {
                                    programs.add(program);
                                    return;
                                }
                                else
                                    System.out.println("\nError! You have already applied to this program.");
                            }
                            else if (choice.equalsIgnoreCase("return"))
                                break;
                            else
                                System.out.println("Invalid input! Please try again.");
                        }
                    } while (!choice.equalsIgnoreCase("return"));
                }
                else if (choice.equalsIgnoreCase("humanities")) {
                    program = new Humanities(university);
                    if (!repeatedProgram(programs, program)) {
                        programs.add(program);
                        return;
                    }
                    System.out.println("\nError! You have already applied to this program.");
                }
                else if (choice.equalsIgnoreCase("engineering")) {
                    program = new Engineering(university);
                    if (!repeatedProgram(programs, program)) {
                        programs.add(program);
                        return;
                    }
                    System.out.println("\nError! You have already applied to this program.");
                }
                else if (choice.equalsIgnoreCase("business")) {
                    program = new Business(university);
                    if (!repeatedProgram(programs, program)) {
                        programs.add(program);
                        return;
                    }
                    System.out.println("\nError! You have already applied to this program.");
                }
                else if (choice.equalsIgnoreCase("cancel"))
                    return;
                else
                    System.out.println("Invalid input! Please try again.");
            } while (!choice.equals("return"));
        }
    }
    
    /**
     * Helper method - determine whether user has inputted a valid university name
     * pre: none
     * post: return true if valid university name is entered and false if invalid university name is entered
     * @param university
     * @return 
     */
    private boolean validateResponse(String university) {
        String[] universityList = {"University of Toronto", "University of Waterloo",
            "McMaster University", "Ryerson University", "York University", "Lakehead University"};
        
        for (String u : universityList) {
            if (university.equalsIgnoreCase(u))
                return true;
        }
        return false;
    }
    
    /**
     * Helper method - check whether the selected program had previously been selected
     * pre: none
     * post: return true if the program had previously been selected and false if program had not previously been selected
     * @param programs
     * @param program
     * @return 
    
    // ORIGINAL METHOD
    private boolean repeatedProgram(ArrayList<Program> programs, Program program) {
        for (Program p : programs) {
            if (program.getUniversity().equalsIgnoreCase(p.getUniversity()) &&
                    program.getProgramName().equalsIgnoreCase(p.getProgramName()))
                return true;
        }
        return false;
    }
    */
    private boolean repeatedProgram(ArrayList<Program> programs, Program program) {
        return programs.stream().anyMatch((p) -> (program.getUniversity().equalsIgnoreCase(p.getUniversity()) &&
                program.getProgramName().equalsIgnoreCase(p.getProgramName())));
    }
}
