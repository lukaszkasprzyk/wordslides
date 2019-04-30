package pl.wordslides.store;


import org.springframework.stereotype.Repository;

import java.util.concurrent.ThreadLocalRandom;

@Repository
public class DummyStoreImpl implements Store {

    @Override
    public boolean containsKey(String key) {
        return ThreadLocalRandom.current().nextBoolean();
    }

    @Override
    public int get(String key) {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }
}
