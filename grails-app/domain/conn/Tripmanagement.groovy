package conn

// entity
class Tripmanagement {
    
    // attributes
    // - none yet
    
    // relations
    static hasMany = [trips:Trip]
    static constraints = {
        // hasMany nullable:true
    }
    
    // constraints
    // - none yet
    
}
