package conn

import java.text.SimpleDateFormat
import grails.converters.*
import org.xml.sax.*

// imports for taxi2team1
import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC
import java.security.MessageDigest
import sun.misc.BASE64Encoder
import sun.misc.CharacterEncoder

/**
 *  ! comment here - The tripmanagement controller
 */
class TripmanagementController {
  
    def authenticationService
    def trips = []                          // the filtered trips from the user search data
    def transportation_mean_collection      // the possible transMeans
    def m_user                              // the current user
    
    /**
     *  ! comment here
     */
    def error() {}
    
    /**
     *  ! comment here
     */
    def index() { 
        redirect action: 'scratch', params: params
    }
    
    /**
     *  @return ! comment here
     */
    def getTransMean(String name){
        
        if (name.contains("ICE")||name.contains("RE")||name.contains("IC"))
            return ("DB")
        else if (name.contains("Bus"))
            return ("Bus")
        else if (name.contains("U"))
            return ("U-Bahn")
        else if (name.contains("S"))
            return ("S-Bahn")
        else if (name.contains("Taxi"))
            return ("Taxi")
        else 
            return (name)               // !! TODO !!
            
    }

    /**
     *  ! comment here
     */
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
    
    /**
     *  ! comment here
     */
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
                //render "Hallo".encode as JSON
                callRemoteCreateTour(save_trp, current)
            }
            redirect(controller: "Index", action: "index")
        }
    }
    
    /**
     *  Calls the method "scratch".
     */
    def scratch_mobile() {
        scratch()
    }
    
    /**
     *  ! comment here
     */
    def scratch() {
        
        //getDistance(params.start,params.end,"driving")
        
        def originID = getLocationId(params.start)
        def destID = getLocationId(params.end)
        
        //buildTripList(originID,destID)
        
        def m_date = params.date_day+"."+params.date_month+"."+params.date_year
        
        if(!params.date_day || !params.date_month  || !params.date_year ){
            m_date = params.date
        }
            
        def baseURL = "http://demo.hafas.de/bin/pub/carmeq/rest.exe"
        def authKey = "carmeq"
        def urlString = "${baseURL}/trip?authKey=${authKey}&originId=${originID}&destId=${destID}&time=${params.time}&date=${m_date}"
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
                tempMean       = new TransportationMean()
                tempMean.setName(it.@name)
                tempMean.setDirection(it.@direction)
                tempMean.save()

                tempConnection.setStart(it.Origin.@name)
                tempConnection.setEnd(it.Destination.@name)
            
                tempTime = new GregorianCalendar()
                def hour, minute, day, month, year
            
                matcher = it.Destination.@time =~ /\[([^:]*):(.*)\]/
                hour    = matcher[0][1].toInteger() 
                minute  = matcher[0][2].toInteger()
                matcher = it.Destination.@date =~/\[([^.]*).([^.]*).([^.]*)\]/
                day     = matcher[0][1].toInteger()
                month   = matcher[0][2].toInteger()
                year    = matcher[0][3].toInteger() + 2000
                tempTime.set(year,month,day,hour,minute)
                Date tmp_date = tempTime.time

                tempConnection.setEnd_time(tmp_date)
                tempTime = null
                tempTime = new GregorianCalendar()
            
                matcher = it.Origin.@time =~ /\[([^:]*):(.*)\]/
                hour    = matcher[0][1].toInteger() 
                minute  = matcher[0][2].toInteger()
                matcher = it.Origin.@date =~/\[([^.]*).([^.]*).([^.]*)\]/
                day     = matcher[0][1].toInteger()
                month   = matcher[0][2].toInteger()
                year    = matcher[0][3].toInteger() + 2000
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
        
            // sort the list
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
        
        // trips.each() {
        // tempTrip = new Trip()
        // tempTrip.temp = true
        // tempTrip.setConnections(it.getConnections())
        // tempTrip.setName(tempTrip.duration().toString())
        // tempTrip.connections.each(){
        // def ddur = getDuration(it.start,it.end,"driving")
        // if (( ddur < 30 ) && !(it.transMean.name.equals("Auto/Taxi")) ){
        // it.transMean.name = "Auto/Taxi "
        // if (it.start_time.getMinutes() + ddur >59){
        // it.end_time.setMinutes(it.start_time.getMinutes() + ddur -60)
        // it.end_time.setHours(it.start_time.getHours() + 1)
        // }else{
        // it.end_time.setMinutes(it.start_time.getMinutes() + ddur )
        // }
        // tempTrip.save()
        // }                
        // def wdur = getDuration(it.start,it.end,"walking")
        // if (( wdur < 15 ) && !(it.transMean.name.equals("Fußweg")) ){
        // it.transMean.name = "Fußweg"
        // if (it.start_time.getMinutes() + wdur >59){
        // it.end_time.setMinutes(it.start_time.getMinutes() + wdur -60)
        // it.end_time.setHours(it.start_time.getHours() + 1)
        // }else{
        // it.end_time.setMinutes(it.start_time.getMinutes() + wdur )
        // }
        // tempTrip.save()
        // }
        // }
        // }
        
        filter(trips)

        // }catch (Throwable t) {
        // redirect(controller: "tripmanagement", action: "error")
        // }
    }
    
    /**
     *  ! comment here
     */
    def getCo2(String transMean,String origin,String destination){
        
        def distance = getDistance(origin,destination,"driving")
         
        if (transMean.contains("ICE")||transMean.contains("RE")||transMean.contains("IC"))
            return (distance*30)
        else if (transMean.contains("Bus"))
            return (distance*25)
        else if (transMean.contains("U"))
            return (distance*10)
        else if (transMean.contains("S"))
            return (distance*10)
        else if (transMean.contains("Walk"))
            return 0
        else
            return 0
    }
    
    /**
     *  @return
     */
    def int getDuration(String origin,String destination,mode){
        
        def apiUrl='http://maps.googleapis.com/maps/api/directions/xml?'
        def urlString = "${apiUrl}origin=${origin}&destination=${destination}&mode=${mode}&sensor=false"
        def InputSource xmlsource = new InputSource(urlString)
        
        def mapsXML = new XmlParser().parse(xmlsource)
        if (mapsXML.route.leg.duration.value.text().equals("")) return 1000
        def duration = mapsXML.route.leg.duration.value.text() as int
        duration = duration / 60
        
        return (duration)
        
    }
    
    /**
     *  @return the distance between two places
     */
    def int getDistance(String origin,String destination,mode){
        
        def apiUrl='http://maps.googleapis.com/maps/api/directions/xml?'
        def urlString = "${apiUrl}origin=${origin}&destination=${destination}&mode=${mode}&sensor=false"
        def InputSource xmlsource = new InputSource(urlString)
        
        def mapsXML = new XmlParser().parse(xmlsource)
        def distance = mapsXML.route.leg.distance.value.text() as int
        distance = distance / 1000
       
        return (distance)

    }
    
    /**
     *  ! comment here
     */    
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
        
        return (result)
    }
    
    /**
     *
     */
    def transportation_mean = {
        render transportation_mean_collection as JSON
    }
    
    // filters the shortest_trip
    def filter(trips){
        trips.sort{it.connections.size()}.sort{it.getStartTime()}
        // TODO: find the best Trips for the user!!
    }
    
    // def generate_connection(String start, String end, TransportationMean tm) {
    // TODO: "get from googlemaps"
    // def distance = 10   // kilometer
    // (distance/tm.average_speed)*60
    // }
    
    /**
     *  Books a taxitour with the software of team 1 if in a connection
     *  of the trip a taxi is the transportationmean.
     */
    def callRemoteCreateTour(Trip selectedTrip, User currentUser){
        
        selectedTrip.connections.each(){
            
            if (it.transMean.getGeneralTransMean() == "Taxi"){
                def taxi2team1 = new RESTClient('http://dev.noova.de:9001/tour/remoteCreateTour')
                def result = taxi2team1.get(query:[uemail:currentUser.email, hash:123456, start:it.start, end:it.end, stime:it.start_time, etime:it.end_time])
            }
         
        }
    }
    
} // eoc