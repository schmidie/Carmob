package conn

// entity
class TransportationMean {

    // attributes
    String name
    String direction
    Integer average_speed
    
    // relations
    // - none yet
    
    // constraints
    static constraints = {
        name (blank:false)
        average_speed (nullable:true)
        direction (nullable:true)
    }
    
}
