package service;

import contracts.BillLine;
import contracts.ProductLine;
import contracts.ReadResult;
import impl.BillCalculatorImpl;
import impl.CsvProductReaderImpl;
import impl.CsvProductWriterImpl;
import impl.SimpleBillResult;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BillingService {
    public void processBilling() {
        CsvProductReaderImpl reader = new CsvProductReaderImpl();
        BillCalculatorImpl calculator = new BillCalculatorImpl();
        CsvProductWriterImpl writer = new CsvProductWriterImpl();
        try {
            ReadResult<ProductLine> resultReader = reader.read();
            ArrayList<ProductLine> products = resultReader.getItems();
            SimpleBillResult<BillLine> resultCalculate = (SimpleBillResult<BillLine>)
                    calculator.calculate(products);
            ArrayList<BillLine> list = resultCalculate.getLines();
            BigDecimal totalPrice = resultCalculate.getTotal();
            int errors = resultReader.getErrorCount();

            writer.write(list, totalPrice, errors);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}