package christmas.domain.entity;

import christmas.domain.entity.menu.CategoryItem;
import christmas.util.ExceptionUtil;
import java.util.HashMap;
import java.util.Map;

public class OrderItems {
    private final Map<CategoryItem, Integer> items;

    private OrderItems() {
        this.items = new HashMap<>();
    }

    private OrderItems(Map<CategoryItem, Integer> orderItemsAndCount) {
        this.items = orderItemsAndCount;
    }

    public static OrderItems create(Map<CategoryItem, Integer> orderItemsAndCount) {
        return new OrderItems(orderItemsAndCount);
    }

    public static OrderItems createEmpty() {
        return new OrderItems();
    }


    public void add(CategoryItem categoryItem, int count) {
        validateDuplicate(categoryItem);
        validateCount(count);
        items.put(categoryItem, count);
    }

    public int calculateTotalPrice() {
        return items.keySet().stream()
                .mapToInt(item -> item.getPrice() * items.get(item))
                .sum();
    }

    public boolean isPriceMoreThan(int minPurchasePrice) {
        return calculateTotalPrice() >= minPurchasePrice;
    }

    private void validateCount(int count) {
        if (count <= 0) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    private void validateDuplicate(CategoryItem categoryItem) {
        if (items.containsKey(categoryItem)) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public Map<CategoryItem, Integer> getItems() {
        return items;
    }

}
