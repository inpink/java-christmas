package christmas.domain.entity.event;

import christmas.domain.entity.menu.CategoryItem;
import java.util.ArrayList;
import java.util.List;

public class Benefit {
    private int discountPrice;
    private final List<CategoryItem> gifts = new ArrayList<>();

    private Benefit(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public static Benefit createEmpty() {
        return new Benefit(0);
    }

    public void add(CategoryItem categoryItem) {
        gifts.add(categoryItem);
        discountPrice += categoryItem.getPrice();
    }

    public void add(int price) {
        discountPrice += price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public List<CategoryItem> getGifts() {
        return gifts;
    }
}
