package impl;

import contracts.DiscountPolicy;
import java.math.BigDecimal;

public class SimpleDiscountPolicy implements DiscountPolicy {
    private static BigDecimal discount = new BigDecimal(0.1);

    @Override
    public BigDecimal discountFor(BigDecimal subtotal, int quantity) {
        return (quantity >= 3) ?
                subtotal.multiply(discount) : BigDecimal.ZERO;
    }
}