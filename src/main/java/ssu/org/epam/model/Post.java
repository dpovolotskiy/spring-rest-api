package ssu.org.epam.model;

public enum Post {
    Manager(1),
    JuniorDeveloper(2),
    MiddleDeveloper(3),
    SeniorDeveloper(4);

    private final int value;

    private Post(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
