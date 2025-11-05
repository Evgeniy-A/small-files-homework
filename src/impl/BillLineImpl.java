package impl;

import static java.math.RoundingMode.HALF_UP;

import contracts.BillLine;
import java.math.BigDecimal;

public class BillLineImpl implements BillLine {
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal subTotalPrice;
    private int quantity;
    private boolean hasDiscount;

    public BillLineImpl(String name, BigDecimal unitPrice,
                        BigDecimal subTotalPrice, int quantity, boolean discount) {
        this.name = name;
        this.unitPrice = unitPrice.setScale(2, HALF_UP);
        this.subTotalPrice = subTotalPrice.setScale(2, HALF_UP);
        this.quantity = quantity;
        this.hasDiscount = discount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean isDiscountApplied() {
        return hasDiscount;
    }

    @Override
    public BigDecimal getFinalTotal() {
        return subTotalPrice;
    }

    @Override
    public String toString() {
        return String.format("%s;%d;%s;%d;%s",
                name,
                quantity,
                unitPrice.toPlainString(),
                hasDiscount ? 1 : 0,
                subTotalPrice.toPlainString());
    }
}