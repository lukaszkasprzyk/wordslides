package pl.wordslides.store


import spock.lang.Specification

class DummyStoreImplTest extends Specification {

    def performanceIndex = 1000000

    def "check performance for containsKey"() {
        given:
        def store = new DummyStoreImpl()
        when:
        long now = System.currentTimeMillis()
        int i = performanceIndex
        while (i-- >= 0) {
            store.containsKey("test")
        }
        then:
        println("END TIME:"+ (System.currentTimeMillis()-now)+" ms")
    }

    def "check performance for get"() {
        given:
        def store = new DummyStoreImpl()

        when:
        long now = System.currentTimeMillis()
        int i = performanceIndex
        while (i-- >= 0) {
            store.get("test")
        }
        then:
        println("END TIME:"+ (System.currentTimeMillis()-now)+" ms")
    }
}
