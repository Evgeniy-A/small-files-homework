package impl;

import contracts.BillResult;
import java.math.BigDecimal;
import java.util.ArrayList;

public class SimpleBillResult<T> implements BillResult {
    private ArrayList<T> lines;
    private BigDecimal total;

    public SimpleBillResult(ArrayList<T> lines, BigDecimal total) {
        this.lines = lines;
        this.total = total;
    }

    @Override
    public ArrayList<T> getLines() {
        return lines;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }
}