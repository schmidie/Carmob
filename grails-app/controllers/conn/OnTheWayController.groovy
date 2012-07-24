package conn

/**
 *  The on-the-way controller of the mobile Interface
 */
class OnTheWayController {

    def authenticationService
    def trip
    
    /**
     *  The index method gets the trip with the given trip id. 
     *  If there is no parameter with trip-id we take the next trip for the current user
     */
    def index() { 
       
        if(params.trip_id){
            trip = Trip.get(params.trip_id)
        }
        else{
            User current = null
            def m_id = null
            
            m_id = authenticationService?.sessionUser?.userObjectId
            if(m_id != null){
                current = User.get(m_id)
                if(current != null){
                    trip = current.getNextTrip()
                }
            }
        }
            
    }
    
} // eoc
