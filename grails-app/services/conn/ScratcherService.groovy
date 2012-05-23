package conn

class ScratcherService {
    
    boolean transactional = false

    
    def getTrips(String start, String end,String date, String time) {

        
        def baseURL = "http://mobile.bahn.de/bin/mobil/query2.exe/dox?"
        def connectionListURL = "${baseURL}REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=${start}&REQ0JourneyStopsS0ID=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=${end}REQ0JourneyDate=${date}&REQ0JourneyStopsZ0ID=&REQ0JourneyTime=${time}&start=Suchen"
        
        
        
        //get the Db stuff and so on Bsp:
        /*
         *def base = "http://ws.geonames.org/search?"
            def qs = []
            qs << "name_equals=" + URLEncoder.encode(iata)
            qs << "fcode=airp"
            qs << "style=full"
            def url = new URL(base + qs.join("&"))
            def connection = url.openConnection()
            
        if(connection.responseCode == 200){...} else{
        log.error("GeocoderService.geocodeAirport FAILED")
        log.error(url)
        log.error(connection.responseCode)
        log.error(connection.responseMessage)
      }      
         **/
                
        def tr1 = new conn.Trip()
        
        def connections = [start,end]
        def tm1 = new conn.TransportationMean(name:'Fahrrad')
        def tm2 = new conn.TransportationMean(name:'Auto')
        def c1 = new conn.Connection(transMean:tm1,start:start,end:end)
        def c2 = new conn.Connection(transMean:tm2 ,start:start,end:end)

        
        //connections.add(1)
        //connections.add(2)

        //for (connection in connections){
        //    tr1.addToConnections(connection)
       // } 
       // bla

        return connections
        
    }
    
    
}
