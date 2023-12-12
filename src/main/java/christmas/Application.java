package christmas;

import christmas.config.AppConfig;
import christmas.controller.OrderController;

public class Application {
    public static void main(String[] args) {
        AppConfig config = generateConfig();
        OrderController orderController = generateOrderController(config);
        orderController.play();
    }

    private static AppConfig generateConfig() {
        return AppConfig.getInstance();
    }

    private static OrderController generateOrderController(AppConfig config) {
        return config.orderController();
    }
}
