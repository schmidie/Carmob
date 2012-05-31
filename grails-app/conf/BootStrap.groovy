package conn

class BootStrap {

    def init = { servletContext ->
        
        // Standorte
        
        if(!Angle.count()){
            // Berlin        
            new Angle(name:"zu Hause", address:"Hermannstrasse 256",  
                post_code:"12049", city:"Berlin").save()

            new Angle(name:"Carmeq (Berlin)", address:"Carnotstrasse 4",  
                post_code:"10587", city:"Berlin").save()

            // Wolfsburg
            new Angle(name:"Carmeq (Wolfsburg)", address:"Major-Hirst-Strasse 11",  
                post_code:"38442", city:"Wolfsburg").save()
             
            new Angle(name:"zu Hause 2", address:"Helmholtzstr.",  
                post_code:"12054", city:"Berlin").save()
            
            new Angle(name:"WB HBF", address:"Wolfsburg 20Hauptbahnhof",  
                post_code:"38442", city:"Wolfsburg").save()

            
        }
        
        //Transmeans
        
        if(!Trip.count()){
            new TransportationMean(name:"Fahrrad", average_speed:17).save()
            new TransportationMean(name:"Auto", average_speed:50).save()
            new TransportationMean(name:"Taxi", average_speed:50).save()
            new TransportationMean(name:"DB", average_speed:100).save()

            //Connections
            def t1 = TransportationMean.findByName("Fahrrad")
            def c1 = new Connection(transMean: t1, start: "jonas", end: "Berlin HBF", start_time: new Date(), distance: 10, area: "local_start").save()

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


            //Trips
            new Trip(name: "FE Werk")
                .addToConnections(c1)
                .addToConnections(c2)
                .addToConnections(c3)
                .save()

            new Trip(name: "XY Werk")
                .addToConnections(c4)
                .addToConnections(c5)
                .addToConnections(c6)
                .addToConnections(c7)
                .save()
            
        }
            
    }
    
    def destroy = { }

}