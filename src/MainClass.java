import java.lang.reflect.Array;
import java.util.*;

public class MainClass {

    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {
        //Create Board
        char[][] Board = {{' ', '|', ' ', '|', ' '},
                {'-', '+', ' ', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', ' ', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printBoard(Board);

        while (true) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter Input Box(1-9): ");
            int playerInput = scan.nextInt();
            while (playerPosition.contains(playerInput) || cpuPosition.contains(playerPosition)) {
                System.out.println("Position Already Taken! Enter Correct Position: ");
                playerInput = scan.nextInt();
            }
            doInput(Board, playerInput, "player");

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rdm = new Random();
            int cpuInput = rdm.nextInt(9) + 1;
            while (playerPosition.contains(cpuInput) || cpuPosition.contains(cpuInput)) {
                cpuInput = rdm.nextInt(9) + 1;
            }
            doInput(Board, cpuInput, "cpu");

            printBoard(Board);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }
    }

    public static void printBoard (char[][] Board) {
        for (char[] row : Board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void doInput (char[][] Board, int input, String user) {
        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPosition.add(input);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPosition.add(input);
        }

        switch (input) {
            case 1: {
                Board[0][0] = symbol;
                break;
            }
            case 2: {
                Board[0][2] = symbol;
                break;
            }
            case 3: {
                Board[0][4] = symbol;
                break;
            }
            case 4: {
                Board[2][0] = symbol;
                break;
            }
            case 5: {
                Board[2][2] = symbol;
                break;
            }
            case 6: {
                Board[2][4] = symbol;
                break;
            }
            case 7: {
                Board[4][0] = symbol;
                break;
            }
            case 8: {
                Board[4][2] = symbol;
                break;
            }
            case 9: {
                Board[4][4] = symbol;
                break;
            }
            default:
                break;
        }
    }

    public static String checkWinner () {
        List rowOne = Arrays.asList(1, 2, 3);
        List rowTwo = Arrays.asList(4, 5, 6);
        List rowThree = Arrays.asList(7, 8, 9);

        List colOne = Arrays.asList(1, 4, 7);
        List colTwo = Arrays.asList(2, 5, 8);
        List colThree = Arrays.asList(3, 6, 9);

        List diagLtoR = Arrays.asList(1, 5, 9);
        List diagRtoL = Arrays.asList(3, 5, 7);

        List<List> winCondition = new ArrayList<List>();
        winCondition.add(rowOne);
        winCondition.add(rowTwo);
        winCondition.add(rowThree);
        winCondition.add(colOne);
        winCondition.add(colTwo);
        winCondition.add(colThree);
        winCondition.add(diagLtoR);
        winCondition.add(diagRtoL);

        for (List l : winCondition) {
            if (playerPosition.containsAll(l)) {
                return "Congratulations! You Won!";
            } else if (cpuPosition.containsAll(l)) {
                return "CPU Won! Sorry :(";
            } else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "Game Tied!";
            }
        }

        return "";
    }
}