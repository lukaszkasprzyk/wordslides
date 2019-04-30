package pl.wordslides.services;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import pl.wordslides.store.Store;

public class MockStore implements Store {

    private static final Map<String, Integer> values = HashMap.of("Mary", 1, "Mary gone", 2, "Mary's gone", 3, "went Mary's", 4, "went", 5, "input", 3);

    public boolean containsKey(String key) {
        return values.containsKey(key);
    }

    public int get(String key) {
        return values.getOrElse(key, null);
    }
}
