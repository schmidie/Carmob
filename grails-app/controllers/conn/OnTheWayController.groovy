package conn

/**
 *  ! comment here - The on-the-way controller
 */
class OnTheWayController {

    def authenticationService
    def trip
    
    /**
     *  ! comment here
     */
    def index() { 
       
        if(params.trip_id){
            trip = Trip.get((long)params.trip_id)
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
