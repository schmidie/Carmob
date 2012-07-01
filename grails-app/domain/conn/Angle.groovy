package conn

// entity-type
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
    
    // methods
    
    /**
     *  @return a collection of angels of the city "Berlin"
     */
    def getAnglesforCity(){
        def city = 'Berlin'
        def tmp_angles
        Angle?.list().each {
              if(it.city == city){
                  tmp_angles.add(it)
              }
        }
        
        return (tmp_angles)
    }
    
} // eoc