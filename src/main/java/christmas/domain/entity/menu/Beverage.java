package christmas.domain.entity.menu;

import java.util.Arrays;

public enum Beverage implements CategoryItem {
    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Beverage(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static boolean containsItem(CategoryItem item) {
        return Arrays.stream(Beverage.values())
                .anyMatch(categoryItem -> categoryItem.equals(item));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
