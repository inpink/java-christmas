package christmas.controller;

import static christmas.messages.ErrorMessages.INVALID_DATE;

import christmas.domain.entity.DateOfVisit;
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

    }

    private DateOfVisit inputValidDateOfVisit() {
        return InputUtil.retryOnInvalidInput(inputView::inputDateOfVisit,
                errorMessage -> outputView.outputErrorMessage(INVALID_DATE.getMessage()));
    }
}
