package conn

import java.text.DateFormat

/**
 *  The index controller, when the user calls the index site the Inex method searches all the trips of the user 
 *  and calculate the co2 emission.
 *  TODO: For more performance the calculation of the co2 could be done at the point, where a new trip is added to the user.
 */
class IndexController {
    
    def authenticationService
    def timeToNextTrip            // time until the next trip of a user
    def myTrips                   // the trips of a user
    def myCo2 = 0
    def m_user
    
    /**
     *  The index method gets all the Trips of the current user.
     *  It calculates for all the Trips the CO2 emission for this user
     */
    def index() {
        
        if (!authenticationService.isLoggedIn(request)) {
            redirect(action:"login")
        }
        
        User current = null
        def m_id = null
        m_id = authenticationService?.sessionUser?.userObjectId
        
        if(m_id != null){
            current = User.get(m_id)
            m_user = current
            if(current != null){
                myTrips = current?.trips?.sort{it.getStartTime()}
                timeToNextTrip = current.getTimeToNextTrip()
                myCo2=0
                for(Trip trip in myTrips){
                    for(Connection conn in trip.connections){
                       try{
                        myCo2+=conn.getCo2()
                        }
                        catch(Throwable e){ 
                        }
                    }
                }
                current?.setCo2(myCo2)
            }
        }
    }
    
    /**
     * Redirection to the login-site,
     */
    def login() {
        if (authenticationService.isLoggedIn(request)) {
            redirect(action:"index")
        }
    }
    
    /**
     *  Redirection to the signup-site.
     */
    def signup() {
        if (authenticationService.isLoggedIn(request)) {
            redirect(action:"index")
        }
    }

} // eoc