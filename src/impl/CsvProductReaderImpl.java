package impl;

import contracts.CsvProductReader;
import contracts.ProductLine;
import contracts.ReadResult;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CsvProductReaderImpl implements CsvProductReader {
    private static final Path PATH = Path.of("src/resources/data.csv");
    private static final int NAME_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;
    private static final int PRICE_INDEX = 2;
    private static final int EXPECTED_FIELD_COUNT = 3;

    @Override
    public ReadResult<ProductLine> read() throws IOException {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(PATH));
        ArrayList<ProductLine> list = new ArrayList<>();
        int errorCount = processLineList(lines, list);
        return new SimpleReadResult<>(list, errorCount);
    }

    private int processLineList(ArrayList<String> lines, ArrayList<ProductLine> products) {
        int errorCount = 0;
        for (String line : lines) {
            String[] fields = splitLine(line);
            if (!isNormalLineLength(fields.length)) {
                continue;
            }
            ProductLineImpl product = createProductLineImpl(fields);
            if (product != null) {
                products.add(product);
            } else {
                errorCount++;
            }
        }
        return errorCount;
    }

    private String[] splitLine(String line) {
        if (line == null || line.startsWith("#")) {
            return new String[0];
        }
        return line.split(";");
    }

    private boolean isNormalLineLength(int length) {
        return length == EXPECTED_FIELD_COUNT;
    }

    private ProductLineImpl createProductLineImpl(String[] productLines) {
        try {
            String name = productLines[NAME_INDEX].trim();
            int quantity = Integer.parseInt(productLines[QUANTITY_INDEX].trim());
            BigDecimal price = new BigDecimal(productLines[PRICE_INDEX].trim().replace(",", "."));
            return new ProductLineImpl(name, quantity, price);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}