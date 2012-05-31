package conn

class TripSearchController {

    def angles
    def cities = []
    
    def index() {   
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
