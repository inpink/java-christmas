package christmas.domain.entity.menu;

import christmas.util.ExceptionUtil;
import java.util.Arrays;
import java.util.List;

public enum Category {
    APPETIZERS("에피타이저", Appetizer.values()),
    MAINS("메인", Main.values()),
    DESSERTS("디저트", Dessert.values()),
    BEVERAGES("음료", Beverage.values());

    private final String displayName;
    private final List<CategoryItem> items;

    Category(String displayName, CategoryItem[] items) {
        this.displayName = displayName;
        this.items = Arrays.stream(items).toList();
    }

    public static CategoryItem find(String itemName) throws IllegalArgumentException {
        return Arrays.stream(Category.values())
                .flatMap(category -> category.items.stream())
                .filter(item -> item.getName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemName));
    }
}