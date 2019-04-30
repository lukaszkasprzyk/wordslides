package pl.wordslides.store;


import org.springframework.stereotype.Repository;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Repository
public class DummyStoreImpl implements Store {

    @Override
    public boolean containsKey(String key) {
        try {
            TimeUnit.MILLISECONDS.sleep(5 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextBoolean();
    }

    @Override
    public int get(String key) {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }
}
