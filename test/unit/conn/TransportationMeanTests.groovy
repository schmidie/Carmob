package conn



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TransportationMean)
class TransportationMeanTests {

    void testQuery() {
        def transportationmeans = [
            new TransportationMean(name: "Taxi", average_speed: "50"),
            new TransportationMean(name: "Fahrrad", average_speed: "17")
        ]
    
        transportationmeans*.save()
    
        assertEquals 2, TransportationMean.list().size()
    }
}