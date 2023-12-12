package christmas.domain.entity.event;


import static christmas.domain.entity.event.SpecialDiscount.DiscountPrice.BASIC;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.Starred;
import java.time.LocalDate;
import java.util.List;

public enum SpecialDiscount implements Event {
    SPECIAL_DISCOUNT("특별 할인",
            1, 31,
            Starred.getStarredDate());

    private final String description;
    private final int startDateOfMonth;
    private final int endDateOfMonth;
    private final List<LocalDate> starredDate;

    SpecialDiscount(String description,
                    int startDateOfMonth,
                    int endDateOfMonth,
                    List<LocalDate> starredDate) {
        this.description = description;
        this.startDateOfMonth = startDateOfMonth;
        this.endDateOfMonth = endDateOfMonth;
        this.starredDate = starredDate;
    }

    public static Benefit calculateDiscount(DateOfVisit date) {
        LocalDate visitDate = date.getDate();
        Benefit benefit = Benefit.createEmpty();

        if (visitDate.getDayOfMonth() >= SPECIAL_DISCOUNT.startDateOfMonth
                && visitDate.getDayOfMonth() <= SPECIAL_DISCOUNT.endDateOfMonth
                && SPECIAL_DISCOUNT.starredDate.contains(visitDate)) {
            benefit.add(SPECIAL_DISCOUNT, calculatePrice());
        }
        return benefit;
    }

    private static int calculatePrice() {
        return BASIC.price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    protected enum DiscountPrice {
        BASIC(1_000);

        private final int price;

        DiscountPrice(int price) {
            this.price = price;
        }
    }
}
