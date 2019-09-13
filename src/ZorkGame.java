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

    static HashMap<Integer, ArrayList<String>> wholeMap = new HashMap<Integer, ArrayList<String>>(){
        private static final long serialVersionUID = 1L;
        {
            put(1, new ArrayList<String>(Arrays.asList("north(n)")));
            put(2, new ArrayList<String>(Arrays.asList("west(w)","east(e)","south(s)","back(b)")));
            put(3, new ArrayList<String>(Arrays.asList("north(n)","east(e)","back(b)")));
            put(4, new ArrayList<String>(Arrays.asList("north(n)","west(w)","back(b)")));
            put(5, new ArrayList<String>(Arrays.asList("south(s)","back(b)")));
            put(6, new ArrayList<String>(Arrays.asList("north(n)","east(e)","back(b)")));
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

    private static void evaluateRoom(int roomNum, String userInput) {
        int temp = currRoomNum;

        switch (roomNum) {
            case 1:
                if(userInput.equalsIgnoreCase("n"))
                    currRoomNum = 2;
                break;
            case 2:
                if(userInput.equalsIgnoreCase("w"))
                    currRoomNum = 3;
                else if(userInput.equalsIgnoreCase("e"))
                    currRoomNum = 4;
                else if(userInput.equalsIgnoreCase("s"))
                    currRoomNum = 1;
                break;
            case 3:
                if(userInput.equalsIgnoreCase("n"))
                    currRoomNum = 5;
                else if(userInput.equalsIgnoreCase("w"))
                    currRoomNum = 2;
                break;
            case 4:
                if(userInput.equalsIgnoreCase("n"))
                    currRoomNum = 7;
                else if(userInput.equalsIgnoreCase("w"))
                    currRoomNum = 2;

        }

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

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<String>> currMap = new HashMap<>();

        boolean toExit = false;

        System.out.println("Welcome to Zork!");
        foyer();

        currRoomNum = 1;


        while(true) {

            System.out.print("\nEnter a direction or Q to quit: ");
            userInput = sc.nextLine();

            evaluateRoom(currRoomNum,userInput);

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
                    foyer(userInput);
                    break;
                case 5:
                    foyer(userInput);
                    break;
                case 6:
                    foyer(userInput);
                    break;
                case 7:
                    foyer(userInput);
                    break;
                case 8:
                    foyer(userInput);
                    break;
            }

        }

    }
}
