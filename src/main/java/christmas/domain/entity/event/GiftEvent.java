package christmas.domain.entity.event;

import static christmas.domain.entity.event.GiftEvent.GiftItem.GIFT_ITEM;
import static christmas.domain.entity.menu.Beverage.CHAMPAGNE;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.domain.entity.menu.CategoryItem;
import java.time.LocalDate;

public enum GiftEvent {
    CONDITIONS(1, 31, 120_000);

    private final int startDateOfMonth;
    private final int endDateOfMonth;
    private final int minPurchasePrice;

    GiftEvent(int startDateOfMonth, int endDateOfMonth, int minPurchasePrice) {
        this.startDateOfMonth = startDateOfMonth;
        this.endDateOfMonth = endDateOfMonth;
        this.minPurchasePrice = minPurchasePrice;
    }

    public static int calculateDiscount(DateOfVisit date, OrderItems orderItems) {
        LocalDate visitDate = date.getDate();

        if (visitDate.getDayOfMonth() >= CONDITIONS.startDateOfMonth
                && visitDate.getDayOfMonth() <= CONDITIONS.endDateOfMonth
                && orderItems.isPriceMoreThan(CONDITIONS.minPurchasePrice)) {

            return calculateGift();
        }
        return 0;
    }

    private static CategoryItem calculateGift() {
        return GIFT_ITEM.getCategoryItem();
    }

    protected enum GiftItem {
        GIFT_ITEM(CHAMPAGNE);

        private final CategoryItem categoryItem;

        GiftItem(CategoryItem categoryItem) {
            this.categoryItem = categoryItem;
        }

        public CategoryItem getCategoryItem() {
            return categoryItem;
        }
    }


}
