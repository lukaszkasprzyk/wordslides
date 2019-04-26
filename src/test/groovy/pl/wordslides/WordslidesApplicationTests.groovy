package pl.wordslides.wordslides

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

/*@RunWith(SpringRunner.class)*/

@SpringBootTest
@Unroll
class WordslidesApplicationTests extends Specification {

    @Autowired
    CheckBean component

    def "when #input is lower than zero #result"() {
        expect:
        component.lessThanZero(input) == result

        where:
        input || result
        1     || false
        0     || false
        -1    || true
    }

}
