package pl.wordslides;

import org.springframework.context.annotation.Bean;
import pl.wordslides.store.MockStore;
import pl.wordslides.services.WordSlide;
import pl.wordslides.services.WordsFactory;
import pl.wordslides.services.impl.SlideCreatorImpl;
import pl.wordslides.services.impl.WordSlideImpl;
import pl.wordslides.store.Store;

public class TestContextClass {

    @Bean
    public WordsFactory wordsFactory() {
        return new WordsFactory();
    }


    @Bean
    public SlideCreatorImpl creator() {
        return new SlideCreatorImpl();
    }


    @Bean
    public Store mockStore() {
        return new MockStore();
    }

    @Bean
    public WordSlide wordSlide() {
        return new WordSlideImpl(creator(), mockStore(), wordsFactory());
    }


}
