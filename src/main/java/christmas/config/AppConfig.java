package christmas.config;

import christmas.controller.OrderController;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    public static AppConfig getInstance() {
        return LazyHolder.INSTANCE;
    }

    public OrderController orderController() {
        return LazyHolder.orderController;
    }

    public OrderService orderService() {
        return LazyHolder.orderService;
    }

    public InputView inputView() {
        return LazyHolder.inputView;
    }

    public OutputView outputView() {
        return LazyHolder.outputView;
    }

    private static class LazyHolder {
        private static final AppConfig INSTANCE = new AppConfig();
        private static final InputView inputView = createInputView();
        private static final OutputView outputView = createOutputView();
        private static final OrderService orderService = createOrderService();
        private static final OrderController orderController = createOrderController();

        private static OrderController createOrderController() {
            return new OrderController(
                    inputView,
                    outputView,
                    orderService);
        }

        private static OrderService createOrderService() {
            return new OrderService();
        }

        private static InputView createInputView() {
            return new InputView();
        }

        private static OutputView createOutputView() {
            return new OutputView();
        }
    }
}