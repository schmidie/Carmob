package conn

import java.text.DateFormat

/**
 *  ! comment here - The index controller
 */
class IndexController {
    
    def authenticationService
    def timeToNextTrip            // time until the next trip of a user
    def myTrips                   // the trips of a user
    def myCo2 = "test"
    def m_user
    
    /**
     *  ! comment here
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