package conn

import grails.converters.*
import org.codehaus.groovy.grails.web.json.*

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib
import org.codehaus.groovy.grails.commons.GrailsApplication

class GeocodeService {

	GrailsApplication grailsApplication
    static transactional = false
    
    // More about these in http://code.google.com/intl/en_GB/apis/maps/documentation/directions/
    static final String apiUrl = "http://maps.googleapis.com"
    static final String path = "/maps/api/directions/"
    static final String output = "json" // json or xml
    static final String mode = "walking" // driving, walking or bicycling.
    static final String sensor = false // requested parameter for google maps api
    
    /**
     * Calculates distance between origin and destination
     */
    int distance(String originAddress, String originCity, String destinationAddress, String destinationCity) {
    	// Get access to grails taglibs
    	ApplicationTagLib g = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib')
    	
    	String url = apiUrl
    	String path = this.path + output
    	Map<String, String> query = [
            "mode":mode, 
            "sensor":sensor, 
            "origin":originAddress.encodeAsURL() + "," + originCity.encodeAsURL(),
            "destination":destinationAddress.encodeAsURL() + "," + destinationCity.encodeAsURL(),
            "units":g.message(code:"localeUnits", default:"metric")
        ]

    	String json = makeAPIRequest(url, path, query)

    	Map results = JSON.parse(json)
    	
    	int distance = 0
    	if (results.status == "OK") {
	    	// Take the first route and first group of data from legs, which is the summary
	    	distance = results.routes[0].legs[0].distance.value as int
    	}
    	return distance
    }
    
    /**
     * Overloaded method for distance with 4 parameters. This calculates distance inside one city
     */
    int distance(String originAddress, String destinationAddress, String city) {
    	 return distance(originAddress, city, destinationAddress, city)
    }
    
    private String makeAPIRequest(String url, String path, Map<String, String> query) {
    	HTTPBuilder http = new HTTPBuilder()
    	String result = ""

    	http.request(url, GET, TEXT) { req ->
    		uri.path = path
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