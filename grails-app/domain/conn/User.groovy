package conn

class User {
     
    Collection trips
    
    // relations
    static hasMany = [
        trips:Trip
    ]
    
    String login
    String password
    String email
    int status // must be set to AuthenticationService.STATUS_NEW at init
    
    static constraints = {
    }
}
