package pl.wordslides.services.impl;

import io.vavr.collection.List;
import org.springframework.stereotype.Component;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;
import pl.wordslides.services.WordSlide;
import pl.wordslides.services.WordsFactory;
import pl.wordslides.store.Store;

import java.util.HashMap;
import java.util.Map;

@Component
public class WordSlideImpl implements WordSlide {

    private final SlideCreatorImpl slideCreator;

    private final Store store;

    private final WordsFactory wordsFactory;

    public WordSlideImpl(SlideCreatorImpl slideCreator, Store store, WordsFactory wordsFactory) {
        this.slideCreator = slideCreator;
        this.store = store;
        this.wordsFactory = wordsFactory;
    }

    @Override
    public Map<String, Integer> search(String input) {
        Map<String, Integer> result = new HashMap<>();
        final List<Word> words = wordsFactory.getWords(input);

        for (int depth = 0; depth <= words.size() - 1; depth++) {
            final List<Slide> slides = slideCreator.create(words, depth);
            slides.toStream().forEach(slide -> {
                final boolean visited = slideVisited(slide);
                final String key = slide.key();
                if (!visited && store.containsKey(key)) {
                    result.put(key, store.get(key));
                    isVisitedSlide(slide);
                }

            });
        }

        return result;
    }

    private void isVisitedSlide(Slide slide) {
        slide.getWords().forEach(Word::setVisited);
    }

    private boolean slideVisited(Slide slide) {
        return slide.getWords().exists(Word::isVisited);
    }

}
