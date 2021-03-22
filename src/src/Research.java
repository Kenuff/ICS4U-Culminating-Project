package src;
import java.io.IOException;
import java.util.Scanner;

/**
 * Computer Science ICS4U
 * Culminating Project - Research class
 * January 2021
 * @author Barbod Habibi
 */

public class Research {
    String[] universityList = {"University of Toronto", "University of Waterloo",
            "McMaster University", "Ryerson University", "York University", "Lakehead University"};
    
    /**
     * Constructor
     * pre: none
     * post: create research class with no parameters
     */
    public Research() {
        
    }
    
    /**
     * Display program rankings based on prestige & admission average (variable)
     * pre: none
     * post: programs rankings are displayed
     */
    public void rankPrograms() {
        // declaration
        Program[] programs;
        String university;
        Scanner input = new Scanner(System.in);
        String choice;
        
        
        // display the programs
        System.out.println("\nFor which program would you like to view the rankings?");
        System.out.println("  - Science");
        System.out.println("  - Humanities");
        System.out.println("  - Engineering");
        System.out.println("  - Business");
        
        while (true) {
            // data obtainment from the user
            System.out.println("Enter your choice: ");
            choice = input.next();
            
            // process data
            if (choice.equalsIgnoreCase("Science")) {
                programs = new Science[6];
                for (int i = 0; i < programs.length; i++)
                    programs[i] = new Science(universityList[i]);
                break;
            }
            else if (choice.equalsIgnoreCase("Humanities")) {
                programs = new Humanities[6];
                for (int i = 0; i < programs.length; i++)
                    programs[i] = new Humanities(universityList[i]);
                break;
            }
            else if (choice.equalsIgnoreCase("Engineering")) {
                programs = new Engineering[6];
                for (int i = 0; i < programs.length; i++)
                    programs[i] = new Engineering(universityList[i]);
                break;
            }
            else if (choice.equalsIgnoreCase("Business")) {
                programs = new Business[6];
                for (int i = 0; i < programs.length; i++)
                    programs[i] = new Business(universityList[i]);
                break;
            }
            else {
                System.out.println("Invalid input! Please try again.");
            }
        }
        
        // output data to the user
        System.out.println("\nRanking of universities' " + choice.toLowerCase() +
                " programs by prestige & admission average of the previous school year (2019-20):");
        int index = 1;
        for (int i = bubbleSort(programs).length - 1; i >= 0; i--) {
            System.out.println("  " + index + ". " + programs[i].getUniversity());
            index++;
        }
    }
    
    /**
     * Helper method - sort university program by prestige from least to highest (bubble sort)
     * pre: none
     * post: university programs are sorted from least to highest by prestige & admission average
     * @param programs
     * @return 
     */
    private static Program[] bubbleSort(Program[] programs) {
        // declaration
        Program temp;
        
        // processing & calculation
        for (int i = 0; i < programs.length; i++) {
            for (int j = i; j < programs.length; j++) {
                if (programs[j].getPrestige() < programs[i].getPrestige()) { // prestige is first priority
                    temp = programs[i];
                    programs[i] = programs[j];
                    programs[j] = temp;
                }
                else if (programs[j].getPrestige() == programs[i].getPrestige()) {
                    if (programs[j].getAdmission() < programs[i].getAdmission()) { // admission average is second priority
                        temp = programs[i];
                        programs[i] = programs[j];
                        programs[j] = temp;
                    }
                }
            }
        }
        
        return programs;
    }
    
