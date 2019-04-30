package pl.wordslides.services.impl;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;
import pl.wordslides.services.SlideCreator;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SlideCreatorImpl implements SlideCreator {

    public List<Slide> create(List<Word> words, int depth) {
        if (words.isEmpty() || depth >= words.size() || depth < 0) {
            return List.empty();
        } else if (depth == 0) {
            return List.of(new Slide(words));
        } else {
            int sliceSize = words.size() - depth;
            Set<Slide> slides = new LinkedHashSet<>();
            for (int padFromLeft = 0; padFromLeft <= words.size() - sliceSize; padFromLeft++) {
                final List<Word> slice = words.subSequence(padFromLeft, sliceSize + padFromLeft);
                Slide subSlide = new Slide(slice);
                slides.add(subSlide);
            }
            return List.ofAll(slides);
        }
    }

}
