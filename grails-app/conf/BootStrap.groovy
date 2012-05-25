package conn

class BootStrap {

    def init = { servletContext ->
        
        // Standorte
        
        // Berlin
        new Angle(name:"zu Hause", address:"Hermannstrasse 256",  
            post_code:"12049", city:"Berlin").save()
        
        new Angle(name:"Carmeq (Berlin)", address:"Carnotstrasse 4",  
            post_code:"10587", city:"Berlin").save()
        
        // Wolfsburg
        new Angle(name:"Carmeq (Wolfsburg)", address:"Major-Hirst-Strasse 11",  
            post_code:"38442", city:"Wolfsburg").save()

    }
    
    def destroy = { }

}