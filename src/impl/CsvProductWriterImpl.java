package impl;

import contracts.BillLine;
import contracts.CsvBillWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CsvProductWriterImpl implements CsvBillWriter {
    private static final String PATH = "src/resources/bill.csv";

    @Override
    public void write(ArrayList<BillLine> lines, BigDecimal total, int errors) throws IOException {
        try (FileWriter writer = new FileWriter(PATH)) {
            for (BillLine line : lines) {
                writer.write(line.toString() + "\n");
            }
            writer.write(String.format("TOTAL;;;;<%s>%n", total.toPlainString()));
            writer.write(String.format("ERRORS;;;;<%d>%n", errors));
        }
    }
}