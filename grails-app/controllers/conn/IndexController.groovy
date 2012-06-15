package conn

import java.text.DateFormat

class IndexController {
    
    // Felder
    def authenticationService
    def timeToNextTrip            // für Zeit bis zum nächsten Trip des Users

    def myTrips                     // für Trips des Users
    
    def index() {
        
        if (!authenticationService.isLoggedIn(request)) {
            redirect(action:"login")
        }
        
        User current = null
        def m_id = null
        m_id = authenticationService?.sessionUser?.userObjectId
            if(m_id != null){
                current = User.get(m_id)
                if(current != null){
                    myTrips = current?.trips?.sort{it.getStartTime()}
                    timeToNextTrip = current.getTimeToNextTrip()
                }
            }
    }
    
    def login() {
        if (authenticationService.isLoggedIn(request)) {
            redirect(action:"index")
        }
    }
    
    def signup() {
        if (authenticationService.isLoggedIn(request)) {
            redirect(action:"index")
        }
    }
}