package impl;

import contracts.ReadResult;
import java.util.ArrayList;

public class SimpleReadResult<T> implements ReadResult {
    private ArrayList<T> items;
    private int errorCount;

    public SimpleReadResult(ArrayList<T> items, int errorCount) {
        this.items = items;
        this.errorCount = errorCount;
    }

    @Override
    public ArrayList<T> getItems() {
        return items;
    }

    @Override
    public int getErrorCount() {
        return errorCount;
    }
}