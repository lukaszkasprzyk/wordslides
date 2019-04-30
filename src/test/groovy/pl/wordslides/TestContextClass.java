package pl.wordslides;

import org.springframework.context.annotation.Bean;
import pl.wordslides.services.MockStore;
import pl.wordslides.services.WordSlideComponent;
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
        return new SlideCreatorImpl(wordsFactory());
    }


    @Bean
    public Store mockStore() {
        return new MockStore();
    }

    @Bean
    public WordSlideComponent wordSlide() {
        return new WordSlideImpl(creator(), mockStore());
    }


}
