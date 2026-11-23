// Written by Arsal Abbas Mirza
// on 22/11/2026, as part of the 'Schlachtschiff' Project.
// An implementation of the classic 'Battleship' Game in Java.
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BattleShipGame {
    private static final int BOARD_SIZE = 6;
    private static final int MAX_TURNS = 15;

    private final Board board;
    private final Scanner scanner;
    private int turnsLeft;

    public BattleShipGame(){
        this.board = new Board(BOARD_SIZE);
        this.scanner = new Scanner(System.in);
        this.turnsLeft = MAX_TURNS;

        List<Ship> shipsToPlace = Arrays.asList(
            new Ship("Cruiser", 3),
            new Ship("Submarine", 3),
            new Ship("Destroyer", 2)
        );
        BoardUtil.placeFleetRandomly(board, shipsToPlace);
    }
    public void start(){
        System.out.println("=== OOP BATTLESHIP ===");
        System.out.println("Sink the fleet within " + MAX_TURNS + " turns!\n");
        while (turnsLeft > 0 && !board.allShipsSunk()) {
            board.print(false);
            System.out.println("Turns remaining: " + turnsLeft);
            int row = getValidCoordinate("Enter row (0-" + (BOARD_SIZE - 1) + "): ");
            int col = getValidCoordinate("Enter column (0-" + (BOARD_SIZE - 1) + "): ");
            String result = board.fireAt(row, col);
            if (result.equals("ALREADY_FIRED")) {
                System.out.println("-> You already targeted that coordinate. Try again!");
                continue;
            } else if (result.startsWith("SUNK:")) {
                String shipName = result.split(":")[1];
                System.out.println("-> BOOM! You sank the enemy " + shipName + "!");
            } else if (result.equals("HIT")) {
                System.out.println("-> DIRECT HIT!");
            } else {
                System.out.println("-> Splash... Missed.");
            }

            turnsLeft--;
        }
        endGame();        
    }
    private void endGame(){
        System.out.println("\n=== GAME OVER ===");
        if (board.allShipsSunk()) {
            System.out.println("Victory! All enemy vessels destroyed!");
        } else {
            System.out.println("Defeat! You ran out of ammunition.");
        }
        board.print(true);
        scanner.close();
    }
    private int getValidCoordinate(String prompt) {
        int coordinate = -1;
        while (coordinate < 0 || coordinate >= BOARD_SIZE) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                coordinate = scanner.nextInt();
                if (coordinate < 0 || coordinate >= BOARD_SIZE) {
                    System.out.println("-> Invalid input. Please enter a number between 0 and " + (BOARD_SIZE - 1) + ".");
                }
            } else {
                System.out.println("-> Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
        return coordinate;
    }
    public static void main(String[] args) throws Exception {
       BattleShipGame game = new BattleShipGame();
       game.start();
    }
}
