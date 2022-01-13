package designpatterns.iterator;

import java.util.Hashtable;

public class CafeMenuIterator extends DefaultIterator {

    public CafeMenuIterator(Hashtable items) {
        super(items.values());
    }

}
