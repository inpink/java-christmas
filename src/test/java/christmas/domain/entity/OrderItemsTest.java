package christmas.domain.entity;

import christmas.domain.entity.menu.Appetizer;
import christmas.domain.entity.menu.Beverage;
import christmas.domain.entity.menu.CategoryItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemsTest {

    @Test
    @DisplayName("주문 항목이 오직 음료만 있을 경우 예외 발생")
    void 주문_항목이_오직_음료만_있을_경우_예외_발생() {
        // Given
        Map<CategoryItem, Integer> onlyBeverages = Map.of(
                Beverage.ZERO_COLA, 2,
                Beverage.RED_WINE, 1
        );

        // When - Then
        assertThrows(IllegalArgumentException.class, () -> OrderItems.create(onlyBeverages));
    }

    @Test
    @DisplayName("주문 항목에 음료와 다른 항목이 포함된 경우 정상 생성")
    void 주문_항목에_음료와_다른_항목이_포함된_경우_정상_생성() {
        // Given
        Map<CategoryItem, Integer> mixedItems = Map.of(
                Appetizer.MUSHROOM_SOUP, 1,
                Beverage.CHAMPAGNE, 1
        );

        // When
        OrderItems orderItems = OrderItems.create(mixedItems);

        // Then
        assertNotNull(orderItems);
    }
}
