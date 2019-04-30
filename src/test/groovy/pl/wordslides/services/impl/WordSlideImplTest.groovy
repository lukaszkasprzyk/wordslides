package pl.wordslides.services.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import pl.wordslides.TestContextClass
import pl.wordslides.services.WordSlide
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = TestContextClass.class)
class WordSlideImplTest extends Specification {

    @Autowired
    WordSlide underTest

    def "for empty input empty result"() {
        when:
        def result = underTest.search("")
        then:
        result.size() == 0
    }

    def "single string input present in store"() {
        when:
        def result = underTest.search("input")
        then:
        result.size() == 1
    }

    def "multi string input partially present in store"() {
        expect:
        def expected = ["went Mary\'s": 4, "Mary": 1]
        when:
        def result = underTest.search("Mary went Mary's gone")
        then:
        result.size() == 2
        result == expected
    }

    def "one thousand words repeated without match in store"() {
        when:
        def result = underTest.search("A MARRY".repeat(1000))
        then:
        result.size() == 0
    }

    def "one thousand words repeated existing in store"() {
        expect:
        def expected = ["Mary": 1]
        when:
        def result = underTest.search("Mary ".repeat(1000))
        then:
        result.size() == 1
        result == expected

    }
}
