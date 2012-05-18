package conn

class Angle {
    
    String name         
    String address          // street and number
    String post_code        
    String city
    

    static constraints = {
        
        name (blank:false)
        address (blank:false)
        post_code (blank:false)
        city (blank:false)
    }
}
