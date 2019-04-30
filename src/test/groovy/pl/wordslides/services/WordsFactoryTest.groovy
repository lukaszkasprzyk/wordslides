package pl.wordslides.services


import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WordsFactoryTest extends Specification {

    WordsFactory underTest = new WordsFactory()

    def "input string words are #result"() {
        expect:
        underTest.getWords(input).map { word -> word.getValue() }.toJavaArray() == result

        where:
        input                       || result
        ""                          || []
        " \n\t\f\r"                 || []
        null                        || []
        "input"                     || ["input"]
        "input test"                || ["input", "test"]
        "input\ntest"               || ["input", "test"]
        "input\ttest"               || ["input", "test"]
        "input\ftest"               || ["input", "test"]
        "input\rtest"               || ["input", "test"]
        " input test "              || ["input", "test"]
        " input             test "  || ["input", "test"]
        "\n\t\f\rinput  test\n\t\f" || ["input", "test"]
    }
}
