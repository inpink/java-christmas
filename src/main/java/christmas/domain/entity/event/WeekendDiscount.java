package christmas.domain.entity.event;

import static christmas.domain.entity.WEEK.WEEKDAY;
import static christmas.domain.entity.WEEK.WEEKEND;
import static christmas.domain.entity.event.WeekendDiscount.DiscountPrice.BASIC;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.domain.entity.menu.CategoryItem;
import christmas.domain.entity.menu.Dessert;
import christmas.domain.entity.menu.Main;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum WeekendDiscount {
    CONDITIONS(1, 31, WEEKEND.getDays(), Main.values());

    private final int startDateOfMonth;
    private final int endDateOfMonth;
    private final List<DayOfWeek> days;
    private final List<CategoryItem> items;

    WeekendDiscount(int startDateOfMonth,
                    int endDateOfMonth,
                    List<DayOfWeek> days,
                    CategoryItem[] items) {
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

        if (visitDate.getDayOfMonth() >= CONDITIONS.startDateOfMonth
                && visitDate.getDayOfMonth() <= CONDITIONS.endDateOfMonth
                && CONDITIONS.days.contains(day)) {
            benefit.add(calculatePrice(orderItemsAndCount));
        }
        return benefit;
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
