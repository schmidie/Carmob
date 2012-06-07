package conn

class TempTrip {
    
    String user
    
    Collection trips
    
     // relations
    static hasMany = [
        trips:Trip
    ]
    

    static constraints = {
    }
}
