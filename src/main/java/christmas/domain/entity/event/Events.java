package christmas.domain.entity.event;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import java.util.function.BiFunction;

public enum Events {
    CHRISTMAS_DDAY_DISCOUNT((date, orderItems) -> ChristmasDDayDiscount.calculateDiscount(date)),
    WEEKDAY_DISCOUNT((date, orderItems) -> WeekdayDiscount.calculateDiscount(date, orderItems)),
    WEEKEND_DISCOUNT((date, orderItems) -> WeekendDiscount.calculateDiscount(date, orderItems)),
    SPECIAL_DISCOUNT((date, orderItems) -> SpecialDiscount.calculateDiscount(date)),
    GIFT_EVENT((date, orderItems) -> GiftEvent.calculateDiscount(date, orderItems));

    private final BiFunction<DateOfVisit, OrderItems, Integer> discountFunction;

    Events(BiFunction<DateOfVisit, OrderItems, Integer> discountFunction) {
        this.discountFunction = discountFunction;
    }

    public int applyDiscount(DateOfVisit date, OrderItems orderItems) {
        return discountFunction.apply(date, orderItems);
    }


    protected enum Condition {
        MIN_PRICE(10_000);

        private final int minPrice;

        Condition(int minPrice) {
            this.minPrice = minPrice;
        }
    }
    }
