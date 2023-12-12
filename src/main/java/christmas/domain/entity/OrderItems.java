package christmas.domain.entity;

import static christmas.constants.IntegerConstants.MAX_ORDER_COUNT;

import christmas.domain.entity.menu.Beverage;
import christmas.domain.entity.menu.CategoryItem;
import christmas.util.ExceptionUtil;
import java.util.HashMap;
import java.util.Map;

public class OrderItems {
    private final Map<CategoryItem, Integer> items;

    private OrderItems(Map<CategoryItem, Integer> orderItemsAndCount) {
        validateNotOnlyBeverage();
        validateNotOverCount();
        this.items = orderItemsAndCount;
    }

    public static OrderItems create(Map<CategoryItem, Integer> orderItemsAndCount) {
        return new OrderItems(orderItemsAndCount);
    }

    public int calculateTotalPrice() {
        return items.keySet().stream()
                .mapToInt(item -> item.getPrice() * items.get(item))
                .sum();
    }

    public int calculateTotalCount() {
        return items.keySet().stream()
                .mapToInt(item -> items.get(item))
                .sum();
    }

    public boolean isPriceMoreThan(int minPurchasePrice) {
        return calculateTotalPrice() >= minPurchasePrice;
    }

    private void validateNotOnlyBeverage() {
        boolean isOnlyBeverage = items.keySet().stream()
                .allMatch(item -> Beverage.containsItem(item));
    }

    private void validateNotOverCount() {
        if (calculateTotalCount() > MAX_ORDER_COUNT.getValue()) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public Map<CategoryItem, Integer> getItems() {
        return items;
    }

}
