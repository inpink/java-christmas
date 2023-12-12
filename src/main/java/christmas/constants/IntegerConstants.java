package christmas.constants;

public enum IntegerConstants {

    THIS_MONTH(12);

    private final int value;

    IntegerConstants(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
