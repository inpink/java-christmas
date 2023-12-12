package christmas.domain.entity.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @ParameterizedTest
    @CsvSource({
            "양송이수프, 6000",
            "타파스, 5500",
            "시저샐러드, 8000",
            "티본스테이크, 55000",
            "바비큐립, 54000",
            "해산물파스타, 35000",
            "크리스마스파스타, 25000",
            "초코케이크, 15000",
            "아이스크림, 5000",
            "제로콜라, 3000",
            "레드와인, 60000",
            "샴페인, 25000"
    })
    @DisplayName("메뉴 이름으로 CategoryItem 찾기")
    void 메뉴_이름으로_CategoryItem_찾기(final String itemName, final int expectedPrice) {
        // When
        final CategoryItem result = Category.find(itemName);

        // Then
        assertEquals(expectedPrice, result.getPrice());
    }

    @ParameterizedTest
    @CsvSource({
            "없는메뉴"
    })
    @DisplayName("존재하지 않는 메뉴 이름으로 CategoryItem 찾을 때 예외 발생")
    void 존재하지_않는_메뉴_이름으로_CategoryItem_찾을_때_예외_발생(final String itemName) {
        // When - Then
        assertThrows(IllegalArgumentException.class, () -> Category.find(itemName));
    }
}
