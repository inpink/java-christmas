package christmas.constants;

public enum IntegerConstants {

    THIS_YEAR(2023),

    THIS_MONTH(12),
    MAX_ORDER_COUNT(20),
    GIFT_COUNT(1);

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
