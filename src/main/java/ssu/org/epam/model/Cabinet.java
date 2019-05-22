package ssu.org.epam.model;

public enum Cabinet {
    FisrtLayer(1),
    SecondLayer(2),
    ThirdLayer(3);

    private final int value;

    private Cabinet(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
