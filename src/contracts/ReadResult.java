package contracts;

import java.util.ArrayList;

public interface ReadResult<T> {
    ArrayList<T> getItems();

    int getErrorCount();
}
