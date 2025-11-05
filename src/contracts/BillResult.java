package contracts;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface BillResult<T> {
    ArrayList<T> getLines();

    BigDecimal getTotal();
}