    /**
     * Display university rankings based on 2021 Maclean's reputational rankings across Canada
     * pre: none
     * post: university rankings are displayed
     */
    public void rankUniversities() {
        // declaration
        University[] universities = new University[6];
        for (int i = 0; i < universities.length; i++)
            universities[i] = new University(universityList[i]);
        
        // process data & output data to the user
        System.out.println("\nRanking of universities based on 2021 Maclean's reputational rankings across Canada");
        int index = 1;
        for (int i = 0; i < cycleSort(universities).length; i++) {
            System.out.printf("%-40s%-10s%-10d\n", "  " + universities[i].getUniversity(),
                    "Ranking: ", universities[i].getRanking());
            index++;
        }
    }
    /**
     * Helper method - sort universities by 2021 Maclean's reputational rankings across Canada (cycle sort)
     * @param universities
     * @return 
     */
    public University[] cycleSort(University[] universities) {
        for (int index = 0; index < universities.length; index++) {
            int position = index; // correct position of the element in the array
            University item = universities[index]; // element that we are checking
            
            // find the correct location of item
            for (int i = index; i < universities.length - 1; i++) {
                if (item.getRanking() > universities[i].getRanking())
                    position++;
            }
            
            // if element is already in correct position, check other elements
            if (index == position)
                continue;
            
            // if there is a duplicate at the correct location, item should not be swapped
            while (item == universities[position])
                position++; // instead, the position will shift to the right
            University temp = item;
            item = universities[position];
            universities[position] = temp;
            
            // rotate the rest of the cycle
            while (position != index) {
                position = index;
                
                // find the correct location of item
                for (int i = index + 1; i < universities.length; i++) {
                    if (item.getRanking() > universities[i].getRanking())
                        position++;
                }
                
                // if there is a duplicate at the correct location, item should not be swapped
                while (item == universities[position])
                    position++; // instead, the position will shift to the right
                temp = item;
                item = universities[position];
                universities[position] = temp;
            }
        }
        
        return universities;
    }
    
    /**
     * Allow user to explore & research available universities
     * pre: none
     * post: information of selected universities is displayed
     * @throws Exception 
     */
    public void researchUniversity() throws Exception {
        // declaration
        University university = new University();
        int choice;
        String response;
        Scanner input = new Scanner(System.in);
        
        while (true) {
            // display the universities to the user
            System.out.println("\nWhich university would you like to research?");
            System.out.println("  1. Lakehead University");
            System.out.println("  2. McMaster University");
            System.out.println("  3. Ryerson University");
            System.out.println("  4. University of Toronto");
            System.out.println("  5. University of Waterloo");
            System.out.println("  6. York University");
            System.out.println("  0. RETURN");

            // data obtainment from the user
            while (true) {
                System.out.println("Enter your choice: ");
                choice = input.nextInt();
                
                if (choice == 1) {
                    university = new University("Lakehead University");
                    break;
                }
                else if (choice == 2) {
                    university = new University("McMaster University");
                    break;
                }
                else if (choice == 3) {
                    university = new University("Ryerson University");
                    break;
                }
                else if (choice == 4) {
                    university = new University("University of Toronto");
                    break;
                }
                else if (choice == 5) {
                    university = new University("University of Waterloo");
                    break;
                }
                else if (choice == 6) {
                    university = new University("York Univeristy");
                    break;
                }
                else if (choice == 0)
                    break;
                else {
                    System.out.println("Invalid input! Please try again.");
                }
            }
            if (choice == 0)
                break;
            else if (choice != 0) {
                // display general university information to the user
                System.out.println(university);

                while (true) {
                    // data obtainment from the user
                    System.out.println("\nWould you like to visit the website as well?");
                    response = input.next();

                    // process data
                    if (response.equalsIgnoreCase("yes")) {
                        university.openWebsite();
                        System.in.read();
                        break;
                    }
                    else if (response.equalsIgnoreCase("no")) {
                        break;
                    }
                    else
                        System.out.println("Invalid input! Please try again.");
                }
            }
        }
    }
    
