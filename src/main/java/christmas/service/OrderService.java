package christmas.service;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.domain.entity.event.Benefit;
import christmas.domain.entity.event.Events;

public class OrderService {
    public Benefit generateBenefit(DateOfVisit date, OrderItems orderItems) {
        return Events.calculateBenefit(date, orderItems);
    }
}
