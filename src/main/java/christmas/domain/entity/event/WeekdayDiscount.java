package christmas.domain.entity.event;

import static christmas.domain.entity.WEEK.WEEKDAY;
import static christmas.domain.entity.event.WeekdayDiscount.DiscountPrice.BASIC;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.domain.entity.event.ChristmasDDayDiscount.DiscountPrice;
import christmas.domain.entity.menu.CategoryItem;
import christmas.domain.entity.menu.Dessert;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum WeekdayDiscount implements Event {
    CONDITIONS(1, 31, WEEKDAY.getDays(), Dessert.values());

    private final int startDateOfMonth;
    private final int endDateOfMonth;
    private final List<DayOfWeek> days;
    private final List<CategoryItem> items;

    WeekdayDiscount(int startDateOfMonth,
                    int endDateOfMonth,
                    List<DayOfWeek> days,
                    CategoryItem[] items) {
        this.startDateOfMonth = startDateOfMonth;
        this.endDateOfMonth = endDateOfMonth;
        this.days = days;
        this.items = Arrays.stream(items).toList();
    }

    public static int calculateDiscount(DateOfVisit date, OrderItems orderItems) {
        LocalDate visitDate = date.getDate();
        DayOfWeek day = date.calculateDayOfWeek();
        Map<CategoryItem, Integer> orderItemsAndCount = orderItems.getItems();

        if (visitDate.getDayOfMonth() >= CONDITIONS.startDateOfMonth
                && visitDate.getDayOfMonth() <= CONDITIONS.endDateOfMonth
                && CONDITIONS.days.contains(day)) {

            return calculatePrice(orderItemsAndCount);
        }
        return 0;
    }

    private static int calculatePrice(Map<CategoryItem, Integer> orderItemsAndCount) {
        return orderItemsAndCount.keySet()
                .stream()
                .filter(item -> CONDITIONS.items.contains(item))
                .mapToInt(item -> orderItemsAndCount.get(item) * BASIC.price)
                .sum();
    }

    protected enum DiscountPrice {
        BASIC(2_023);

        private final int price;

        DiscountPrice(int price) {
            this.price = price;
        }
    }
}
