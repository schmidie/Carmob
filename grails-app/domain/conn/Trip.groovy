package conn

// entity
class Trip {
    
    // attributes
    String name
    // String start
    // String end
    
    // Note: We shoud get "start", "end", etc. from the connections!
    
    // relations
    static hasMany = [
        connections : Connection
    ]
        
    // constriants
    static constraints = {
        name (blank:false)
    }
    
}
