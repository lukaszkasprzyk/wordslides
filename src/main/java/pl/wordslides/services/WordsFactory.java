package pl.wordslides.services;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.wordslides.data.Word;

@Service
public class WordsFactory {

    public List<Word> getWords(String input) {
        if (StringUtils.hasText(input)) {
            return Stream.of(input.trim().split("\\s+"))
                    .map(Word::new).toList();
        } else {
            return List.empty();
        }
    }
}
