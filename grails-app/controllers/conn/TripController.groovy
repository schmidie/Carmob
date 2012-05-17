package conn
               
import grails.converters.*

class TripController {

    def scratcherService = new ScratcherService()
    static scaffold = Trip
    
    def scratch = {
        def result = scratcherService.getTrips(params.start, params.end);
        render result as JSON
    }
}
