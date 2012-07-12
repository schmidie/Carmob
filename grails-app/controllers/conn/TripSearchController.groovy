package conn

/**
 *  ! comment here - The tripsearch controller
 */
class TripSearchController {

    def authenticationService
    def cities = []
    def angles
    def trips
    
    /**
     *  ! comment here
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
     *  ! comment here
     */
    def location() {}

} // eoc
