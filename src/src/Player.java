package src;
import java.util.Scanner;

public class Player extends Student {
    /*
    Natural Talents (4 chosen):
    - Critical Thinking         index = 0   benefit all subjects but with half the effect
    - Logical-matehamtical      index = 1
    - Abstract thinking         index = 2
    - Spatial visualization     index = 3
    - Linguistic intelligence   index = 4
    - Working memory            index = 5
    - Kinesthetic intelligence  index = 6
    - Emotional intelligence    index = 7
    */
    boolean[] talents = {false, false, false, false, false, false, false, false};
    
    /*
    Sleep: determines grade fluctuation
    - *         +3, -7      value = 1
    - **        +4, -6      value = 2
    - ***       +5, -5      value = 3
    - ****      +6, -4      value = 4
    - *****     +7, -3      value = 5
    */
    int[] effort;
    int energy;
    
    /**
     * Constructor
     * pre: none
     * post: player object is created with no parameters
     */
    public Player() {
        super();
        setTalents();
        effort = new int[5];
        energy = 17;
    }
    
    /**
     * Constructor overload
     * pre: none
     * post: player object is created
     * @param firstName
     * @param lastName 
     */
    public Player(String firstName, String lastName) {
        super(firstName, lastName, 12, "Maple Virtual High School");
        setTalents();
        effort = new int[5];
        energy = 17;
    }
    
    /**
     * Modifier method
     * pre: none
     * post: talents of the player are set
     */
    public final void setTalents() {
        // declaration
        Scanner input = new Scanner(System.in);
        int[] choice = new int[4];
        int i = 0;
        
        // data obtainment from the user
        System.out.println("Pick 4 natural strengths:\n" +
                "1. Critical thinking\t* benefit all subjects but with half the effect\n" +
                "2. Logical-mathematical intelligence\n" +
                "3. Abstract thinking\n" +
                "4. Spatial visualization\n" +
                "5. Linguistic & verbal intelligence\n" +
                "6. Working memory\n" +
                "7. Athleticism and kinesthetic learning\n" +
                "8. Emotional intelligence");
        while (i < 4) {
            System.out.println("\nEnter choice #" + (i + 1) +  ": ");
            choice[i] = input.nextInt();
            if (choice[i] < 1 || choice[i] > 8 || repeatedChoice(choice, i)) {
                System.out.println("Invalid input! Please try again.");
                continue;
            }
            talents[choice[i] - 1] = true;
            i++;
        }
        System.out.println("Talents determined successfully!");
    }
    /**
     * Helper method - determine whether newly inputted value is repeated or not (linear search)
     * pre: none
     * post: return true if the course is repeated and false if the course is not repeated
     * @param choice
     * @param index
     * @return 
     */
    private boolean repeatedChoice(int[] choice, int index) {
        int item = choice[index];
        for (int i = index - 1; i >= 0; i--) {
            if (choice[index] == choice[i])
                return true;
        }
        return false;
    }
    
    /**
     * Modifier method
     * pre: none
     * post: effort and energy input of player is set in a one-dimensional array
     * @param semester
     */
    public void setEffort(int semester) {
        // declaration
        Scanner input = new Scanner(System.in);
        
        // data obtainment from the user
        while(true) {
            while(true) {
                System.out.println("Enter the amount of sleep (out of 5): ");
                effort[0] = input.nextInt();
                if (validateInput(effort[0]))
                    break;
                else
                    System.out.println("Invalid input! Please try again.");
            }
            while(true) {
                System.out.println("Enter the energy input for period 1 (" + getTimetable()[semester - 1][0].getName() + "): ");
                effort[1] = input.nextInt();
                if (validateInput(effort[1]))
                    break;
                else
                    System.out.println("Invalid input! Please try again.");
            }
            while(true) {
                System.out.println("Enter the energy input for period 2 (" + getTimetable()[semester - 1][1].getName() + "): ");
                effort[2] = input.nextInt();
                if (validateInput(effort[2]))
                    break;
                else
                    System.out.println("Invalid input! Please try again.");
            }
            while(true) {
                System.out.println("Enter the energy input for period 3 (" + getTimetable()[semester - 1][2].getName() + "): ");
                effort[3] = input.nextInt();
                if (validateInput(effort[3]))
                    break;
                else
                    System.out.println("Invalid input! Please try again.");
            }
            while(true) {
                System.out.println("Enter the energy input for period 4 (" + getTimetable()[semester - 1][3].getName() + "): ");
                effort[4] = input.nextInt();
                if (validateInput(effort[4]))
                    break;
                else
                    System.out.println("Invalid input! Please try again.");
            }
            
            if (totalEnergyInput(effort, 0) != getEnergy())
                System.out.println("Total energy input does not add up to " + getEnergy() + "! Please try again.");
            else
                break;
        }
    }
    /**
     * Helper method - validate user's input
     * pre: none
     * post: return true if input is between 1 and 5, and false if input is not between 1 and 5
     * @param input
     * @return 
     */
    private boolean validateInput(int input) {
        return !(input < 1 || input > 5);
    }
    /**
     * Helper method - add total amount of energy input
     * pre: none
     * post: total amount of energy input is returned
     * @param effort
     * @param index
     * @return 
     */
    private int totalEnergyInput(int[] effort, int index) {
        if (index == effort.length - 1) // base case
            return effort[index];
        else // recursive case
            return totalEnergyInput(effort, index + 1) + effort[index];
    }
    
    /**
     * Accessor method
     * pre: none
     * post: talents of student are returned
     * @return 
     */
    public boolean[] getTalents() {
        return talents;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: total amount of energy of player is returned
     * @return 
     */
    public int getEnergy() {
        return energy;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: an array of player energy input is returned
     * @return 
     */
    public int[] getEffort() {
        return effort;
    }
    
    /**
     * Accessor method
     * pre: none
     * post: a string, containing all information associated with player, is returned
     * @return
     */
    @Override
    public String toString() {
        String info = "\nName: " + super.getName()
                + "\nGrade: " + super.getGrade()
                + "\nStrengths:";
        if (talents[0]) {
            info += "\n- Critical thinking";
        }
        if (talents[1]) {
            info += "\n- Logical-mathematical intelligence";
        }
        if (talents[2]) {
            info += "\n- Abstract thinking";
        }
        if (talents[3]) {
            info += "\n- Spatial visualization";
        }
        if (talents[4]) {
            info += "\n- Linguistic & verbal intelligence";
        }
        if (talents[5]) {
            info += "\n- Working memory";
        }
        if (talents[6]) {
            info += "\n- Athleticism & kinesthetic intelligence";
        }
        if (talents[7]) {
            info += "\n- Emotional intelligence";
        }

        return info;
    }
}