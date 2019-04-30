package pl.wordslides.store;

public interface Store {

    boolean containsKey(String key);

    int get(String key);
}
