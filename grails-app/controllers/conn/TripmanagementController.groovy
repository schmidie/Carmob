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
        //scratch(params.start,params.end,params.date,params.time)
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
        
    def scratch = { start,end,date,time ->
        
        if (start == null) render "startparameter fehlt"; return null
        if (end == null) render "endparameter fehlt"; return null
        if (date == null) render "dateparameter fehlt"; return null
        if (time == null) render "timeparameter fehlt"; return null
        
        //URL erstellen
        def baseURL = "http://mobile.bahn.de/bin/mobil/query2.exe/dox?"
        def urlString = "${baseURL}REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=${start}&REQ0JourneyStopsS0ID=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=${end}REQ0JourneyDate=${date}&REQ0JourneyStopsZ0ID=&REQ0JourneyTime=${time}&start=Suchen.html"
        urlString = urlString.replace(" ","%20").replace("&amp;","&")
        
        //html Inhalt einlesen
        def URL url = new URL(urlString)
        def URLConnection urlc = url.openConnection()
        def BufferedReader reader = new BufferedReader( new InputStreamReader(urlc.getInputStream() ))
        
        //Inhalt nach weiterführendem "Verbindungs"-Link durchsuchen
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
        
        //Alle weiterführenden "Verbingungs"-Links erstellen
        def tripLinkList = []
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-0").replace("&amp;","&"))
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-1").replace("&amp;","&"))
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-2").replace("&amp;","&"))
        tripLinkList.add(tripLink.replace("co=C0-4","co=C0-3").replace("&amp;","&"))
        tripLinkList.add(tripLink)
        
        def tripList = []
        tripLinkList.each() {
        
        url = new URL(it)
        urlc = url.openConnection()
        reader = new BufferedReader( new InputStreamReader(urlc.getInputStream() ))        
        
        def tempTrip
        def tempConnection
        def tempConnectionList = []
        def tempDate_departure
        def tempDate_arrival
        def tempMean

        def scratcherState = 0
        while ((inputLine = reader.readLine()) != null ) {
            
            switch ( scratcherState ) {
                case 0:
                if (inputLine ==~/(.*)<div id="content">(.*)/ ) {
                    //render "TABLESTART"
                    scratcherState=1
                }
                break
 
                case 1:
                if (inputLine ==~/(.*)<div class="rline haupt" style=" ">(.*)/ ) {
                    //render "CONNECTION"
                    scratcherState=2
                    tempConnection = new Connection()
                }
                break
 
                case 2:
                if (inputLine ==~/(.*)<span class="bold">([^<]*)(.*)/ ) {
                    matcher = inputLine =~/(.*)<span class="bold">([^<]*)(.*)/
                    scratcherState=3
                    tempConnection.setStart(matcher[0][2])
                }
                break
 
                case 3:
                if (inputLine ==~/(.*)<a href="(.*)/ ) {
                    //render "WITH NEXT " //there is a mean, so find it!
                    scratcherState=4
                }
                
                if (inputLine ==~/(.*)<span class="bold">([^<]*)(.*)/ ) {
                    matcher = inputLine =~/(.*)<span class="bold">([^<]*)(.*)/
                    //render matcher[0][2] //Griebnitzsee
                    //render "TO" //there is no mean, we are done here
                    scratcherState=1
                }
                break
 
                case 4:
                if (inputLine ==~/(.*)<span class="bold">([^<]*)(.*)/ ) {
                    matcher = inputLine =~/(.*)<span class="bold">([^<]*)(.*)/
                    scratcherState=5
                    tempMean = new TransportationMean()
                    tempMean.setName(matcher[0][2])
                    tempConnection.setTransMean( tempMean )
                }
                break
 
                case 5:
                if (inputLine ==~/(.*)ab (\d\d:\d\d)(.*)/ ) {
                    matcher = inputLine =~/(.*)ab (\d\d:\d\d)(.*)/
                    scratcherState=6
                    tempDate_departure = new Date()
                    tempDate_departure.setHours( (matcher[0][2] =~ /(\d\d):(\d\d)/ )[0][1].toInteger() )
                    tempDate_departure.setMinutes( (matcher[0][2] =~ /(\d\d):(\d\d)/ )[0][2].toInteger() )
                }
                break
 
                case 6:
                if (inputLine ==~/(.*)am (\d\d.\d\d.\d\d)(.*)/ ) {
                    matcher = inputLine =~/(.*)am (\d\d.\d\d.\d\d)(.*)/
                    scratcherState=7
                    tempDate_departure.setDate( (matcher[0][2] =~ /(\d\d).(\d\d).(\d\d)/ )[0][1].toInteger() )
                    tempDate_departure.setMonth( (matcher[0][2] =~ /(\d\d).(\d\d).(\d\d)/ )[0][2].toInteger() )
                    tempDate_departure.setYear( (matcher[0][2] =~ /(\d\d).(\d\d).(\d\d)/ )[0][3].toInteger() )
                    tempConnection.setStart_time(tempDate_departure)
                }
                break
 
                case 7:
                if (inputLine ==~/(.*)<span class="bold">([^<]*)(.*)/ ) {
                    matcher = inputLine =~/(.*)<span class="bold">([^<]*)(.*)/
                    scratcherState=8
                    tempConnection.setEnd(matcher[0][2])
                }
                break
 
                case 8:
                if (inputLine ==~/(.*)an (\d\d:\d\d)(.*)/ ) {
                    matcher = inputLine =~/(.*)an (\d\d:\d\d)(.*)/
                    scratcherState=9
                    tempDate_arrival = new Date()
                    tempDate_arrival.setHours( (matcher[0][2] =~ /(\d\d):(\d\d)/ )[0][1].toInteger() )
                    tempDate_arrival.setMinutes( (matcher[0][2] =~ /(\d\d):(\d\d)/ )[0][2].toInteger() )
                }
                break
 
                case 9:
                if (inputLine ==~/(.*)am (\d\d.\d\d.\d\d)(.*)/ ) {
                    matcher = inputLine =~/(.*)am (\d\d.\d\d.\d\d)(.*)/
                    scratcherState=1
                    tempDate_arrival.setDate( (matcher[0][2] =~ /(\d\d).(\d\d).(\d\d)/ )[0][1].toInteger() )
                    tempDate_arrival.setMonth( (matcher[0][2] =~ /(\d\d).(\d\d).(\d\d)/ )[0][2].toInteger() )
                    tempDate_arrival.setYear( "1".concat((matcher[0][2] =~ /(\d\d).(\d\d).(\d\d)/ )[0][3]).toInteger() )
                    //todo: Date aendern, 1900Problem
                    tempConnection.setEnd_time(tempDate_arrival)
                    
                    tempConnectionList.add(tempConnection) 
                    tempConnection = null
                }
                break
 
 
                default:
                render "this should never happen"
            }
            
            
        }
        reader.close()
        tempTrip = new Trip()
        tempTrip.setConnections(tempConnectionList)
        tripList.add(tempTrip)
        }
        
        return tripList
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
