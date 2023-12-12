package christmas.controller;

import static christmas.messages.ErrorMessages.INVALID_DATE;
import static christmas.messages.ErrorMessages.INVALID_ORDER;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.domain.entity.event.Badge;
import christmas.domain.entity.event.Benefit;
import christmas.service.OrderService;
import christmas.util.InputUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {
    private final InputView inputView;
    private final OutputView outputView;
    private final OrderService orderService;

    public OrderController(final InputView inputView,
                           final OutputView outputView,
                           final OrderService orderService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
    }

    public void play() {
        final DateOfVisit date = inputValidDateOfVisit();
        final OrderItems orderItems = inputValidOrderItems();

        Benefit benefit = orderService.generateBenefit(date, orderItems);
        Badge badge = orderService.generateBadge(benefit);

        outputResult(benefit, orderItems, badge);
    }

    private DateOfVisit inputValidDateOfVisit() {
        return InputUtil.retryOnInvalidInput(inputView::inputDateOfVisit,
                errorMessage -> outputView.outputErrorMessage(INVALID_DATE.getMessage()));
    }

    private OrderItems inputValidOrderItems() {
        return InputUtil.retryOnInvalidInput(inputView::inputOrderItems,
                errorMessage -> outputView.outputErrorMessage(INVALID_ORDER.getMessage()));
    }

    private void outputResult(Benefit benefit, OrderItems orderItems, Badge badge) {
        outputView.outputTitle();
        outputView.outputOrderItems(orderItems);
        outputView.outputTotalPriceBeforeDiscount(orderItems);
        outputView.outputGifts(benefit);
        outputView.outputBenefits(benefit);
        outputView.outputTotalBenefitPrice(benefit);
        outputView.outputEstimatedPriceAfterDiscount(orderItems, benefit);
        outputView.outputBadge(badge);
    }
}
