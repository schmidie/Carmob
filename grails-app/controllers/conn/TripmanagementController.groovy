package conn

import java.text.SimpleDateFormat

import grails.converters.*
import org.xml.sax.*

class TripmanagementController {
  
    def authenticationService
    //def authenticationService
    
    //the filtered trips from the user search data
    def trips = []
    
    //the possible transMeans
    def transportation_mean_collection
    
    // the current user
    def m_user
    
    def index() { 
             
            redirect action: 'scratch', params: params

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
        
        getDistance(params.start,params.end,"driving")
        
        def originID = getLocationId(params.start)
        def destID = getLocationId(params.end)
        
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
        
        trips.each() {
            
            tempTrip = new Trip()
            tempTrip.temp = true
            tempTrip.setConnections(it.getConnections())
            tempTrip.setName(tempTrip.duration().toString())
            tempTrip.connections.each(){
  
                def ddur = getDuration(it.start,it.end,"driving")
                if (( ddur < 30 ) && !(it.transMean.name.equals("Auto/Taxi")) ){
                    
                    it.transMean.name = "Auto/Taxi "
                    if (it.start_time.getMinutes() + ddur >59){
                        it.end_time.setMinutes(it.start_time.getMinutes() + ddur -60)
                        it.end_time.setHours(it.start_time.getHours() + 1)
                    }else{
                        it.end_time.setMinutes(it.start_time.getMinutes() + ddur )
                    }
                    
                    tempTrip.save()
                }                
                
                def wdur = getDuration(it.start,it.end,"walking")
                if (( wdur < 15 ) && !(it.transMean.name.equals("Fußweg")) ){
                    
                    it.transMean.name = "Fußweg"
                    if (it.start_time.getMinutes() + wdur >59){
                        it.end_time.setMinutes(it.start_time.getMinutes() + wdur -60)
                        it.end_time.setHours(it.start_time.getHours() + 1)
                    }else{
                        it.end_time.setMinutes(it.start_time.getMinutes() + wdur )
                    }
                    
                    tempTrip.save()
                }
            }
        }
        
        filter(trips)

    }
    
    def int getDuration(String origin,String destination,mode){
        
        def apiUrl='http://maps.googleapis.com/maps/api/directions/xml?'
        def urlString = "${apiUrl}origin=${origin}&destination=${destination}&mode=${mode}&sensor=false"
        def InputSource xmlsource = new InputSource(urlString)
        
        def mapsXML = new XmlParser().parse(xmlsource)
        if (mapsXML.route.leg.duration.value.text().equals("")) return 1000
        def duration = mapsXML.route.leg.duration.value.text() as int
        duration = duration / 60
        //render duration
        return duration
        

    }
    

    def int getDistance(String origin,String destination,mode){
        
        def apiUrl='http://maps.googleapis.com/maps/api/directions/xml?'
        def urlString = "${apiUrl}origin=${origin}&destination=${destination}&mode=${mode}&sensor=false"
        def InputSource xmlsource = new InputSource(urlString)
        
        def mapsXML = new XmlParser().parse(xmlsource)
        def distance = mapsXML.route.leg.distance.value.text() as int
        distance = distance / 1000
        //render distance
        return distance
        

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
           
    def transportation_mean = {
        
        render transportation_mean_collection as JSON
    }
    
    // filters the shortest_trip
    def filter(trips){
        /*def shortest_trip = null
        def triplist = Trip.list()
        triplist.each(){
            if(shortest_trip == null || it.duration() < shortest_trip.duration()){
                shortest_trip = it
            }
        }*/
        // test-output
        //render shortest_trip as JSON
        
        trips.sort{it.connections.size()}.sort{it.duration()}
        // TODO: find the best Trips for the user!!
    }
    
//    def generate_connection(String start, String end, TransportationMean tm) {
//        // TODO: get from googlemaps
//        def distance = 10   // kilometer
//        
//        (distance/tm.average_speed)*60
//        
//    }
    
}