package src;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Computer Science ICS4U
 * Culminating Project - Teach Assist class
 * January 2021
 * @author Barbod Habibi
 */

public class TeachAssist {
    Player player;
    
    /**
     * Constructor
     * pre: none
     * post: Teach Assist object is created with no parameters
     */
    public TeachAssist() {
        player = new Player();
    }
    
    /**
     * Constructor overload
     * pre: none
     * post: Teach Assist object is created
     * @param p 
     */
    public TeachAssist(Player p) {
        player = p;
    }
    
    /**
     * Modifier method
     * pre: none
     * post: new marks are calculated for the courses in specified semester
     * @param semester 
     */
    public void updateMarks(int semester) {
        // declaration
        Random rand = new Random();
        double updatedMark;
        double fluctuation;
        int min;
        
        for (int i = 0; i < 4; i++) {
            // obtained mark = (median ± fluctuation) + talent benefit + work ethic ± sleep fluctuation
            updatedMark = player.getTimetable()[semester - 1][i].getMedian();
            updatedMark += 2.5 * player.getTimetable()[semester - 1][i].getBenefits(player);
            updatedMark += 2.5 * player.getEffort()[i + 1];
            player.getFullName();
            
            // apply grade fluctuations due to sleep
            switch (player.getEffort()[0]) {
                case 1:
                    min = 7;
                    break;
                case 2:
                    min = 6;
                    break;
                case 3:
                    min = 5;
                    break;
                case 4:
                    min = 4;
                    break;
                case 5:
                    min = 3;
                    break;
                default:
                    min = 5;
            }
            fluctuation = rand.nextInt(11) - min;
            updatedMark += fluctuation;
            
            // updated mark cannot exceed 100%
            if (updatedMark > 100)
                updatedMark = 100;
            
            player.getTimetable()[semester - 1][i].addMark(updatedMark);
        }
    }
    
    /**
     * Display grades for courses in specified semester.
     * pre: none
     * post: grades for courses in specified semester are displayed
     * @param semester
     * @throws IOException 
     */
    public void open(int semester) throws IOException { // display the courses and associated grades in the semester
        // declaration
        Scanner input = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n* SEMESTER " + semester + " *");
            System.out.printf("%-50s%10s\n", "COURSE", "MARK");
            for (int i = 0; i < 4; i++) {
                if (player.getTimetable()[semester - 1][i].getMark() == 0) {
                    System.out.printf("%-10s%-40s%10s\n", player.getTimetable()[semester - 1][i].getCode(),
                            player.getTimetable()[semester - 1][i].getName(),
                            "N/A");
                }
                else {
                    System.out.printf("%-10s%-40s%10.1f%s\n", player.getTimetable()[semester - 1][i].getCode(),
                            player.getTimetable()[semester - 1][i].getName(),
                            roundMark(player.getTimetable()[semester - 1][i].getMark()), "%");
                }
            }
            System.out.println("\n1. View grade history.");
            System.out.println("0. RETURN");
            
            while (true) {
                System.out.println("Enter your choice: ");
                choice = input.nextInt();

                if (choice == 0) {
                    break;
                } else if (choice == 1) {
                    viewGradeHistory(semester);
                    break;
                } else {
                    System.out.println("Invalid input! Please try again.");
                }
            }
        } while (choice != 0);
    }
    /**
     * Helper method - round mark to one decimal place
     * pre: none
     * post: mark is rounded to one decimal place and returned
     * @param mark
     * @return 
     */
    private double roundMark(double mark) {
        return Math.round(mark * 10) / 10.0;
    }
    
    /**
     * Display grade history of specified course.
     * pre: non
     * post: grade history of specified course is displayed
     * @param semester
     * @throws IOException 
     */
    public void viewGradeHistory(int semester) throws IOException {
        // declaration
        Scanner input = new Scanner(System.in);
        int choice;
        
        // data obtainment from the user
        do {
            for (int i = 0; i < 4; i++)
                System.out.println((i + 1) + ". " + player.getTimetable()[semester - 1][i].getName());
            System.out.println("0. RETURN");
            
            // process data
            while (true) {
                System.out.println("Enter your choice: ");
                choice = input.nextInt();
                
                if (choice >= 1 && choice <= 4) {
                    System.out.println("* Grade History for " + player.getTimetable()[semester - 1][choice - 1].getName() + ":");
                    printGradeHistory(player.getTimetable()[semester - 1][choice - 1].getHistory());
                    System.in.read();
                    break;
                }
                else if (choice == 0)
                    break;
                else
                    System.out.println("Invalid input! Please try again.");
            }
        } while (choice != 0);
    }
    /**
     * Helper method - iterate and print marks in grade history
     * pre: none
     * post: marks in grade history are printed
     * @param history 
    
    // ORIGINAL METHOD
    private void printGradeHistory(ArrayList<Double> history) {
        for (Double h : history)
            System.out.println("\t" + h + "%");
    }
    */
    private void printGradeHistory(ArrayList<Double> history) {
        history.stream().forEach((h) -> {
            System.out.println("\t" + h + "%");
        });
    }
    
    /**
     * Accessor method
     * pre: none
     * post: display student transcript
     * @return 
     */
    public HashMap<String, Double> getTranscript() {
        // key: course code     value: mark
        HashMap<String, Double> transcript = new HashMap<>();
        
        // create transcript from the timetable
        for (int s = 0; s < 2; s++) {
            for (int p = 0; p < 4; p++) {
                transcript.put(player.getTimetable()[s][p].getCode(), player.getTimetable()[s][p].getMark());
            }
        }
        
        return transcript;
    }
}
