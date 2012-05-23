package conn

// entity
class Angle {
    
    // attributes
    String name         
    String address      // street and number
    String post_code        
    String city
    
    // relations
    // - none yet
    
    // constraints
    static constraints = {
        name        (blank:false)
        address     (blank:false)
        post_code   (blank:false)
        city        (blank:false)
    }
}
