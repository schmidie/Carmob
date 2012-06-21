package conn

class BootStrap {

    def init = { servletContext ->
        
        // Standorte
        
       // if(!Angle.count()){
            // Berlin        
            //new Angle(name:"zu Hause", address:"Hermannstrasse 256",  
            //    post_code:"12049", city:"Berlin").save()

            //new Angle(name:"Hbhf Berlin", address:"Europaplatz 1",  
            //    post_code:"10557", city:"Berlin").save()
            
            //new Angle(name:"Bhf Berlin-Spandau", address:"Segefelder Straße 1",  
            //    post_code:"13597", city:"Berlin").save()
            
            new Angle(name:"Carmeq (Berlin)", address:"Helmholtzstr., Berlin",  
                post_code:"10587", city:"Berlin",  vicinity:"Berlin").save()

            // Wolfsburg
            new Angle(name:"Carmeq - Autovision (Wolfsburg)", address:"Forum AutoVision, Wolfsburg 11",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
             
            new Angle(name:"VW TE (Hopfengarten)", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW TE (Rübenkamp)", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW AutoUni/Mobile/LifeCampus", address:"Hageberg Brücke Sandkamp, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW FE (LKW Wache)", address:"VW LKW Waage, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW FE (Fussgänger Wache)", address:"Saarstraße, Wolfsburg ",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()

            new Angle(name:"VW HMI (Fa. Volke)", address:"AutoMuseum, Wolfsburg ",  
                post_code:"38446", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW Isenbüttel", address:"Kl. Moorweg, Isenbüttel ",  
                post_code:"38550", city:"Isenbüttel",  vicinity:"Isenbüttel").save()
            
            new Angle(name:"VW Prüfgelände Ehra(GEHT NICHT)", address:"",  
                post_code:"38468", city:"Ehra-Lessien",  vicinity:"Ehra-Lessien").save()
            
            // Ingolstadt
            new Angle(name:"Carmeq (Ingolstadt)", address:"Gewerbegebiet, Gaimersheim ",  
                post_code:"85080", city:"Gaimersheim",  vicinity:"Gaimersheim").save()
            
            new Angle(name:"Audi TE", address:"Audi - Tor 10, Ingolstadt",  
                post_code:"85055", city:"Ingolstadt",  vicinity:"Ingolstadt").save()
            
            new Angle(name:"Audi Forum", address:"Scherzerstraße, Ingolstadt ",  
                post_code:"85057", city:"Ingolstadt",  vicinity:"Ingoltstadt").save()
            
            // Stuttgart
            new Angle(name:"Carmeq (Stuttgart)", address:"Schlossplatz, Stuttgart ",  
                post_code:"70173", city:"Stuttgart",  vicinity:"Stuttgart").save()
            
            new Angle(name:"Porsche Weissach", address:"Weissach (Württ) Marktplatz ",  
                post_code:"71287", city:"Weissach",  vicinity:"Weissbach").save()
            
            // Prag
            new Angle(name:"e4t Prag(GEHT NICHT)", address:"Novodvorská 994/138",  
                post_code:"147 00", city:"Praha 4-Branîk",  vicinity:"Prag").save()
            
            new Angle(name:"Skoda Werk(GEHT NICHT)", address:"Mladá Boleslav",  
                post_code:"", city:"CZ",  vicinity:"Prag").save()
       // }
         
    }
    
    def destroy = { }

}