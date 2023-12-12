package christmas.domain.entity.event;

import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.OrderItems;
import christmas.domain.entity.menu.Appetizer;
import christmas.domain.entity.menu.CategoryItem;
import christmas.domain.entity.menu.Dessert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

class WeekdayDiscountTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-07, 1, 2023", // 평일, 디저트 1개, 할인 2023원
            "2023-12-02, 1, 0",    // 주말, 디저트 1개, 할인 없음
            "2023-12-04, 2, 4046"  // 평일, 디저트 2개, 할인 4046원
    })
    void 평일_디저트_할인_계산_테스트(final LocalDate visitDate, final int dessertCount, final int expectedDiscount) {
        // Given
        final DateOfVisit date = DateOfVisit.create(visitDate.getDayOfMonth());
        final Map<CategoryItem, Integer> orderItemsAndCount = new HashMap<>();
        orderItemsAndCount.put(Dessert.CHOCOLATE_CAKE, dessertCount);
        final OrderItems orderItems = OrderItems.create(orderItemsAndCount);

        // When
        final int discount = WeekdayDiscount.calculateDiscount(date, orderItems).getDiscountPrice();

        // Then
        assertEquals(expectedDiscount, discount);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-12-07, 1, 0", // 평일, 디저트가 아닌 메뉴 1개, 할인 없음
            "2023-12-02, 1, 0", // 주말, 디저트가 아닌 메뉴 1개, 할인 없음
            "2023-12-04, 2, 0"  // 평일, 디저트가 아닌 메뉴 2개, 할인 없음
    })
    void 평일_디저트_아닌_메뉴_할인_계산_테스트(final LocalDate visitDate, final int nonDessertCount, final int expectedDiscount) {
        // Given
        final DateOfVisit date = DateOfVisit.create(visitDate.getDayOfMonth());
        final Map<CategoryItem, Integer> orderItemsAndCount = new HashMap<>();
        orderItemsAndCount.put(Appetizer.MUSHROOM_SOUP, nonDessertCount);
        final OrderItems orderItems = OrderItems.create(orderItemsAndCount);


        // When
        final int discount = WeekdayDiscount.calculateDiscount(date, orderItems).getDiscountPrice();

        // Then
        assertEquals(expectedDiscount, discount);
    }
}
