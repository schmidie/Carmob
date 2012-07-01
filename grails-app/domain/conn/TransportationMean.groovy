package conn

// entity-type
class TransportationMean {

    // attributes
    String  name
    String  direction
    Integer average_speed
    
    // relations
    // - none yet
    
    // constraints
    static constraints = {
        name          (blank:false)
        average_speed (nullable:true)
        direction     (nullable:true)
    }
    
    // methods
    
    /**
     *  @return the general type of the transportationmean
     */    
    def getGeneralTransMean(){
        
        if (name.contains("ICE")||name.contains("RE")||name.contains("IC")||name.contains("RB")){
            return ("DB")
        }            
        else if (name.contains("Bus")){
            return ("Bus")
        }
        else if (name.contains("U")){
            return ("U-Bahn")
        }            
        else if (name.contains("S")){
            return ("S-Bahn")
        }
        else if (name.contains("Taxi")){
            return ("Taxi")
        }            
        else 
            return (name)
    }
    
} // eoc