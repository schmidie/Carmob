package conn

class BootStrap {

    def init = { servletContext ->
        
        // predefined angels

        
        if(!Angle.count()){
            // Berlin        
            //new Angle(name:"HauptBHF Berlin", address:"Europaplatz 1",  
            //    post_code:"10557", city:"Berlin", vincinity:"Berlin").save()
            
            //new Angle(name:"OstBHF Berlin", address:"Am Ostbahnhof",
            //    post_code:"10243", city:"Berlin", vincinity:"Berlin").save()
            
            //new Angle(name:"BHF Berlin-Spandau", address:"Segefelder Str. 1",  
            //    post_code:"13597", city:"Berlin", vincinity:"Berlin").save()
            
            new Angle(name:"CQ Büro (B)", address:"Helmholtzstr., Berlin",  
                post_code:"10587", city:"Berlin",  vicinity:"Berlin").save()

            // Wolfsburg
            //new Angle(name:"CQ Autovision", address:"Forum AutoVision, Wolfsburg 11",  
            //    post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
             
            new Angle(name:"VW-TE Hopfengarten", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW-TE Rübenkamp", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW AutoUni", address:"Hageberg Brücke Sandkamp, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW-FE LKW Wache", address:"VW LKW Waage, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            //new Angle(name:"VW-FE FG Wache", address:"Saarstraße, Wolfsburg ",  
            //    post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()

            //new Angle(name:"VW-HMI Volke", address:"AutoMuseum, Wolfsburg ",  
            //    post_code:"38446", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            //new Angle(name:"VW Isenbüttel", address:"Kl. Moorweg, Isenbüttel ",  
            //    post_code:"38550", city:"Isenbüttel",  vicinity:"Wolfsburg").save()
            
            //new Angle(name:"VW Ehra (GEHT NICHT)", address:"Am Platz 1945",  
            //    post_code:"38468", city:"Ehra-Lessien",  vicinity:"Wolfsburg").save()
            
            // Ingolstadt
            //new Angle(name:"CQ Büro (IN)", address:"Gewerbegebiet, Gaimersheim ",  
            //    post_code:"85080", city:"Gaimersheim",  vicinity:"Ingolstadt").save()
            
            new Angle(name:"Audi TE", address:"Audi - Tor 10, Ingolstadt",  
                post_code:"85055", city:"Ingolstadt",  vicinity:"Ingolstadt").save()
            
            //new Angle(name:"Audi Forum", address:"Scherzerstraße, Ingolstadt ",  
            //    post_code:"85057", city:"Ingolstadt",  vicinity:"Ingoltstadt").save()
            
            // Stuttgart
            //new Angle(name:"CQ Büro (S)", address:"Schlossplatz, Stuttgart ",  
            //    post_code:"70173", city:"Stuttgart",  vicinity:"Stuttgart").save()
            
            //new Angle(name:"Porsche", address:"Weissach (Württ) Marktplatz ",  
            //    post_code:"71287", city:"Weissach",  vicinity:"Stuttgart").save()
            
            // Prag
            //new Angle(name:"e4t Prag (GEHT NICHT)", address:"Novodvorská 994/138",  
            //    post_code:"147 00", city:"Praha 4-Branîk",  vicinity:"Prag").save()
            
            //new Angle(name:"Skoda Werk (GEHT NICHT)", address:"Mladá Boleslav",  
            //    post_code:"", city:"CZ",  vicinity:"Prag").save()
        }
         
    }
    
    def destroy = {}

}