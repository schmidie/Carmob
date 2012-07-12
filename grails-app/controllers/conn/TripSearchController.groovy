package conn

/**
 *  ! comment here - The tripsearch controller
 */
class TripSearchController {

    def authenticationService
    def cities = []
    def angles
    def trips
    
    /**
     *  ! comment here
     */
    def index() { 
        
        new Angle(name:"CQ Büro (B)", address:"Helmholtzstr., Berlin",  
                post_code:"10587", city:"Berlin",  vicinity:"Berlin").save()

            new Angle(name:"VW-TE Hopfengarten", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW-TE Rübenkamp", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW AutoUni", address:"Hageberg Brücke Sandkamp, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW-FE LKW Wache", address:"VW LKW Waage, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()

            new Angle(name:"Audi TE", address:"Audi - Tor 10, Ingolstadt",  
                post_code:"85055", city:"Ingolstadt",  vicinity:"Ingolstadt").save()


        User current = null
        def m_id = null
        m_id = authenticationService?.sessionUser?.userObjectId
            if(m_id != null){
                current = User.get(m_id)
                if(current != null){
                    trips = current?.trips?.sort{it.getStart()}
                }
            }
        
        angles = Angle?.list()
                
        angles.each {
            if(!cities.contains(it.city)){
                cities.add(it.city)
            } 
        }  
            
    }
    
    
    /**
     *  ! comment here
     */
    def location() {}

} // eoc
