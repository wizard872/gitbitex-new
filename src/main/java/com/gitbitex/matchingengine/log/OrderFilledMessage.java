package com.gitbitex.matchingengine.log;

import com.gitbitex.enums.OrderSide;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderFilledMessage extends OrderLog {
    private String orderId;
    private BigDecimal size;
    private BigDecimal price;
    private BigDecimal funds;
    private long tradeId;
    private OrderSide side;
    private String userId;

    public OrderFilledMessage() {
        this.setType(LogType.ORDER_FILLED);
    }
}
