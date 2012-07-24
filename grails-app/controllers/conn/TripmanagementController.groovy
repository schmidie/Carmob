package conn

import java.text.SimpleDateFormat
import grails.converters.*
import org.xml.sax.*

// imports for taxi2team1
import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC


/**
 *  The tripmanagement controller for the controlling of the trip selection
 *  
 */
class TripmanagementController {
  
    def authenticationService
    def trips = []                          // the filtered trips from the user search data
    def transportation_mean_collection      // the possible transMeans
    def m_user                              // the current user
    

    def error() {}
    
    def index() { 
        redirect action: 'scratch', params: params
    }
    
    /**
     *  get the general Transportation Mean from a given string
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
     *  saves a trip for the current user from the on The way view if the user chooses dynamicly a new trip
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
     *  save_trip saves a trip for a user.
     *  if the user selects a trip from the list,
     *   the trip will be saved and for all taxi elements we try to book a taxi-trip with the API
     *   The user will be redirected to the index
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
     *  Scratch Mobile just calls the normal scratch method
     */
    def scratch_mobile() {
        scratch()
    }
    
    /**
     *  search for trips with the given parameter date, origin, destination, time
     *  If we find some trips from the API, sort this trips and add some intermodality with other transportation Means
     */
    def scratch() {
        
        try{
            def originID = getLocationId(params.start)
            def destID = getLocationId(params.end)       
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
                    month   = matcher[0][2].toInteger() -1 // TODO: bad fix time is alwas on month before ??
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
                    month   = matcher[0][2].toInteger() -1 // TODO: bad fix time is alwas on month before ??
                    year    = matcher[0][3].toInteger() + 2000
                    tempTime.set(year,month,day,hour,minute)
                    tmp_date = tempTime.time              
                    tempConnection.setStart_time(tmp_date)            
                    tempConnection.setTransMean(tempMean)       
                    tempConnection.save()           
                    tempConnection.setCo2(getCo2(tempMean.name,tempConnection.getStart(),tempConnection.getEnd()))         
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
            
            // add Taxi Elements
            addOtherTransMean("Taxi",30)        
            //add bike Elements
            addOtherTransMean("Bike",12)
            // filter the trips
            filter(trips)
            
        }
        catch (Throwable t) {
        }
        // redirect(controller: "tripmanagement", action: "error")
    }
    
    /**
     * addOtherTransMean adds other TransportationMeans to the existing trips. 
     * The maxDistance is the maximum distance to travel with this transporationMean. 
     */
    def addOtherTransMean(String name,Integer maxDistance){

    def tempTrip_taxi_bike
    def connections_taxi_bike = [] 
    def toSave = false
    def beforeTransMean = ""
    tempTrip_taxi_bike = new Trip()
    tempTrip_taxi_bike.temp = true
    tempTrip_taxi_bike.setName(tempTrip_taxi_bike.duration().toString())
    
        // iterate all the trips and check if we can create some new trips with the new TransportationMean 
    trips[0]?.getConnections().each(){
        def ddis = getDistance(it.start,it.end,"driving")
        if (( ddis < maxDistance )&&(name=="Taxi"?!it.start.contains('Berlin'):it.start.contains('Berlin'))){    
            if(beforeTransMean != name){
                def tmp_Connection = new Connection()
                def tmp_tempMean = new TransportationMean()
                tmp_tempMean.setName(name)
                beforeTransMean = name
                tmp_tempMean.save()
                tmp_Connection.setTransMean(tmp_tempMean)
                tmp_Connection.setStart(it.getStart())
                tmp_Connection.setEnd(it.getEnd())
                tmp_Connection.setEnd_time(it.getEnd_time())
                //tmp_Connection.setStart_time(new Date(it.getEnd_time().time - (getDuration(it.getStart(),it.getEnd(),name)*1000)))
                //tmp_Connection.setStart_time(new Date(it.getEnd_time().time - ((getDuration(it.getStart(),it.getEnd(),name)*60000))))
                tmp_Connection.setStart_time(new Date(it.getEnd_time().time - (long)((name=="Taxi"?30:35)*60000)))
                tmp_Connection.save()
                connections_taxi_bike.add(tmp_Connection)
                toSave=true
            }
            else{
                connections_taxi_bike.last().setEnd(it.getEnd())
                //calculate the new start time
                //connections_taxi_bike.last()?.setStart_time(
                //    new Date(it.getEnd_time().time - ((5)*60000)))
            }
        }
        else{
            beforeTransMean = it.transMean.name
            connections_taxi_bike.add(it)
        }
        tempTrip_taxi_bike.setConnections(connections_taxi_bike)
    }
    // only save the new trips if we changed something
    if(toSave){
        
        tempTrip_taxi_bike.save()   
        try{
            tempConnection.setCo2(getCo2(tempTrip_taxi_bike.tempMean.name,tempTrip_taxi_bike.getStart(),tempTrip_taxi_bike.getEnd))
        }catch(Throwable t){
            
        }
        
        trips.add(tempTrip_taxi_bike)
    }
    
}
    
