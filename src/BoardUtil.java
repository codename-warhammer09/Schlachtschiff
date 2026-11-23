// Written by Arsal Abbas Mirza
// on 22/11/2026, as part of the 'Schlachtschiff' Project.
import java.util.List;
import java.security.SecureRandom;

public final class BoardUtil {

    // Private constructor prevents instantiation of a utility class
    private BoardUtil() {}

    public static void initializeGrid(CellState[][] grid, CellState defaultState) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = defaultState;
            }
        }
    }

    public static void placeFleetRandomly(Board board, List<Ship> ships) {
        SecureRandom random = new SecureRandom();
        int size = board.getSize();

        for (Ship ship : ships) {
            boolean placed = false;
            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int row = random.nextInt(size);
                int col = random.nextInt(size);

                if (canPlaceShip(board, row, col, ship.getLength(), horizontal)) {
                    board.addShip(ship, row, col, horizontal);
                    placed = true;
                }
            }
        }
    }

    public static boolean canPlaceShip(Board board, int row, int col, int length, boolean horizontal) {
        int size = board.getSize();

        if (horizontal && col + length > size) return false;
        if (!horizontal && row + length > size) return false;

        for (int i = 0; i < length; i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            if (board.getCellState(r, c) != CellState.WATER) {
                return false;
            }
        }
        return true;
    }
}