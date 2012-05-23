package conn

import grails.converters.*

class TripmanagementController {
  
//    static enum Area{
//        local_start,
//        intercity,
//        local_end
//    }
      
    // for testing
    def connections_filtered
    
    def index() { 
        // Just for testing TODO: dynamic!
        if(Connection.count()){
            connections_filtered = Connection.list()
        }     
    }
    
    // static scaffold = Tripmanagement
    
    // all the transportation means -> TODO: dynamic
    def transportation_mean_collection = [
        new TransportationMean(name:"Fahrrad", average_speed:17).save(),
        new TransportationMean(name:"Auto", average_speed:50).save(),
        new TransportationMean(name:"Taxi", average_speed:50).save()
    ]
    
    // give back all transportation means
    def transportation_mean = {
        render transportation_mean_collection as JSON
    }
    
    def scratcherService = new ScratcherService()
        
    def scratch = {
        //URL erstellen
        def baseURL = "http://mobile.bahn.de/bin/mobil/query2.exe/dox?"
        def urlString = "${baseURL}REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=${params.start}&REQ0JourneyStopsS0ID=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=${params.end}REQ0JourneyDate=${params.date}&REQ0JourneyStopsZ0ID=&REQ0JourneyTime=${params.time}&start=Suchen.html"
        urlString = urlString.replace(" ","%20").replace("&amp;","&")
        
        // html Inhalt einlesen
        def URL url = new URL(urlString)
        def URLConnection urlc = url.openConnection()
        def BufferedReader reader = new BufferedReader( new InputStreamReader(urlc.getInputStream() ))
        
        // Inhalt nach weiterführendem "Verbindungs"-Link durchsuchen
        def String inputLine 
        def matcher
        def String tripLink
        while ((inputLine = reader.readLine()) != null ) {                      //gehe alle HTML-Zeilen durch
            if (inputLine ==~ /(.+)href=\"([^"]+)(.+)/) {                       //wenn ein href="..." vorkommt
                matcher = inputLine =~ /(.+)href=\"([^"]+)(.+)/                 //matche auf seinen inhalt
                if (matcher[0][2] ==~ /(.+)co=C0(.+)/ ) tripLink = matcher[0][2]//kommt co=C0 vor
            }                                                                   //dann speicher es als gültigen Link
        }
        reader.close()
        
        // Alle weiterführenden "Verbingungs"-Links erstellen
        def tripLinkList = []
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-0").replace("&amp;","&"))
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-1").replace("&amp;","&"))
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-2").replace("&amp;","&"))
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-3").replace("&amp;","&"))
        tripLinkList.add(tripLink)
        
        url = new URL(tripLinkList.get(0) )
        urlc = url.openConnection()
        reader = new BufferedReader( new InputStreamReader(urlc.getInputStream() ))        
        
        while ((inputLine = reader.readLine()) != null ) {
            render inputLine
        }
        reader.close()
    }
    
    def generate_connection(String start, String end, TransportationMean tm) {
        // TODO: get from googlemaps
        def distance = 10   // kilometer
        
        (distance/tm.average_speed)*60
    }
   
}