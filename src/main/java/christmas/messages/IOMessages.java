package christmas.messages;

import static christmas.constants.IntegerConstants.THIS_MONTH;

public enum IOMessages {

    WELCOME(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다."
            ,THIS_MONTH.getValue())),
    INPUT_VISIT_DATE(String.format("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
            ,THIS_MONTH.getValue())),
    INPUT_ORDER_ITEMS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFITS_TITLE(String.format("%d월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
            ,THIS_MONTH.getValue())),
    ORDER_ITEMS("주문 메뉴"),
    TOTAL_PRICE_BEFORE_DISCOUNT("할인 전 총주문 금액"),
    GIFT("증정 메뉴"),
    BENEFITS("혜택 내역"),
    TOTAL_BENEFIT_PRICE("총혜택 금액"),
    ESTIMATED_PRICE_AFTER_DISCOUNT("할인 후 예상 결제 금액"),
    BADGE(String.format("%d월 이벤트 배지"
            ,THIS_MONTH.getValue()));


    private final String message;

    IOMessages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
