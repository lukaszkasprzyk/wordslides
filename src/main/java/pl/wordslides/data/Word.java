package pl.wordslides.data;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
@RequiredArgsConstructor
public class Word {

    @NonNull
    private final String value;
    private boolean visited;

    public void setVisited() {
        visited = true;
    }
}
