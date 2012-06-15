package conn

class TripSearchController {

    // Testkommentar
    def angles
    def cities = []
    def trips
    
    def index() { 
        
            if(Trip.count()){
              trips = Trip.list()

            }
        
            if(Angle.count()){
                angles = Angle.list()
                
                angles.each {
                    if(!cities.contains(it.city)){
                        cities.add(it.city)
                    } 
                }
            }  
            
    }
    
    
    
    def location() { }

}
