package christmas.domain.entity.event;

import christmas.domain.entity.menu.CategoryItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Benefit {
    private Map<Event, Integer> discounts = new HashMap<>();
    private final List<CategoryItem> gifts = new ArrayList<>();

    public static Benefit createEmpty() {
        return new Benefit();
    }

    public void add(CategoryItem categoryItem) {
        gifts.add(categoryItem);
    }

    public void add(Event event, int price) {
        discounts.put(event, price);
    }

    public void merge(Benefit other) {
        this.discounts.putAll(other.discounts);
        this.gifts.addAll(other.gifts);
    }

    public int calculateFakeDiscountPrice() {
        int realDiscountPrice = discounts.keySet().stream()
                .mapToInt(event -> discounts.get(event))
                .sum();

        int fakeDiscountPrice = getFakeDiscountPrice();
        return realDiscountPrice + fakeDiscountPrice;
    }

    private int getFakeDiscountPrice() {
        int fakeDiscountPrice = gifts.stream()
                .mapToInt(gift -> gift.getPrice())
                .sum();
        return fakeDiscountPrice;
    }

    public int calculateRealDiscountPrice() {
        return calculateFakeDiscountPrice() - getFakeDiscountPrice();
    }

    public Map<Event, Integer> getDiscounts() {
        return discounts;
    }

    public List<CategoryItem> getGifts() {
        return gifts;
    }

    public boolean isEmptyGift() {
        return gifts.size() == 0;
    }
}
