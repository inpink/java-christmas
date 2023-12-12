package christmas.domain.entity;

import christmas.domain.entity.menu.CategoryItem;
import christmas.util.ExceptionUtil;
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
        validateDuplicate(categoryItem);
        items.put(categoryItem, count);
    }

    private void validateDuplicate(CategoryItem categoryItem) {
        if (items.containsKey(categoryItem)) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
}
