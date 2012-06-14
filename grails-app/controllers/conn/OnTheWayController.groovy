package conn

class OnTheWayController {

    def authenticationService
    def trip
    
    def index() { 
        //TODO

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
}
