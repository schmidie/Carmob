package conn


import grails.converters.*

class TripmanagementController {
  
    
    static enum Area{
        local_start,
        intercity,
        local_end
    }
        
    // for testing
    def connections_filtered
    
    def index() { 
        // Just for testing TODO: dynamic!
         if(Connection.count()){
                connections_filtered = Connection.list()
            }  
        
    }
    //static scaffold = Tripmanagement
    
    // all the transportation means -> TODO: dynamic
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
    
    def generate_connection(String start, String end, TransportationMean tm) {
        // TODO: get from googlemaps
        def distance = 10   // kilometer
        
        (distance/tm.average_speed)*60
        
    }
    
}
