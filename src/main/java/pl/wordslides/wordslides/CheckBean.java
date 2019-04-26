package pl.wordslides.wordslides;

import org.springframework.stereotype.Component;

@Component
public class CheckBean {

    public boolean lessThanZero(int input) {
        return input < 0;
    }
}
