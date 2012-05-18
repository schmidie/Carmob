package conn

class TripSearchController {

    def angles
    
    def index() { 
        if(Angle.count()){
            angles: Angle.list()
        }
    }
    
    def location() { }

}
