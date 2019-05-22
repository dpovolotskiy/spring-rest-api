package ssu.org.epam.model;

public enum Project {
    Testing(1),
    Developing(2),
    DevOps(3),
    Managing(4);

    private final int value;

    private Project(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
