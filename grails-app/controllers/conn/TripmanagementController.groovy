package conn


import grails.converters.*

class TripmanagementController {

    def index() { }
    
    def transportation_mean_collection = [
        new TransportationMean(name:"Fahrrad", average_speed:17).save(),
        new TransportationMean(name:"Auto", average_speed:50).save(),
        new TransportationMean(name:"Taxi", average_speed:50).save()
    ]

    def scratcherService = new ScratcherService()
        
    def scratch = {
        
        def result = scratcherService.getTrips(params.start, params.end);
        render result as JSON
    }
        
    def transportation_mean = {
        
        render transportation_mean_collection as JSON
    }
    
    
}
