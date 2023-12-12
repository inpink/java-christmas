package christmas.domain.entity;

import christmas.domain.entity.menu.CategoryItem;
import christmas.domain.entity.menu.Appetizer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemsTest {

    @ParameterizedTest
    @MethodSource("categoryItemProvider")
    @DisplayName("동일한 CategoryItem을 두 번 추가할 경우 예외 발생")
    void 동일한_CategoryItem을_두_번_추가할_경우_예외_발생(final CategoryItem categoryItem) {
        // Given
        final OrderItems orderItems = OrderItems.createEmpty();

        // When
        orderItems.add(categoryItem, 1);

        // Then
        assertThrows(Exception.class, () -> orderItems.add(categoryItem, 1));
    }

    private static Stream<CategoryItem> categoryItemProvider() {
        return Stream.of(Appetizer.values());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    @DisplayName("부적절한 개수(0 이하)로 CategoryItem 추가할 경우 예외 발생")
    void 부적절한_개수로_CategoryItem_추가할_경우_예외_발생(final int invalidCount) {
        // Given
        final OrderItems orderItems = OrderItems.createEmpty();
        final CategoryItem categoryItem = Appetizer.MUSHROOM_SOUP;

        // When - Then
        assertThrows(Exception.class, () -> orderItems.add(categoryItem, invalidCount));
    }
}
