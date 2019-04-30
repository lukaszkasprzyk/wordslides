package pl.wordslides.services.impl;

import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;
import pl.wordslides.services.WordSlideComponent;
import pl.wordslides.store.Store;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WordSlideImpl implements WordSlideComponent {

    private final SlideCreatorImpl slideCreator;

    private final Store store;

    public WordSlideImpl(SlideCreatorImpl slideCreator, Store store) {
        this.slideCreator = slideCreator;
        this.store = store;
    }

    @Override
    public Map<String, Integer> search(String input) {
        Map<String, Integer> result = new HashMap<>();
        log.error("slide create");
        final List<Slide> slides = slideCreator.create(input);
        log.error("slide process start with " + slides.size());
        long now = System.currentTimeMillis();
        slides.toStream().forEach(slide -> {
            log.error("slide process:");
            final boolean visited = slide.getWords().exists(Word::isVisited);
            final String key = slide.key();
            if (!visited && store.containsKey(key)) {
                result.put(key, store.get(key));
                slide.getWords().forEach(Word::setVisited);
            }

        });
        log.error("slide process end with:" + (System.currentTimeMillis() - now));
        return result;
    }

}
