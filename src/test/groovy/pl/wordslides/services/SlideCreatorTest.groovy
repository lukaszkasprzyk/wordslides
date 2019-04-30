package pl.wordslides.services

import io.vavr.collection.List
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import pl.wordslides.TestContextClass
import pl.wordslides.data.Slide
import pl.wordslides.services.impl.SlideCreatorImpl
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = TestContextClass.class)
class SlideCreatorTest extends Specification {

    @Autowired
    SlideCreatorImpl underTest

    def "for empty input empty result"() {
        when:
        def result = underTest.create("")
        then:
        result.size() == 0
    }

    def "for null input empty result"() {
        when:
        def result = underTest.create(null)
        then:
        result.size() == 0
    }

    def "single word"() {
        when:
        def result = underTest.create("input")
        then:
        result.size() == 1
        result.first().key() == "input"
    }

    def "same phrase two times"() {
        when:
        def result = underTest.create("input input")
        then:
        result.size() == 2
        result.first().key() == "input input"
        result.get(1).key() == "input"
    }

    def "two words with and order"() {
        when:
        def result = underTest.create("input text")
        then:
        result.size() == 3
        result.first().key() == "input text"
        result.get(1).key() == "input"
        result.get(2).key() == "text"
    }

    def "few words with order"() {
        when:
        def slides = underTest.create(" Mary went Mary's\ngone ")
        int level = 0
        then:
        slides.size() == 10

        slides.get(level++).key() == "Mary went Mary's gone"

        checkSize(slides, level)
        slides.get(level++).key() == "Mary went Mary's"

        slides.get(level++).key() == "went Mary's gone"

        checkSize(slides, level)
        slides.get(level++).key() == "Mary went"

        slides.get(level++).key() == "went Mary's"

        slides.get(level++).key() == "Mary's gone"

        checkSize(slides, level)
        slides.get(level++).key() == "Mary"

        slides.get(level++).key() == "went"

        slides.get(level++).key() == "Mary's"

        slides.get(level).key() == "gone"

    }

    private static boolean checkSize(List<Slide> result, int index) {
        result.get(index).size() < result.get(index - 1).size()
    }

}
