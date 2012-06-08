package conn

import java.text.DateFormat

class IndexController {
    
    // Felder
    def authenticationService
    def jetzt = new Date()          // für Zeit bis zum nächsten Trip des Users
    
//    def userID                      // für aktuellen User bestimmen
//    def users                       // für aktuellen User bestimmen
//    def user                        // für aktuellen User bestimmen
    def myTrips                     // für Trips des Users
    
    def index() {
        
       
        
//        userID = authenticationService.sessionUser.userObjectId
//        users = User.list()
//        users.each() {
//            if (it.id == userID) {
//                user = it
//                myTrips = user.trips.list()
//            }
//        }

        myTrips = Trip.list()
        
        if (!authenticationService.isLoggedIn(request)) {
            redirect(action:"login")
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