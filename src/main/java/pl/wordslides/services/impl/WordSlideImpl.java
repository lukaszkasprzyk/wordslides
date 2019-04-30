package pl.wordslides.services.impl;

import io.vavr.collection.List;
import org.springframework.stereotype.Component;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;
import pl.wordslides.services.WordSlideComponent;
import pl.wordslides.store.Store;

import java.util.HashMap;
import java.util.Map;

@Component
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
        final List<Slide> slides = slideCreator.create(input);
        slides.toStream().forEach(slide -> {
            final boolean visited = slide.getWords().exists(Word::isVisited);
            final String key = slide.key();
            if (!visited && store.containsKey(key)) {
                result.put(key, store.get(key));
                slide.getWords().forEach(Word::setVisited);
            }

        });
        return result;
    }

}
