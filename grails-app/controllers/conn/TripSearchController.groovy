package conn

class TripSearchController {

    // Testkommentar
    def angles
    def cities = []
    
    def index() { 
            if(!Angle.count()){
                
            new Angle(name:"Carmeq (Berlin)", address:"Helmholtzstr., Berlin",  
                post_code:"10587", city:"Berlin",  vicinity:"Berlin").save()

            // Wolfsburg
            new Angle(name:"Carmeq - Autovision (Wolfsburg)", address:"Major-Hirst-Strasse 11",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
             
            new Angle(name:"VW TE (Hopfengarten)", address:"Fallersleben Bahnhofstraße, Wolfsburg",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW TE (Rübenkamp)", address:"Rübenkamp 2",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW AutoUni/Mobile/LifeCampus", address:"Herrmann-Münch-Strasse 1",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW FE (LKW Wache)", address:"VW Nordstrasse",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW FE (Fussgänger Wache)", address:"VW Nordstrasse",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()

            new Angle(name:"VW HMI (Fa. Volke)", address:"Daimlerstraße 35",  
                post_code:"38446", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW Isenbüttel", address:"Am Krainhop",  
                post_code:"38550", city:"Isenbüttel",  vicinity:"Isenbüttel").save()
            
            new Angle(name:"VW Prüfgelände Ehra", address:"",  
                post_code:"38468", city:"Ehra-Lessien",  vicinity:"Ehra-Lessien").save()
            
            // Ingolstadt
            new Angle(name:"Carmeq (Ingolstadt)", address:"Sachsstrasse 14b",  
                post_code:"85080", city:"Gaimersheim",  vicinity:"Gaimersheim").save()
            
            new Angle(name:"Audi TE", address:"Tor 9",  
                post_code:"85055", city:"Ingolstadt",  vicinity:"Ingolstadt").save()
            
            new Angle(name:"Audi Forum", address:"Ettinger Strasse",  
                post_code:"85057", city:"Ingolstadt",  vicinity:"Ingoltstadt").save()
            
            // Stuttgart
            new Angle(name:"Carmeq (Stuttgart)", address:"König Strasse 43b",  
                post_code:"70173", city:"Stuttgart",  vicinity:"Stuttgart").save()
            
            new Angle(name:"Porsche Weissach", address:"Porschestrasse",  
                post_code:"71287", city:"Weissach",  vicinity:"Eissbach").save()
            
            // Prag
            new Angle(name:"e4t Prag", address:"Novodvorská 994/138",  
                post_code:"147 00", city:"Praha 4-Branîk",  vicinity:"Prag").save()
            
            new Angle(name:"Skoda Werk", address:"Mladá Boleslav",  
                post_code:"", city:"CZ",  vicinity:"Prag").save()
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