    /**
     *  gets the CO2 emission for a trip from origin to destination with the transportationMean transMean
     *  Be carefull, this calculations are only rounded values they are not 100% correct!
     */
    def getCo2(String transMean,String origin,String destination){
        
        def distance = getDistance(origin,destination,"driving")
         
        if ( transMean.contains("ICE") || transMean.contains("RE") || transMean.contains("IC") || transMean.contains("RB"))
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
     *  @return the duration between two places origin and destination with the given mode
     *   mode can be car, bike, walk
     */
    def int getDuration(String origin,String destination,mode){
        
        def apiUrl='http://maps.googleapis.com/maps/api/directions/xml?'
        def urlString = "${apiUrl}origin=${origin.replaceAll(' ','%20')}&destination=${destination.replaceAll(' ','%20')}&mode=${mode.replaceAll(' ','%20')}&sensor=false"
        def InputSource xmlsource = new InputSource(urlString)
        
        def mapsXML = new XmlParser().parse(xmlsource)
        if (mapsXML.route.leg.duration.value.text().equals("")) return 1000
        def duration = mapsXML.route.leg.duration.value.text() as int
        duration = duration / 60
        
        return (duration)
        
    }
    
    /**
     *  @return the distance between two places with the given mode
     *  mode can be car, bike, walk
     */
    def int getDistance(String origin,String destination,mode){
        try{
            def apiUrl='http://maps.googleapis.com/maps/api/directions/xml?'
            def urlString = "${apiUrl}origin=${origin.replaceAll(' ','%20')}&destination=${destination.replaceAll(' ','%20')}&mode=${mode.replaceAll(' ','%20')}&sensor=false"
            def InputSource xmlsource = new InputSource(urlString)

            def mapsXML = new XmlParser().parse(xmlsource)
            def distance = mapsXML.route.leg.distance.value.text() as int
            distance = distance / 1000

            return (distance)
        }
        catch (Throwable t) {
            return 0
        }

    }
    
    /**
     *  Gets the Locatio-ID of a given Location
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
    def transportation_mean = {
        render transportation_mean_collection as JSON
    }*/
    
    // filters the shortest_trip
    def filter(trips){
        trips.sort{it.connections.size()}.sort{it.getStartTime()}
        // TODO: find the best Trips for the user!!
    }
    
    
    /**
     *  Books a taxitour with the software of team 1 if in a connection
     *  of the trip a taxi is the transportationmean.
     */
    def callRemoteCreateTour(Trip selectedTrip, User currentUser){
        
        // Dummy für Taxi an Team 1 START
        //def rawTrip = new RESTClient('http://dev.noova.de:9001/tour/remoteCreateTour')
        //def rTresult = rawTrip.get(query:[uemail:'tombullmann@googlemail.com', hash:'', start:'Hauptbahnhof', end:'Carmeq Wolfsburg, Autovision', stime:'2012-07-16 12:00:00', etime:'2012-07-16 12:15:00', city:'Wolfsburg'])
        // Dummy für Taxi an Team 1 END
        
        try{
        selectedTrip?.connections?.each(){
            
            if (it.transMean.getGeneralTransMean() == "Taxi"){
                def taxi2team1 = new RESTClient('http://dev.noova.de:9001/tour/remoteCreateTour')
                def result = taxi2team1.get(query:[uemail:currentUser.email, hash:'', start:it.getStart(), end:it.getEnd(), stime:it.getStart_time(), etime:it.getEnd_time(), city:'Wolfsburg'])
            }
        }
        }catch(Throwable e){

        }
    }
    
} // eoc