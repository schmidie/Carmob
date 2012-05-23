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
        
        //def result = scratcherService.getTrips(params.start, params.end);
        //render result as JSON
        
        def baseURL = "http://mobile.bahn.de/bin/mobil/query2.exe/dox?"
        def urlString = "${baseURL}REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=${params.start}&REQ0JourneyStopsS0ID=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=${params.end}REQ0JourneyDate=${params.date}&REQ0JourneyStopsZ0ID=&REQ0JourneyTime=${params.time}&start=Suchen"
        
        @Grab(group='org.ccil.cowan.tagsoup',
          module='tagsoup', version='1.2' )
	def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()
        def slurper = new XmlSlurper(tagsoupParser)
        def htmlParser = slurper.parse(urlString)
        
        def String pipe
        
        //htmlParser.depthFirst().each { render it.attributes()}
        
        htmlParser.depthFirst().each { if (!it.@action.isEmpty()) pipe = it.attributes().get('action')}
        
        def matcher = pipe =~ /(\w+)\?ld=(\d+)&n=(\S+)&i=(\S+)&rt=(\S+)/
        
        def ldString = matcher[0][2]
        def iString = matcher[0][4]
       
        
        def routeURL = "http://mobile.bahn.de/bin/mobil/query2.exe/dox?ld=${ldString}&n=1&i=${iString}&rt=1&use_realtime_filter=1&co=C0-0&vca&"
        
        render routeURL
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
