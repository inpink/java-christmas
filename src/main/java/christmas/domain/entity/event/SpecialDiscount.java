package christmas.domain.entity.event;


import static christmas.domain.entity.event.SpecialDiscount.DiscountPrice.BASIC;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.Starred;
import java.time.LocalDate;
import java.util.List;

public enum SpecialDiscount {
    CONDITIONS(1, 31, Starred.getStarredDate());

    private final int startDateOfMonth;
    private final int endDateOfMonth;
    private final List<LocalDate> starredDate;

    SpecialDiscount(int startDateOfMonth,
                    int endDateOfMonth,
                    List<LocalDate> starredDate) {
        this.startDateOfMonth = startDateOfMonth;
        this.endDateOfMonth = endDateOfMonth;
        this.starredDate = starredDate;
    }

    public static int calculateDiscount(DateOfVisit date) {
        LocalDate visitDate = date.getDate();
        if (visitDate.getDayOfMonth() >= CONDITIONS.startDateOfMonth
                && visitDate.getDayOfMonth() <= CONDITIONS.endDateOfMonth
                && CONDITIONS.starredDate.contains(visitDate)) {

            return calculatePrice();
        }
        return 0;
    }

    private static int calculatePrice() {
        return BASIC.price;
    }

    protected enum DiscountPrice {
        BASIC(1_000);

        private final int price;

        DiscountPrice(int price) {
            this.price = price;
        }
    }
}
