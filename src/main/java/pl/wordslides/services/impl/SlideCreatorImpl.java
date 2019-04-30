package pl.wordslides.services.impl;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;
import pl.wordslides.services.SlideCreator;
import pl.wordslides.services.WordsFactory;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SlideCreatorImpl implements SlideCreator {

    private WordsFactory wordsFactory;

    public SlideCreatorImpl(WordsFactory wordsFactory) {
        this.wordsFactory = wordsFactory;
    }

    @Override
    public List<Slide> create(String input) {
        List<Word> words = wordsFactory.getWords(input);

        if (!words.isEmpty()) {
            return createSlide(words);
        } else return List.empty();
    }

    private List<Slide> createSlide(List<Word> wordList) {
        Slide rootSlide = new Slide(wordList);
        Set<Slide> slides = new LinkedHashSet<>();
        slides.add(rootSlide);
        for (int sliceSize = wordList.size() - 1; sliceSize > 0; sliceSize--) {
            for (int padFromLeft = 0; padFromLeft <= wordList.size() - sliceSize; padFromLeft++) {
                final List<Word> slice = wordList.subSequence(padFromLeft, sliceSize + padFromLeft);
                if (slice.nonEmpty()) {
                    Slide subSlide = new Slide(slice);
                    slides.add(subSlide);
                }
            }
        }
        return List.ofAll(slides);
    }

}
