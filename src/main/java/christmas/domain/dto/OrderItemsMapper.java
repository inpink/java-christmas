package christmas.domain.dto;

import christmas.domain.entity.menu.Category;
import christmas.domain.entity.menu.CategoryItem;
import christmas.domain.entity.OrderItems;
import christmas.util.StringUtil;
import christmas.validation.IntegerValidator;
import java.util.Arrays;
import java.util.List;

public class OrderItemsMapper {
    public static OrderItems toOrderItems(String input) {
        List<String> separated = seperate(input);
        OrderItems orderItems = OrderItems.createEmpty();

        separated.stream()
                .forEach(item -> addItem(item, orderItems));

        return orderItems;
    }

    private static void addItem(String item, OrderItems orderItems) {
        String[] itemAndCount = item.split("-");
        String itemName = itemAndCount[0];
        String count = itemAndCount[1];
        CategoryItem categoryItem = Category.find(itemName);

        IntegerValidator.validateInteger(count);
        int validCount = Integer.parseInt(count);
        orderItems.add(categoryItem, validCount);
    }

    private static List<String> seperate(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();
        return separated;
    }
}
