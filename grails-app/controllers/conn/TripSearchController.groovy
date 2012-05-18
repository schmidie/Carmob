package conn

class TripSearchController {

    def angles = [
        new Angle(  name:"zu Hause",         
            address:"herrmann",         
            post_code:"12053",        
            city:"Berlin").save(),
        new Angle(  name:"Carmeq",         
            address:"gggg",         
            post_code:"44444",        
            city:"Berlin").save()
    ]
    
    def index() { 
    }
    
    def location() { }

}
