
package conn
import grails.converters.*



class Trip_tipps {
 //api test   maps.googleapis.com/maps/api/directions/json?origin=amrumerstrasse+berlin&destination=appenzellerstrasse+berlin&region=de&mode=driving&sensor=false
    private static final String apiUrl='maps.googleapis.com/maps/api/directions/json?'
    
    static String mode = "" // driving, walking or bicycling.
    static final String sensor = false // requested parameter for google maps api
    static final destinationID = "Berlin Hauptbahnhof (S+U), Berlin"
    def originID = getLocationId(params.start)
     /**
     * Calculates distance between origin and destination
     */
       
    int distance(String origin,String destination ) {
        
       //if "origincity== berlin"
       //if distance <=7 km mode == walking
       if (mode == "walking"){
            def url = "${apiUrl}origin=${originID}&destination=${destinationID}&mode=walking&sensor=false"
           }
        
        
        else if (mode == "Auto"||mode == "Taxi"){
            def url = "${apiUrl}origin=${originID}&destination=${destinationID}&mode=driving&sensor=false" 
        }
        
        
        else  (mode == "Fahrrad"){
            def url = "${apiUrl}origin=${originID}&destination=${destinationID}&mode=bicyclin&sensor=false" 
        }
        
            String json = makeAPIRequest(url)

            Map results = JSON.parer(json)

            int distance = 0
            int duration = 0
            int Distance_Duration=[distance,duration]

             if (results.status == "OK") {
                // Take the first route and first group of data from legs, which is the summary
                distance = results.routes[0].legs[0].distance.value as int //m.
                duration = results.routes[0].legs[1].duration.value as int // sec.
                distance=distance/1000   //km
                duration=duration/60    //minutes
            }
    return Distance_Duration
       
       
        
    }
    // distance in a city
    int distance(String originAddress, String destinationAddress, String city) {
    	 return distance(originAddress, city, destinationAddress, city)
    }
    
    private String makeAPIRequest(String url) {
    	url = new URL()
        urlc = url.openConnection()
    	String result = ""

    	http.request(url, GET, TEXT) { req ->
    		
		uri.query = query
    	 
		response.success = { resp, reader ->
                    result = reader.text
    		}
    	     
    		response."404" = {
                    result = "404"
    		}
    	}
    	return result
    }
    
}
