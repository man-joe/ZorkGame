import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;

public class ZorkGame {
    static int numRoomVisited = 0;
    static int currRoomNum = 0;
    static int prevRoomNum = 0;
    static Random rnd = new Random();
    static Scanner sc = new Scanner(System.in);
    static String userInput = "";
    static ArrayList<Integer> roomNumsVisited = new ArrayList<>();
    static boolean foundSecret = false;

    static HashMap<Integer, ArrayList<String>> wholeMap = new HashMap<Integer, ArrayList<String>>(){
        private static final long serialVersionUID = 1L;
        {
            put(1, new ArrayList<String>(Arrays.asList("north(n)")));
            put(2, new ArrayList<String>(Arrays.asList("west(w)","east(e)","south(s)","back(b)")));
            put(3, new ArrayList<String>(Arrays.asList("north(n)","east(e)","back(b)")));
            put(4, new ArrayList<String>(Arrays.asList("north(n)","west(w)","back(b)")));
            put(5, new ArrayList<String>(Arrays.asList("south(s)","back(b)")));
            put(6, new ArrayList<String>(Arrays.asList("east(e)","back(b)")));
            put(7, new ArrayList<String>(Arrays.asList("west(w)","south(s)","back(b)")));
            put(8, new ArrayList<String>(Arrays.asList("south(s)","back(b)")));
        }};

    //Foyer is starting area, after Foyer, back option is available to all rooms
    private static void foyer() {
        System.out.println("Welcome to the Foyer!");
        System.out.println("There's a dead scorpion...");

        System.out.println("You can go: " + wholeMap.get(1).toString());

        if(numRoomVisited == 0){ //first time visiting room
            wholeMap.get(1).add("back(b)");
            prevRoomNum = 1;
        }

        numRoomVisited++;
    }

    private static void evaluateRoom(String userInput) {
        int temp = currRoomNum;

        

        switch (currRoomNum) {
            case 1: // Foyer directional room choices
                if(userInput.equalsIgnoreCase("n"))
                    currRoomNum = 2;
                break;
            case 2: // Front Room directional room choices
                if(userInput.equalsIgnoreCase("w"))
                    currRoomNum = 3;
                else if(userInput.equalsIgnoreCase("e"))
                    currRoomNum = 4;
                else if(userInput.equalsIgnoreCase("s"))
                    currRoomNum = 1;
                break;
            case 3: // Library directional room choices
                if(userInput.equalsIgnoreCase("n"))
                    currRoomNum = 5;
                else if(userInput.equalsIgnoreCase("w"))
                    currRoomNum = 2;
                break;
            case 4: // Kitchen directional room choices
                if(userInput.equalsIgnoreCase("n"))
                    currRoomNum = 7;
                else if(userInput.equalsIgnoreCase("w"))
                    currRoomNum = 2;
                break;
            case 5: // Dining room directional room choices
                if(userInput.equalsIgnoreCase("s"))
                    currRoomNum = 3;
                break;
            case 6: // Vault directional room choices
                if(userInput.equalsIgnoreCase("sr"))
                    currRoomNum = 8;
                else if(userInput.equalsIgnoreCase("e"))
                    currRoomNum = 7;
                break;
            case 7:
                if(userInput.equalsIgnoreCase("w"))
                    currRoomNum = 6;
                else if(userInput.equalsIgnoreCase("s"))
                    currRoomNum = 4;
                break;
            case 8:
                if (userInput.equalsIgnoreCase("s"))
                    currRoomNum = 6;
                break;
        }

        if(!roomNumsVisited.contains(currRoomNum))
            roomNumsVisited.add(currRoomNum);

        if(userInput.equalsIgnoreCase("b"))
            currRoomNum = prevRoomNum;

        prevRoomNum = temp;
    }

    private static void frontRoom(){
        System.out.println("Welcome to the Front Room!");
        System.out.println("There's a piano...");

        System.out.println("You can go: " + wholeMap.get(2).toString());

        numRoomVisited++;
    }

    private static void library(){
        System.out.println("Welcome to the Library!");
        System.out.println("There are spicers...");

        System.out.println("You can go: " + wholeMap.get(3).toString());
    }

    private static void kitchen() {
        System.out.println("Welcome to the Kitchen!");
        System.out.println("There are bats...");

        System.out.println("You can go: " + wholeMap.get(4).toString());
    }

    private static void diningRoom() {
        System.out.println("Welcome to the Dining Room!");
        System.out.println("There is dust an empty box...");

        System.out.println("You can go: " + wholeMap.get(5).toString());
    }

    private static void vault() {
        System.out.println("Welcome to the Vault!");
        System.out.println("There are 3 walking skeletons...");

        if(rnd.nextInt(4) == 0 && foundSecret == false){ //25% chance of finding secret room
            System.out.println("And a mysterious door appears!!!!");
            wholeMap.get(6).add("secret room(sr)");
        }

        System.out.println("You can go: " + wholeMap.get(6).toString());
    }

    private static void parlor() {
        System.out.println("Welcome to the Parlor!");
        System.out.println("There is a treasure chest...");

        System.out.println("You can go: " + wholeMap.get(7).toString());
    }

    private static void secretRoom() {
        System.out.println("Welcome to the Secret Room!");
        System.out.println("There are piles of gold...");

        System.out.println("You can go: " + wholeMap.get(8).toString());
    }

    public static void main(String[] args) {
        boolean enterOrNot = false;

        System.out.println("Welcome to Zork!");
        
        System.out.println("Do you want to enter?(y/n):");
        userInput = sc.nextLine();
        if(userInput.equalsIgnoreCase("y")){
            foyer();
            currRoomNum = 1; 
            roomNumsVisited.add(1);
            enterOrNot = true; 
        }
        while(enterOrNot) {

            System.out.print("\nEnter a direction or Q to quit: ");
            userInput = sc.nextLine();

            evaluateRoom(userInput);

            if(userInput.equalsIgnoreCase("q"))
                break;

            switch (currRoomNum) {
                case 1:
                    foyer();
                    break;
                case 2:
                    frontRoom();
                    break;
                case 3:
                    library();
                    break;
                case 4:
                    kitchen();
                    break;
                case 5:
                    diningRoom();
                    break;
                case 6:
                    vault();
                    break;
                case 7:
                    parlor();
                    break;
                case 8:
                    secretRoom();
                    break;
            }
        }

        System.out.println("Thanks for Playing Zork!");
        System.out.println("You have visited " + roomNumsVisited.size() + " rooms.");
        if(rnd.nextInt(4) == 0) // 25% this will happen
            System.out.println("You feel a strange presence as you leave...");
    }
}
