package christmas.domain.dto;

import christmas.domain.entity.menu.Category;
import christmas.domain.entity.menu.CategoryItem;
import christmas.domain.entity.OrderItems;
import christmas.util.ExceptionUtil;
import christmas.util.StringUtil;
import christmas.validation.IntegerValidator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItemsMapper {
    public static OrderItems toOrderItems(String input) {
        List<String> separated = seperate(input);
        Map<CategoryItem, Integer> orderItems = new HashMap<>();

        separated.stream()
                .forEach(item -> addItem(item, orderItems));

        return OrderItems.create(orderItems);
    }

    private static void addItem(String item, Map<CategoryItem, Integer> orderItems) {
        String[] itemAndCount = item.split("-");
        String itemName = itemAndCount[0];
        String count = itemAndCount[1];
        CategoryItem categoryItem = Category.find(itemName);

        validateDuplicate(orderItems, categoryItem);
        IntegerValidator.validateInteger(count);

        int validCount = Integer.parseInt(count);
        validateCount(validCount);
        orderItems.put(categoryItem, validCount);
    }

    private static void validateDuplicate(Map<CategoryItem, Integer> orderItems, CategoryItem categoryItem) {
        if (orderItems.containsKey(categoryItem)) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    private static void validateCount(int count) {
        if (count <= 0) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    private static List<String> seperate(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();
        return separated;
    }
}
