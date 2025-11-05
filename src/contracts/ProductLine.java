package contracts;

import java.math.BigDecimal;

public interface ProductLine {
    String getName();

    int getQuantity();

    BigDecimal getUnitPrice();
}
