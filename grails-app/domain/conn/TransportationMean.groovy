package conn

class TransportationMean {

    String name             // what transMean 
    Integer average_speed   // the average_speed of the transMean
    
    static constraints = {
        name (blank:false)
        average_speed (nullable:true)
    }
}
