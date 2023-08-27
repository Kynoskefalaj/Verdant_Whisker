package root;

public enum Options_SubState {
    TOP(0),
    FULL_SCREEN(1),
    CONTROLS(2),
    END_GAME(3),
    GRAPHICS(4),
    PROPORTIONS(5),
    MERCH_SELECT(6),
    BUY(7),
    SELL(8),
    INVENTORY(9);

    private final int value;

    Options_SubState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
