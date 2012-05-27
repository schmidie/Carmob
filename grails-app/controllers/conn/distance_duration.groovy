
package conn
import grails.converters.*



class distance_duration {
 //api test   maps.googleapis.com/maps/api/directions/json?origin=amrumerstrasse+berlin&destination=appenzellerstrasse+berlin&region=de&mode=driving&sensor=false
    private static final String apiUrl='maps.googleapis.com/maps/api/directions/json?'
    
    static String mode = "" // driving, walking or bicycling.
    static final String sensor = false // requested parameter for google maps api
   
     /**
     * Calculates distance between origin and destination
     */
    
    int distance(String originAddress, String originCity, String destinationAddress, String destinationCity) {
        
       
       if (mode == "walking"){
            Map<String, String> query = [
            "walking":mode,
            "sensor":sensor, 
            "origin":originAddress.encodeAsURL() + "," + originCity.encodeAsURL(),
            "destination":destinationAddress.encodeAsURL() + "," + destinationCity.encodeAsURL()]}
        
        else if (mode == "Auto"||mode == "Taxi"){
            Map<String, String> query = [
            "driving":mode,
            "sensor":sensor, 
            "origin":originAddress.encodeAsURL() + "," + originCity.encodeAsURL(),
            "destination":destinationAddress.encodeAsURL() + "," + destinationCity.encodeAsURL()]}
        
        else if (mode == "Fahrrad"){
            Map<String, String> query = [
            "bicycling":mode,
            "sensor":sensor, 
            "origin":originAddress.encodeAsURL() + "," + originCity.encodeAsURL(),
            "destination":destinationAddress.encodeAsURL() + "," + destinationCity.encodeAsURL()]}
        
        
            String json = makeAPIRequest(url,query)

            Map results = JSON.parer(json)

            int distance = 0
            int duration = 0
            int Distance_Duration=[distance,duration]

             if (results.status == "OK") {
                // Take the first route and first group of data from legs, which is the summary
                distance = results.routes[0].legs[0].distance.value as int //m.
                duration = results.routes[0].legs[1].duration.value as int // sec.
                distance=distance/1000   
                duration=duration/60
            }
    return Distance_Duration
       
       
        
    }
    // distance in a city
    int distance(String originAddress, String destinationAddress, String city) {
    	 return distance(originAddress, city, destinationAddress, city)
    }
    
    private String makeAPIRequest(String url,  Map<String, String> query) {
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
