package christmas.domain.entity.event;

import christmas.domain.entity.DateOfVisit;
import java.time.LocalDate;

public enum ChristmasDDayDiscount implements Event {

    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 1, 25);

    private final String description;
    private final int startDateOfMonth;
    private final int endDateOfMonth;

    ChristmasDDayDiscount(String description, int startDateOfMonth, int endDateOfMonth) {
        this.description = description;
        this.startDateOfMonth = startDateOfMonth;
        this.endDateOfMonth = endDateOfMonth;
    }


    public static Benefit calculateDiscount(DateOfVisit date) {
        LocalDate visitDate = date.getDate();
        Benefit benefit = Benefit.createEmpty();

        if (visitDate.getDayOfMonth() >= CHRISTMAS_D_DAY_DISCOUNT.startDateOfMonth
                && visitDate.getDayOfMonth() <= CHRISTMAS_D_DAY_DISCOUNT.endDateOfMonth) {
            benefit.add(CHRISTMAS_D_DAY_DISCOUNT,calculatePrice(visitDate));
        }
        return benefit;
    }

    private static int calculatePrice(LocalDate visitDate) {
        return DiscountPrice.BASIC.price
                + (DiscountPrice.EXTRA_PER_DAY.price * (visitDate.getDayOfMonth() - 1));
    }

    @Override
    public String getDescription() {
        return description;
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
