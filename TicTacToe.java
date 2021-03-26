import java.util.*;

public class TicTacToe {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Integer> userPositions = new ArrayList<>();
    private static ArrayList<Integer> computerPositions = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);
        while (true) {
            placePostion(gameBoard, "user");
            checkWinner();

            placePostion(gameBoard, "computer");
            checkWinner();
        }
    }


    private static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char character : row) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    private static int getInputPosition(String player) {
        if (player.equals("user")) {
            System.out.println("Enter the position to fill (1 to 9)");
            int pos = scanner.nextInt();
            while (userPositions.contains(pos) || computerPositions.contains(pos)) {
                System.out.println("#" + pos + " position is already taken try again");
                pos = scanner.nextInt();
            }
            return pos;
        } else {
            Random ran = new Random();
            System.out.println("Computer's turn to play game");
            int pos = ran.nextInt(9) + 1;
            while (userPositions.contains(pos) || computerPositions.contains(pos)) {
                pos = ran.nextInt(9) + 1;
            }
            return pos;
        }

    }


    private static void placePostion(char[][] gameBoard, String user) {
        int pos = getInputPosition(user);
        char key;
        if (user.equals("user")) {
            key = 'X';
            userPositions.add(pos);
        } else {
            key = 'O';
            computerPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = key;
                break;
            case 2:
                gameBoard[0][2] = key;
                break;
            case 3:
                gameBoard[0][4] = key;
                break;
            case 4:
                gameBoard[2][0] = key;
                break;
            case 5:
                gameBoard[2][2] = key;
                break;
            case 6:
                gameBoard[2][4] = key;
                break;
            case 7:
                gameBoard[4][0] = key;
                break;
            case 8:
                gameBoard[4][2] = key;
                break;
            case 9:
                gameBoard[4][4] = key;
                break;
        }

        printGameBoard(gameBoard);
    }


    private static void checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List lastRow = Arrays.asList(7, 8, 9);
        List firstCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List lastCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);


        List<List> lists = new ArrayList();
        lists.add(topRow);
        lists.add(midRow);
        lists.add(lastRow);
        lists.add(firstCol);
        lists.add(midCol);
        lists.add(lastCol);
        lists.add(cross1);
        lists.add(cross2);
        String message = null;
        for (List l : lists) {
            if (userPositions.containsAll(l)) {
                System.out.println( "User is the winner");
                System.exit(0);
            } else if (computerPositions.containsAll(l)) {
                System.out.println( "Computer is the winner");
                System.exit(0);
            } else if (userPositions.size() + computerPositions.size() >= 9) {
                System.out.println( "TIE");
                System.exit(0);
            }
        }
    }
}
