package christmas.domain.entity;

import christmas.domain.entity.menu.CategoryItem;
import java.util.HashMap;
import java.util.Map;

public class OrderItems {
    private final Map<CategoryItem, Integer> items = new HashMap<>();

    private OrderItems() {

    }

    public static OrderItems createEmpty() {
        return new OrderItems();
    }

    public void add(CategoryItem categoryItem, int count) {
        items.put(categoryItem, count);
    }
}
