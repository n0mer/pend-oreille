
package android.reflect.util.test;

import java.util.ArrayList;
import java.util.Collection;

public class PersistedArrayList<T> extends ArrayList<T> {
    public PersistedArrayList() {
        super();
    }

    public PersistedArrayList(Collection<? extends T> collection) {
        super(collection);
    }

    public PersistedArrayList(int capacity) {
        super(capacity);
    }

    public long listId = System.currentTimeMillis();

}
