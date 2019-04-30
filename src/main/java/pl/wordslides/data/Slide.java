package pl.wordslides.data;

import io.vavr.collection.List;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@ToString(includeFieldNames = false)
public class Slide {

    @NonNull
    private final List<Word> words;

    public int size() {
        return words.length();
    }

    public String key() {
        return this.words.map(Word::getValue).collect(Collectors.joining(" "));
    }
}
