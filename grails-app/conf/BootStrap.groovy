package conn

class BootStrap {

    def init = { servletContext ->
        
        // Standorte
        
        if(!Angle.count()){
            // Berlin        
            //new Angle(name:"zu Hause", address:"Hermannstrasse 256",  
            //    post_code:"12049", city:"Berlin").save()

            //new Angle(name:"Hbhf Berlin", address:"Europaplatz 1",  
            //    post_code:"10557", city:"Berlin").save()
            
            //new Angle(name:"Bhf Berlin-Spandau", address:"Segefelder Straße 1",  
            //    post_code:"13597", city:"Berlin").save()
            
            new Angle(name:"CQ Büro (B)", address:"Helmholtzstr., Berlin",  
                post_code:"10587", city:"Berlin",  vicinity:"Berlin").save()

            // Wolfsburg
            new Angle(name:"CQ Autovision", address:"Forum AutoVision, Wolfsburg 11",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
             
            new Angle(name:"VW-TE Hopfengarten", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW-TE Rübenkamp", address:"Mozartstraße, Wolfsburg-Fallersleben",  
                post_code:"38442", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW AutoUni", address:"Hageberg Brücke Sandkamp, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW-FE LKW Wache", address:"VW LKW Waage, Wolfsburg",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW-FE FG Wache", address:"Saarstraße, Wolfsburg ",  
                post_code:"38440", city:"Wolfsburg",  vicinity:"Wolfsburg").save()

            new Angle(name:"VW-HMI Volke", address:"AutoMuseum, Wolfsburg ",  
                post_code:"38446", city:"Wolfsburg",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW Isenbüttel", address:"Kl. Moorweg, Isenbüttel ",  
                post_code:"38550", city:"Isenbüttel",  vicinity:"Wolfsburg").save()
            
            new Angle(name:"VW Ehra (GEHT NICHT)", address:"Am Platz 1945",  
                post_code:"38468", city:"Ehra-Lessien",  vicinity:"Wolfsburg").save()
            
            // Ingolstadt
            new Angle(name:"CQ Büro (IN)", address:"Gewerbegebiet, Gaimersheim ",  
                post_code:"85080", city:"Gaimersheim",  vicinity:"Ingolstadt").save()
            
            new Angle(name:"Audi TE", address:"Audi - Tor 10, Ingolstadt",  
                post_code:"85055", city:"Ingolstadt",  vicinity:"Ingolstadt").save()
            
            new Angle(name:"Audi Forum", address:"Scherzerstraße, Ingolstadt ",  
                post_code:"85057", city:"Ingolstadt",  vicinity:"Ingoltstadt").save()
            
            // Stuttgart
            new Angle(name:"CQ Büro (S)", address:"Schlossplatz, Stuttgart ",  
                post_code:"70173", city:"Stuttgart",  vicinity:"Stuttgart").save()
            
            new Angle(name:"Porsche", address:"Weissach (Württ) Marktplatz ",  
                post_code:"71287", city:"Weissach",  vicinity:"Stuttgart").save()
            
            // Prag
            new Angle(name:"e4t Prag (GEHT NICHT)", address:"Novodvorská 994/138",  
                post_code:"147 00", city:"Praha 4-Branîk",  vicinity:"Prag").save()
            
            new Angle(name:"Skoda Werk (GEHT NICHT)", address:"Mladá Boleslav",  
                post_code:"", city:"CZ",  vicinity:"Prag").save()
        }
        
        //Transmeans
        /*
        if(!Trip.count()){
            
            def trip = new Trip()
            
            new TransportationMean(name:"Fahrrad", average_speed:17).save()
            new TransportationMean(name:"Auto", average_speed:50).save()
            new TransportationMean(name:"Taxi", average_speed:50).save()
            new TransportationMean(name:"DB", average_speed:100).save()

            //Connections
            def t1 = TransportationMean.findByName("Fahrrad")
            def c1 = new Connection(transMean: t1, start: "jonas", end: "Berlin HBF", start_time: new Date(),end_time: new Date()).save()


            def t2 = TransportationMean.findByName("DB")
            def c2 = new Connection(transMean: t2, start: "Berlin HBF", end: "Wolfsburg HBF", start_time: new Date(), distance: 150, area: "intercity").save()

            def t3 = TransportationMean.findByName("Taxi")
            def c3 = new Connection(transMean: t3, start: "Wolfsburg HBF", end: "Wolfsburg FE Werk", start_time: new Date(), distance: 5, area: "local_end").save()

            def t4 = TransportationMean.findByName("Auto")
            def c4 = new Connection(transMean: t4, start: "jonas", end: "Berlin HBF", start_time: new Date(), distance: 10, area: "local_start").save()

            def t5 = TransportationMean.findByName("DB")
            def c5 = new Connection(transMean: t5, start: "Berlin HBF", end: "Wolfsburg HBF", start_time: new Date(), distance: 150, area: "intercity").save()

            def t6 = TransportationMean.findByName("Taxi")
            def t7 = TransportationMean.findByName("Fahrrad")
            def c6 = new Connection(transMean: t6, start: "Wolfsburg HBF", end: "Wolfsburg FE Werk", start_time: new Date(), distance: 5, area: "local_end").save()
            def c7 = new Connection(transMean: t7, start: "Wolfsburg HBF", end: "Wolfsburg FE Werk", start_time: new Date(), distance: 5, area: "local_end").save()
            */
//          Hier entsteht ein Laufzeitfehler weil addToConnections() mit null-objekten aufgerufen wird
//            //Trips
//            new Trip(name: "FE Werk")
//                .addToConnections(c1)
//                .addToConnections(c2)
//                .addToConnections(c3)
//                .save()
//
//            new Trip(name: "XY Werk")
//                .addToConnections(c4)
//                .addToConnections(c5)
//                .addToConnections(c6)
//                .addToConnections(c7)
//                .save()
            
        //}
            
    }
    
    def destroy = { }

}