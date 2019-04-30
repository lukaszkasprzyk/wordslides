package pl.wordslides.services;

import io.vavr.collection.List;
import pl.wordslides.data.Slide;

public interface SlideCreator {

    List<Slide> create(String input);

}
