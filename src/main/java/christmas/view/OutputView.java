package christmas.view;

import static christmas.constants.IntegerConstants.GIFT_COUNT;
import static christmas.messages.IOMessages.BADGE;
import static christmas.messages.IOMessages.BENEFITS;
import static christmas.messages.IOMessages.BENEFITS_TITLE;
import static christmas.messages.IOMessages.ESTIMATED_PRICE_AFTER_DISCOUNT;
import static christmas.messages.IOMessages.GIFT;
import static christmas.messages.IOMessages.ORDER_ITEMS;
import static christmas.messages.IOMessages.TOTAL_BENEFIT_PRICE;
import static christmas.messages.IOMessages.TOTAL_PRICE_BEFORE_DISCOUNT;

import christmas.domain.entity.event.Badge;
import christmas.domain.entity.event.Benefit;
import christmas.domain.entity.OrderItems;
import christmas.util.StringUtil;
import java.util.stream.Collectors;

public class OutputView {

    public void outputErrorMessage(String message) {
        System.out.println(message);
    }

    public void outputTitle() {
        System.out.println(BENEFITS_TITLE.getMessage());
    }

    public void outputOrderItems(OrderItems orderItems) {
        System.out.println(formatTitle(ORDER_ITEMS.getMessage()));

        System.out.println(formatOrderItems(orderItems));
    }

    public void outputTotalPriceBeforeDiscount(OrderItems orderItems) {
        System.out.println(formatTitle(TOTAL_PRICE_BEFORE_DISCOUNT.getMessage()));
        System.out.println(formatTotalPrice(orderItems) + "원");
    }

    public void outputGifts(Benefit benefit) {
        System.out.println(formatTitle(GIFT.getMessage()));
        System.out.println(formatGifts(benefit));
    }

    public void outputBenefits(Benefit benefit) {
        System.out.println(formatTitle(BENEFITS.getMessage()));

        if (benefit.isEmptyGift()) {
            System.out.println("없음");
            return;
        }
        benefit.getDiscounts().forEach((event, discount) ->
                System.out.println(event.getDescription() + ": "
                        + StringUtil.formatByThousandSeparator(-1 * discount) + "원"));
    }

    public void outputTotalBenefitPrice(Benefit benefit) {
        System.out.println(formatTitle(TOTAL_BENEFIT_PRICE.getMessage()));
        System.out.println(
                StringUtil.formatByThousandSeparator(-1 * benefit.calculateFakeDiscountPrice())
                        + "원");
    }

    public void outputEstimatedPriceAfterDiscount(OrderItems orderItems, Benefit benefit) {
        System.out.println(formatTitle(ESTIMATED_PRICE_AFTER_DISCOUNT.getMessage()));
        System.out.println(orderItems.calculateTotalPrice() - benefit.calculateRealDiscountPrice());
    }

    public void outputBadge(Badge badge) {
        System.out.println(formatTitle(BADGE.getMessage()));
        System.out.println(badge.getDescription());
    }

    private String formatGifts(Benefit benefit) {
        if (benefit.isEmptyGift()) {
            return "없음";
        }
        return benefit.getGifts()
                .stream()
                .map(item -> item.getName() + " " + GIFT_COUNT.getValue() + "개")
                .collect(Collectors.joining(", "));
    }

    private static String formatOrderItems(OrderItems orderItems) {
        return orderItems.getItems().entrySet()
                .stream()
                .map(entry -> entry.getKey().getName() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }


    private static String formatTotalPrice(OrderItems orderItems) {
        return StringUtil.formatByThousandSeparator(orderItems.calculateTotalPrice());
    }

    private String formatTitle(String title) {
        return "<" + title + ">";
    }
}