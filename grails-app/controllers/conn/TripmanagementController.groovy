package conn


import grails.converters.*

class TripmanagementController {

        
    def scratch = {
        
        def result = scratcherService.getTrips(params.start, params.end);
        render result as JSON
    }
        
    def transportation_mean = {
        
        render transportation_mean_collection as JSON
    }
    
}
