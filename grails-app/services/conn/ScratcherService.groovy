package conn

class ScratcherService {
    
    boolean transactional = false

    
    def getTrips(String start, String end) {

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

        return connections
        
    }
    
    
}