    /**
     * Allow user to explore & research available programs
     * pre: none
     * post: information of selected programs is displayed
     */
    public void researchProgram() throws IOException {
        // declaration
        Program program;
        String choice, field;
        Scanner input = new Scanner(System.in);
        
        // data obtainment from the user
        while (true) {
            System.out.println("\nWhich program would you like to research?");
            System.out.println("  - Science");
            System.out.println("  - Humanities");
            System.out.println("  - Engineering");
            System.out.println("  - Business");
            System.out.println("  - RETURN");
            System.out.println("Enter your choice: ");
            choice = input.next();

            // process data
            if (choice.equalsIgnoreCase("science")) {
                // display the fields of science to the user
                System.out.println("Which field of integrated science are you interested in?");
                System.out.println("  - Physics");
                System.out.println("  - Chemistry");
                System.out.println("  - Biology");
                System.out.println("  - RETURN");
                // choose the specific field of science
                while (true) {
                    System.out.println("Enter your choice: ");
                    field = input.next();

                    if (field.equalsIgnoreCase("physics") || field.equalsIgnoreCase("chemistry") || field.equalsIgnoreCase("biology")) {
                        viewScienceRequirements(field); // output data to the user
                        System.in.read();
                        break;
                    }
                    else if (field.equalsIgnoreCase("return"))
                        break;
                    else
                        System.out.println("Invalid input! Please try again.");
                }
            }
            else if (choice.equalsIgnoreCase("humanities")) {
                //ouput data to the user
                viewHumanitiesRequirements();
                System.in.read();
            }
            else if (choice.equalsIgnoreCase("engineering")) {
                // output data to the user
                viewEngineeringRequirements();
                System.in.read();
            }
            else if (choice.equalsIgnoreCase("business")) {
                // ouptput data to the user
                viewBusinessRequirements();
                System.in.read();
            }
            else if (choice.equalsIgnoreCase("return"))
                break;
            else
                System.out.println("Invalid input! Please try again.");
        }
    }
    
    /**
     * Helper method - display science requirements
     * pre: none
     * post: science requirements are displayed
     * @param field 
     */
    private void viewScienceRequirements(String field) {
        System.out.println("\nAdmission requirements for " + format(field) + ":");
        
        if (field.equalsIgnoreCase("physics")) {
            System.out.println("  ENG4U: English");
            System.out.println("  SPH4U: Physics");
            System.out.println("  MHF4U: Advanced Functions");
            System.out.println("  MCV4U: Calculus & Vectors");
        }
        else if (field.equalsIgnoreCase("chemistry")) {
            System.out.println("  ENG4U: English");
            System.out.println("  SCH4U: Chemistry");
            System.out.println("  MHF4U: Advanced Funtions");
            System.out.println("  MCV4U: Calculus & Vectors");
        }
        else {
            System.out.println("  ENG4U: English");
            System.out.println("  SBI4U: Biology");
            System.out.println("  SCH4U: Chemistry");
            System.out.println("  MHF4U: Advanced Functions");
            System.out.println("  MCV4U: Calculus & Vectors");
        }
    }
    
    /**
     * Helper method - format the string: change first letter to upper case and the rest to lower case
     * pre: none
     * post: field name is formatted: first letter is upper case and the rest is lower case
     * @param field
     * @return 
     */
    private String format(String field) {
        return field.substring(0,1).toUpperCase() + field.substring(1).toLowerCase();
    }
    
    /**
     * Helper method - display engineering requirements
     * pre: none
     * post: engineering requirements are displayed
     */
    private void viewEngineeringRequirements() {
        System.out.println("\nAdmission Requirements for Engineering:");
        System.out.println("  ENG4U: English");
        System.out.println("  MHF4U: Advanced Functions");
        System.out.println("  MCV4U: Calculus & Vectors");
        System.out.println("  SPH4U: Physics");
        System.out.println("  SCH4U: Chemistry");
    }
    
    /**
     * Helper method - display business requirements
     * pre: none
     * post: business requirements are displayed
     */
    private void viewBusinessRequirements() {
        System.out.println("\nAdmission Requirements for Business Studies:");
        System.out.println("  ENG4U: English");
        System.out.println("  MHF4U: Advanced Functions OR MDM4U: Mathematics of Data Management");
        System.out.println("  CIA4U: Analyzing Current Economics Issues OR BBB4M: International Business");
    }
    
    /**
     * Helper method - display humanities requirements
     * pre: none
     * post: humanities requirements are displayed
     */
    private void viewHumanitiesRequirements() {
        System.out.println("\nAdmission Requirements for Humanities:");
        System.out.println("  ENG4U: English");
        System.out.println("  HHG4M: Psychology of Human Development");
    }
}