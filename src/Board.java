import java.util.ArrayList;
import java.util.List;
public class Board {
    private final int size;
    private final CellState[][] grid;
    private final Ship[][] shipGrid; // Maps Ship Instances
    private final List<Ship> fleet; 

    public Board(int size) {
        this.size = size;
        this.grid = new CellState[size][size];
        this.shipGrid = new Ship[size][size];
        this.fleet = new ArrayList<>();
        BoardUtil.initializeGrid(this.grid, CellState.WATER);
    }
    public void addShip(Ship ship, int row, int col, boolean horizontal){
        for (int i = 0; ship.getLength() > i; i++){
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            grid[r][c] = CellState.SHIP;
            shipGrid[r][c] = ship;
        }
        if (!fleet.contains(ship)) {
            fleet.add(ship);
        }
    }
    public String fireAt(int row, int col)
{
    if (row < 0 || row >= size || col < 0 || col >= size) {
        return "INVALID";
    }
    CellState currentState = grid[row][col];
    if (currentState == CellState.HIT || currentState == CellState.MISS) {
        return "ALREADY_FIRED";
    }
    if (currentState == CellState.SHIP) {
        grid[row][col] = CellState.HIT;
        Ship hitShip = shipGrid[row][col];
        hitShip.registerHit();
        if (hitShip.isSunk()) {
            return "SUNK:" + hitShip.getName();
        }
        return "HIT";
    } else {
        grid[row][col] = CellState.MISS;
        return "MISS";
    }
}
public boolean allShipsSunk() {
    if (fleet.isEmpty()) {
        return false;
    }
    for (Ship ship : fleet) {
        if (!ship.isSunk()) {
            return false;
        }
    }
    return true;
}
public int getSize() {
    return size;
}
public CellState getCellState(int row, int col) {
    return grid[row][col];

}
public void print(boolean revealShips) {
        System.out.print("\n  ");
        for (int c = 0; c < size; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int r = 0; r < size; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < size; c++) {
                System.out.print(grid[r][c].getSymbol(revealShips) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
