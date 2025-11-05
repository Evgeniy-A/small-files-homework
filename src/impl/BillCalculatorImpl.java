package impl;

import contracts.BillCalculator;
import contracts.BillLine;
import contracts.BillResult;
import contracts.ProductLine;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BillCalculatorImpl implements BillCalculator {
    private static final SimpleDiscountPolicy DISCOUNT_POLICY = new SimpleDiscountPolicy();

    @Override
    public BillResult<BillLine> calculate(ArrayList<ProductLine> products) {
        ArrayList<BillLine> lines = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductLine product : products) {
            BillLineImpl billLine = createBillLine(product);
            totalPrice = totalPrice.add(billLine.getFinalTotal());
            lines.add(billLine);
        }
        return new SimpleBillResult<>(lines, totalPrice);
    }

    private BillLineImpl createBillLine(ProductLine product) {
        BigDecimal subTotal = product.getUnitPrice().multiply(new BigDecimal(product.getQuantity()));
        BigDecimal discount = DISCOUNT_POLICY.discountFor(subTotal, product.getQuantity());
        BigDecimal finalSubTotal = subTotal.subtract(discount);
        boolean hasDiscount = discount.compareTo(BigDecimal.ZERO) > 0;
        return new BillLineImpl(product.getName(), product.getUnitPrice(),
                finalSubTotal, product.getQuantity(), hasDiscount);
    }
}