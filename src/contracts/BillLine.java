package contracts;

import java.math.BigDecimal;

public interface BillLine {
    String getName();

    int getQuantity();

    BigDecimal getUnitPrice();

    boolean isDiscountApplied();

    BigDecimal getFinalTotal();
}
