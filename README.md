# Schlachtschiff

Schlachtschiff is a console implementation of the classic Battleship game written in Java. The player has 15 turns to find and sink three enemy ships placed randomly on a 6 x 6 board.

## Gameplay

The board uses numbered rows and columns:

- `~` - hidden water or hidden ship
- `X` - a hit
- `O` - a miss
- `S` - a ship, shown only when the board is revealed at the end

At the start of each turn, enter a valid row and column in the range `0` to `5`. Repeated attacks do not consume a turn. The game ends when every enemy ship is sunk or when all 15 turns have been used.

## OOP Design

- `BattleShipGame` manages the player-facing game loop, input, turn count, and end state.
- `Board` owns the board grid, tracks ship positions, processes attacks, and determines whether the fleet is sunk.
- `Ship` models a vessel, including its name, length, and number of hits.
- `CellState` represents the possible states of a board cell and their console symbols.
- `BoardUtil` provides grid initialization and non-overlapping random fleet placement.

The classes separate game rules, board state, ship state, and utility behavior. Encapsulation keeps mutable state inside `Board` and `Ship`, while the utility class centralizes placement and validation logic.

## Project Structure

```text
Schlachtschiff/
|-- src/
|   |-- BattleShipGame.java
|   |-- Board.java
|   |-- BoardUtil.java
|   |-- CellState.java
|   `-- Ship.java
|-- bin/
`-- README.md
```

## Requirements

- Java Development Kit (JDK) 8 or newer
- A Java-compatible terminal or IDE

## Run with VS Code

1. Open the project folder in VS Code.
2. Open `src/BattleShipGame.java`.
3. Run the `main` method using the Java extension.

The workspace settings compile sources in `src` and place class files in `bin`.

## Run from the Terminal

From the project root:

```powershell
javac -d bin src\*.java
java -cp bin BattleShipGame
```
