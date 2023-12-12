package christmas.domain.entity.event;

import christmas.domain.entity.DateOfVisit;
import java.time.LocalDate;

public enum ChristmasDDayDiscount implements Event {

    CONDITIONS(1, 25);

    private final int startDateOfMonth;
    private final int endDateOfMonth;

    ChristmasDDayDiscount(int startDateOfMonth, int endDateOfMonth) {
        this.startDateOfMonth = startDateOfMonth;
        this.endDateOfMonth = endDateOfMonth;
    }

    public static Benefit calculateDiscount(DateOfVisit date) {
        LocalDate visitDate = date.getDate();
        Benefit benefit = Benefit.createEmpty();

        if (visitDate.getDayOfMonth() >= CONDITIONS.startDateOfMonth
                && visitDate.getDayOfMonth() <= CONDITIONS.endDateOfMonth) {
            benefit.add(calculatePrice(visitDate));
        }
        return benefit;
    }

    private static int calculatePrice(LocalDate visitDate) {
        return DiscountPrice.BASIC.price
                + (DiscountPrice.EXTRA_PER_DAY.price * (visitDate.getDayOfMonth() - 1));
    }

    protected enum DiscountPrice {
        BASIC(1_000),
        EXTRA_PER_DAY(100);

        private final int price;

        DiscountPrice(int price) {
            this.price = price;
        }
    }
}
