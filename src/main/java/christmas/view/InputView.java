package christmas.view;

import static christmas.messages.IOMessages.INPUT_ORDER_ITEMS;
import static christmas.messages.IOMessages.INPUT_VISIT_DATE;
import static christmas.messages.IOMessages.WELCOME;

import christmas.domain.dto.DateOfVisitMapper;
import christmas.domain.dto.OrderItemsMapper;
import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.util.InputUtil;

public class InputView {


    public DateOfVisit inputDateOfVisit() {
        System.out.println(WELCOME.getMessage());
        System.out.println(INPUT_VISIT_DATE.getMessage());
        String input = InputUtil.input();

        return DateOfVisitMapper.toDateOfVisit(input);
    }

    public OrderItems inputOrderItems() {
        System.out.println(INPUT_ORDER_ITEMS.getMessage());
        String input = InputUtil.input();

        return OrderItemsMapper.toOrderItems(input);
    }

}
