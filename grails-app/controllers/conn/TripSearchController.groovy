package conn

/**
 *  The tripsearch controller
 */
class TripSearchController {

    def authenticationService
    def cities = []
    def angles
    def trips
    
    /**
     *  The index method of the tripSearchController
     *  find all the trips of the current user to create a fast selection of start/end in the View.
     *  Find also all the Angles and all the cities for the start-Angle and end-Angle selection.
     *  
     */
    def index() { 
       


        User current = null
        def m_id = null
        m_id = authenticationService?.sessionUser?.userObjectId
            if(m_id != null){
                current = User.get(m_id)
                if(current != null){
                    trips = current?.trips?.sort{it.getStart()}
                }
            }
        
        angles = Angle?.list()
                
        angles.each {
            if(!cities.contains(it.city)){
                cities.add(it.city)
            } 
        }  
            
    }
    
    
    /**
     */
    def location() {}

} // eoc
