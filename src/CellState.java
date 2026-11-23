public enum CellState {
    WATER('~', '~'),
    SHIP('~', 'S'),
    HIT('X', 'X'),
    MISS('O', 'O');

    private final char hiddenSymbol;
    private final char revealedSymbol;

    CellState(char hiddenSymbol, char revealedSymbol) {
        this.hiddenSymbol = hiddenSymbol;
        this.revealedSymbol = revealedSymbol;
    }
    public char getSymbol(boolean revealed) {
        return revealed ? revealedSymbol : hiddenSymbol;
    }
}
