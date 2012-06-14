package conn

import java.text.SimpleDateFormat

import grails.converters.*
import org.xml.sax.*

class TripmanagementController {
  
    def authenticationService
    //def authenticationService
    
    static enum Area{
        local_start,
        intercity,
        local_end
    }
        
    // for testing
    
    //the filtered trips from the user search data
    def trips = []
    
    //the possible transMeans
    def transportation_mean_collection
    
    // the current user
    def m_user
    
    def index() { 
        // Just for testing TODO: dynamic!
        //scratch(params.start,params.end,params.date,params.time)
      
         //trips = scratch("Wolfsburg Hbf","Hermannstraße Berlin", "25.06.2012", "14:00")
        /*
         if(Trip.count()){
             trips = Trip.list()
           }  
            
        if(TransportationMean.count()){
            transportation_mean_collection = TransportationMean.list()
        }
        */
             
            redirect action: 'scratch', params: params

        //m_user = User
        
    }
    
    // for testing
    def events = { 
        render(contentType: "text/xml") { 
            data()
            { 
                String startDate = new SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.US).format(new Date()) + " GMT" 
                event('start': startDate, 'title':"Nice title", 'link':"http://localhost:8080/carmob/connection/list", "This is a description")

                startDate = new SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.US).format(new Date() + 20) + " GMT" 
                event('start': startDate, 'title':"Hello World", 'link':"http://localhost:8080/carmob/connection/list", "This is another description")

                startDate = new SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.US).format(new Date() + 40) + " GMT" 
                String endDate = new SimpleDateFormat("MMM dd yyyy HH:mm:ss", Locale.US).format(new Date() + 45) + " GMT" 
                event('start': startDate, 'end': endDate, 'title':"Hello again", 'link':"http://localhost:8080/carmob/connection/list", "This is another description")
            } 
        } 
    }
    
    def getTransMean(String name){
        
        if (name.contains("ICE")||name.contains("RE")||name.contains("IC"))
            return "DB"
        else if (name.contains("Bus"))
            return "Bus"
        else if (name.contains("U"))
            return "U-Bahn"
        else if (name.contains("S"))
            return "S-Bahn"
        else 
            return "TODO"
    }

    def scratcherService = new ScratcherService()
    
    def save_trip_mobile(){
        Trip save_trp = null
        save_trp = Trip.get(params.id)
        
        if(save_trp == null){
            render(view: "scratch")
        }
        else{
                        
            save_trp.temp =false
            
            User current = null
            current = User.get(authenticationService.sessionUser.userObjectId)
            if(current != null){
                current.addToTrips(save_trp)
            }
            redirect(controller: "onTheWay", action: "index",  params: [trip_id: save_trp.id])
        }

    }
    
    def save_trip(){
        //if(trips.count()){
        Trip save_trp = null
        save_trp = Trip.get(params.id)
        
        if(save_trp == null){
            render(view: "scratch")
        }
        else{
                        
            save_trp.temp =false
            
            User current = null
            current = User.get(authenticationService.sessionUser.userObjectId)
            if(current != null){
                current.addToTrips(save_trp)
            }
            redirect(controller: "Index", action: "index")
        }

    }
    
    def scratch_mobile() {
        scratch()
    }
    
    def scratch() {
        
        //hopfengarten = "Fallersleben Bahnhofstraße, Wolfsburg"
        //carnotstraße = "Helmholtzstr., Berlin"
        
        
        def originID = getLocationId(params.start)
        def destID = getLocationId(params.end)
        
        //render originID
        //render " + "
        //render destID
        
        //buildTripList(originID,destID)
        
        def baseURL = "http://demo.hafas.de/bin/pub/carmeq/rest.exe"
        def authKey = "carmeq"
        def urlString = "${baseURL}/trip?authKey=${authKey}&originId=${originID}&destId=${destID}&time=${params.time}&date=${params.date}"
        def InputSource xmlsource = new InputSource(urlString)
        
        def Connection tempConnection
        def TransportationMean tempMean
        def GregorianCalendar tempTime
        def Trip tempTrip
        def tempConnectionList = []
        def matcher
        
        def tripListXML = new XmlParser().parse(xmlsource)
        tripListXML.Trip.each{
            
            it.LegList.Leg.each{
                tempConnection = new Connection()
            
                tempMean = new TransportationMean()
                tempMean.setName(it.@name)
                tempMean.setDirection(it.@direction)
                tempMean.save()

                tempConnection.setStart(it.Origin.@name)
                tempConnection.setEnd(it.Destination.@name)
            
                tempTime = new GregorianCalendar()
                def hour, minute, day, month, year
            
                matcher = it.Destination.@time =~ /\[([^:]*):(.*)\]/
                hour = matcher[0][1].toInteger() 
                minute = matcher[0][2].toInteger()
                matcher = it.Destination.@date =~/\[([^.]*).([^.]*).([^.]*)\]/
                day = matcher[0][1].toInteger()
                month = matcher[0][2].toInteger()
                year = matcher[0][3].toInteger() + 2000
                tempTime.set(year,month,day,hour,minute)
                Date tmp_date = tempTime.time

                tempConnection.setEnd_time(tmp_date)
                tempTime = null
                tempTime = new GregorianCalendar()
            
                matcher = it.Origin.@time =~ /\[([^:]*):(.*)\]/
                hour = matcher[0][1].toInteger() 
                minute = matcher[0][2].toInteger()
                matcher = it.Origin.@date =~/\[([^.]*).([^.]*).([^.]*)\]/
                day = matcher[0][1].toInteger()
                month = matcher[0][2].toInteger()
                year = matcher[0][3].toInteger() + 2000
                tempTime.set(year,month,day,hour,minute)
                tmp_date = tempTime.time

                tempConnection.setStart_time(tmp_date)
            
                tempConnection.setTransMean(tempMean)
                tempConnection.save()
                tempConnectionList.add(tempConnection) 

                
                tempConnection = null
                
            }
            
            tempTrip = new Trip()
            
            tempTrip.temp = true
        
            //sort the list
            def s = tempConnectionList.size()
            def tempConnectionList2 = []
            while(s-- > 0){
            tempConnectionList2.add(tempConnectionList.pop() )
            }
        
        
        tempTrip.setConnections(tempConnectionList2.sort{it.start_time})
        tempTrip.setName(tempTrip.duration().toString())
        
            
        tempTrip.save()     
                 
        trips.add(tempTrip)
        }
        
        
        trips
        //render(view: "scratch", model: [trips: trips, start: {params.start}, end: {params.end}, date: {params.date}, time: {params.time}])

    }
    
    
    
    def String getLocationId(String name) {
        
        def result
        
        def baseURL = "http://demo.hafas.de/bin/pub/carmeq/rest.exe"
        def authKey = "carmeq"
        def urlString = "${baseURL}/location.name?authKey=${authKey}&input=${name} "
        def InputSource xmlsource = new InputSource(urlString)
        
        def locationXML = new XmlParser().parse(xmlsource)
        locationXML.StopLocation.each{
            if ((it.@name).equals(name) )  result = it.@id
        }
        
        return result
    }
        
    def old_scratch() { //start,end,date,time ->
        
        // Zum Testen der Funktion:
        
        // von wolfsburg nach helmholtzstr. (carmeq)
        // http://localhost:8080/carmob/tripmanagement/scratch?start=Wolfsburg%20Hbf&end=Helmholtzstr.%20Berlin&date=25.06.2012&time=16:00
         
        // von wolfsburg nach Hermannstraße (privat)
        // http://localhost:8080/carmob/tripmanagement/scratch?start=Wolfsburg%20Hbf&end=Hermannstra%C3%9Fe%20Berlin&date=25.06.2012&time=14:00
        
        // von Helmholtzstr. (carmeq) nach Wolfsburg
        // http://localhost:8080/carmob/tripmanagement/scratch?start=Helmholtzstr.%20Berlin&end=Wolfsburg%20Hauptbahnhof&date=25.06.2012&time=09:00
        
//        if (start == null) render "startparameter fehlt"; return null
//        if (end == null) render "endparameter fehlt"; return null
//        if (date == null) render "dateparameter fehlt"; return null
//        if (time == null) render "timeparameter fehlt"; return null
        
        //URL erstellen
        def baseURL = "http://mobile.bahn.de/bin/mobil/query2.exe/dox?"
        def urlString = "${baseURL}REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=${params.start}&REQ0JourneyStopsS0ID=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=${params.end}REQ0JourneyDate=${params.date}&REQ0JourneyStopsZ0ID=&REQ0JourneyTime=${params.time}&start=Suchen.html"
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
                if (matcher[0][2] ==~ /(.+)co=C0(.+)/ ){
                    tripLink = matcher[0][2]                                    //kommt co=C0 vor
                } 
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
        
        //sort the list
        def s = tempConnectionList.size()
        def tempConnectionList2 = []
        while(s-- > 0){
            tempConnectionList2.add(tempConnectionList.pop())
        }
        
        
            
        // test
        //def t7 = TransportationMean.findByName("Fahrrad")
        //def c7 = new Connection(transMean: t7, start: "Wolfsburg HBF", end: "Wolfsburg FE Werk", start_time: new Date(),end_time: new Date()+1, distance: 5, area: "local_end").save()

        //tempConnectionList2
        tempTrip.setConnections(tempConnectionList2)
        tempTrip.setName(tempTrip.duration().toString())

            
        /*tempTrip.getConnections().each() {
            render it.getTransMean().getName()
                        render " "
            render it.getStart()
                        render " "
            render it.getStart_time()
                        render " "
            render it.getEnd()
                        render " "
            render it.getEnd_time()
                        render " "
            }*/
            
        // save the scratched trips in the TempTrip Model

            
        trips.add(tempTrip)

        }
        
        /*trips.each() {
            
            it.getConnections().each {
            render it.getTransMean().getName()
                        render " "
            render it.getStart()
                        render " "
            render it.getStart_time()
                        render " "
            render it.getEnd()
                        render " "
            render it.getEnd_time()
                        render " "
            }
            //render "#..................................#"
            
        }*/
        
           // new TempTrip()
           // .setUser(auth.user().toString())
           // .setTrips(trips)
           // .save()
        
       //return trips
        render(view: "scratch", model: [trips: trips, start: {params.start}, end: {params.end}, date: {params.date}, time: {params.time}])

    }
        
    def transportation_mean = {
        
        render transportation_mean_collection as JSON
    }
    
    // creates some trips fpr testing the filter-method
    def create_tripsample = {
        Trip.list().each() {
            it.delete()
        }
        
        def sampleConnection_1 = new Connection(
            transMean: new TransportationMean(
                name:"Fahrrad",
                average_speed:17
            ).save(),
            start: "Wiesbadener Str. 9",
            end: "Berliner HBF",
            start_time: new Date(),
            end_time: new Date(),
            regular:false,
            distance: 20,
            area:"Berlin"
        ).save()
        
        def sampleConnection_2 = new Connection(
            transMean: new TransportationMean(
                            name:"Bahn",
                            average_speed:150
                       ).save(),
            start: "Berliner HBF",
            end: "Wolfburger HBF",
            start_time: new Date(),
            end_time: new Date(),
            regular:false,
            distance: 200,
            area:"Germany"
        ).save()
        
        def sampleConnection_3 = new Connection(
            transMean: new TransportationMean(
                            name:"Taxi",
                            average_speed:50
                       ).save(),
            start: "Wolfburger HBF",
            end: "VW FE",
            start_time: new Date(),
            end_time: new Date(),
            regular:false,
            distance: 10,
            area:"Wolfsburg"
        ).save()
        
        def sampleTrip_1 = new Trip(name: "berlin_wolfsburg").save()
        sampleTrip_1.addToConnections(sampleConnection_1)
        sampleTrip_1.addToConnections(sampleConnection_2)
        
        def sampleTrip_2 = new Trip(name: "berlin_wolfsburg").save()
        sampleTrip_2.addToConnections(sampleConnection_1)
        sampleTrip_2.addToConnections(sampleConnection_2)
        sampleTrip_2.addToConnections(sampleConnection_3)
        
        def sampleTrips = [sampleTrip_1, sampleTrip_2]
        
        // test-output
        render sampleTrips as JSON
    }
    
    // filters the shortest_trip
    def filter = {
        def shortest_trip = null
        def triplist = Trip.list()
        triplist.each(){
            if(shortest_trip == null || it.duration() < shortest_trip.duration()){
                shortest_trip = it
            }
        }
        
        // test-output
        render shortest_trip as JSON
    }
    
//    def generate_connection(String start, String end, TransportationMean tm) {
//        // TODO: get from googlemaps
//        def distance = 10   // kilometer
//        
//        (distance/tm.average_speed)*60
//        
//    }
    
}