package root;

public enum ScreenProportions {
    res16_9(0),
    res21_9(1),
    res3_2(2);

    private final int value;

    ScreenProportions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
