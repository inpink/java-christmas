package christmas.domain.entity.event;

import static christmas.domain.entity.WEEK.WEEKEND;
import static christmas.domain.entity.event.WeekendDiscount.DiscountPrice.BASIC;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.domain.entity.menu.CategoryItem;
import christmas.domain.entity.menu.Main;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum WeekendDiscount implements Event {
    WEEKEND_DISCOUNT("주말 할인",
            1, 31,
            WEEKEND.getDays(), Main.values());

    private final String description;
    private final int startDateOfMonth;
    private final int endDateOfMonth;
    private final List<DayOfWeek> days;
    private final List<CategoryItem> items;

    WeekendDiscount(String description, int startDateOfMonth,
                    int endDateOfMonth,
                    List<DayOfWeek> days,
                    CategoryItem[] items) {
        this.description = description;
        this.startDateOfMonth = startDateOfMonth;
        this.endDateOfMonth = endDateOfMonth;
        this.days = days;
        this.items = Arrays.stream(items).toList();
    }

    public static Benefit calculateDiscount(DateOfVisit date, OrderItems orderItems) {
        LocalDate visitDate = date.getDate();
        DayOfWeek day = date.calculateDayOfWeek();
        Map<CategoryItem, Integer> orderItemsAndCount = orderItems.getItems();
        Benefit benefit = Benefit.createEmpty();

        if (visitDate.getDayOfMonth() >= WEEKEND_DISCOUNT.startDateOfMonth
                && visitDate.getDayOfMonth() <= WEEKEND_DISCOUNT.endDateOfMonth
                && WEEKEND_DISCOUNT.days.contains(day)) {
            benefit.add(WEEKEND_DISCOUNT, calculatePrice(orderItemsAndCount));
        }
        return benefit;
    }

    private static int calculatePrice(Map<CategoryItem, Integer> orderItemsAndCount) {
        return orderItemsAndCount.keySet()
                .stream()
                .filter(item -> WEEKEND_DISCOUNT.items.contains(item))
                .mapToInt(item -> orderItemsAndCount.get(item) * BASIC.price)
                .sum();
    }

    @Override
    public String getDescription() {
        return description;
    }

    protected enum DiscountPrice {
        BASIC(2_023);

        private final int price;

        DiscountPrice(int price) {
            this.price = price;
        }
    }
}
